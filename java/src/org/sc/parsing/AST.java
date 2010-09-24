package org.sc.parsing;

import java.util.*;

public class AST {
	
	public static AST parse(String input) { 
		ASTParserHandler handler = new ASTParserHandler();
		new CParser(input, handler).parse();
		return handler.getAST();
	}

	private String token;
	private ArrayList<AST> children;
	
	public AST(String t) { token = t; children= null; }
	
	public AST(Collection<AST> cs) { 
		token = null;
		children = new ArrayList<AST>(cs);
	}
	
	public AST(AST c, AST... cs) { 
		token = null;
		children = new ArrayList<AST>();
		children.add(c);
		for(int i = 0; i < cs.length; i++) { children.add(cs[i]); }
	}
	
	public boolean isLeaf() { return token != null; }
	
	public String getToken() { return token; }
	
	public int size() { return children.size(); }
	public AST child(int i) { return children.get(i); }
	
	public String toString() { 
		if(token != null) { 
			return String.format("[%s]", token);
		} else { 
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for(AST child : children) { 
				if(sb.length() > 1) { 
					sb.append(" ");
				}
				sb.append(child.toString());
			}
			sb.append(")");
			return sb.toString();
		}
	}
}
