package tdanford.autodata;

import java.util.*;

import org.json.*;

import tdanford.autodata.selectors.SelectedValue;
import tdanford.autodata.selectors.Selector;

public abstract class AutoModel {

	public AutoModel() {
	}
	
	public static boolean isSubclass(Class<?> c1, Class<?> c2) { 
		return AutoUtils.isSubclass(c1, c2); 
	}
	
	public Collection<Selector> immediateSelectors() { 
		return AutoUtils.findImmediateSelectors(this); 
	}
	
	public Collection<Selector> findMatchingImmediateSelectors(String p) { 
		return AutoUtils.findMatchingImmediateSelectors(this, p);
	}
	
	public Selector findImmediateSelector(String fname) { 
		return AutoUtils.findImmediateSelector(this, fname); 
	}
	
	public Selector findImmediateSelector(String fname, Class type) { 
		return AutoUtils.findImmediateSelector(this, fname, type); 
	}
	
	public JSONObject asJSON() { return AutoUtils.asJSON(this); }
	public void setFromJSON(JSONObject obj) { AutoUtils.setFromJSON(this, obj); }
	public <T> void setFromModel(T obj) { AutoUtils.setFromModel(this, obj); }
	
	public String toString() { return asJSON().toString(); }
	
	public int hashCode() { return AutoUtils.modelHash(this); }
	
	public boolean equals(Object o) {
		if(!(o instanceof AutoModel)) { return false; }
		return AutoUtils.modelEquals(this, o); 
	}
}
