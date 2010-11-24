package tdanford.db.ops;

import java.util.*;
import java.io.*;

import tdanford.db.CostModel;
import tdanford.db.Schema;
import tdanford.db.Tuple;
import tdanford.db.itrs.DbItr;
import tdanford.db.itrs.FileScanItr;

public class FileScan implements Op {
	
	private File file;
	private Schema schema;
	
	public FileScan(Schema s, File f) { 
		file = f;
		schema = s;
	}
	
	public DbItr evalOp() { return scan(); }

	public DbItr scan() {
		try {
			return new FileScanItr(file, schema);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public Schema schema() {
		return schema;
	}
	
	public int cost(CostModel model) { 
		return model.scan() + (int)(file.length() / model.throughput());
	}
}

