package tdanford.db;

public class Select implements Op {
	
	private Op inner;
	private Schema schema;
	private String[] keys;
	
	public Select(Op itr, String... ss) { 
		keys = ss.clone();
		setSubOp(0, itr);
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

	public Op getSubOp(int i) {
		return i==0 ? inner : null;
	}

	public int length() {
		return 1;
	}

	public void setSubOp(int i, Op j) {
		if(i != 0) { throw new IllegalArgumentException(); }
		inner = j;
		schema = inner.schema().subset(keys);
	}

}
