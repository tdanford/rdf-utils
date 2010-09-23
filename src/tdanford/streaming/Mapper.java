package tdanford.streaming;

import tdanford.commons.Function;
import tdanford.commons.Predicate;

public class Mapper<I,O> implements Transform<I,O> {
	
	private Function<I,O> func;
	
	public Mapper(Function<I,O> f) { 
		func = f;
	}

	public HeavyIterator<O> execute(I value) {
		return new SingleIterator<O>(func.evaluate(value));
	}

	public void dispose() {
		func = null;
	}
}
