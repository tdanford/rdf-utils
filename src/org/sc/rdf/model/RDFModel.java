package org.sc.rdf.model;

import java.util.*;
import java.util.regex.*;
import java.lang.reflect.*;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.sc.rdf.Prefixes;


import tdanford.autodata.*;
import tdanford.autodata.selectors.*;

public class RDFModel extends AutoModel {
	
	public String _uri;
	public RDFObject _object;
	
	public String rdfs__label, rdfs__comment;

	public RDFModel() { 
		super();
	}
	
	public RDFModel(RDFModel m) {
		_uri = m._uri;
		_object = m._object;
	}
	
	public RDFModel(RDFObjectManager m, RDFObject obj) { 
		this(m, obj, new HashMap<String,RDFModel>());
	}
	
	public RDFModel(RDFObjectManager m, RDFObject obj, Map<String,RDFModel> created) {
		super();
		setFromObject(m, obj, created);
	}
	
	public List<Statement> asStatements(ValueFactory valfact) { 
		LinkedList<Statement> stmts = new LinkedList<Statement>();
		appendStatements(valfact, stmts);
		return stmts;
	}
	
	public void appendStatements(ValueFactory valfact, List<Statement> stmts) { 
		Field[] fs = getClass().getFields();
		String prefix = getPrefix();
		
		Resource subj = _uri.startsWith("blank") ? 
			valfact.createBNode(_uri) : 
			valfact.createURI(_uri);
		
		for(Field f : fs) { 
			int modifiers = f.getModifiers();
			if(Modifier.isPublic(modifiers) && 
				!Modifier.isStatic(modifiers) && 
				!Modifier.isFinal(modifiers)) { 

				try {
					appendFieldStatements(subj, f, prefix, valfact, stmts);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	private void appendValueStatements(Resource subj, URI pred, Object value, ValueFactory valfact, List<Statement> stmts) { 
		if(value instanceof RDFModel) { 
			RDFModel m = (RDFModel)value;
			m.appendStatements(valfact, stmts);
			
		} else if (value instanceof RDFObject) { 
			String objuri = ((RDFObject)value).key();
			Resource obj = objuri.startsWith("blank") ? valfact.createBNode(objuri) : valfact.createURI(objuri);
			Statement s = valfact.createStatement(subj, pred, obj);
			stmts.add(s);
			
		} else { 
			Value v = valfact.createLiteral(value.toString());
			Statement s = valfact.createStatement(subj, pred, v);
			stmts.add(s);
		}
	}
	
	private void appendFieldStatements(Resource subject, 
			Field f, 
			String prefix, 
			ValueFactory valfact, 
			List<Statement> stmts) 
	throws IllegalAccessException {
		
		Class type = f.getType();
		String fname = f.getName();
		String predName =  qualifiedName(prefix, fname);
		boolean array = type.isArray();
		Class component = array ? type.getComponentType() : type;
		
		URI pred = valfact.createURI(predName);
		
		if(fname.equals("_uri") && isSubclass(type, String.class)) {
			// ignore.
			
		} else if(fname.equals("_object") && isSubclass(RDFObject.class, type)) {
			// ignore.
			
		} else if (isSubclass(component, List.class)) {
			List lst = (List)f.get(this);
			if(lst != null) { 
				for(Object val : lst) { 
					appendValueStatements(subject, pred, val, valfact, stmts);
				}
			}
		} else if (array) { 
			Object arrayValue = f.get(this);
			if(arrayValue != null) { 
				int length = Array.getLength(arrayValue);
				for(int i = 0; i < length; i++) { 
					Object val = Array.get(arrayValue, i);
					if(val != null) { 
						appendValueStatements(subject, pred, val, valfact, stmts);
					}
				}
			}
		} else { 
			Object val = f.get(this);
			appendValueStatements(subject, pred, val, valfact, stmts);
		}
	}
	
	public String fullDescription() { 
		StringBuilder b = new StringBuilder(String.format("%s (%s)", toString(), _uri));
		Field[] fs = getClass().getFields();
		for(Field f : fs) { 
			int mod = f.getModifiers();
			String fname = f.getName();
			if(fname.equals("_object")) { continue; }
			if(fname.equals("_uri")) { continue; }

			if(Modifier.isPublic(mod) &&
				!Modifier.isStatic(mod)) { 
				Class type = f.getType();
				try { 
					Object obj = f.get(this);
					if(obj != null) { 
						if(type.isArray()) {
							int len = Array.getLength(obj);
							if(len > 1) { 
								b.append(String.format("\n\t%s: [", fname));
								for(int i = 0; i < len; i++) { 
									Object value = Array.get(obj, i);
									b.append("\n\t\t" + value.toString());
								}
								b.append("\n\t]");
							} else { 
								obj = Array.get(obj, 0);
								b.append(String.format("\n\t%s: [ %s ]", fname, obj.toString()));
							}
						} else { 
							b.append(String.format("\n\t%s: %s", fname, obj.toString()));
						}
					}
				} catch (IllegalAccessException e) {
					b.append(String.format("\n\t%s: %s", fname, "<ILLEGAL-ACCESS>"));
				}
			}
		}
		return b.toString();
	}
	
	public String toString() { return _uri; }
	
	public int hashCode() { return _object.hashCode(); }
	
	public boolean equals(Object o) { 
		if(!(o instanceof RDFModel)) { return false; }
		RDFModel m = (RDFModel)o;
		return m._object.equals(_object);
	}
	
	public void setFromObject(RDFObjectManager m, RDFObject obj) { 
		setFromObject(m, obj, new HashMap<String,RDFModel>());
	}
	
	public Set<String> outgoingConnections(RDFModel m) { 
		Set<String> preds = new TreeSet<String>();
		for(String p : _object.predicates()) { 
			if(_object.hasValue(p, m._object)) { 
				preds.add(p);
			}
		}
		return preds;
	}
	
	public Set<String> incomingConnections(RDFModel m) { 
		Set<String> preds = new TreeSet<String>();
		for(String p : _object.incomingPredicates()) { 
			if(_object.hasIncomingValue(p, m._object)) { 
				preds.add(p);
			}
		}
		return preds;
	}
	
	public String getPrefix() { 
		try {
			Field f = getClass().getField("prefix");
			int mod = f.getModifiers();
			if(Modifier.isPublic(mod) && isSubclass(f.getType(), String.class)) {  
				return (String)f.get(this);
			}
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void setFromObject(RDFObjectManager m, RDFObject obj, Map<String,RDFModel> created) { 
		Field[] fs = getClass().getFields();
		String prefix = getPrefix();
		
		for(Field f : fs) { 
			int modifiers = f.getModifiers();
			if(Modifier.isPublic(modifiers) && 
				!Modifier.isStatic(modifiers) && 
				!Modifier.isFinal(modifiers)) { 

				try {
					loadField(f, prefix, m, obj, created);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	/**
	 * The rule that converts field names into (locally-qualified) URIs.  
	 * 
	 * @param prefix
	 * @param fname
	 * @return
	 */
	private String qualifiedName(String prefix, String fname) { 
		String predName =  fname.contains("__") ? fname.replace("__", ":") : 
			String.format("%s:%s", prefix != null ? prefix : "_", fname);
		return predName;
	}
	
	private void loadField(Field f, String prefix, RDFObjectManager manager, RDFObject obj, Map<String,RDFModel> created) 
		throws IllegalAccessException, NoSuchMethodException {
		
		Class type = f.getType();
		String fname = f.getName();
		String predName = qualifiedName(prefix, fname);
		boolean array = type.isArray();
		Class component = array ? type.getComponentType() : type;
		
		if(fname.equals("_uri") && isSubclass(type, String.class)) {
			f.set(this, obj.key());
			
		} else if(fname.equals("_object") && isSubclass(RDFObject.class, type)) {
			f.set(this, obj);
			
		} else if (isSubclass(component, List.class)) { 
			f.set(this, loadListField(f, prefix, manager, obj, created));
			
		} else if(obj.hasPredicate(predName)) {
			LinkedList values = new LinkedList();
			
			if(isSubclass(component, String.class)) { 
				//values.addAll(Arrays.asList(obj.findTypedValues(predName, component)));
				values.addAll(Arrays.asList(obj.findTypedValues(predName, Object.class)));
				
				if(array) { 
					Object farray = Array.newInstance(component, values.size());
					for(int i = 0; i < values.size(); i++) { 
						Array.set(farray, i, values.get(i).toString());
					}
					f.set(this, farray);
				} else { 
					Object singleValue = values.isEmpty() ? null : values.getFirst();
					f.set(this, singleValue.toString());
				}
				
			} else if (isSubclass(component, RDFObject.class)) {
				values.addAll(Arrays.asList(obj.findTypedValues(predName, component)));
				if(array) { 
					Object farray = Array.newInstance(component, values.size());
					for(int i = 0; i < values.size(); i++) { 
						Array.set(farray, i, values.get(i));
					}
					f.set(this, farray);
				} else { 
					Object singleValue = values.isEmpty() ? null : values.getFirst();
					f.set(this, singleValue);
				}	
				

			} else if (isSubclass(component, RDFModel.class)) {
				try { 
					Constructor constructor = 
						//component.getConstructor(RDFManager.class, RDFObject.class, Map.class);
						component.getConstructor();
					values.addAll(Arrays.asList(obj.findTypedValues(predName, RDFObject.class)));

					if(array) { 
						Object farray = Array.newInstance(component, values.size());
						for(int i = 0; i < values.size(); i++) {
							RDFObject vobj = (RDFObject)values.get(i);
							RDFModel m = null;
							if(created.containsKey(vobj.key())) {  
								m = created.get(vobj.key());
							} else { 
								m = (RDFModel)constructor.newInstance();
								created.put(vobj.key(), m);
								m.setFromObject(manager, vobj, created);
							}
							if(!AutoModel.isSubclass(m.getClass(), component)) { 
								throw new IllegalArgumentException(String.format(
										"%s is not a subclass of %s in %s", 
										m.getClass().getSimpleName(), 
										component.getClass().getSimpleName(), 
										f.getName()));
							}
							Array.set(farray, i, m);
						}
						f.set(this, farray);
					} else { 
						RDFObject vobj = values.isEmpty() ? null : (RDFObject)values.getFirst();
						RDFModel m = null;
						if(created.containsKey(vobj.key())) {  
							m = created.get(vobj.key());
						} else { 
							m = (RDFModel)constructor.newInstance();
							created.put(vobj.key(), m);
							m.setFromObject(manager, vobj, created);
						}
						f.set(this, m);
					}				

				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} else { 
				Constructor c = component.getConstructor(String.class);
				try { 
					if(c != null) { 
						values.addAll(Arrays.asList(obj.findTypedValues(predName, String.class)));
						if(array) { 
							Object farray = Array.newInstance(component, values.size());
							for(int i = 0; i < values.size(); i++) { 
								Array.set(farray, i, c.newInstance(values.get(i)));
							}
							f.set(this, farray);
						} else { 
							Object singleValue = values.isEmpty() ? null : values.getFirst();
							f.set(this, c.newInstance(singleValue));
						}					
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List loadListField(Field f, String prefix, RDFObjectManager manager,
			RDFObject obj, Map<String, RDFModel> created) {
		
		try {
			Class cls = getClass();
			Method creator = cls.getMethod(f.getName(), RDFObjectManager.class, RDFObject.class, Map.class);
			if(AutoModel.isSubclass(creator.getReturnType(), List.class)) { 
				List lst = (List)creator.invoke(this, manager, obj, created);
				return lst;
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		
		} catch (NoSuchMethodException e) {
			// nothing!
			return null;

		} catch (IllegalAccessException e) {
			e.printStackTrace();

		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static String asString(Object s, String fmt, String alt) { 
		return s != null ? String.format(fmt, s.toString()) : alt;
	}
	
	public static String concat(String sep, Object... objs) { 
		StringBuilder sb = new StringBuilder();
		for(Object obj : objs) { 
			String str = asString(obj, "%s", "");
			if(str.length() > 0 && sb.length() > 0) { 
				sb.append(sep);
			}
			sb.append(str);
		}
		return sb.toString();
	}
	
	public static <T> int count(T[] array) { 
		return array != null ? array.length : 0;
	}
	
	public static <T> int size(Collection<T> lst) { 
		return lst != null ? lst.size() : 0;
	}
}
