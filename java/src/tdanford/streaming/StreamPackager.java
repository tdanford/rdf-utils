package tdanford.streaming;

import java.util.*;

public class StreamPackager<A,B> 
	implements Transform<Collection<A>,Collection<B>> {
	
	private PoolingSink<B> output;
	private Transform<A,B> transform;

	public StreamPackager(Transform<A,B> t) { 
		transform = t;
		output = new PoolingSink<B>();
	}

	public HeavyIterator<Collection<B>> execute(Collection<A> value) {
		output.clear();
		Stream<A,B> stream = new Stream<A,B>(value.iterator(), transform, output);
		stream.complete();
		HeavyIterator<Collection<B>> itr = 
			new SingleIterator<Collection<B>>(output.getLast());
		stream.dispose();
		return itr;
	}

	public void dispose() {
		output.dispose();
		transform.dispose();
		output = null;
		transform = null;
	}
}
