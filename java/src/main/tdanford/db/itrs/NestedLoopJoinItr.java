package tdanford.db.itrs;

import java.util.ArrayList;
import java.util.LinkedList;

import tdanford.db.JoinPredicate;
import tdanford.db.Schema;
import tdanford.db.Tuple;
import tdanford.db.ops.Op;

public class NestedLoopJoinItr implements DbItr {
	
	private Schema schema;
	
	private DbItr left;
	
	private ArrayList<Tuple> rightTuples;
	
	private LinkedList<Tuple> pending;
	
	private JoinPredicate predicate;
	
	public NestedLoopJoinItr(Op leftOp, Op rightOp, JoinPredicate p) {
		predicate = p;
		schema = leftOp.schema().append(rightOp.schema());
		
		left = leftOp.evalOp();
		
		DbItr right = rightOp.evalOp();

		rightTuples = new ArrayList<Tuple>();
		while(right.hasNext()) { 
			rightTuples.add(right.next());
		}
		right.close();
		
		pending = new LinkedList<Tuple>();
		findNextPending();
	}

	public void close() {
		left.close();
	}
	
	public Schema schema() {
		return schema;
	}
	
	public boolean hasNext() {
		return !pending.isEmpty();
	}
	
	private void findNextPending() { 
		while(pending.isEmpty() && left.hasNext()) { 
			Tuple t1 = left.next();
			for(Tuple t2 : rightTuples) { 
				if(predicate.shouldJoin(t1, t2)) { 
					pending.addLast(t1.append(schema, t2));
				}
			}
		}
	}
	
	public Tuple next() {
		Tuple next = pending.removeFirst();
		if(pending.isEmpty()) { 
			findNextPending();
		}
		return next;
	}

	public void remove() {
		throw new UnsupportedOperationException("remove");
	}
}		