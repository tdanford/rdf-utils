package tdanford.graph;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class JenaModelPropertyGraph implements PropertyGraph {
	
	private Model model;
	
	public JenaModelPropertyGraph(Model m) { 
		model = m;
	}
	
	public Resource asResource(Identifier id) { 
		return model.getResource(id.toString());
	}
	
	public Property asProperty(Identifier id) { 
		return model.getProperty(id.toString());
	}
	
	public Identifier asIdentifier(Resource rec) { 
		return new Identifier(rec.getURI());
	}

	private class IdentifierIterator implements Iterator<Identifier> {
		
		private ResIterator itr;
		
		public IdentifierIterator(ResIterator ri) { 
			itr = ri;
		}

		public boolean hasNext() {
			return itr != null && itr.hasNext();
		}

		public Identifier next() {
			Identifier id = asIdentifier(itr.next());
			if(!itr.hasNext()) { 
				close();
			}
			return id;
		}
		
		public void close() { 
			if(itr != null) { 
				itr.close();
				itr = null;
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		} 
		
	}

	public Set<Identifier> followForward(Identifier id, Identifier prop) {
		Resource start = asResource(id);
		Property property = asProperty(prop);
		StmtIterator itr = start.listProperties(property);
		try {
			TreeSet<Identifier> ids = new TreeSet<Identifier>();
			
			while(itr.hasNext()) { 
				Statement stmt = itr.next();
				Resource object = stmt.getObject().as(Resource.class);
				ids.add(asIdentifier(object));
			}
			
			return ids;
			
		} finally { 
			itr.close();
		}
	}

	public Set<Identifier> followReverse(Identifier id, Identifier prop) {
		Resource start = asResource(id);
		Property property = asProperty(prop);
		StmtIterator itr = model.listStatements(null, property, start);
		try {
			TreeSet<Identifier> ids = new TreeSet<Identifier>();
			
			while(itr.hasNext()) { 
				Statement stmt = itr.next();
				Resource object = stmt.getSubject();
				ids.add(asIdentifier(object));
			}
			
			return ids;
			
		} finally { 
			itr.close();
		}
	}

	public boolean hasForward(Identifier id, Value value) {
		Resource start = asResource(id);
		if(value instanceof Identifier) { 
			Resource target = asResource((Identifier)value);
			return model.contains(start, null, target);
		} else if (value instanceof Literal) {
			Literal target = (Literal)value;
			return model.contains(start, null, target.toString());
		} else { 
			return false;
		}
	}

	public boolean hasIdentifier(Identifier id) {
		Resource start = asResource(id);
		return model.containsResource(start); 
	}

	public boolean hasReverse(Identifier target, Identifier source) {
		Resource start = asResource(source);
		Resource end = asResource(target);
		return model.contains(start, null, end);
	}

	public Iterator<Identifier> allIdentifiers() {
		return new IdentifierIterator(model.listSubjects());
	}

}
