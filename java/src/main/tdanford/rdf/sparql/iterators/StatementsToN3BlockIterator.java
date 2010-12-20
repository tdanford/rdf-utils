package tdanford.rdf.sparql.iterators;

import java.io.StringWriter;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.NiceIterator;

public class StatementsToN3BlockIterator extends NiceIterator<String> {
	
	public static int blockSize = 100;
	
	private ExtendedIterator<Statement> stmts;
	
	public StatementsToN3BlockIterator(ExtendedIterator<Statement> stmts) { 
		this.stmts = stmts;
	}

	public void close() { 
		stmts.close();
	}
	
	public boolean hasNext() {
		return stmts.hasNext();
	}
	
	public String next() {
		return nextModelTripleString();
	}
	
	private String nextModelTripleString() {
		StringBuilder sb = new StringBuilder();
		Model tempModel = ModelFactory.createDefaultModel();
		for(int i = 0; i < blockSize && stmts.hasNext(); i++) { 
			Statement stmt = stmts.next();
			tempModel.add(stmt);
		}
	
		StringWriter writer = new StringWriter();
		tempModel.write(writer, "N-TRIPLE", null);
		
		return writer.toString();
	}
}
