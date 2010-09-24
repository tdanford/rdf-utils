package tdanford.autodata.selectors;

/**
 * Retrieves the immutable value of the toString() method of an object -- 
 * ToStringSelector is non-mutable and non-aggregate.  
 * 
 * @author tdanford
 *
 * @param <T>
 */
public class ToStringSelector<T> implements Selector<T,String> {

	public String get(T model) {
		return model.toString();
	}

	public Class getType() {
		return String.class;
	}

	public boolean hasValue(T model) {
		return true;
	}

	public boolean isMutable() {
		return false;
	}

	public void set(T model, String value) {
		throw new UnsupportedOperationException("no set() on ToStringSelector");
	}

	public Selector[] getSubSelectors(T model) {
		return null;
	}

	public boolean isAggregate() {
		return false;
	}
}
