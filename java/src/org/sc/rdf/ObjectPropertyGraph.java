package org.sc.rdf;

import java.util.*;


import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.sc.rdf.sesame.SesameBindingTable;
import org.sc.rdf.sesame.SparqlEndpoint;

public class ObjectPropertyGraph {

	private Internment<String> intern;
	private Prefixes prefixes;
	private String id;
	private Map<String,Set<String>> links;
	private int size;
	
	public ObjectPropertyGraph(String i) {
		intern = new Internment<String>();
		prefixes = Prefixes.DEFAULT;
		id = prefixes.contract(i);
		links = new TreeMap<String,Set<String>>();
		size = 0;
	}
	
	public ObjectPropertyGraph(Repository rep, URI u) throws RepositoryException { 
		intern = new Internment<String>();
		prefixes = Prefixes.DEFAULT;
		id = prefixes.contract(u.toString());
		links = new TreeMap<String,Set<String>>();
		
		SparqlEndpoint endp = new SparqlEndpoint(rep);
		SesameBindingTable<Value> results = endp.evaluate(
				String.format("select ?x ?y where { ?x <%s> ?y . }",
						u.toString()));
		for(int i = 0; i < results.size(); i++) { 
			Resource xrec = (Resource)results.get(i, "x");
			Value y = results.get(i, "y");
			if(y instanceof Resource) { 
				Resource yrec = (Resource)y;
				addLink(xrec.toString(), yrec.toString());
			}
		}
		endp.dispose();
	}
	
	public String getID() { return id; }
	
	public void addLink(String i1, String i2) { 
		i1 = intern.intern(prefixes.contract(i1)); 
		i2 = intern.intern(prefixes.contract(i2));
		
		if(!links.containsKey(i1)) { 
			links.put(i1, new TreeSet<String>());
		}
		if(!links.get(i1).contains(i2)) { 
			size += 1;
		}
		links.get(i1).add(i2);
	}
	
	public int size() { 
		return size; 
	}
	
	public Set<String> getInverseLinks(String i2) { 
		i2 = prefixes.contract(i2);
		Set<String> i1s = new TreeSet<String>();
		for(String i1 : links.keySet()) { 
			if(links.get(i1).contains(i2)) { 
				i1s.add(i1);
			}
		}
		return i1s;
	}
	
	public Set<String> getLinks(String i1) { 
		i1 = prefixes.contract(i1);
		return links.containsKey(i1) ? 
				links.get(i1) : new TreeSet<String>();
	}
	
	public Set<String> connected(String i1) {
		i1 = prefixes.contract(i1);
		Set<String> connected = new TreeSet<String>();
		
		LinkedList<String> pending = new LinkedList<String>();
		if(links.containsKey(i1)) { 
			pending.addAll(links.get(i1));
		}
		
		while(!pending.isEmpty()) { 
			String i2 = pending.removeFirst();
			if(!connected.contains(i2)) { 
				connected.add(i2);
				if(links.containsKey(i2)) { 
					pending.addAll(links.get(i2));
				}
			}
		}
		
		return connected;
	}
	
	public boolean isConnected(String i1, String i2) { 
		i1 = prefixes.contract(i1);
		i2 = prefixes.contract(i2);
		
		if(!links.containsKey(i1)) { 
			return false;
		}
		
		if(links.get(i1).contains(i2)) { 
			return true;
		}
		
		Set<String> visited = new TreeSet<String>();
		LinkedList<String> pending = new LinkedList<String>();
		pending.addLast(i1);

		while(!pending.isEmpty()) { 
			String ni = pending.removeFirst();
			if(ni.equals(i2)) { return true; }
			visited.add(ni);
			if(links.containsKey(ni)) { 
				Set<String> next = links.get(ni);
				for(String n : next) { 
					if(!visited.contains(n)) { 
						pending.addLast(n);
					}
				}
			}
		}
		
		return false;
	}
}
