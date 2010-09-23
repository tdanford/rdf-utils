package tdanford.autodata.selectors;

import java.lang.reflect.*;
import tdanford.autodata.AutoModel;

/**
 * A Selector is an object for accessing the fields or subfields (fields of field values) 
 * of objects.
 * 
 * 'Aggregate' selectors are selectors whose values have a natural array of selectors 
 * themselves -- a natural example is the selector for an array object (which has 
 * sub-selectors that retrieve the slots of the array), but other examples could include 
 * Collections and even other Objects.  
 * 
 * @author tdanford
 *
 * @param <T>
 * @param <V>
 */
public interface Selector<T,V> {

	public V get(T model);               // return the value at this model
	public void set(T model, V value);   // throws an error if isMutable() == false
	public boolean hasValue(T model);    // check if this value is defined for this model
	public Class getType();              // the expected type of get()'s returned value
	public boolean isMutable();          // can we call set() on this?
	
	public boolean isAggregate();
	public Selector[] getSubSelectors(T model);
}


