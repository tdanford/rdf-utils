package tdanford.db.indexing;

import java.io.File;

public interface Index {
	
	public File file();
	
	public int indexOffset(Object... values);

}
