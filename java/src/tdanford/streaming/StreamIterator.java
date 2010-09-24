package tdanford.streaming;

import java.util.*;

public class StreamIterator<A,B> implements HeavyIterator<B> {

	private Stream<A,B> stream;
	private PoolingSink<B> sink;
	private Iterator<B> itr;
	private B next;
	private long count; 
	
	public StreamIterator(Iterator<A> inp, Transform<A,B> trans) { 
		sink = new PoolingSink<B>();
		stream = new Stream<A,B>(inp, trans, sink);
		itr = new EmptyIterator<B>();
		count = 0;
		findNext();
	}
	
	private void findNext() {
		while(!itr.hasNext() && stream.hasNext()) {
			sink.clear();
			stream.execute();
			itr = sink.getLast().iterator();
		}		
		next = itr.hasNext() ? itr.next() : null;
	}

	public boolean hasNext() {
		return next != null;
	}

	public B next() {
		B v = next;
		count += 1;
		findNext();
		return v;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void dispose() {
		if(itr instanceof HeavyIterator) { 
			((HeavyIterator)itr).dispose();
			stream.dispose();
			sink.dispose();
			stream = null;
			sink = null;
			itr = null;
		}
	}
}
