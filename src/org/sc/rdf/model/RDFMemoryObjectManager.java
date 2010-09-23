package org.sc.rdf.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.*;

import org.openrdf.model.BNode;
import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.Value;
import org.openrdf.sail.Sail;
import org.openrdf.sail.SailException;
import org.sc.rdf.InteractiveSelection;
import org.sc.rdf.Internment;
import org.sc.rdf.Prefixes;
import org.sc.rdf.sesame.SailSource;

import tdanford.autodata.AutoModel;
import tdanford.commons.Predicate;
import tdanford.streaming.HeavyIterator;
import tdanford.streaming.Sink;

public class RDFMemoryObjectManager implements Sink<Statement>, RDFObjectManager {

	private Internment<String> strings;
	private Prefixes prefixes;
	private Map<String,RDFObject> models;
	private BlankGenSym blankSyms;
	
	public RDFMemoryObjectManager() { 
		models = new HashMap<String,RDFObject>();
		prefixes = Prefixes.DEFAULT;
		blankSyms = new BlankGenSym();
		strings = new Internment<String>();
	}
	
	public RDFMemoryObjectManager(Sail s) throws SailException { 
		this();
		HeavyIterator<Statement> stmts = new SailSource(s);
		consume(stmts);
		stmts.dispose();
	}
	
	/**
	 * This will throw a ConcurrentException if you're not careful. 
	 * Don't call it from within a loop over the contents of a model (i.e., from within
	 * RDFModel),  because it can modify (through calls to replaceModel()) the contents
	 * of those lists.  
	 * 
	 * @param <T>
	 * @param u
	 * @param cls
	 * @return
	 */
	public <T extends RDFObject> T loadObject(String u, Class<T> cls) { 
		try {
			if(!models.containsKey(u)) {
				String iu = strings.intern(u);
				Constructor<T> constructor = cls.getConstructor(String.class);
				T rdf = constructor.newInstance(iu);
				models.put(iu, rdf);
				return rdf; 

			} else { 
				RDFObject model = models.get(u);
				if(AutoModel.isSubclass(model.getClass(), cls)) { 
					return (T)model;
				} else { 
					Constructor<T> constructor = cls.getConstructor(RDFMemoryObjectManager.class, RDFObject.class);
					T rdf = constructor.newInstance(this, model);
					replaceObject(model, rdf);
					return rdf;
				}
			}		
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);

		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}
	
	public <T1 extends RDFObject, T2 extends RDFObject> void replaceObject(T1 oldModel, T2 newModel) {
		models.put(oldModel.key(), newModel);
		for(String key : models.keySet()) { 
			RDFObject m = models.get(key);
			m.replace(oldModel, newModel);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.sc.rdf.model.RDFManager#hasModel(java.lang.String)
	 */
	public boolean hasObject(String u) { 
		return models.containsKey(u);
	}
	
	/* (non-Javadoc)
	 * @see org.sc.rdf.model.RDFManager#getModel(java.lang.String)
	 */
	public RDFObject loadObject(String u) {
		return loadObject(u, RDFObject.class);
	}
	
	public <T extends RDFObject> T findObject(String u, Class<T> cls) { 
		if(models.containsKey(u)) { 
			RDFObject m = models.get(u);
			if(AutoModel.isSubclass(m.getClass(), cls)) { 
				return (T)m;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.sc.rdf.model.RDFManager#findModel(java.lang.String)
	 */
	public RDFObject findObject(String u) { 
		if(models.containsKey(u)) { 
			return models.get(u);
		} else { 
			return null;
		}
	}
	
	public RDFSelection select(String u) {
		LinkedList<Object> vals = new LinkedList<Object>();
		RDFObject m = findObject(u);
		if(m != null) { vals.add(m); }
		return new RDFSelection(this, vals);
	}
	
	public RDFSelection execute(String cmd) { 
		InteractiveSelection sel = new InteractiveSelection(this);
		sel.interpretAndReturnCommand(cmd);
		return sel.getCurrentSelection();
	}
	
	public RDFSelection select(SparqlablePredicate pred) { 
		LinkedList<Object> vals = new LinkedList<Object>();
		for(String key : models.keySet()) { 
			RDFObject m = models.get(key);
			if(pred.accepts(m)) { 
				vals.add(m);
			}
		}
		return new RDFSelection(this, vals);		
	}
	
	public RDFSelection selectAll() { 
		return new RDFSelection(this, models.values());
	}
	
	public void consume(HeavyIterator<Statement> itr) {
		blankSyms.clear();
		
		while(itr.hasNext()) { 
			Statement stmt = itr.next();
			
			Resource subjectRec = stmt.getSubject();
			RDFObject subject = null;
			if(subjectRec instanceof BNode) { 
				BNode bn = (BNode)subjectRec;
				String bnid = blankSyms.sym(bn.getID());
				subject = loadObject(bnid);
			} else { 
				subject = loadObject(prefixes.contract(subjectRec.stringValue()));
			}
			
			Value objectRec = stmt.getObject();
			Object object = null;
			if(objectRec instanceof Literal) { 
				object = objectRec.stringValue();
			} else if (objectRec instanceof BNode ) {
				BNode bn = (BNode)objectRec;
				String bnid = blankSyms.sym(bn.getID());
				object = loadObject(bnid);
			} else { 
				object = loadObject(prefixes.contract(objectRec.stringValue()));
			}
			
			String ipred = strings.intern(
							prefixes.contract(stmt.getPredicate().stringValue()));
			
			// ensure that we know something about each predicate.
			RDFObject pred = loadObject(ipred);
			
			subject.addRDFTriple(ipred, object);
		}
	}

	/* (non-Javadoc)
	 * @see org.sc.rdf.model.RDFManager#dispose()
	 */
	public void dispose() {
		strings.clear();
		models.clear();
	}

	public int size() {
		return models.size();
	}
}

class GenSym {
	
	private String prefix;
	private long idx;
	
	public GenSym(String p) { 
		prefix = p;
		idx = 0;
	}
	
	public String sym() { 
		return String.format("%s#%d", prefix, idx++);
	}
}

class BlankGenSym { 
	
	private GenSym gensym;
	private Map<String,String> syms;
	
	public BlankGenSym() { 
		gensym = new GenSym("blank");
		syms= new HashMap<String,String>();
	}
	
	public void clear() { syms.clear(); }
	
	public String sym(String blankid) { 
		if(syms.containsKey(blankid)) { 
			return syms.get(blankid);
		} else { 
			String id = gensym.sym();
			syms.put(blankid, id);
			return id;
		}
	}
}
