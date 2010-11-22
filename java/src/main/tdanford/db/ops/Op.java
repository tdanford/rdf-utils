package tdanford.db.ops;

import tdanford.db.CostModel;
import tdanford.db.Schema;
import tdanford.db.itrs.DbItr;

public interface Op {
	
	public int length();
	public Op getSubOp(int i);
	public void setSubOp(int i, Op j);
	
	public Schema schema();
	
	public DbItr evalOp();
	
	public int cost(CostModel model);
	
	public static abstract class Leaf implements Op {
		public Op getSubOp(int i) {
			throw new IllegalArgumentException(String.valueOf(i));
		}

		public int length() {
			return 0;
		}

		public void setSubOp(int i, Op j) {
			throw new IllegalArgumentException(String.valueOf(i));
		}
	}
}
