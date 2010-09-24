package org.sc.rdf.model;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;

import java.util.*;

import tdanford.autodata.*;
import tdanford.autodata.selectors.*;

import org.openrdf.model.*;
import org.sc.rdf.Prefixes;

public class RDFObject {
	
	private Map<String,ArrayList<Object>> rdf; 
	private Map<String,ArrayList<Object>> inv;
	private String key;
	
	public RDFObject() {
		super();
		rdf = new TreeMap<String,ArrayList<Object>>();
		inv = new TreeMap<String,ArrayList<Object>>();
		key = null;		
	}
	
	public RDFObject(RDFReference ref) { 
		this(ref.asObject());
	}
	
	public RDFObject(URI u) { 
		this(u.stringValue()); 
	}
	
	public RDFObject(String u) { 
		this();
		key = u;
	}
	
	public RDFObject(RDFObject m) { 
		rdf = new TreeMap<String,ArrayList<Object>>(m.rdf);
		inv = new TreeMap<String,ArrayList<Object>>(m.inv);
		key = m.key;
	}
	
	public Resource asResource(ValueFactory valfact) { 
		Resource subject = key.startsWith("blank") ? 
				valfact.createBNode(key) : 
				valfact.createURI(key);
		return subject;
	}
	
	public List<Statement> asStatements(ValueFactory valfact) { 
		LinkedList<Statement> stmts = new LinkedList<Statement>();
		Resource subject = asResource(valfact);
		for(String predKey : rdf.keySet()) {
			URI predicate = valfact.createURI(predKey);
			for(Object obj : rdf.get(predKey)) {
				Value object = null;
				
				if(obj instanceof RDFObject) {
					RDFObject rdfObj = (RDFObject)obj;
					object = rdfObj.asResource(valfact);
					
				} else if (obj instanceof RDFReference) {
					RDFReference ref = (RDFReference)obj;
					object = ref.asResource(valfact);
					
				} else { 
					object = valfact.createLiteral(obj.toString());
				}
				
				if(object != null) { 
					stmts.addLast(valfact.createStatement(subject, predicate, object));
				}
			}
		}
		return stmts;
	}
	
	public <T1 extends RDFObject,T2 extends RDFObject> void replace(T1 oldm, T2 newm) { 
		
		for(String key : rdf.keySet()) { 
			if(rdf.get(key).contains(oldm)) { 
				rdf.get(key).remove(oldm);
				rdf.get(key).add(newm);
			}
		}
		for(String key : inv.keySet()) { 
			if(inv.get(key).contains(oldm)) { 
				inv.get(key).remove(oldm);
				inv.get(key).add(newm);
			}
		}
	}
	
	public void addRDFTriple(String p, Object o) {
		if(!rdf.containsKey(p)) { 
			rdf.put(p, new ArrayList<Object>()); 
		}
		rdf.get(p).add(o);
		if(o instanceof RDFObject) { 
			RDFObject m = (RDFObject)o;
			m.addIncomingTriple(this, p);
			
			// Remove this block of code, as we don't want to instantiate 
			// the "opposite" of each triple.  
			/*
		} else if (o instanceof RDFReference) { 
			RDFReference ref = (RDFReference)o;
			RDFObject obj = ref.asObject();
			obj.addIncomingTriple(this, p);
			*/
		}
	}
	
	public void printTriples(PrintWriter ps) { 
		printTriples(ps, 0, 0);
	}
	
