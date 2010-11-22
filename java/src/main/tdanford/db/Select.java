package tdanford.db;

public class Select implements Op {
	
	private Op subOp;
	private String[] keys;
	private Schema schema;

	public Select(Op op, String... ss) {
		keys = ss.clone();
		subOp = op;
		schema = subOp.schema().subset(keys);
	}

	public Schema schema() {
		return schema;
	}
	
	public DbItr evalOp() { return new SelectItr(); }

	public Op getSubOp(int i) {
		return i==0 ? subOp : null;
	}

	public int length() {
		return 1;
	}

	public void setSubOp(int i, Op j) {
		if(i != 0) { throw new IllegalArgumentException(); }
		subOp = j;
		schema = subOp.schema().subset(keys);
	}

	private class SelectItr implements DbItr { 
		private DbItr inner;

		public void reset() { 
			inner.reset();
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

}
