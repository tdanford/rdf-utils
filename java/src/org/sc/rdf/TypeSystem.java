package org.sc.rdf;

import java.util.*;
import java.io.*;

public class TypeSystem {
	
	private Prefixes prefixes;
	private Map<String,Type> types;
	
	public TypeSystem() {
		prefixes = Prefixes.DEFAULT;
		types = new TreeMap<String,Type>();
	}
	
	public void addTypes(String... ids) { 
		for(int i = 0; i < ids.length; i++) {
			String id = prefixes.contract(ids[i]);
			if(!types.containsKey(id)) { 
				types.put(id, new Type(id));
			}
		}
	}
	
	public Set<String> getSuperTypes(String id) { 
		id = prefixes.contract(id);
		Set<Type> ts = types.get(id).superTypes();
		Set<String> tns = new TreeSet<String>();
		for(Type t : ts) { tns.add(t.id); }
		return tns;
	}
	
	public Set<String> getSubTypes(String id) { 
		id = prefixes.contract(id);
		Set<Type> ts = types.get(id).subTypes();
		Set<String> tns = new TreeSet<String>();
		for(Type t : ts) { tns.add(t.id); }
		return tns;
	}
	
	public void addSubtyping(String t1, String t2) {
		t1 = prefixes.contract(t1);
		t2 = prefixes.contract(t2);
		if(!types.containsKey(t1) || !types.containsKey(t2)) { 
			throw new IllegalArgumentException(t1 + "," + t2);
		}
		types.get(t1).supertypes.add(types.get(t2));
		types.get(t2).subtypes.add(types.get(t1));
		//System.out.println(String.format("%s < %s", t1, t2));
		
		if(types.get(t1).hasSuperType(types.get(t1))) { 
			types.get(t1).supertypes.remove(types.get(t2));
			types.get(t2).subtypes.remove(types.get(t1));
			throw new IllegalArgumentException("Can't add circular typing rule!");
		}
	}
	
	public boolean isSubtype(String t1, String t2) {
		t1 = prefixes.contract(t1);
		t2 = prefixes.contract(t2);
		if(!types.containsKey(t1) || !types.containsKey(t2)) { 
			return false;
		}
		return types.get(t1).hasSuperType(types.get(t2));
	}

	public void printSuperTypeTree(String t) { 
		printSuperTypeTree(t, System.out); 
	}
	
	public void printSuperTypeTree(String t, PrintStream ps) { 
		t = prefixes.contract(t);
		if(types.containsKey(t)) { 
			types.get(t).printSuperTypeTree(0, ps);
		} else { 
			System.out.println(String.format("%s?", t));
		}
	}

	public void printSubTypeTree(String t) { 
		printSubTypeTree(t, System.out); 
	}
	
	public void printSubTypeTree(String t, PrintStream ps) { 
		t = prefixes.contract(t);
		if(types.containsKey(t)) { 
			types.get(t).printSubTypeTree(0, ps);
		} else { 
			System.out.println(String.format("%s?", t));
		}
	}

	private static class Type implements Comparable<Type> {
		public String id;
		public Set<Type> supertypes;
		public Set<Type> subtypes;
	
		public Type(String i) { 
			id = i;
			supertypes = new TreeSet<Type>();
			subtypes = new TreeSet<Type>();
		}
		
		public void printSuperTypeTree(int indent, PrintStream ps) { 
			for(int i = 0; i < indent; i++) { 
				ps.print("  ");
			}
			ps.println(id);
			for(Type t : supertypes) { 
				t.printSuperTypeTree(indent+1, ps);
			}
		}
		
		public void printSubTypeTree(int indent, PrintStream ps) { 
			for(int i = 0; i < indent; i++) { 
				ps.print("  ");
			}
			ps.println(id);
			for(Type t : subtypes) { 
				t.printSubTypeTree(indent+1, ps);
			}
		}
		
		public Set<Type> superTypes() { 
			Set<Type> ts = new TreeSet<Type>();
			accSuperTypes(ts);
			return ts;
		}
		
		public Set<Type> subTypes() { 
			Set<Type> ts = new TreeSet<Type>();
			accSubTypes(ts);
			return ts;
		}
		
		private void accSuperTypes(Set<Type> acc) { 
			for(Type t : supertypes) { 
				if(!acc.contains(t)) { 
					acc.add(t);
					t.accSuperTypes(acc);
				}
			}
		}
		
		private void accSubTypes(Set<Type> acc) { 
			for(Type t : subtypes) { 
				if(!acc.contains(t)) { 
					acc.add(t);
					t.accSubTypes(acc);
				}
			}
		}
		
		public boolean hasSuperType(Type t) { 
			if(supertypes.contains(t)) { return true; }
			Set<Type> visited = new TreeSet<Type>();
			visited.add(this);
			return hasSuperType(visited, t);
		}

		public boolean hasSubType(Type t) { 
			return t.hasSuperType(this);
		}
		
		private boolean hasSuperType(Set<Type> visited, Type t) { 
			for(Type tt : supertypes) { 
				if(!visited.contains(tt)) { 
					Set<Type> nvisited = new TreeSet<Type>(visited);
					nvisited.add(tt);
					if(tt.hasSuperType(nvisited, t)) { 
						return true;
					}
				}
			}
			return false;
		}
		
		public int compareTo(Type t) { 
			return id.compareTo(t.id);
		}
		
		public int hashCode() { return id.hashCode(); }
		
		public boolean equals(Object o) { 
			if(!(o instanceof Type)) { return false; }
			Type t = (Type)o;
			return id.equals(t.id);
		}
	}
}
