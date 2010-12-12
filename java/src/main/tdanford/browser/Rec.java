package tdanford.browser;

public class Rec implements Comparable<Rec> {
	
	public static enum Type { RESOURCE, LITERAL };

	private boolean isBlank;
	private Type type;
	private String value;
	
	public Rec(String v) { 
		value = v;
		isBlank = false;
		type = Type.RESOURCE;
		
		if(value == null) { throw new IllegalArgumentException("Null value to Rec"); }
		if(value.length() == 0) { throw new IllegalArgumentException("Empty value to Rec"); }
	}
	
	public Rec(String v, boolean b) { 
		this(v);
		isBlank = b;
	}
	
	public Rec(String v, Type t) { 
		this(v);
		isBlank = false;
		type = t;
	}
	
	public int hashCode() { return value.hashCode(); }
	
	public boolean equals(Object o) {
		 if(!(o instanceof Rec)) { return false; }
		 Rec r  =(Rec)o;
		 
		 return type.equals(r.type) && isBlank == r.isBlank && r.value.equals(value);
	}
	
	public String toString() { return value; }

	public int compareTo(Rec r) {
		if(!type.equals(r.type)) { 
			return type.compareTo(r.type);
		}
		return value.compareTo(r.value);
	}

	public boolean isBlank() {
		return isBlank;
	}
	
	public String value() { return value; }
	
	public Type type() { return type; }
}