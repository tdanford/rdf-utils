package tdanford.streaming;

import java.util.*;

public class Splitter<B> implements Transform<Collection<B>,B> {

	public HeavyIterator<B> execute(Collection<B> value) {
		return new HeavyIterator.Wrapper<B>(value.iterator());
	}

	public void dispose() {
	}
}
