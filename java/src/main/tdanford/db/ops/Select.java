package tdanford.db.ops;

import tdanford.db.CostModel;
import tdanford.db.Schema;
import tdanford.db.itrs.DbItr;
import tdanford.db.itrs.SelectItr;

public class Select implements Op {
	
	private Op subOp;
	private String[] keys;

	public Select(Op op, String... ss) {
		keys = ss.clone();
		subOp = op;
	}

	public Schema schema() {
		return subOp.schema().subset(keys);
	}
	
	public DbItr evalOp() { return new SelectItr(schema(), subOp); }

	public Op getSubOp(int i) {
		return i==0 ? subOp : null;
	}

	public int length() {
		return 1;
	}

	public void setSubOp(int i, Op j) {
		if(i != 0) { throw new IllegalArgumentException(); }
		subOp = j;
	}
	
	public int cost(CostModel model) { 
		return subOp.cost(model);
	}
}

