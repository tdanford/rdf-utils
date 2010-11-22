package tdanford.db;

import java.util.*;

public class InMemoryJoin implements Op {

	private Op left, right;
	private Schema leftSchema, rightSchema;
	private Schema schema;
	
	private JoinPredicate predicate;
	
	private ArrayList<Tuple> rightTuples;
	private LinkedList<Tuple> pending;
	
	public InMemoryJoin(Op left, Op right, JoinPredicate p) { 
		predicate = p;
		setSubOp(0, left);
		setSubOp(1, right);
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

	public Op getSubOp(int i) {
		if(i==0) { 
			return left;
		} else if (i==1) { 
			return right;
		} else { 
			return null;
		}
	}

	public int length() {
		return 2;
	}

	public void setSubOp(int i, Op j) {
		if(i == 0) { 

			left = j;
			leftSchema = left.schema();
			schema = leftSchema.append(rightSchema);

			pending = new LinkedList<Tuple>();
			findNextPending();

		} else if (i == 1) { 

			right = j;
			rightSchema = right.schema();
			schema = leftSchema.append(rightSchema);

			rightTuples = new ArrayList<Tuple>();
			while(right.hasNext()) { 
				rightTuples.add(right.next());
			}
			right.close();

			pending = new LinkedList<Tuple>();
			findNextPending();

		} else { 
			throw new IllegalArgumentException();
		}
	}
}
