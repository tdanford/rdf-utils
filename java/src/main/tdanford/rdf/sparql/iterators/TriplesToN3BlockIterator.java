package tdanford.rdf.sparql.iterators;

import java.util.Iterator;

import com.hp.hpl.jena.graph.*;
import com.hp.hpl.jena.util.iterator.*;

public class TriplesToN3BlockIterator extends NiceIterator<String> {
	
	public static int blockSize = 100;
	
	private Iterator<Triple> stmts;
	
	public TriplesToN3BlockIterator(Iterator<Triple> stmts) { 
		this.stmts = stmts;
	}

	public void close() { 
	}
	
	public boolean hasNext() {
		return stmts.hasNext();
	}
	
	public String next() {
		return nextModelTripleString();
	}
	
	private String nextModelTripleString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < blockSize && stmts.hasNext(); i++) {
			Triple t = stmts.next();
			String tstr = createN3Triple(t);
			sb.append(String.format("%s . ", tstr));
		}
		return sb.toString();
	}
	
	public String createN3Triple(Triple t) { 
		String squery = t.getSubject() != null ? 
				String.format("<%s>", t.getSubject().toString()) : null; 
		String pquery = t.getPredicate() != null ? 
				String.format("<%s>", t.getPredicate().toString()) : null; 
		String oquery = t.getObject() != null ? 
				String.format("<%s>", t.getObject().toString()) : null;
				String condition = String.format("%s %s %s", squery, pquery, oquery);
		if(squery==null || pquery==null || oquery==null) { 
			throw new IllegalArgumentException(condition);
		}
		return condition;		
	}
}
