package org.sc.rdf.model;

import java.util.regex.Pattern;
import tdanford.commons.Predicate;

/**
 * An ObjectManager is a kind of caching middle layer, between some source of triples (below)
 * and the consumer of the objects (above).  It loads and collates triples into RDFObject items, 
 * which are then passed to the user.  
 * 
 * @author tdanford
 */
public interface RDFObjectManager {

	public boolean hasObject(String u);
	public RDFObject loadObject(String u);
	public RDFObject findObject(String u);
	
	public void dispose();
	
	public RDFSelection execute(String str);
	
	public RDFSelection select(String uri);
	public RDFSelection select(SparqlablePredicate pred);
	public RDFSelection selectAll();
}