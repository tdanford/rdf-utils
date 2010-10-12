package tdanford.graph;

public class Literal implements Value { 
	
	private Object obj; 
	
	public Literal(Object obj) { 
		this.obj = obj;
	}
	
	public String toString() { return String.valueOf(obj); }
	
	public int hashCode() { return obj.hashCode(); }
	
	public boolean equals(Object o) { 
		if(!(o instanceof Literal)) { return false; }
		Literal l = (Literal)o;
		return l.obj.equals(obj);
	}
}
