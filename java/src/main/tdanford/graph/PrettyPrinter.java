package tdanford.graph;

import java.io.PrintStream;

public class PrettyPrinter {
	
	public static String SPACER = "  ";

	private int depthToSearch;
	private int indent;
	private PropertyGraph graph;
	private Identifier id;
	
	public PrettyPrinter(PropertyGraph graph, Identifier id, int indent, int depthToSearch) { 
		this.depthToSearch = depthToSearch;
		this.indent = indent;
		this.graph = graph;
		this.id = id;
	}
	
	public void output(PrintStream ps) { 
		String spacing = indent();
		ps.println(String.format("%s%s", spacing, id.toString()));
	}
	
	public String indent() { 
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < indent; i++) { 
			sb.append(SPACER);
		}
		return sb.toString();
	}
}
