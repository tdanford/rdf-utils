package org.sc.parsing;

import java.util.Iterator;
import java.util.LinkedList;

public class PushbackIterator<T> implements Iterator<T> {
	
	private Iterator<T> inner;
	private LinkedList<T> pushed;
	
	public PushbackIterator(Iterator<T> i) { 
		inner = i;
		pushed = new LinkedList<T>();
	}
	
	public void pushback(T v) { 
		pushed.addFirst(v);
	}

	public boolean hasNext() {
		return !pushed.isEmpty() || inner.hasNext();
	}

	public T next() {
		if(pushed.isEmpty()) { 
			return inner.next();
		} else { 
			return pushed.removeFirst();
		}
	}

	public void remove() { throw new UnsupportedOperationException(); } 
}