package tdanford.autodata.selectors;

import java.lang.reflect.Array;
import java.util.Collection;

import tdanford.autodata.AutoModel;
import tdanford.autodata.AutoUtils;

public abstract class AbstractSelector<T, V> implements Selector<T, V> {
	
	public AbstractSelector() { 
	}

	public Selector[] getSubSelectors(T model) {
		Object value = get(model);
		if(value != null) { 
			if(value instanceof AutoModel) { 
				Collection<Selector> csels = AutoUtils.findImmediateSelectors(value);
				Selector[] array = csels.toArray(new Selector[0]);
				Selector[] sels = new Selector[array.length];
				for(int i = 0; i < sels.length; i++) { 
					sels[i] = new PathSelector(this, array[i]);
				}
				return sels;

			} else if(getType().isArray()) { 
				int len = Array.getLength(value);
				Selector[] sels = new Selector[len];
				for(int i = 0; i < len; i++) { 
					sels[i] = new ArraySelector(this, i);
				}
				return sels;
			}
		}
		return null;
	}

	public boolean isAggregate() {
		Class type = getType();
		return type.isArray() || AutoUtils.isSubclass(type, AutoModel.class);
	} 
}
