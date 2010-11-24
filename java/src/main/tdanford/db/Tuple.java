package tdanford.db;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;

public class Tuple implements Comparable<Tuple> {

	private Object[] values;
	private Schema schema;
	
	public Tuple(Schema s, Object... vs) { 
		if(vs.length != s.width()) { 
			throw new IllegalArgumentException(String.valueOf(vs.length));
		}
		values = vs.clone();
		schema = s;
	}
	
	public Tuple(String[] array, Schema s) { 
		if(array.length != s.width()) { 
			throw new IllegalArgumentException(String.valueOf(array.length));
		}
		schema = s;
		values = new Object[array.length];
		for(int i = 0; i < values.length; i++) { 
			values[i] = parseValue(array[i], schema.type(i));
		}
	}
	
	public static class KeyComparator implements Comparator<Tuple> {
		private Key key;
		public KeyComparator(Key k) { 
			key = k;
		}
		public int compare(Tuple t1, Tuple t2) { 
			return t1.compareToWith(t2, key);
		}
	}
	
	public int compareTo(Tuple t) { 
		return compareToWith(t, schema.key());
	}
	
	public int compareToWith(Tuple t, Key k) { 
		for(int i = 0; i < k.length(); i++) { 
			String key = k.key(i);
			int idx = schema.nameIndex(key);
			Object v1 = values[idx], v2 = t.values[idx];
			int c = compareValues(v1, v2, schema.type(idx));
			if(c != 0) { return c; }
		}
		return 0;
	}
	
	public static int compareValues(Object v1, Object v2, Class type) { 
		if(isSubclass(type, Comparable.class)) { 
			if(v1 == null || v2 == null) { 
				if(v1 != null) { return 1; }
				if(v2 != null) { return -1; }
			}
			Comparable c1 = (Comparable)v1, c2 = (Comparable)v2;
			return c1.compareTo(c2);
		} else if(type.isArray()) { 
			Class ctype = type.getComponentType();
			if(v1 == null || v2 == null) { 
				if(v1 != null) { return 1; }
				if(v2 != null) { return -1; }
			}
			if(isSubclass(ctype, Comparable.class)) { 			
				int len1 = Array.getLength(v1);
				int len2 = Array.getLength(v2);
				if(len1 < len2) { return -1; }
				if(len1 > len2) { return 1; }
				for(int i = 0; i < len1; i++) { 
					Comparable c1 = (Comparable)Array.get(v1, i), c2 = (Comparable)Array.get(v2, i);
					int cc = c1.compareTo(c2);
					if(cc != 0) { return cc; }
				}
				return 0;
			}
		}
		throw new IllegalArgumentException(type.getSimpleName());
	}
	
	public Tuple subset(Schema s) { 
		Object[] vs = new Object[s.width()];
		for(int i = 0; i < s.width(); i++) { 
			String n = s.name(i);
			Integer idx = schema.nameIndex(n);
			vs[i] = values[idx];
		}
		return new Tuple(s, vs);
	}
	
	public Tuple append(Schema s, Tuple t) { 
		Object[] vs = new Object[values.length + t.values.length];
		for(int i = 0; i < values.length; i++) { vs[i] = values[i]; }
		for(int i = 0; i < t.values.length; i++) { vs[i+values.length] = t.values[i]; }
		return new Tuple(s, vs);
	}
	
	private static Object parseValue(String valueStr, Class type) { 
		if(isSubclass(type, String.class)) { 
			return valueStr;
		
		} else if (isSubclass(type, Boolean.class)) { 
			return Boolean.parseBoolean(valueStr);
		
		} else if (isSubclass(type, Integer.class)) { 
			return Integer.parseInt(valueStr);
		
		} else if (isSubclass(type, Double.class)) { 
			return Double.parseDouble(valueStr);
	
		} else if (type.isArray()) { 
			Class ctype = type.getComponentType();
	
			if(isSubclass(type, char.class)) { 
				return valueStr.toCharArray();
	
			} else if (isSubclass(type, byte.class)) { 
				return valueStr.getBytes(Charset.defaultCharset());
			}
		} 
		
		throw new IllegalArgumentException(type.getCanonicalName());
	}
	
	private static boolean isSubclass(Class c1, Class c2) { 
		return c2.isAssignableFrom(c1);
	}
	
	public Object value(int i) { return values[i]; }
	public Schema schema() { return schema; }
	public int width() { return values.length; }
	
	public Object[] allValues() { return values.clone(); }
	
	public Object[] values(String... keys) { 
		Object[] vs = new Object[keys.length];
		for(int i = 0; i < keys.length; i++) { 
			int idx = schema.nameIndex(keys[i]);
			vs[i] = values[idx];
		}
		return vs;
	}
	
	public int hashCode() { 
		int code = 17;
		for(Object o : values) { 
			if(o != null) { 
				code += o.hashCode(); code *= 37;
			}
		}
		return code;
	}
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(int i = 0; i < values.length; i++) { 
			sb.append(" ");
			sb.append(stringForm(values[i]));
		}
		sb.append(" )");
		return sb.toString();
	}
	
	private String stringForm(Object v) { 
		if(v==null) { 
			return "NULL";
		} else if (v instanceof String) { 
			return String.format("\"%s\"", ((String)v).replaceAll("\"", "\\\""));
		} else if (v.getClass().isArray()) { 
			Class ctype = v.getClass().getComponentType();
			if(isSubclass(ctype, char.class)) { 
				char[] array = (char[])v;
				return new String(array);
			} else { 
				return String.format("%s-array:%d", ctype.getSimpleName(), Array.getLength(v));
			}
			
		} else { 
			return String.valueOf(v);
		}
	}
	
	public boolean equals(Object o) { 
		if(!(o instanceof Tuple)) { return false; }
		Tuple t = (Tuple)o;
		if(!schema.equals(t.schema)) { return false; }
		for(int i = 0; i < values.length; i++) { 
			if(!isEqualWithNull(values[i], t.values[i])) { 
				return false; 
			}
		}
		return true;
	}
	
	public static boolean isEqualWithNull(Object v1, Object v2) { 
		if(v1==null || v2 == null) { 
			if(v1 != null || v2 != null) { 
				return false; 
			} else { 
				return true;
			}
		} else { 
			return v1.equals(v2);
		}
	}
}

