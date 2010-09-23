package tdanford.autodata;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.io.*;
import java.lang.reflect.*;

import org.json.*;

import tdanford.autodata.selectors.*;
import tdanford.commons.StringUtils;

public abstract class AutoUtils {
	
	public static int FLOATING_PRECISION = 5;

	public static boolean isSubclass(Class<?> c1, Class<?> c2) { 
		return c2.isAssignableFrom(c1);
	}
	
	public static boolean isEqualClass(Class<?> c1, Class<?> c2) { 
		return isSubclass(c1, c2) && isSubclass(c2, c1);
	}
	
	/**
	 * Takes a model and a selector, and returns a simple string representation of the 
	 * value of the selector at that model.  
	 * 
	 * Returns the special value "NONE" if the given selector doesn't have a value 
	 * (is undefined) in this model.
	 * 
	 * This can *only* handle "simple" values -- non-aggregates such as Integers, 
	 * Floats, etc, but not arrays, other models, or other recursive data specifications.
	 * For those, we need to use the JSON output format.
	 * 
	 * @param <T>
	 * @param m
	 * @param sel
	 * @return
	 */
	public static <T> String getValueAsString(T m, Selector sel) { 
		String valueString = "NONE";
		if(sel.hasValue(m)) {
			Object value = sel.get(m);
			if(value != null) { 
				if(value instanceof Double) { 
					valueString = String.format(String.format("%%.%df", FLOATING_PRECISION), (Double)value);
				} else if (value instanceof Float) { 
					valueString = String.format(String.format("%%.%df", FLOATING_PRECISION), (Float)value);					
				} else if (value instanceof String) { 
					valueString = quote(escape((String)value));
				} else if (value instanceof Character) { 
					valueString = quote(escape(String.valueOf((Character)value)));
				} else { 
					valueString = value.toString();
				}
			}
		}
		return valueString;
	}
	
	/**
	 * This can *only* handle "simple" values -- non-aggregates such as Integers, 
	 * Floats, etc, but not arrays, other models, or other recursive data specifications.
	 * For those, we need to use the JSON output format.

	 * @param <T>
	 * @param type
	 * @param vstr
	 * @return
	 */
	public static <T> void setValueFromString(T model, Selector sel, String vstr) {
		Class type = sel.getType();
		if(vstr.equals("NONE")) {
			// do nothing.
		} else if (isSubclass(type, Double.class)) { 
			sel.set(model, Double.valueOf(vstr));
		} else if (isSubclass(type, Float.class)) { 
			sel.set(model, Float.valueOf(vstr));
		} else if (isSubclass(type, String.class)) { 
			sel.set(model, unescape(unquote(vstr)));
		} else if (isSubclass(type, Integer.class)) { 
			sel.set(model, Integer.parseInt(vstr));
		} else if (isSubclass(type, Long.class)) { 
			sel.set(model, Long.parseLong(vstr));
		} else if (isSubclass(type, Boolean.class)) {
			sel.set(model, Boolean.parseBoolean(vstr));
		} else if (isSubclass(type, Character.class)) {
			sel.set(model, unescape(unquote(vstr)).charAt(0));
		} else if (isSubclass(type, Short.class)) { 
			sel.set(model, Short.parseShort(vstr));
		} else { 
			throw new IllegalArgumentException(String.format(
					"Unsupported value type: %s", type.getName()));
		}
	}
	
	public static String quote(String str) { 
		return String.format("\"%s\"", str);
	}
	
	public static String unquote(String quot) { 
		if(quot.startsWith("\"") && quot.endsWith("\"")) { 
			return quot.substring(1, quot.length()-1);
		} else { 
			return quot;
		}
	}
	
	public static String escape(String str) { 
		return StringUtils.escapeQuotes(
				StringUtils.escapeTabs(
					StringUtils.escapeCommas(str)));
	}
	
	public static String unescape(String str) { 
		return StringUtils.unescapeQuotes(
				StringUtils.unescapeTabs(
					StringUtils.unescapeCommas(str)));
	}
	
	public static <T> void tabOutput(T m, Selector id, Collection<Selector> sels, PrintStream ps) {
		ps.print(getValueAsString(m, id));
		for(Selector sel : sels) { 
			ps.print(String.format("\t%s", getValueAsString(m, sel)));
		}
		ps.println();
	}
	
