package tdanford.autodata.selectors;

import java.util.regex.*;
import tdanford.autodata.AutoModel;

/**
 * Selectors can be composed -- PathSelector is the composition of two selectors, a
 * 'head' and a 'tail' selector.  When given an object to select from, the head 
 * selector is applied first, and then the tail selector is applied to the result. 
 * When an set() is called, the head selector's get() method is first called, and then 
 * the set() is called on the tail selector.  
 * 
 * The selector will throw a dynamic exception if the type of the head selector 
 * doesn't match the expected type of the tail selector.  
 * 
 * If the head selector returns null when get() is called on a given object, then the 
 * entire selector returns null from the corresponding method.  
 * 
 * @author tdanford
 *
 * @param <T>
 * @param <P>
 * @param <V>
 */
public class PathSelector<T,P,V> implements Selector<T,V> {
	
	public static Selector parse(String n) { 
		Pattern p1 = Pattern.compile("^([^.]+)$");
		Pattern p2 = Pattern.compile("^([^.]+)\\.(.+)$");
		Matcher m = p1.matcher(n);
		if(m.matches()) { 
			return new NamedSelector(n);
		} 
		m = p2.matcher(n);
		if(m.matches()) { 
			return new PathSelector(new NamedSelector(m.group(1)), 
					PathSelector.parse(m.group(2)));
		}
		return null;
	}
	
	private Selector<T,P> head;
	private Selector<P,V> tail;
	
	public PathSelector(Selector<T,P> first, Selector<P,V> last) { 
		head = first;
		tail = last;
		if(head == null) { throw new IllegalArgumentException("null head selector"); }
		if(tail == null) { throw new IllegalArgumentException("null tail selector"); }
	}
	
	public String toString() { 
		return String.format("%s.%s", head.toString(), tail.toString());
	}
	
	public boolean isMutable() { return tail.isMutable(); }
	
	public Class getType() { return tail.getType(); }

	public V get(T model) {
		if(head.hasValue(model)) { 
			return tail.get(head.get(model));
		} else { 
			return null;
		}
	}

	public boolean hasValue(T model) {
		return head.hasValue(model) && tail.hasValue(head.get(model));
	}

	public void set(T model, V value) {
		if(head.hasValue(model)) { 
			tail.set(head.get(model), value);
		}
	}

	public Selector[] getSubSelectors(T model) {
		P headValue = (P)head.get(model);
		if(headValue != null) { 
			Selector[] intermediate = tail.getSubSelectors(headValue);
			Selector[] sels = new Selector[intermediate.length];
			for(int i = 0; i < sels.length; i++) { 
				sels[i] = new PathSelector(head, intermediate[i]);
			}
			return sels;
		} else { 
			return null;
		}
	}

	public boolean isAggregate() {
		return tail.isAggregate();
	} 
}