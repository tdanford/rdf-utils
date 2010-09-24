package tdanford.streaming;

import java.util.*;

public class SequentialIterator<T> extends ImmutableIterator<T> implements HeavyIterator<T> {

	private LinkedList<Iterator<T>> itrs;
	private T next;
	
	public SequentialIterator(Iterator<T>... itrarray) { 
		itrs = new LinkedList<Iterator<T>>();
		for(int i = 0; i < itrarray.length; i++) { 
			itrs.addLast(itrarray[i]);
		}
		
		findNext();
	}
	
	private void findNext() {
		next = null;
		while(!itrs.isEmpty() && next == null) { 
			if(itrs.getFirst().hasNext()) { 
				next = itrs.getFirst().next();
			} else { 
				Iterator<T> itr = itrs.removeFirst();
				if(itr instanceof HeavyIterator) { 
					HeavyIterator heavy = (HeavyIterator)itr;
					heavy.dispose();
				}
			}
		}
	}

	public boolean hasNext() { 
		return next != null; 
	}
	
	public T next() {
		T v = next;
		findNext();
		return v;
	}
	
	public void dispose() { 
		next = null;
		while(!itrs.isEmpty()) { 
			Iterator<T> itr = itrs.removeFirst();
			if(itr instanceof HeavyIterator) { 
				HeavyIterator heavy = (HeavyIterator)itr;
				heavy.dispose();
			}
		}
	}
}
