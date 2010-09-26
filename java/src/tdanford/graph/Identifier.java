package tdanford.graph;

import java.net.URI;

public class Identifier implements Value, Comparable<Identifier> { 
	
	private String id;
	
	public Identifier(URI u) { 
		this(u.toString());
	}
	
	public Identifier(String str) { 
		id = str;
	}
	
	public String toString() { return id; }
	
	public int hashCode() { return id.hashCode(); }
	
	public boolean equals(Object o) { 
		return ((o instanceof Identifier)) && 
			id.equals(((Identifier)o).id);
	}
	
	public int compareTo(Identifier ident) { 
		return id.compareTo(ident.id);
	}
}

