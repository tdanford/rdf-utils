package org.sc.rdf.sesame;

import java.util.Iterator;

import org.openrdf.model.Statement;
import org.openrdf.sail.Sail;
import org.openrdf.sail.SailConnection;
import org.openrdf.sail.SailException;

import tdanford.streaming.HeavyIterator;
import tdanford.streaming.Sink;

/**
 * Drops Statements into a Sail.  
 * 
 * @author tdanford
 */
public class SailSink implements Sink<Statement> {
	
	private Sail sail;
	
	public SailSink(Sail s) { 
		sail = s;
	}
	
	public void consume(Iterator<Statement> itr) { 
		consume(new HeavyIterator.Wrapper<Statement>(itr));
	}

	public void consume(HeavyIterator<Statement> itr) {
		SailConnection cxn = null;
		try { 
			cxn = sail.getConnection();
			while(itr.hasNext()) { 
				Statement stmt = itr.next();
				cxn.addStatement(
						stmt.getSubject(), 
						stmt.getPredicate(), 
						stmt.getObject(), 
						stmt.getContext());
			}
		
		} catch(SailException e) { 
			e.printStackTrace(System.err);

		} finally { 
			if(cxn != null) { 
				try {
					cxn.close();
				} catch (SailException e) {
					e.printStackTrace();
				}
			}
			
			itr.dispose();
		}
	}
	
	public Sail getSail() { 
		return sail;
	}

	public void dispose() {
		// don't shut the sail down -- we might not have ownership of it.
		sail = null;
	}

}
