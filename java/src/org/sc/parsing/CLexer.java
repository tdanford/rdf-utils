package org.sc.parsing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class CLexer implements Iterator<CLex> {
	
	private PushbackIterator<CToken> tokens;
	private LinkedList<CLex> pending;
	
	public CLexer(Iterator<CToken> t) { 
		tokens = new PushbackIterator<CToken>(t);
		pending = new LinkedList<CLex>();
		findNextLex();
	}

	public void remove() { throw new UnsupportedOperationException(); }

	public boolean hasNext() {
		return !pending.isEmpty();
	}

	private void findNextLex() { 
		lexloop: while(tokens.hasNext()) {
	
			StringBuilder v = new StringBuilder();
			Set<CLex.CTag> tags = new HashSet<CLex.CTag>();
			CToken token = tokens.next();
	
			switch(token.type) { 
			case CHAR:
				v.append(token.value);
				while(tokens.hasNext() && (token = tokens.next()).type.equals(CToken.CType.CHAR)) {  
					v.append(token.value);
				}
				if(!token.type.equals(CToken.CType.CHAR)) { 
					tokens.pushback(token);
				}
				
				pending.addLast(new CLex(v.toString()));
				break lexloop;

			case SPACE:
				while(tokens.hasNext() && 
						(token = tokens.next()).type.equals(CToken.CType.SPACE)) { 
				}
				if(!token.type.equals(CToken.CType.SPACE)) { 
					tokens.pushback(token);
				}
				break;

			case QUOTE:
				while(tokens.hasNext() && !(token = tokens.next()).type.equals(CToken.CType.QUOTE)) {  
					if(token.type.equals(CToken.CType.ESCAPE)) {
						if(tokens.hasNext()) { 
							token = tokens.next();
						} else { 
							throw new IllegalArgumentException("Can't end a stream with an escape!");
						}
					}
					v.append(token.value);
				}
				pending.addLast(new CLex(v.toString()));
				break lexloop;
				
			case ESCAPE:
				throw new IllegalArgumentException("No escape marks outside of quotes!");
				
			case GROUPSTART:
				pending.addLast(new CLex(String.valueOf(token.value), CLex.CTag.GROUPSTART));
				break lexloop;
				
			case GROUPEND:
				pending.addLast(new CLex(String.valueOf(token.value), CLex.CTag.GROUPEND));
				break lexloop;
			}
		}
	}

	public CLex next() {
		CLex l = pending.removeFirst();
		if(pending.isEmpty()) { findNextLex(); }
		return l;
	} 

}