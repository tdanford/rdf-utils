package tdanford.db.ops;

import java.util.*;

import tdanford.db.CostModel;
import tdanford.db.JoinPredicate;
import tdanford.db.Schema;
import tdanford.db.itrs.DbItr;
import tdanford.db.itrs.NestedLoopJoinItr;

public class InMemoryHashJoin implements Op {

	private Op leftOp, rightOp;
	private String key;
	
	public InMemoryHashJoin(Op left, Op right, String key) { 
		this.key = key;
		leftOp = left;
		rightOp = right;
	}
	
	public Op getSubOp(int i) {
		if(i==0) { 
			return leftOp;
		} else if (i==1) { 
			return rightOp;
		} else { 
			return null;
		}
	}

	public int length() {
		return 2;
	}
	
	public int cost(CostModel model) { 
		return leftOp.cost(model) + rightOp.cost(model);
	}
	
	public Schema schema() { return leftOp.schema().append(rightOp.schema()); }
	public DbItr evalOp() { return new NestedLoopJoinItr(leftOp, rightOp, null); }

	public void setSubOp(int i, Op j) {
		if(i == 0) { 
			leftOp = j;

		} else if (i == 1) { 
			rightOp = j;

		} else { 
			throw new IllegalArgumentException();
		}
	}
}
