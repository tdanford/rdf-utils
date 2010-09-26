package tdanford.graph;

import java.util.Set;
import java.util.TreeSet;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
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

	@Override
	public Set<Identifier> followReverse(Identifier id, Identifier prop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasForward(Identifier id, Value value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasIdentifier(Identifier id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasReverse(Identifier id, Value value) {
		// TODO Auto-generated method stub
		return false;
	}

}