	private static String indent(int d) { 
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < d; i++) { 
			sb.append("  ");
		}
		return sb.toString();
	}
	
	public void printTriples(PrintWriter ps, int depth, int indent) { 
		Iterator<String> keyitr = rdf.keySet().iterator();
		String subject = key;
		while(keyitr.hasNext()) {
			String pred = keyitr.next();
			ArrayList<Object> values = rdf.get(pred);
			Iterator itr = values.iterator();
			while(itr.hasNext()) { 
				Object val = itr.next();
				String valstr = 
					val instanceof RDFObject ? val.toString() : 
						val instanceof RDFReference ? val.toString() : 
						String.format("\"%s\"", val.toString());
				ps.println(String.format("%s%s\t%s\t%s .", 
						indent(indent), 
						subject, pred, valstr));
				
				if((val instanceof RDFObject || val instanceof RDFReference) 
						&& depth > 0) { 
					RDFObject object = 
						(val instanceof RDFObject) ? 
								(RDFObject)val : 
								((RDFReference)val).asObject();
					object.printTriples(ps, depth-1, indent+1);
				}
			}
			
		}		
	}
	
	public void addIncomingTriple(Object subj, String p) { 
		if(!inv.containsKey(p)) { 
			inv.put(p, new ArrayList<Object>());
		}
		inv.get(p).add(subj);
	}
	
	public String toTurtleString() { 
		return toTurtleString(0);
	}

	public String toTurtleString(int depth) { 
		StringBuilder sb = new StringBuilder();
		sb.append(key); sb.append("\n");
		Iterator<String> keyitr = rdf.keySet().iterator();
		while(keyitr.hasNext()) { 
			String p = keyitr.next();
			ArrayList<Object> values = rdf.get(p);
			Iterator itr = values.iterator();
			if(itr.hasNext()) { 
				Object val = itr.next();
				String valstr = 
					val instanceof RDFObject ? val.toString() : 
						val instanceof RDFReference ? val.toString() : 
						String.format("\"%s\"", val.toString());
				
				if(itr.hasNext()) { 
					sb.append(String.format("\t%s\n\t\t%s", p, valstr)); 
				} else { 
					sb.append(String.format("\t%s %s", p, valstr));
				}
				
				if(itr.hasNext()) { 
					sb.append(" ,\n");
					while(itr.hasNext()) {
						val = itr.next();
						valstr = 
							val instanceof RDFObject ? val.toString() : 
							val instanceof RDFReference ? val.toString() : 
							String.format("\"%s\"", val.toString());
								
						sb.append(String.format("\t\t%s", valstr)); 
						if(itr.hasNext()) { 
							sb.append(" ,");
						} else {
							if(keyitr.hasNext()) { 
								sb.append(" ;");
							}
						}
						sb.append("\n");
					}
				} else { 
					if(keyitr.hasNext()) { 
						sb.append(" ;\n");
					}
				}
			}
		}
		sb.append(" .\n");
		return sb.toString();
	}
	
	public String toString() { 
		String lbl = label(); 
		String k = key;
		if(lbl == null) { 
			return k;
		} else { 
			return String.format("%s (\"%s\")", k, lbl);
		}
	}
	
	public static String[] labelPredicates = {
		"rdfs:label", 
		"skos:prefLabel", 
		"swan:hasPreferredName",
		"swan:hasName"
	};
	
	public String label() {
		for(String pred : labelPredicates) { 
			if(rdf.containsKey(pred)) { 
				return rdf.get(pred).get(0).toString();
			}
		}
		return null;
	}
	
	public String key() { return key; }
	
	public boolean isBlank() { return key.startsWith("blank#"); }
	
	public Object[] values(String p) { 
		return rdf.containsKey(p) ? 
				rdf.get(p).toArray(new Object[0]) : new Object[0];
	}
	
	public int numValues(String p) { 
		return rdf.containsKey(p) ? rdf.get(p).size() : 0;
	}
	
	public RDFObject modelValue(String p, int i) {
		return (RDFObject)rdf.get(p).get(i);
	}
	
	public String[] predicates() { 
		return rdf.keySet().toArray(new String[0]);
	}
	
	public String[] incomingPredicates() { 
		return inv.keySet().toArray(new String[0]);
	}
	
	public RDFObject[] incoming(String p) { 
		return inv.containsKey(p) ? 
				inv.get(p).toArray(new RDFObject[0]) : new RDFObject[0];
	}
	
	public int hashCode() { return key.hashCode(); }
	
	public boolean equals(Object o) { 
		if(!(o instanceof RDFObject)) { return false; }
		RDFObject m = (RDFObject)o;
		return m.key.equals(key);
	}

	public boolean hasPredicate(String p) {
		return rdf.containsKey(p);
	}
	
	public boolean hasIncomingPredicate(String p) { 
		return inv.containsKey(p);
	}
	
	public boolean hasMatchingValue(String p, String v) { 
		if(!hasPredicate(p)) { return false; }
		for(Object val : rdf.get(p)) { 
			if(val.toString().equals(v)) { 
				return true;
			}
			if(val instanceof RDFObject) { 
				RDFObject robj = (RDFObject)val;
				if(robj.key().equals(v)) { 
					return true;
				}
				
				String lbl = robj.label();
				if(lbl != null && lbl.equals(v)) { 
					return true;
				}
			} else if (val instanceof RDFReference) { 
				RDFReference ref = (RDFReference)val;
				RDFObject robj = ref.asObject();
				if(robj.key().equals(v)) { 
					return true;
				}
				
				String lbl = robj.label();
				if(lbl != null && lbl.equals(v)) { 
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasAnyValue(String k, Collection vs) {
		if(!hasPredicate(k)) { return false; }
		for(Object v : rdf.get(k)) { 
			if(vs.contains(v)) { 
				return true;
			}
		}
		return false;
	}

	public boolean hasValue(String string, Object val) {
		return hasPredicate(string) && 
			rdf.get(string).contains(val);
	}
	
	public boolean hasIncomingValue(String string, Object val) { 
		return hasIncomingPredicate(string) &&  
			inv.get(string).contains(val);
	}

	public <T> T typedValue(String p, int i) {
		return (T)rdf.get(p).get(i);
	}
	
	public <T> T[] findTypedValues(String p, Class<T> cls) {
		ArrayList<T> lst = new ArrayList<T>();
		if(rdf.containsKey(p)) { 
			for(Object v : rdf.get(p)) { 
				if(AutoModel.isSubclass(v.getClass(), cls)) { 
					lst.add((T)v);
				}
			}
		}
		
		T[] arr = (T[]) Array.newInstance(cls, lst.size());
		return lst.toArray(arr);
	}
}
