package org.sc.parsing;

import java.util.*;

public class ASTParserHandler implements ParserHandler {
	
	private AST toplevel;
	private Stack<LinkedList<AST>> stack;
	
	public ASTParserHandler() {
		toplevel = null;
		stack = new Stack<LinkedList<AST>>();
	}
	
	public AST getAST() { return toplevel; }
	
	public void handleAST(AST a) { 
		if(stack.isEmpty()) { 
			toplevel = a;
		} else { 
			stack.peek().addLast(a);
		}		
	}
	
	public void handleGroupEnd() {
		handleAST(new AST(stack.pop()));
	}

	public void handleGroupStart() {
		stack.push(new LinkedList<AST>());
	}

	public void handleQuotedValue(String v) {
		handleAST(new AST(new AST(v)));
	}

	public void handleValue(String v) {
		handleAST(new AST(v));
	} 	
	
}

