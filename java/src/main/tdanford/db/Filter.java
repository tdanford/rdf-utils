package tdanford.db;

public class Filter implements Op {
	
	private TuplePredicate predicate;
	private Op subOp;
	private Schema schema;
	
	public Filter(Op sub, TuplePredicate p) { 
		predicate  =p;
		subOp = sub;
		schema = subOp.schema();
	}
	
	public Op getSubOp(int i) {
		return i == 0 ? subOp : null;
	}

	public int length() {
		return 1;
	}

	public void setSubOp(int i, Op j) {
		if(i != 0) { throw new IllegalArgumentException(); }
		subOp = j;
		schema = subOp.schema();
	}
	
	public DbItr evalOp() { return new FilterItr(); }
	
	public Schema schema() { return schema; }

	private class FilterItr implements DbItr {
		
		private DbItr inner;
		private Tuple next;
		
		public FilterItr() { 
			inner = subOp.evalOp();
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

		public void reset() { 
			inner.reset();
		}

		public void close() {
			inner.close();
			next = null;
			inner = null;
		}

		public Schema schema() {
			return inner.schema();
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
}
