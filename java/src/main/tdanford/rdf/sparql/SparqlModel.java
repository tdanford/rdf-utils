package tdanford.rdf.sparql;

import java.net.URL;


import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class SparqlModel {
	
	public static void main(String[] args) { 
		SparqlUpdateProperties props = new SparqlUpdateProperties("joseki");
		Model model = createSparqlModel(props.getSparqlURL(), props.getUpdateURL(), null, props.isVirtuosoEndpoint());
		
		OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM, model);

		OntClass cls = ontModel.createClass("nc:ontClassTest");  // need a prefix expander here.
		//cls.addLabel(ontModel.createLiteral("Ontology Test Class"));
		
		StmtIterator itr = cls.listProperties();
		try { 
			while(itr.hasNext()) { 
				System.out.println(itr.next());
			}
		} finally { itr.close(); }

	}
	
	public static Model createSparqlModel(URL query, URL update, String graph, boolean isVirtuoso) {
		HTTPSparqlQuery queryer = new HTTPSparqlQuery(query);
		HTTPSparqlUpdate updater = new HTTPSparqlUpdate(query, update, isVirtuoso);
		return ModelFactory.createModelForGraph(
				new SparqlGraphBase(queryer, updater, graph, isVirtuoso));
	}

}
