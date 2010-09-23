package org.sc.rdf.model;

import tdanford.commons.Predicate;

public interface SparqlablePredicate<T extends RDFObject> extends Predicate<T> {
	public String asSparqlQuery();
}
