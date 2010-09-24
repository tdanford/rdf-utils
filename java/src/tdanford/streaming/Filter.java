package tdanford.streaming;

import tdanford.commons.Predicate;

public class Filter<T> implements Transform<T,T> {
	
	private Predicate<T> filter;
	
	public Filter() { this(new Predicate.All<T>()); }
	
	public Filter(Predicate<T> f) { 
		filter = f;
	}

	public HeavyIterator<T> execute(T value) {
		return filter.accepts(value) ? 
				new SingleIterator<T>(value) : 
				new EmptyIterator<T>();
	}

	public void dispose() {
		filter = null;
	}
}
