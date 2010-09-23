package org.sc.rdf.model;

import org.openrdf.model.Resource;
import org.openrdf.model.ValueFactory;

public class RDFReference {
	
	private String key;
	private RDFObjectManager manager;
	
	public RDFReference(RDFObjectManager m, String k) { 
		key = k;
		manager = m;
	}

	public RDFObjectManager getManager() { return manager; }
	public String getKey() { return key; }
	
	public RDFObject asObject() { return manager.loadObject(key); }
	
	public Resource asResource(ValueFactory valfact) { 
		Resource subject = key.startsWith("blank") ? 
				valfact.createBNode(key) : 
				valfact.createURI(key);
		return subject;
	}
	
	public int hashCode() { return key.hashCode(); }
	
	public String toString() { return key; }
	
	public boolean equals(Object o) { 
		if(!(o instanceof RDFReference)) { return false; }
		RDFReference r = (RDFReference)o;
		return r.key.equals(key) && manager == r.manager;
	}
}
