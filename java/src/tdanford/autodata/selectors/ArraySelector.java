package tdanford.autodata.selectors;

import java.lang.reflect.Array;
import java.util.Collection;

import tdanford.autodata.AutoModel;
import tdanford.autodata.AutoUtils;

public class ArraySelector<T,V> extends AbstractSelector<T,V> {
	
	private Selector<T,V[]> selector;
	private int index;
	
	public ArraySelector(Selector<T,V[]> sel, int i) {
		selector = sel;
		index =i;
		
		if(index < 0) { throw new IllegalArgumentException("negative index"); }
		if(sel == null) { throw new IllegalArgumentException("null array selector"); }
	}
	
	public boolean isMutable() { return true; }
	
	public String toString() { 
		return String.format("%s[%d]", selector.toString(), index);
	}
	
	public Class getType() { return selector.getType().getComponentType(); }
	
	public V get(T model) {
		V[] array = selector.get(model);
		if(array != null && index < array.length) { 
			return array[index];
		} else { 
			return null;
		}
	}

	public boolean hasValue(T model) {
		return selector.hasValue(model) && selector.get(model).length > index; 
	}

	public void set(T model, V value) {
		V[] array = selector.get(model);
		if(array.length > index) { 
			array[index] = value;
		}
	}
}