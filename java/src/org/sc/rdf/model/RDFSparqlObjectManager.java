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
import org.sc.rdf.InteractiveSelection;
import org.sc.rdf.Internment;
import org.sc.rdf.Prefixes;
import org.sc.rdf.sesame.SesameBindingTable;
import org.sc.rdf.sesame.SparqlEndpoint;

import tdanford.autodata.AutoModel;
import tdanford.commons.Predicate;
import tdanford.streaming.HeavyIterator;
import tdanford.streaming.Sink;

public class RDFSparqlObjectManager implements RDFObjectManager {

	private Internment<String> strings;
	private Prefixes prefixes;
	private BlankGenSym blankSyms;	

	private SparqlEndpoint sparql;
	private Map<String,RDFObject> models;
	
	public RDFSparqlObjectManager(SparqlEndpoint endp) {
		sparql = endp;
		models = new HashMap<String,RDFObject>();
		prefixes = Prefixes.DEFAULT;
		blankSyms = new BlankGenSym();
		strings = new Internment<String>();
	}
	
	private <T extends RDFObject> T lookupModel(String uri, Class<T> cls) 
		throws InstantiationException, 
			IllegalAccessException, 
			InvocationTargetException, 
			SecurityException, 
			NoSuchMethodException {
		
		uri = strings.intern(uri);
		Constructor<T> constructor = cls.getConstructor(String.class);
		T rdf = constructor.newInstance(uri);
		
		models.put(uri, rdf);

		SesameBindingTable<Value> bindings = 
			sparql.evaluate(String.format("SELECT ?y ?z WHERE { <%s> ?y ?z . }", uri));
		for(int i =0; i < bindings.size(); i++) { 
			Value z = bindings.get(i, "z"), y = bindings.get(i, "y");
			String pred = prefixes.contract(y.toString());
			if(z instanceof Resource) { 
				Resource r = (Resource)z;
				String target = prefixes.contract(r.toString());
				RDFReference ref = new RDFReference(this, target);
				rdf.addRDFTriple(pred, ref);
			} else { 
				rdf.addRDFTriple(pred, z.toString());
			}
		}
		
		bindings = sparql.evaluate(String.format("SELECT ?x ?y WHERE { ?x ?y <%s> . }", uri));
		for(int i =0; i < bindings.size(); i++) { 
			Value x = bindings.get(i, "x"), y = bindings.get(i, "y");
			String pred = prefixes.contract(y.toString());
			Resource r = (Resource)x;
			String subject = prefixes.contract(r.toString());
			RDFReference ref = new RDFReference(this, subject);
			rdf.addRDFTriple(pred, ref);
		}
		
		return rdf; 		
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
				return lookupModel(u, cls);

			} else { 
				RDFObject model = models.get(u);
				if(AutoModel.isSubclass(model.getClass(), cls)) { 
					return (T)model;
				} else { 
					Constructor<T> constructor = cls.getConstructor(RDFSparqlObjectManager.class, RDFObject.class);
					T rdf = constructor.newInstance(this, model);
					replaceModel(model, rdf);
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
	
	private <T1 extends RDFObject, T2 extends RDFObject> void replaceModel(T1 oldModel, T2 newModel) {
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
	
	/** Selection and Execution Commands **************************************/
	
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
		String query = pred.asSparqlQuery();
		SesameBindingTable<Value> bindings = sparql.evaluate(query);
		LinkedList values = new LinkedList();
		for(int i = 0; i < bindings.size(); i++) { 
			Value v = bindings.get(i, "x");
			String uri = prefixes.contract(v.toString());
			if(models.containsKey(uri)) { 
				values.add(models.get(uri));
			} else { 
				values.add(new RDFReference(this, uri));
			}
		}
		return new RDFSelection(this, values);
	}
	
	public RDFSelection selectAll() { 
		SesameBindingTable table = sparql.evaluate("select ?x");
		LinkedList<RDFReference> refs = new LinkedList<RDFReference>();
		for(int i = 0; i < table.size(); i++) { 
			String uri = table.get(i, "x").toString();
			refs.add(new RDFReference(this, uri));
		}
		return new RDFSelection(this, refs);
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


