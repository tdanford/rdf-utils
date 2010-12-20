package tdanford.rdf.sparql.iterators;

import java.io.StringWriter;

import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class ModificationIterator {
	
	private ExtendedIterator<String> stmts;

	private String graph;
	private String command;
	private boolean isVirtuosoEndpoint;
	
	public ModificationIterator(String cmd, ExtendedIterator<String> stmts, String graph, boolean isVirtuoso) {
		this.command = cmd;
		this.stmts = stmts;
		this.graph = graph;
		this.isVirtuosoEndpoint = isVirtuoso;
	}
	
	public boolean isFinished() { 
		return !stmts.hasNext();
	}
	
	public void close() { 
		stmts.close();
	}
	
	public String nextStatement() { 
		return String.format(
				"%s DATA %s{ %s }",
				command,
				(graph != null 
						? String.format("INTO %s<%s> ", 
								(isVirtuosoEndpoint ? "GRAPH " : ""), graph)
						: ""), 
				stmts.next());
	}
}