package tdanford.autodata.selectors;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

import tdanford.autodata.AutoModel;
import tdanford.autodata.AutoUtils;

public class FieldSelector<T,V> extends AbstractSelector<T,V> {
	
	private Field field;
	
	public FieldSelector(T m, String fname) {
		this(m.getClass(), fname);
	}
	
	public FieldSelector(Class<?> cls, String fname) {
		try {
			field = cls.getField(fname);
		} catch (SecurityException e) {
			e.printStackTrace();
			field = null;
		} catch (NoSuchFieldException e) {
			field = null;
		}
		if(field == null) { 
			throw new IllegalArgumentException(
					String.format("No field %s in class %s", fname, cls.getSimpleName())); 
		}
	}
	
	public FieldSelector(Field f) { 
		field = f;
		if(f == null) { 
			throw new IllegalArgumentException("null Field");
		}
	}
	
	public boolean isMutable() { return true; }
	
	public String toString() { return field.getName(); }
	
	public Class getType() { return field.getType(); }

	public V get(T model) {
		Object obj = null;
		try {
			obj = field.get(model);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return (V)obj;
	}

	public void set(T model, V value) {
		try {
			field.set(model, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public boolean hasValue(T model) {
		Object obj = null;
		try {
			obj = field.get(model);
		} catch (IllegalArgumentException e) {
			return false;
		} catch (IllegalAccessException e) {
			return false;
		}
		return obj != null;
	}
	
}