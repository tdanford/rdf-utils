package tdanford.streaming;

import java.util.*;

public class SinglePackager<A> implements Transform<A,Collection<A>> {

	public HeavyIterator<Collection<A>> execute(A value) {
		LinkedList<A> lst = new LinkedList<A>();
		lst.add(value);
		return new SingleIterator<Collection<A>>(lst);
	}

	public void dispose() {
	}
}
