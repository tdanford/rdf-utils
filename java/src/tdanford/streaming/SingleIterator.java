package tdanford.streaming;

import java.util.Iterator;

public class SingleIterator<T> extends ImmutableIterator<T> implements HeavyIterator<T> {
	
	private T value;
	
	public SingleIterator() { value = null; } 
	public SingleIterator(T v) { value = v; }

	public boolean hasNext() { 
		return value != null; 
	}
	
	public T next() {
		T v = value;
		value = null;
		return v; 
	}
	
	public void dispose() { 
		value = null;
	}
}
