package tdanford.db;

import java.util.Iterator;

public interface DbItr extends Iterator<Tuple> {
	public void close();
	public Schema schema(); 
}
