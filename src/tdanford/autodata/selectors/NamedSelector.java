package tdanford.autodata.selectors;

import tdanford.autodata.AutoModel;
import tdanford.autodata.AutoUtils;

public class NamedSelector<T,V> extends AbstractSelector<T, V> {
	
	private String fieldName;
	private Class type;
	
	public NamedSelector(String fname, Class typ) { 
		fieldName = fname; 
		type = typ;
		if(fieldName == null) { throw new IllegalArgumentException("null field name"); }
		if(type == null) { throw new IllegalArgumentException("null type"); }
	}
	
	public NamedSelector(String fname) { 
		this(fname, Object.class);
	}
	
	public boolean isMutable() { return true; }

	public V get(T model) {
		Selector<T,V> sel = AutoUtils.findImmediateSelector(model, fieldName);
		if(sel != null && AutoUtils.isSubclass(sel.getType(), type)) { 
			return sel.get(model);
		} else { 
			return null;
		}
	}

	public Class getType() {
		return type;
	}

	public boolean hasValue(T model) {
		Selector<T,V> sel = AutoUtils.findImmediateSelector(model, fieldName);
		return sel != null && AutoUtils.isSubclass(sel.getType(), type) && sel.hasValue(model); 
	}

	public void set(T model, V value) {
		Selector<T,V> sel = AutoUtils.findImmediateSelector(model, fieldName);
	
		// This 'if' statement checks *equivalence* of classing, not "isSubclass(type, sel.getType())", 
		// intentionally.  This is because, as a general rule, 
		// * SUBCLASSING AND MUTATION DON'T MIX WELL *. 
		if(sel != null && AutoUtils.isEqualClass(type, sel.getType())) {   
			sel.set(model, value);
		} 
	}
}
