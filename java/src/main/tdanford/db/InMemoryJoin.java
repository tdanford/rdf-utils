package tdanford.db;

import java.util.*;

public class InMemoryJoin implements Op {

	private Op leftOp, rightOp;
	private Schema schema;	
	private JoinPredicate predicate;
	
	public InMemoryJoin(Op left, Op right, JoinPredicate p) { 
		predicate = p;
		leftOp = left;
		rightOp = right;
		schema = leftOp.schema().append(rightOp.schema());
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
	
	public Schema schema() { return schema; }
	public DbItr evalOp() { return new InMemoryJoinItr(); }

	public void setSubOp(int i, Op j) {
		if(i == 0) { 

			leftOp = j;
			schema = leftOp.schema().append(rightOp.schema());

		} else if (i == 1) { 

			rightOp = j;
			schema = leftOp.schema().append(rightOp.schema());

		} else { 
			throw new IllegalArgumentException();
		}
	}

	private class InMemoryJoinItr implements DbItr { 
		
		private DbItr left;
		
		private ArrayList<Tuple> rightTuples;
		
		private LinkedList<Tuple> pending;
		
		public InMemoryJoinItr() { 
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

		public void reset() { 
			left.reset();
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
}
