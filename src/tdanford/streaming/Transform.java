package tdanford.streaming;

import java.util.Iterator;

import tdanford.resources.Heavy;

public interface Transform<A,B> extends Heavy {
	public HeavyIterator<B> execute(A value);
}
