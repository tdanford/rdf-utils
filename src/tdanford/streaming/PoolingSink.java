package tdanford.streaming;

import java.util.*;

import tdanford.resources.Heavy;

public class PoolingSink<A> implements Sink<A>, Heavy {
	
	private LinkedList<A> last;
	
	public PoolingSink() { 
		last = new LinkedList<A>();
	}

	public void consume(HeavyIterator<A> itr) {
		while(itr.hasNext()) { 
			last.addLast(itr.next());
		}
		itr.dispose();
	}
	
	public Collection<A> getLast() { return last; }
	
	public void clear() { last.clear(); }
	
	public void dispose() { 
		last = null;
	}
}
