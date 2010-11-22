package tdanford.db;

public class Filter implements Op {
	
	private TuplePredicate predicate;
	private Op inner;
	private Tuple next;
	
	public Filter(Op itr, TuplePredicate p) { 
		predicate = p;
		setSubOp(0, itr);
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

	public Op getSubOp(int i) {
		return i == 0 ? inner : null;
	}

	public int length() {
		return 1;
	}

	public void setSubOp(int i, Op j) {
		if(i != 0) { throw new IllegalArgumentException(); }
		inner = j;
		next = null;
		findNextMatch();
	}
}
