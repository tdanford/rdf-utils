package tdanford.rdf.sparql.iterators;

import java.util.Collection;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class InsertionIterator extends ModificationIterator { 
	
	public InsertionIterator(Collection<Triple> ts, String g, boolean isVirtuoso) { 
		super("INSERT", new TriplesToN3BlockIterator(ts.iterator()), g, isVirtuoso);
	}

	public InsertionIterator(Model m, String g, boolean isVirtuosoEndpoint) { 
		this(m.listStatements(), g, isVirtuosoEndpoint);
	}

	public InsertionIterator(ExtendedIterator<Statement> stmts, String g, boolean isVirtuosoEndpoint) { 
		super("INSERT", new StatementsToN3BlockIterator(stmts), g, isVirtuosoEndpoint);
	}
}