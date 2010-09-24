package tdanford.streaming;

import java.util.*;

import tdanford.resources.Heavy;

public interface Sink<A> extends Heavy {
	public void consume(HeavyIterator<A> itr);
}
