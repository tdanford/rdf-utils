package tdanford.db.indexing;

import java.io.File;

public class HashIndex implements Index {

	private File file;
	private HashFunction hasher;
	
	public HashIndex(File f, String hashKey, HashFunction hashFunction) { 
		
	}
	
	public HashFunction getHashFunction() { return hasher; }

	public File file() {
		return file;
	}

	public int indexOffset(Object... values) {
		return 0;
	}
}
