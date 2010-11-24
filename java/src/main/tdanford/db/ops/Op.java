package tdanford.db.ops;

import tdanford.db.CostModel;
import tdanford.db.Schema;
import tdanford.db.itrs.DbItr;

public interface Op {
	
	public Schema schema();
	
	public DbItr evalOp();
	
	public int cost(CostModel model);	
}
