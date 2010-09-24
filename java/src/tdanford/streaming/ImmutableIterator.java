package tdanford.streaming;

import java.util.Iterator;

public abstract class ImmutableIterator<E> implements Iterator<E> {
	public void remove() { throw new UnsupportedOperationException("no remove()"); }
}
