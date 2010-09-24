package org.sc.parsing;

import java.util.Iterator;

public class CParser { 
	
	private Iterator<CLex> lexitr;
	private ParserHandler handler;
	
	public CParser(String input, ParserHandler h) { 
		this(new CLexer(new CTokenizer(input)), h);
	}
	
	public CParser(CLexer lexer, ParserHandler h) { 
		lexitr = lexer;
		handler = h;
	}
	
	public void parse() { 
		int groupDepth = 0;
		while(lexitr.hasNext()) { 
			CLex lex = lexitr.next();
			if(lex.tags.contains(CLex.CTag.GROUPSTART)) {
				groupDepth += 1;
				handler.handleGroupStart();
			} else if (lex.tags.contains(CLex.CTag.GROUPEND)) {
				groupDepth -= 1;
				if(groupDepth < 0) { 
					throw new IllegalArgumentException("Extra group-end token.");
				}
				handler.handleGroupEnd();
			} else if (lex.tags.contains(CLex.CTag.QUOTED)) { 
				handler.handleQuotedValue(lex.value);
			} else { 
				handler.handleValue(lex.value);
			}
		}
		if(groupDepth != 0) { 
			throw new IllegalArgumentException("Unclosed group.");
		}
	}
}