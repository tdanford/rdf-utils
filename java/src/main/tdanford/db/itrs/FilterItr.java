package tdanford.db.itrs;

import tdanford.db.Schema;
import tdanford.db.Tuple;
import tdanford.db.TuplePredicate;
import tdanford.db.ops.Op;

public class FilterItr implements DbItr {
	
	private DbItr inner;
	private Tuple next;
	private Schema schema;
	private TuplePredicate predicate;
	
	public FilterItr(Op subOp, Schema s, TuplePredicate p) {
		schema = s;
		inner = subOp.evalOp();
		predicate = p;
		findNextMatch();
	}
	
	private void findNextMatch() {
		next = null;
		Tuple t = null;
		while(t == null && inner.hasNext()) { 
			if(!predicate.accepts(t)) { 
				t = null;
			}
		}
		next = t;
	}

	public void close() {
		inner.close();
		next = null;
		inner = null;
	}

	public Schema schema() {
		return schema;
	}

	public boolean hasNext() {
		return next != null;
	}

	public Tuple next() {
		Tuple t = next;
		findNextMatch();
		return t;
	}

	public void remove() {
		throw new UnsupportedOperationException("remove");
	}

}