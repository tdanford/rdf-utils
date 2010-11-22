package tdanford.db;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;

public class Tuple {

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

