package org.sc.rdf.sesame;

import info.aduna.iteration.CloseableIteration;

import java.util.Iterator;
import org.openrdf.model.Statement;
import org.openrdf.sail.Sail;
import org.openrdf.sail.SailConnection;
import org.openrdf.sail.SailException;

import tdanford.streaming.HeavyIterator;

public class SailSource implements HeavyIterator<Statement> {
	
	private Sail sail;
	private SailConnection cxn;
	private CloseableIteration<? extends Statement,SailException> itr;
	
	public SailSource(Sail s) throws SailException { 
		sail = s;
		cxn = sail.getConnection();
		itr = cxn.getStatements(null, null, null, true);  // true="we want inferred statements." 
	}

	public boolean hasNext() {
		try {
			return itr.hasNext();
		} catch (SailException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Statement next() {
		try {
			return itr.next();
		} catch (SailException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void remove() {
		try {
			itr.remove();
		} catch (SailException e) {
			e.printStackTrace();
		}
	}

	public void dispose() {
		try {
			itr.close();
			cxn.close();

		} catch (SailException e) {
			e.printStackTrace();
		
		} finally {
			itr = null;
			cxn = null;
			sail = null;  // don't shut down the sail -- we might not own it.
		}
	}
}
