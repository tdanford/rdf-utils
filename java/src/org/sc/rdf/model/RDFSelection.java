package org.sc.rdf.model;

import java.util.*;
import java.io.*;

import org.sc.rdf.InteractiveSelection;

import tdanford.autodata.AutoModel;
import tdanford.commons.Predicate;
import tdanford.random.Chooser;

/**
 * Each RDFSelection is a set of objects drawn from a particular RDFObjectManager.
 * @author tdanford
 */
public class RDFSelection {

	private static Random rand = new Random();
	
	private RDFObjectManager manager;
	private Set<Object> values;
	
	/**
	 * Creates an empty RDFSelection. 
	 * @param m The object manager on which this selection is based.
	 */
	public RDFSelection(RDFObjectManager m) {
		manager = m;
		values = new HashSet<Object>();
	}
	
	/**
	 * Creates a copy of the values in the given RDFSelection. 
	 * @param sel The selection object to be copied.
	 */
	public RDFSelection(RDFSelection sel) { 
		manager = sel.manager;
		values = new HashSet<Object>(sel.values);
	}

	/**
	 * Creates an RDFSelection from a given set of objects.
	 * @param m
	 * @param obj
	 * @param rest
	 */
	public RDFSelection(RDFObjectManager m, Object obj, Object... rest) { 
		this(m);
		values.add(obj);
		for(int i = 0; i < rest.length; i++) { values.add(obj); }
	}
	
	/**
	 * Creates an RDFSelection from a given set of objects.
	 * @param m
	 * @param objs  Each object must be drawn from the given RDFObjectManager
	 */
	public RDFSelection(RDFObjectManager m, Collection objs) {
		this(m);
		values = new HashSet<Object>(objs);
	}

	/**
	 * Tests for set containment between two RDFSelection objects.
	 * @param s
	 * @return
	 */
	public boolean isSubsetOf(RDFSelection s) { 
		for(Object v : values) { 
			if(!s.values.contains(v)) { 
				return false; 
			}
		}
		return true;
	}
	
	/**
	 * Tests for set equality. 
	 * 
	 * @param s
	 * @return
	 */
	public boolean isEqualTo(RDFSelection s) { 
		if(values.size() != s.values.size()) { return false; } 
		for(Object v : values) { 
			if(!s.values.contains(v)) { 
				return false; 
			}
		}
		return true;
	}
	
	/**
	 * The inverse of isSubsetOf().
	 * @param s
	 * @return
	 */
	public boolean isSupersetOf(RDFSelection s) { 
		return s.isSubsetOf(this);
	}
	
	public boolean isEmpty() { return values.isEmpty(); }
	
	public void print() { 
		PrintWriter w = new PrintWriter(System.out);
		print(w);
		w.flush();
	}
	
	public void print(PrintWriter ps) { 
		for(Object val : values) { 
			if(val instanceof RDFObject) { 
				RDFObject m = (RDFObject)val;
				ps.print(m.toTurtleString());
			} else if (val instanceof RDFReference) { 
				RDFReference ref = (RDFReference)val;
				ps.println(String.format("%s", ref.getKey()));
			} else { 
				ps.println(val.toString());
			}
		}
	}
	
	public void printTriples(PrintWriter ps, int depth) {
		for(Object val : values) { 
			if(val instanceof RDFObject) { 
				RDFObject m = (RDFObject)val;
				m.printTriples(ps, depth, 0);
			} else if (val instanceof RDFReference) { 
				RDFReference ref = (RDFReference)val;
				ref.asObject().printTriples(ps, depth, 0);
			}
		}		
	}
	
	public Collection<Object> objects() { 
		return new LinkedList<Object>(values); 
	}
	
	public Collection<Object> objects(Predicate p) { 
		LinkedList<Object> objs = new LinkedList<Object>();
		for(Object v : values) { 
			if(p.accepts(v)) { objs.add(v); }
		}
		return objs;
	}
	
	public <T> Collection<T> typed(Class<T> type) { 
		LinkedList<T> tvals = new LinkedList<T>();
		for(Object v : values) { 
			if(AutoModel.isSubclass(v.getClass(), type)) { 
				T tval = (T)v;
				tvals.add(tval);
			}
		}
		return tvals;
	}
	
	/**
	 * Destructively modifies the current RDFSelection object, 
	 * keeping only those objects for which pred.accepts() returns
	 * <tt>true</tt>.  
	 * @param pred
	 * @return A reference to <tt>this</tt>, the current RDFSelection.
	 */
	public RDFSelection subset(Predicate pred) { 
		HashSet<Object> vs = new HashSet<Object>(values);
		values.clear();
		for(Object v : vs) { 
			if(pred.accepts(v)) { 
				values.add(v);
			}
		}
		return this;
	}
	
