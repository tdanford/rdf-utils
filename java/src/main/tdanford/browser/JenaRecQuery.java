package tdanford.browser;

import java.util.LinkedList;
import java.util.TreeSet;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class JenaRecQuery implements RecQuery {
	
	private Model model;
	private Property rdfsLabel;
	
	public JenaRecQuery(Model m) { 
		model = m;
		rdfsLabel = model.getProperty("http://www.w3.org/2000/01/rdf-schema#label");
	}
	
	public Resource resource(Rec rec) { 
		return model.getResource(rec.value());
	}
	
	public Rec rec(RDFNode res) { 
		return new Rec(res.toString(), res.isAnon());
	}

	public RecSet backward(Rec object, Rec property) {
		Resource jObject = resource(object);
		Property jProperty = resource(property).as(Property.class);
		ResIterator itr = model.listSubjectsWithProperty(jProperty, jObject);
		LinkedList<Rec> recs = new LinkedList<Rec>();
		try { 
			while(itr.hasNext()) { 
				recs.add(rec(itr.next()));
			}
		} finally { 
			itr.close();
		}
		return new RecSet(recs);
	}

	public RecSet backwardProperties(Rec object) {
		Resource jObject = resource(object);
		StmtIterator itr = model.listStatements(null, null, jObject);
		TreeSet<Rec> recs = new TreeSet<Rec>();
		try { 
			while(itr.hasNext()) { 
				Statement stmt = itr.next();
				Property prop = stmt.getPredicate();
				recs.add(rec(prop));
			}
		} finally { 
			itr.close();
		}
		return new RecSet(recs);
	}

	public RecSet forward(Rec subject, Rec property) {
		Resource jSubject = resource(subject);
		Property jProperty = resource(property).as(Property.class);
		StmtIterator itr = jSubject.listProperties(jProperty);
		LinkedList<Rec> recs = new LinkedList<Rec>();
		try { 
			while(itr.hasNext()) { 
				recs.add(rec(itr.next().getObject()));
			}
		} finally { 
			itr.close();
		}
		return new RecSet(recs);
	}

	public RecSet forwardProperties(Rec subject) {
		Resource jSubject = resource(subject);
		StmtIterator itr = model.listStatements(jSubject, null, (RDFNode)null);
		TreeSet<Rec> recs = new TreeSet<Rec>();
		try { 
			while(itr.hasNext()) { 
				Statement stmt = itr.next();
				Property prop = stmt.getPredicate();
				recs.add(rec(prop));
			}
		} finally { 
			itr.close();
		}
		return new RecSet(recs);
	}

	public Rec identified(String id) {
		return rec(model.createResource(id));
	}

	public RecSet labeled(String lbl) {
		if(rdfsLabel != null) { 
			ResIterator itr = model.listSubjectsWithProperty(rdfsLabel, model.createLiteral(lbl));
			LinkedList<Rec> recs = new LinkedList<Rec>();
			try { 
				while(itr.hasNext()) { 
					recs.add(rec(itr.next()));
				}
				return new RecSet(recs);
			} finally { 
				itr.close();
			}
		} else { 
			return new RecSet();
		}
	}

}
