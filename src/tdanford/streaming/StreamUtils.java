package tdanford.streaming;

import java.util.*;

public abstract class StreamUtils {

	public static <T> Collection<T> asCollection(Iterator<T> itr) { 
		LinkedList<T> values = new LinkedList<T>();
		while(itr.hasNext()) { values.add(itr.next()); }
		return values;
	}
	
	
}
