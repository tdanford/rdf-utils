package tdanford.streaming;

import java.util.*;

import tdanford.resources.Heavy;

public class CountingSink<A> implements Sink<A>, Heavy {
	
	private int count;
	
	public CountingSink() { 
		super();
		count = 0;
	}
	
	public void consume(HeavyIterator<A> values) { 
		while(values.hasNext()) { 
			values.next();
			count += 1;
		}
		values.dispose();
	}
	
	public int getCount() { return count; }

	public void dispose() {
	}
}
