package org.sc.parsing;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class CToken { 
	
	public static enum CType { CHAR, SPACE, QUOTE, ESCAPE, GROUPSTART, GROUPEND };
	
	public char value;
	public CType type;
	
	public CToken(CType t, char v) { 
		type = t;
		value = v;
	}
	
	public int hashCode() { 
		int code = 17;
		code += (int)value; code *= 37;
		return code;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof CToken)) { return false; }
		CToken ct = (CToken)o;
		return ct.value==value && ct.type.equals(type);
	}
	
	public String toString() { 
		if(type.equals(CType.CHAR)) { 
			return String.valueOf(value);
		} else if (type.equals(CType.SPACE)) { 
			return " ";
		} else { 
			return String.format("(%s)", type.toString());
		}
	}
}




