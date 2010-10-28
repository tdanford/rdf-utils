package org.sc.owl.reifier;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import java.util.*;

public class Reifier {
	
	private OntModel base;

	public Reifier(OntModel model) { 
		this.base = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM);
		this.base.add(model);
	}
	
	private Individual reifyIndividualIn(OntModel model, OntClass type, OntProperty retProp) { 

		Individual idv = type.createIndividual();

		ExtendedIterator<OntClass> superItr = type.listSuperClasses();
		try { 
			while(superItr.hasNext()) { 
				OntClass superClass = superItr.next();
				
				if(superClass.isRestriction()) { 
					Restriction restriction = superClass.asRestriction();
					OntProperty prop = restriction.getOnProperty();

					if(prop.isObjectProperty()) { 
						OntProperty invProp = prop.getInverse();

						if(restriction.isSomeValuesFromRestriction()) {
							SomeValuesFromRestriction someValues = 
								restriction.asSomeValuesFromRestriction();
							
							OntClass someValuesFrom = someValues.getSomeValuesFrom().as(OntClass.class);
							
							

						} else if (restriction.isAllValuesFromRestriction()) { 

						} else if (restriction.isMaxCardinalityRestriction()) { 

						} else if (restriction.isMinCardinalityRestriction()) { 

						}
					} else {
						// restriction that leads to a data literal.

					}
					
				}
			}
			
		} finally { 
			superItr.close();
		}
		
		return null;
	}
	
	public Individual reify(String classURI) { 
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM);
		OntClass cls = model.getOntClass(classURI);
		Individual idv = cls.createIndividual();

		return idv;
	}
}
