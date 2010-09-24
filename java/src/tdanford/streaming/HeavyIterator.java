package tdanford.streaming;

import java.util.Iterator;

import tdanford.resources.Heavy;

public interface HeavyIterator<E> extends Iterator<E>, Heavy {
	
	public static class Wrapper<T> implements HeavyIterator<T> { 
		private Iterator<T> itr;
		
		public Wrapper(Iterator<T> i) { itr = i; }
		public Wrapper(T v) { this(new SingleIterator<T>(v)); }

		public boolean hasNext() {
			return itr.hasNext();
		}

		public T next() {
			return itr.next();
		}

		public void remove() {
			itr.remove();
		}

		public void dispose() {
			if(itr instanceof Heavy) { 
				((Heavy)itr).dispose();
			}
			itr = null;
		}
	}
}
