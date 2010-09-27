package tdanford.graph;

import java.util.*;

public class GraphSet {

	private Set<Identifier> ids;

	public GraphSet(Identifier... is) { 
		this.ids = new TreeSet<Identifier>(Arrays.asList(is));
	}
	
	public GraphSet(GraphSet s) { 
		this.ids = new TreeSet<Identifier>(s.ids);
	}
	
	
}
