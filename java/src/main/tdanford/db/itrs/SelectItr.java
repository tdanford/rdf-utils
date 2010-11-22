package tdanford.db.itrs;

import tdanford.db.Schema;
import tdanford.db.Tuple;
import tdanford.db.ops.Op;

public class SelectItr implements DbItr { 
	
	private DbItr inner;
	
	private Schema schema;
	
	public SelectItr(Schema s, Op op) { 
		inner = op.evalOp();
		schema = s;
	}

	public void close() {
		inner.close();
	}

	public Schema schema() {
		return schema;
	}

	public boolean hasNext() {
		return inner.hasNext();
	}

	public Tuple next() {
		return inner.next().subset(schema);
	}

	public void remove() {
		throw new UnsupportedOperationException("remove");
	}
}