	public RDFSelection t() { 
		return p("rdf:type");
	}
	
	public RDFSelection tt() { 
		return pp("rdf:type");
	}
	
	public RDFSelection execute(String cmd) { 
		InteractiveSelection sel = new InteractiveSelection(manager);
		sel.setSelection(this);
		System.out.println(sel.interpretAndReturnCommand(cmd));
		return this;
	}
	
	public RDFSelection singleP(String pred) { 
		HashSet<Object> vs = new HashSet<Object>(values);
		HashSet<Object> nvs = new HashSet<Object>();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				RDFObject m = (RDFObject)v;
				Object[] va = m.values(pred);
				for(int i = 0; i < va.length; i++) { 
					nvs.add(va[i]);
				}
			} else if (v instanceof RDFReference) { 
				RDFObject m = ((RDFReference)v).asObject();
				Object[] va = m.values(pred);
				for(int i = 0; i < va.length; i++) { 
					nvs.add(va[i]);
				}
			}
		}

		values.clear();
		values.addAll(nvs);
		return this;		
	}
	
	public RDFSelection singlePP(String pred) { 
		HashSet<Object> vs = new HashSet<Object>(values);
		HashSet<Object> nvs = new HashSet<Object>();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				RDFObject m = (RDFObject)v;
				Object[] va = m.incoming(pred);
				for(int i = 0; i < va.length; i++) { 
					nvs.add(va[i]);
				}
			} else if (v instanceof RDFReference) { 
				RDFObject m = ((RDFReference)v).asObject();
				Object[] va = m.incoming(pred);
				for(int i = 0; i < va.length; i++) { 
					nvs.add(va[i]);
				}
			}
		}

		values.clear();
		values.addAll(nvs);
		return this;		
	}
	
	public RDFSelection p(String rawpred) {  
		RDFSelection rdf = this;
		String[] parray = rawpred.split("\\|");
		for(String pred : parray) { 
			rdf = rdf.singleP(pred);
		}
		return rdf;
	}
	
	public RDFSelection pp(String rawpred) {  
		RDFSelection rdf = this;
		String[] parray = rawpred.split("\\|");
		for(String pred : parray) { 
			rdf = rdf.singlePP(pred);
		}
		return rdf;
	}
	
	public RDFSelection p() { 
		HashSet<Object> vs = new HashSet<Object>(values);
		values.clear();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				RDFObject m = (RDFObject)v;
				for(String pred : m.predicates()) { 
					Object[] va = m.values(pred);
					for(int i = 0; i < va.length; i++) { 
						values.add(va[i]);
					}
				}
			} else if (v instanceof RDFReference) { 
				RDFObject m = ((RDFReference)v).asObject();
				for(String pred : m.predicates()) { 
					Object[] va = m.values(pred);
					for(int i = 0; i < va.length; i++) { 
						values.add(va[i]);
					}
				}
			}
		}
		return this;		
	}
	
	public RDFSelection pp() { 
		HashSet<Object> vs = new HashSet<Object>(values);
		values.clear();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				RDFObject m = (RDFObject)v;
				for(String pred : m.incomingPredicates()) { 
					Object[] va = m.incoming(pred);
					for(int i = 0; i < va.length; i++) { 
						values.add(va[i]);
					}
				}
			} else if (v instanceof RDFReference) { 
				RDFObject m = ((RDFReference)v).asObject();
				for(String pred : m.incomingPredicates()) { 
					Object[] va = m.incoming(pred);
					for(int i = 0; i < va.length; i++) { 
						values.add(va[i]);
					}
				}
			}
		}
		return this;		
	}
	
	public RDFSelection outp() { 
		HashSet vs = new HashSet(values);
		values.clear();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				String[] pred = ((RDFObject)v).predicates();
				for(String pr : pred) { 
					values.add(manager.loadObject(pr));
				}
			} else if (v instanceof RDFReference) { 
				String[] pred = ((RDFReference)v).asObject().predicates();
				for(String pr : pred) { 
					values.add(manager.loadObject(pr));
				}				
			}
		}
		return this;
	}
	
	public RDFSelection inp() { 
		HashSet vs = new HashSet(values);
		values.clear();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				String[] pred = ((RDFObject)v).incomingPredicates();
				for(String pr : pred) { 
					values.add(manager.loadObject(pr));
				}
			} else if (v instanceof RDFReference) { 
				String[] pred = ((RDFReference)v).asObject().incomingPredicates();
				for(String pr : pred) { 
					values.add(manager.loadObject(pr));
				}				
			}
		}
		return this;
	}
	
	public RDFSelection subj() {
		HashSet<String> preds = new HashSet<String>();
		for(Object v : values) { 
			if(v instanceof RDFObject) { 
				preds.add(((RDFObject)v).key());
			} else if (v instanceof RDFReference) { 
				preds.add(((RDFReference)v).getKey());
			}
		}
		values = new HashSet(manager.select(new RDFModelHasPredicate(preds, true)).objects());
		return this;
	}
	
	public RDFSelection obj() {
		HashSet<String> preds = new HashSet<String>();
		for(Object v : values) { 
			if(v instanceof RDFObject) { 
				preds.add(((RDFObject)v).key());
			} else if (v instanceof RDFReference) { 
				preds.add(((RDFReference)v).getKey());
			}
		}
		values = new HashSet(manager.select(new RDFModelHasPredicate(preds, false)).objects());
		return this;
	}
	
	private static class RDFModelHasPredicate implements SparqlablePredicate<RDFObject> {
		
		private Set<String> preds;
		private boolean subject;
		
		public RDFModelHasPredicate(Collection<String> ps, boolean subj) { 
			preds = new TreeSet<String>(ps);
			subject = subj;
		}

		public boolean accepts(RDFObject m) { 
			String[] parray = subject ? m.predicates() : m.incomingPredicates();
			for(int i = 0; i < parray.length; i++) { 
				if(preds.contains(parray[i])) { 
					return true;
				}
			}
			return false;
		}

		public String asSparqlQuery() {
			StringBuilder sb = new StringBuilder();
			for(String p : preds) { 
				String clause = subject ? 
						String.format("{ ?x <%s> ?y }", p) : 
						String.format("{ ?y <%s> ?x }", p);
				if(sb.length() > 0) { 
					sb.append(" UNION "); 
				}
				sb.append(clause);
			}
			
			return String.format("SELECT DISTINCT ?x WHERE { %s }", sb.toString());
		}
	}
	
	public void list() { 
		PrintWriter w = new PrintWriter(System.out);
		list(w);
		w.flush();
	}

	public void list(PrintWriter ps) {
		int i = 0;
		for(Object v : values) { 
			ps.println(String.format("% 4d: %s", i++, v.toString()));
		}
	}

	public Object choose() {
		int c = rand.nextInt(Math.max(1, values.size()));
		Iterator itr = values.iterator();
		Object v = null;
		while(c >= 0 && itr.hasNext()) { v = itr.next(); c -= 1; }
		return v;
	}

	public int size() {
		return values.size();
	}
	
	public RDFSelection add(Collection c) { 
		values.addAll(c);
		return this;
	}

	public RDFSelection remove(Predicate p) {
		Iterator itr = values.iterator();
		while(itr.hasNext()) { 
			if(p.accepts(itr.next())) { 
				itr.remove();
			}
		}
		return this;
	}
	
	public RDFSelection hasData(String key, String value) {
		HashSet vs = new HashSet(values);
		values.clear();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				RDFObject m = (RDFObject)v;
				if(m.hasPredicate(key) && m.hasMatchingValue(key, value)) { 
					values.add(m);
				}
			} else if (v instanceof RDFReference) { 
				RDFObject m = ((RDFReference)v).asObject();
				if(m.hasPredicate(key) && m.hasMatchingValue(key, value)) { 
					values.add(m);
				}
			}
		}
		
		return this;
	}

	public RDFSelection hasData(String key) {
		HashSet vs = new HashSet(values);
		values.clear();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				RDFObject m = (RDFObject)v;
				if(m.hasPredicate(key) && m.hasAnyValue(key, vs)) { 
					values.add(m);
				}
			} else if (v instanceof RDFReference) { 
				RDFObject m = ((RDFReference)v).asObject();
				if(m.hasPredicate(key) && m.hasAnyValue(key, vs)) { 
					values.add(m);
				}
			}
		}
		
		return this;
	}

	public RDFSelection choose(Integer num) {
		values = new HashSet(new Chooser(values).sample(Math.min(values.size(), num), false));
		return this;
	}

	public RDFSelection pick(Integer... num) {
		ArrayList<Object> varray = new ArrayList<Object>(values);
		values.clear();
		for(int i = 0; i < num.length; i++) { 
			if(num[i] >= 0 && num[i] < varray.size()) { 
				values.add(varray.get(num[i]));
			}
		}
		return this;
	}
	
	public boolean contains(Object o) { 
		return values.contains(o);
	}

	public RDFSelection filter(String pred, RDFSelection targets) {
		HashSet vs = new HashSet(values);
		Collection ts = targets.objects();
		values.clear();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				RDFObject m = (RDFObject)v;
				if(m.hasPredicate(pred) && m.hasAnyValue(pred, ts)) { 
					values.add(m);
				}
			} else if(v instanceof RDFReference) { 
				RDFObject m = ((RDFReference)v).asObject();
				if(m.hasPredicate(pred) && m.hasAnyValue(pred, ts)) { 
					values.add(m);
				}
			}
		}
		return this;
	}

	public RDFSelection withPredicate(String pred) {
		HashSet vs = new HashSet(values);
		values.clear();
		for(Object v : vs) { 
			if(v instanceof RDFObject) { 
				RDFObject m = (RDFObject)v;
				if(m.hasPredicate(pred)) { 
					values.add(m);
				}
			} else if(v instanceof RDFReference) { 
				RDFObject m = ((RDFReference)v).asObject();
				if(m.hasPredicate(pred)) { 
					values.add(m);
				}
			}
		}
		return this;		
	}

	public RDFSelection pstar(String pred) {
		RDFSelection next = new RDFSelection(this);
		int oldSize = 0;
		do { 
			next.p(pred);
			oldSize = values.size();
			values.addAll(next.values);
		} while(values.size() > oldSize);
		
		return this;
	}

	public RDFSelection ppstar(String pred) {
		RDFSelection next = new RDFSelection(this);
		int oldSize = 0;
		do { 
			next.pp(pred);
			oldSize = values.size();
			values.addAll(next.values);
		} while(values.size() > oldSize);
		
		return this;
	}

	public RDFSelection pstar() {
		RDFSelection next = new RDFSelection(this);
		int oldSize = 0;
		do { 
			next.p();
			oldSize = values.size();
			values.addAll(next.values);
		} while(values.size() > oldSize);
		
		return this;
	}

	public RDFSelection ppstar() {
		RDFSelection next = new RDFSelection(this);
		int oldSize = 0;
		do { 
			next.pp();
			oldSize = values.size();
			values.addAll(next.values);
		} while(values.size() > oldSize);
		
		return this;
	}

	public RDFSelection subtract(RDFSelection d2) {
		Iterator itr = values.iterator();
		while(itr.hasNext()) { 
			Object val = itr.next();
			if(d2.values.contains(val)) { 
				itr.remove();
			}
		}
		return this;
	}

	public RDFSelection intersect(RDFSelection d2) {
		Iterator itr = values.iterator();
		while(itr.hasNext()) { 
			Object val = itr.next();
			if(!d2.values.contains(val)) { 
				itr.remove();
			}
		}
		return this;
	}
	
	public RDFSelection hasValueIn(String pred, RDFSelection sel) { 
		Set<Object> newValues = new HashSet<Object>();
		nextObject: for(Object val : values) { 
			if(val instanceof RDFObject) { 
				RDFObject obj = (RDFObject)val;
				if(obj.hasPredicate(pred)) { 
					for(Object predval : obj.values(pred)) { 
						if(sel.values.contains(predval)) { 
							newValues.add(obj);
							continue nextObject;
						}
					}
				}
			}
		}
		
		values = newValues;
		return this;
	}
	
	public void printTable(File file, String... cols) throws IOException {
		PrintStream ps = new PrintStream(new FileOutputStream(file));
		for(Object value : values) { 
			if(value instanceof RDFObject) { 
				RDFObject obj = (RDFObject)value;
				ps.print(String.format("object\t%s", obj.key()));
				for(String col : cols) { 
					ps.print("\t");
					if(obj.hasPredicate(col)) {
						Object[] vs =  obj.values(col);
						for(int i = 0; i < vs.length; i++) { 
							if(i > 0) { ps.print(","); }
							ps.print(vs[i].toString());
						}
					}
				}
				ps.println();
			} else { 
				ps.println(String.format("literal\t\"%s\"", value.toString()));
			}
		}
		ps.close();
	}

	public RDFSelection rawpreds() {
		Set<String> predlist = new TreeSet<String>();
		for(Object val : values) { 
			if(val instanceof RDFObject) { 
				RDFObject r = (RDFObject)val;
				for(String p : r.predicates()) { 
					predlist.add(p);
				}
			} else if (val instanceof RDFReference) { 
				RDFObject r = ((RDFReference)val).asObject();
				for(String p : r.predicates()) { 
					predlist.add(p);
				}
			}
		}
		values.clear();
		values.addAll(predlist);
		return this;
	}
}
