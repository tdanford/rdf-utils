package tdanford.autodata.selectors;

import tdanford.autodata.AutoModel;

/**
 * A utility class.  Matches a Selector with a particular object from which 
 * the value can be selected/set.  
 * 
 * This is akin to a 'slot'.  
 * 
 * @author tdanford
 *
 * @param <T>
 * @param <V>
 */
public class SelectedValue<T,V> {

	private Selector<T,V> selector;
	private T model;
	
	public SelectedValue(Selector<T,V> s, T m) { 
		selector = s; 
		model = m;
	}
	
	public boolean hasValue() { return selector.hasValue(model); }
	public Class getType() { return selector.getType(); }
	public V get() { return selector.get(model); }
	public void set(V value) { selector.set(model, value); }
	
	public Selector<T,V> getSelector() { return selector; }
	public T getModel() { return model; }
	
	public String toString() { return String.format("%s(%s)", selector.toString(), model.toString()); }
	
	public int hashCode() { 
		int code = 17;
		code += selector.hashCode(); code *= 37;
		code += model.hashCode(); code *= 37;
		return code;
	}
	
	public boolean equals(Object o) { 
		if(!(o instanceof SelectedValue)) { return false; }
		SelectedValue v = (SelectedValue)o;
		if(!selector.equals(v.selector)) { return false; }
		if(!model.equals(v.model)) { return false; }
		return true;
	}
}
