package tdanford.db.itrs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import tdanford.db.Schema;
import tdanford.db.Tuple;

public class FileScanItr implements DbItr {

	private File file;
	private Schema schema;
	private RandomAccessFile lineReader;
	private Tuple nextTuple;
	
	public FileScanItr(File f, Schema s) throws IOException {
		schema = s;
		file = f;
		lineReader = new RandomAccessFile(file, "r");
		readToNextTuple();
	}
	
	public long offset() throws IOException { 
		return lineReader.getFilePointer();
	}
	
	private void readToNextTuple() throws IOException { 
		nextTuple = null;
		String line = lineReader.readLine();
		if(line != null) { 
			String[] array = line.split("|");
			Tuple t = new Tuple(array, schema);
			nextTuple = t;
		}
	}

	public void reset() { 
		try { 
			if(lineReader != null) { lineReader.close(); } 
			lineReader = new RandomAccessFile(file, "r");
			readToNextTuple();
		} catch(IOException e) { 
			throw new IllegalStateException(e);
		}
	}

	public void close() {
		try {
			lineReader.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		lineReader = null;
	}

	public boolean hasNext() {
		return nextTuple != null;
	}

	public Tuple next() {
		Tuple t = nextTuple;
		try {
			readToNextTuple();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return t;
	}
	
	public Schema schema() { return schema; }

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
