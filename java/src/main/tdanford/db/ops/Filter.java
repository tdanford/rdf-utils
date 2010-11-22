package tdanford.db.ops;

import tdanford.db.CostModel;
import tdanford.db.Schema;
import tdanford.db.TuplePredicate;
import tdanford.db.itrs.DbItr;
import tdanford.db.itrs.FilterItr;

public class Filter implements Op {
	
	private TuplePredicate predicate;
	private Op subOp;
	
	public Filter(Op sub, TuplePredicate p) { 
		predicate  =p;
		subOp = sub;
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
	}
	
	public int cost(CostModel model) { 
		return subOp.cost(model);
	}
	
	public DbItr evalOp() { return new FilterItr(subOp, schema(), predicate); }
	
	public Schema schema() { return subOp.schema(); }

}

