package tdanford.random;

import java.util.*;

public class Chooser<X> {
	
	private ArrayList<X> items;
	private Random rand;

	public Chooser(Collection<X> its) { 
		items = new ArrayList<X>(its);
		rand = new Random();
	}
	
	public Chooser(X... array) { 
		items = new ArrayList<X>();
		for(int i = 0; i < array.length; i++) { 
			items.add(array[i]);
		}
		rand = new Random();
	}
	
	public X choose() { 
		return items.get(rand.nextInt(items.size()));
	}
	
	public Collection<X> sample(int count, boolean replacement) { 
		LinkedList<X> chosen = new LinkedList<X>();
		if(count <= 0) { return chosen; }
		
		ArrayList<X> array = replacement ? items : new ArrayList<X>(items);
		if(replacement) { 
			for(int i = 0; i < count; i++) { 
				int idx = rand.nextInt(array.size());
				chosen.addLast(array.get(idx));
			}
		} else { 
			if(count >= items.size()) { 
				return array;
			}
			for(int i = 0; i < count; i++) { 
				int idx = rand.nextInt(array.size());
				X value = array.remove(idx);
				chosen.addLast(value);
			}
		}
		return chosen;
	}

	public int size() {
		return items.size();
	}
}