	public static Object tabInput(Class type, Collection<Selector> sels, String line) { 
		String[] array = line.split("\t");
		if(array.length != sels.size()+1) { 
			throw new IllegalArgumentException(String.format("line length %d " +
					"doesn't match selector set %d", array.length, sels.size() + 1));
		}
		Object value = null;
		try {
			value = type.newInstance();
			int i = 1;
			for(Selector sel : sels) { 
				setValueFromString(value, sel, array[i]);
				i++;
			}
		
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static <T> Collection<Selector> findImmediateSelectors(T m) { 
		ArrayList<Selector> sels = new ArrayList<Selector>();
		
		Class c = m.getClass();
		Field[] fs = c.getFields();
		for(int i = 0; i < fs.length; i++) {
			int mod = fs[i].getModifiers();
			if((mod & Modifier.PUBLIC) != 0 && 
			   (mod & Modifier.STATIC) == 0) {
				sels.add(new FieldSelector(fs[i]));
			}
		}
		
		return sels;
	}
	
	public static <T> Collection<Selector> findMatchingImmediateSelectors(T m, String p) { 
		Pattern pt = Pattern.compile(p);
		ArrayList<Selector> sels = new ArrayList<Selector>();
		
		Class c = m.getClass();
		Field[] fs = c.getFields();
		for(int i = 0; i < fs.length; i++) {
			int mod = fs[i].getModifiers();
			if((mod & Modifier.PUBLIC) != 0 && 
			   (mod & Modifier.STATIC) == 0) {
				
				String fname = fs[i].getName();
				Matcher fnameMatcher = pt.matcher(fname);
				if(fnameMatcher.matches()) { 
					sels.add(new FieldSelector(fs[i]));
				}
			}
		}
		
		return sels;
	}
	
	public static <T,V> Selector<T,V> findImmediateSelector(T m, String fname) {
		Class c = m.getClass();
		try {
			Field f = c.getField(fname);
			return new FieldSelector<T,V>(f);
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchFieldException e) {
			return null;
		}
	}
	
	public static <T,V> Selector<T,V> findImmediateSelector(T m, String fname, Class type) {
		Class c = m.getClass();
		try {
			Field f = c.getField(fname);
			if(isSubclass(f.getType(), type)) { 
				return new FieldSelector<T,V>(f);
			} else { 
				return null;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchFieldException e) {
			return null;
		}
	}
	
	public static <T> void setFromModel(T target, T source) { 
		setFromModel(target, source, null);
	}
	
	public static <T> void setFromModel(T target, T source, String prefix) {
		Collection<Selector> ss = findImmediateSelectors(source);
		Class mclass = source.getClass();

		Pattern prefixPattern = prefix != null ? Pattern.compile(String.format("%s_(.*)", prefix)) : null;

		for(Selector s : ss) {
			Class type = s.getType();

			String fieldName = s.toString();
			if(prefix != null) { 
				Matcher prefixMatch = prefixPattern.matcher(fieldName);
				if(prefixMatch.matches()) { 
					fieldName = prefixMatch.group(1);
				}
			}

			Selector tsel = findImmediateSelector(target, fieldName, s.getType());
			if(tsel != null) {  
				tsel.set(target, s.get(source));
			}
		}
	}
	
	public static boolean modelEquals(Object m1, Object m2) {  
		if(!isEqualClass(m1.getClass(), m2.getClass())) { 
			return false;
		}
		
		return false;
	}
	
	public static <T> int modelHash(T model) { 
		Collection<Selector> ss = findImmediateSelectors(model);
		int code = 0;
		for(Selector s : ss) { 
			if(s.hasValue(model)) { 
				Object value = s.get(model);
				if(value != null) { 
					if(value instanceof Double) { 
						long bits = Double.doubleToLongBits((Double)value);
						code += (int)(bits >> 32);
					} else if (value instanceof Float) {
						code += Float.floatToIntBits((Float)value);
					} else { 
						code += value.hashCode();
					}
					code *= 37;
				}
			}
		}
		return code;
	}
	
	/** JSON Methods **************************************************************/
	
	public static Object unjsonify(Class type, Object value) { 
		if(value == JSONObject.NULL) { 
			return null;
		} else if(value instanceof JSONObject) { 
			if(isSubclass(type, AutoModel.class)) { 
				AutoModel m;
				try {
					m = (AutoModel)(type.newInstance());
					m.setFromJSON((JSONObject)value);
					return m;
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				return null;
			} else { 
				return null;
			}
		} else if (value instanceof JSONArray) {
			if(type.isArray()) {
				
				Class arrayType = type.getComponentType();
				JSONArray jsonArray = (JSONArray)value;
				int length = jsonArray.length();
				Object array = Array.newInstance(arrayType, length);

				for(int i = 0; i < length; i++) { 
					try {
						Array.set(array, i, unjsonify(arrayType, jsonArray.get(i)));
					} catch (JSONException e) {
						e.printStackTrace();
						Array.set(array, i, null);
					}
				}
				
				return array;
				
			} else { 
				System.err.println(type.toString() + " was not array-type.");
				return null;
			}
		} else if(isSubclass(value.getClass(), type)) {
			return value;
        } else if (isSubclass(type, java.lang.Number.class)) {
            if (isSubclass(type, java.lang.Double.class)) {
            	if(value instanceof String) { 
            		return Double.parseDouble((String)value);
            	} else { 
            		return new Double(((Number)value).doubleValue());
            	}
            } else if (isSubclass(type, java.lang.Integer.class)) {
            	if(value instanceof String) { 
            		return Integer.parseInt((String)value);
            	} else { 
            		return new Integer(((Number)value).intValue());
            	}
            } else if (isSubclass(type, java.lang.Character.class)) {
                return new Character(((Character)value).charValue());
            } else if (isSubclass(type, java.lang.Float.class)) {
                return new Float(((Number)value).floatValue());
            } else if (isSubclass(type, java.lang.Long.class)) {
                return new Long(((Number)value).longValue());
            } else if (isSubclass(type, java.lang.Short.class)) {
                return new Short(((Number)value).shortValue());
            } else if (isSubclass(type, java.lang.Byte.class)) {
                return new Byte(((Number)value).byteValue());
            }           
        } else if (isSubclass(type, java.awt.Color.class)) { 
            String[] pieces = ((String)value).split(",");
            return new Color(Integer.parseInt(pieces[0]),
                             Integer.parseInt(pieces[1]),
                             Integer.parseInt(pieces[2]),
                             Integer.parseInt(pieces[3]));
        }

		throw new RuntimeException("Couldn't map " + value + " to " + type);
	}
	
	public static Object jsonify(Object value) throws JSONException {
		if(value == null) { 
			return JSONObject.NULL;
		} 
		
		Class vclass = value.getClass();

		if(vclass.isArray()) {
			JSONArray array = new JSONArray();
			int length = Array.getLength(value);
			
			for(int i = 0; i < length; i++) { 
				array.put(i, jsonify(Array.get(value, i)));
			}
			
			return array;
			
		} else if(isSubclass(vclass, Boolean.class)) { 
			return value;
		} else if(isSubclass(vclass, String.class)) { 
			return value;
		} else if (isSubclass(vclass, Integer.class)) { 
			return value;
		} else if (isSubclass(vclass, Double.class)) { 
			return value;
		} else if (isSubclass(vclass, Long.class)) { 
			return value;
		} else if (isSubclass(vclass, Character.class)) { 
			return value;
		} else if (isSubclass(vclass, AutoModel.class)) {
			return asJSON(value);
		} else if (isSubclass(vclass, Color.class)) {
            Color c = (Color)value;
            return String.format("%d,%d,%d,%d", c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
        }
		
		return null;
	}

	public static JSONObject asJSON(Object target) { 
		JSONObject obj = new JSONObject();

		Collection<Selector> ss = findImmediateSelectors(target);
		for(Selector s : ss) { 
			String sname = s.toString();
			try {
				Object fvalue = s.get(target);
				obj.put(sname, jsonify(fvalue));

			} catch (JSONException e) {
				System.err.println(String.format("Model Class: %s", target.getClass().getName()));
				System.err.println(String.format("Field Name: %s (%s)", s.toString(), s.getType().getName()));
				System.err.println(String.format("Value: %s", target.toString()));
				e.printStackTrace(System.err);
			}
		}

		return obj;
	}
	
	public static void setFromJSON(Object target, JSONObject obj) {
		Collection<Selector> ss = findImmediateSelectors(target);
		for(Selector s : ss) { 
			try {
				String fname = s.toString();
				if(obj.has(fname)) { 
					Object value = obj.get(fname);
					Class type = s.getType();
					Object unjsoned = unjsonify(s.getType(), value);                        
					s.set(target, unjsoned);
				} else { 
					s.set(target, null);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
