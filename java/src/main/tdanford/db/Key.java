package tdanford.db;

import java.util.*;

public class Key implements Comparable<Key> {
	
	private String[] key;
	private Set<String> keySet;

	public Key(String... ks) { 
		key = ks.clone();
		keySet = new TreeSet<String>();
		for(String k : key) { 
			if(keySet.contains(k)) { 
				throw new IllegalArgumentException(k);
			} else { 
				keySet.add(k);
			}
		}
	}
	
	public Key(Schema s) { 
		this(s.columnNames());
	}
	
	/**
	 * For two Key objects k1 and k2, k1.isSubkey(k2) == true if and only 
	 * if k2 is a prefix of k1.
	 * 
	 * @param k
	 * @return
	 */
	public boolean isSubkey(Key k) { 
		if(key.length < k.key.length) { return false; }
		for(int i = 0; i < key.length; i++) { 
			if(!key[i].equals(k.key[i])) { return false; }
		}
		return true;
	}
	
	public int compareTo(Key k) { 
		for(int i = 0; i < Math.min(key.length, k.key.length); i++) { 
			int c = key[i].compareTo(k.key[i]);
			if(c != 0) { return c; }
		}
		if(key.length < k.key.length) { return -1; }
		if(key.length > k.key.length) { return 1; }
		return 0;
	}
	
	public boolean contains(String k) { return keySet.contains(k); }
	public int length() { return key.length; } 
	public String key(int i) { return key[i]; }
	
	public int hashCode() { 
		int code = 17;
		for(String k : key) { 
			code += k.hashCode(); code *= 37;
		}
		return code;
	}
	
	public boolean equals(Object o) { 
		if(!(o instanceof Key)) { return false; }
		Key k = (Key)o;
		if(key.length != k.key.length) { return false; }
		for(int i = 0; i < key.length; i++) { 
			if(!key[i].equals(k.key[i])) { 
				return false;
			}
		}
		return true;
	}
}
