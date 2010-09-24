package tdanford.streaming;

import java.util.Iterator;

public class DrainingSink<T> implements Sink<T> {

	public void consume(HeavyIterator<T> itr) {
		while(itr.hasNext()) { 
			itr.next();
		}
		itr.dispose();
	}
	
	public void dispose() {}

}
