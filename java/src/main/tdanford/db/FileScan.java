package tdanford.db;

import java.util.*;
import java.io.*;

public class FileScan extends Op.Leaf {
	
	private File file;
	private Schema schema;
	
	public FileScan(Schema s, File f) { 
		file = f;
		schema = s;
	}
	
	public DbItr evalOp() { return scan(); }

	public DbItr scan() {
		try {
			return new FileScanner();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public Schema schema() {
		return schema;
	}

	private class FileScanner implements DbItr {
		
		private BufferedReader lineReader;
		private Tuple nextTuple;
		
		public FileScanner() throws IOException { 
			lineReader = new BufferedReader(new FileReader(file));
			readToNextTuple();
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
				lineReader = new BufferedReader(new FileReader(file));
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
}
