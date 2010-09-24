package tdanford.resources;

import java.util.*;

public class HeavyHandler implements Heavy {
	
	private LinkedList<Heavy> heavies;
	
	public HeavyHandler() { 
		heavies = new LinkedList<Heavy>();
	}
	
	public synchronized void register(Heavy h) {
		if(heavies == null) { throw new IllegalStateException("HeavyHandler already disposed."); }
		heavies.addLast(h);
	}

	public synchronized void dispose() {
		// do this little song-and-dance, just in case one of the calls to h.dispose()
		// comes back to this object and calls register() on a *new* Heavy object.  
		// In that case, we won't get a ConcurrentModificationException from the Iterator,
		// and we'll still manage to empty out *all* the disposable objects.
		while(!heavies.isEmpty()) { 
			LinkedList<Heavy> hs = new LinkedList<Heavy>(heavies);
			heavies.clear();

			Iterator<Heavy> itr = hs.iterator();
			while(itr.hasNext()) { 
				Heavy h = itr.next();
				h.dispose();
			}
		}
		
		heavies = null;
	}
}
