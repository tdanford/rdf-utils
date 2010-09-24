package org.sc.rdf;

import java.util.*;

public class TypeAssignments {
	
	private Prefixes prefixes;
	private Set<String> types;
	private Map<String,Set<String>> assignments;

	public TypeAssignments() {
		prefixes = Prefixes.DEFAULT;
		types = new TreeSet<String>();
		assignments = new TreeMap<String,Set<String>>();
	}
	
	public void addAssignment(String individual, String type) { 
		individual = prefixes.contract(individual);
		type = prefixes.contract(type);
		
		if(!assignments.containsKey(individual)) { 
			assignments.put(individual, new TreeSet<String>());
		}
		assignments.get(individual).add(type);
		types.add(type);
		//System.out.println(String.format("%s -> %s", individual, type));
		
		if(!hasAssertedType(individual, type)) { 
			throw new IllegalStateException(individual + "," + type);
		}
	}
	
	public boolean hasAssertedType(String idv, String type) { 
		idv = prefixes.contract(idv);
		type = prefixes.contract(type);
		return assignments.containsKey(idv) && 
			assignments.get(idv).contains(type);
	}
	
	public boolean hasInferredType(String idv, String type, TypeSystem system) { 
		idv = prefixes.contract(idv);
		type = prefixes.contract(type);
		
		if(assignments.containsKey(idv)) { 
			if(assignments.get(idv).contains(type)) { return true; }
			for(String asserted : assignments.get(idv)) { 
				if(system.isSubtype(asserted, type)) { 
					return true;
				}
			}
		}
		return false;
	}
	
	public Set<String> getTypes() { return types; }
	public Set<String> getIndividuals() { return assignments.keySet(); }
	
	public Set<String> getTypes(String individual) {
		individual = prefixes.contract(individual);
		return assignments.containsKey(individual) ? 
				assignments.get(individual) : 
				new TreeSet<String>();
	}
	
	public Set<String> getInferredTypes(String idv, TypeSystem system) { 
		idv = prefixes.contract(idv);
		Set<String> typs = new TreeSet<String>();
		if(assignments.containsKey(idv)) { 
			typs.addAll(assignments.get(idv));
			for(String asserted : assignments.get(idv)) { 
				typs.addAll(system.getSuperTypes(asserted)); 
			}
		}
		return typs;
	}
	
	public int countIndividuals(String type) {
		type = prefixes.contract(type);
		int count = 0;
		for(String idv : assignments.keySet()) { 
			if(assignments.get(idv).contains(type)) { 
				count += 1;
			}
		}
		return count;
	}
	
	public int countInferredIndividuals(String type, TypeSystem system) {
		type = prefixes.contract(type);
		int count = 0;
		for(String idv : assignments.keySet()) { 
			if(hasInferredType(idv, type, system)) { 
				count += 1;
			}
		}
		return count;
	}
	
	public Set<String> getIndividuals(String type) {
		type = prefixes.contract(type);
		TreeSet<String> indivs = new TreeSet<String>();
		for(String idv : assignments.keySet()) { 
			if(assignments.get(idv).contains(type)) { 
				indivs.add(idv);
			}
		}
		return indivs;
	}

	public Set<String> getInferredIndividuals(String type, TypeSystem system) {
		type = prefixes.contract(type);
		TreeSet<String> indivs = new TreeSet<String>();
		for(String idv : assignments.keySet()) { 
			if(hasInferredType(idv, type, system)) { 
				indivs.add(idv);
			}
		}
		return indivs;
	}
}
