package tdanford.streaming;

import java.util.*;

import tdanford.resources.Heavy;

public class UniquePoolingSink<A> implements Sink<A>, Heavy {
	
	private Set<A> last;
	
	public UniquePoolingSink() { 
		last = new HashSet<A>();
	}

	public void consume(HeavyIterator<A> itr) {
		while(itr.hasNext()) { 
			last.add(itr.next());
		}
		itr.dispose();
	}
	
	public Collection<A> getLast() { return last; }
	
	public void clear() { last.clear(); }
	
	public void dispose() { 
		last = null;
	}
}
