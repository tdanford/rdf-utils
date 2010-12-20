package tdanford.rdf.sparql.iterators;

import java.util.Collection;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class DeletionIterator extends ModificationIterator { 
	
	public DeletionIterator(Collection<Triple> ts, String g, boolean isVirtuoso) { 
		super("DELETE", new TriplesToN3BlockIterator(ts.iterator()), g, isVirtuoso);
	}
	
	public DeletionIterator(Model m, String g, boolean isVirtuosoEndpoint) { 
		this(m.listStatements(), g, isVirtuosoEndpoint);
	}
	public DeletionIterator(ExtendedIterator<Statement> stmts, String g, boolean isVirtuosoEndpoint) { 
		super("DELETE", new StatementsToN3BlockIterator(stmts), g, isVirtuosoEndpoint);
	}
}