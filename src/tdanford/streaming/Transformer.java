package tdanford.streaming;

import java.util.Iterator;

public class Transformer<A,B,C> implements Transform<A, C> {
	
	public static <X,Y> Transform<X,Y> cat(Transform<X,Y> t1, 
										   Transform<Y,Y>... t2s) { 
		Transform<X,Y> t = t1;
		for(int i = 0; i < t2s.length; i++) { 
			t = new Transformer<X,Y,Y>(t, t2s[i]);
		}
		return t;
	}
	
	private Transform<A,B> first;
	private Transform<B,C> second;
	
	public Transformer(Transform<A,B> f, Transform<B,C> s) { 
		first = f; 
		second = s;
	}
	
	public Transform<A,B> getFirstTransform() { return first; }
	public Transform<B,C> getSecondTransform() { return second; }
	
	public String toString() { return String.format("%s->%s", first.toString(), second.toString()); }
	
	public void dispose() { 
		first.dispose();
		second.dispose();
		first = null;
		second = null;
	}

	public HeavyIterator<C> execute(A value) {
		return new CombiningIterator(value);
	}

	private class CombiningIterator implements HeavyIterator<C> {
		
		private HeavyIterator<B> pending;
		private HeavyIterator<C> current;
		
		public CombiningIterator(A val) { 
			pending = first.execute(val);
			current = new EmptyIterator<C>();
			while(!current.hasNext() && pending.hasNext()) { 
				current = second.execute(pending.next());
			}
		}

		public boolean hasNext() {
			return current != null && current.hasNext();
		}

		public C next() {
			while(!current.hasNext() && pending.hasNext()) { 
				current = second.execute(pending.next());
			}
			return current.next();
		}

		public void remove() {
			throw new UnsupportedOperationException("no remove()");
		} 
		
		public void dispose() { 
			current.dispose();
			pending.dispose();
			pending = null;
			current = null;
		}
	}
}
