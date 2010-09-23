package org.sc.parsing;

import java.util.Iterator;

public class CTokenizer implements Iterator<CToken> {
	
	private String input;
	private int idx;

	public CTokenizer(String i) { 
		input = i;
		idx = 0;
	}

	public boolean hasNext() {
		return idx < input.length();
	}

	public CToken next() {
		char c = input.charAt(idx++);
		CToken.CType type = CToken.CType.CHAR;
		if(Character.isWhitespace(c)) { 
			type = CToken.CType.SPACE;
		} else if (c == '"') { 
			type = CToken.CType.QUOTE;
		} else if (c == '\\') {  
			type = CToken.CType.ESCAPE;
		} else if (c == '(') {  
			type = CToken.CType.GROUPSTART;
		} else if (c == ')') {  
			type = CToken.CType.GROUPEND;
		}
		return new CToken(type, c);
	}

	public void remove() { throw new UnsupportedOperationException(); } 
}