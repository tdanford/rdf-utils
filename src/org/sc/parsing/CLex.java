package org.sc.parsing;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CLex { 
	
	public static enum CTag { SEP, QUOTED, GROUPSTART, GROUPEND };
	
	public String value;
	public Set<CTag> tags;
	
	public CLex(String v, CTag... cts) { 
		value = v;
		tags = new HashSet<CTag>();
		for(int i = 0; i < cts.length; i++) { tags.add(cts[i]); }
	}
	
	public CLex(String v, Collection<CTag> ts) { 
		value = v;
		tags = new HashSet<CTag>(ts);
	}
	
	public int hashCode() { return value.hashCode(); }
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		if(!tags.isEmpty()) { 
			sb.append("<");
			boolean first = true;
			for(CTag t : tags) {
				if(!first) { sb.append(","); }
				sb.append(t.toString());
				first = false;
			}
			sb.append(">");
		}
		return sb.toString();
	}
	
	public boolean equals(Object o) { 
		if(!(o instanceof CLex)) { return false; }
		CLex l = (CLex)o;
		if(!value.equals(l.value)) { return false; }
		if(tags.size() != l.tags.size()) { return false; }
		for(CTag t : tags) { 
			if(!l.tags.contains(t)) { 
				return false; 
			}
		}
		return true;
	}
}