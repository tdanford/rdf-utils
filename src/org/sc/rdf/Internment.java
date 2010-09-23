package org.sc.rdf;

import java.util.*;

/**
 * Guarantees that references to equal objects are represented by a canonical object.  
 * Call to intern(v) will return the canonical reference for v.
 * 
 * @author tdanford
 *
 * @param <T> The type of the intern'ed value.  Needs to be properly hash-able.
 */
public class Internment<T> {

	private Map<T,T> intern;
	
	public Internment() { 
		intern = new HashMap<T,T>();
	}
	
	public int size() { 
		return intern.size();
	}
	
	public void clear() { 
		intern.clear();
	}
	
	public T intern(T v) { 
		if(!intern.containsKey(v)) { 
			intern.put(v, v);
			return v;
		} else { 
			return intern.get(v);
		}
	}
}
