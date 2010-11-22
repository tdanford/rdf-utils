package tdanford.db.itrs;

import java.util.Iterator;

import tdanford.db.Schema;
import tdanford.db.Tuple;

public interface DbItr extends Iterator<Tuple> {
	public void close();
	public Schema schema(); 
}
