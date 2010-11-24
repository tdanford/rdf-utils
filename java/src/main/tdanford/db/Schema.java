package tdanford.db;

import java.util.Map;
import java.util.TreeMap;

public class Schema {  
	
	private Key key;
	private String[] names;
	private Map<String,Integer> nameIndices;
	private Class[] types;
	
	public Schema(String[] ns, Class[] ts) { 
		names  =ns.clone();
		types = ts.clone();
		
		if(names.length != types.length) { throw new IllegalArgumentException(); }
		nameIndices = new TreeMap<String,Integer>();
		int i = 0;
		for(String n : ns) { 
			if(nameIndices.containsKey(n)) { 
				throw new IllegalArgumentException(n);
			}
			nameIndices.put(n, i++);
		}

		key = new Key(names);
	}
	
	public Schema(String[] ns1, Class[] ts1, String[] ns2, Class[] ts2) { 
		if(ns1.length != ts1.length) { throw new IllegalArgumentException(); }
		if(ns2.length != ts2.length) { throw new IllegalArgumentException(); }

		names  = new String[ns1.length+ns2.length];
		types = new Class[ts1.length+ts2.length];
		
		for(int i = 0; i < ns1.length; i++) { 
			names[i] = ns1[i]; 
			types[i] = ts1[i];
		}
		
		for(int i = 0; i < ns2.length; i++) { 
			names[i+ns1.length] = ns2[i];
			types[i+ts1.length] = ts2[i];
		}
		
		nameIndices = new TreeMap<String,Integer>();
		int i = 0;
		for(String n : names) { 
			if(nameIndices.containsKey(n)) { 
				throw new IllegalArgumentException(n);
			}
			nameIndices.put(n, i++);
		}
		
		key = new Key(names);
	}
	
	public int width() { return names.length; }
	public String name(int i) { return names[i]; }
	public Class type(int i) { return types[i]; }
	public Integer nameIndex(String n) { return nameIndices.get(n); }
	
	public boolean sharesName(Schema s) { 
		for(String n : nameIndices.keySet()) { 
			if(s.nameIndices.containsKey(n)) { 
				return true;
			}
		}
		return false;
	}
	
	public Schema append(Schema s) { 
		return new Schema(names, types, s.names, s.types);
	}
	
	public Schema subset(String... ss) { 
		String[] ns = new String[ss.length];
		Class[] ts = new Class[ss.length];
		for(int i = 0; i < ss.length; i++) { 
			String n = ss[i];
			Integer idx = nameIndices.get(n);
			if(idx==null) { throw new IllegalArgumentException(n); }
			Class t = types[idx];
			ns[i] = n;
			ts[i] = t;
		}
		return new Schema(ns, ts);
	}

	public String[] columnNames() {
		return names.clone();
	}
	
	public Key key() { return key; }
}
