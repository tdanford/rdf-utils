package org.sc.rdf.sesame;

import java.util.*;

import java.io.*;
import java.net.URI;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.Value;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.RDFParserFactory;
import org.openrdf.rio.rdfxml.RDFXMLParserFactory;
import org.openrdf.rio.turtle.TurtleParserFactory;

import org.sc.rdf.URIRetriever;

/**
 * Turns the RDF parsing capabilities of Sesame (which are presented through a parser and 
 * a Visitor pattern) into an Iterator<Statement>, through the magic of threading.  
 * (This would be easier if Java had co-routines, but nevermind.)  
 * 
 * The RDFIterator runs an internal thread, which loads Statements from the underlying URI 
 * in blocks of read_block_size (= 10,000 by default).  The Iterator is synchronous with the
 * thread, and so after every block, the Iterator will block while the thread parses more RDF.
 * 
 * @author tdanford
 */
public class RDFIterator implements Iterator<Statement> {
	
	private static final int read_block_size = 1000;

	private LinkedList<Statement> stmts;
	private boolean finishedParsing;
	private InputStream inputStream;
	
	public RDFIterator(URI u) {
		URIRetriever retriever = new URIRetriever();
		inputStream = retriever.getInputStream(u);
		finishedParsing = false;
		stmts = new LinkedList<Statement>();
		load(u.toASCIIString(), u);
	}
	
	public RDFIterator(File f, URI base) throws IOException { 
		inputStream = new FileInputStream(f);
		finishedParsing = false;
		stmts = new LinkedList<Statement>();
		load(f.getName(), base);
		inputStream.close();
	}
	
	public RDFIterator(InputStream str, String fmt, URI base) { 
		inputStream = str;
		finishedParsing = false;
		stmts = new LinkedList<Statement>();
		load(fmt, base);		
	}
	
	public synchronized boolean hasNext() {
		//System.out.println("hasNext()");
		if(!finishedParsing && stmts.isEmpty()) { 
			try {
				//System.out.println("Iterator Notifying and Waiting...");
				notifyAll();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("\tIterator Awake!");
		}
		boolean hasMore = !stmts.isEmpty();
		//System.out.println("\t=" + hasMore);
		return hasMore;
	}

	public synchronized Statement next() {
		//System.out.println("next()");
		if(!finishedParsing && stmts.isEmpty()) { 
			try {
				//System.out.println("Iterator Notifying and Waiting...");
				notifyAll();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("\tIterator Awake!");
		}
		Statement stmt = stmts.removeFirst();
		//System.out.println(String.format("\t-> %s", stmt.toString()));
		return stmt;
	}
	
	public void remove() {
		throw new UnsupportedOperationException("can't remove from RDFIterator");
	}
	
	private synchronized void addStatement(Resource s, org.openrdf.model.URI p, Value o, Resource... ctxts) { 
		Statement stmt = new StatementImpl(s, p, o);
		stmts.addLast(stmt);
		
		if(stmts.size() >= read_block_size) { 
			try {
				//System.out.println("Parser Notifying and Waiting...");
				notifyAll();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("\tReader Awake!");
		}
	}	
	
	private class RDFParsingRunnable implements Runnable { 

		private RDFParser parser;
		private URI baseURI; 
		
		public RDFParsingRunnable(RDFParser p, URI b) { 
			parser = p;
			baseURI = b;
		}
		
		public void run() {
			synchronized(RDFIterator.this) { 
				try {
					parser.parse(inputStream, baseURI.toASCIIString());
					inputStream.close();

				} catch (RDFParseException e) {
					e.printStackTrace();
				} catch (RDFHandlerException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally { 		
					if(inputStream != null) { 
						try {
							inputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						} 
					} 
					finishedParsing = true;
					RDFIterator.this.notifyAll();
					//System.out.println("Finished!");
				}
			}
		}
	}
	
	private void load(String fmt, URI base) { 
		System.out.println(String.format("Format: %s", fmt));
		RDFFormat format = RDFFormat.RDFXML;
		RDFParserFactory factory = new RDFXMLParserFactory();
		
		if(fmt.endsWith("ttl")) { 
			format = RDFFormat.TURTLE;
			factory = new TurtleParserFactory();
		}
		
		if(fmt.endsWith("owl")) { 
			format = RDFFormat.RDFXML;
			factory = new RDFXMLParserFactory();
		}
		
		if(fmt.endsWith("xml")) { 
			format = RDFFormat.RDFXML;
			factory = new RDFXMLParserFactory();
		}
		
		RDFParser parser = factory.getParser();
		parser.setRDFHandler(new ItrRDFHandler());

		new Thread(new RDFParsingRunnable(parser, base)).start();
	}
	
	private class ItrRDFHandler implements RDFHandler {
		
		public void endRDF() throws RDFHandlerException {
		}

		public void handleComment(String arg0) throws RDFHandlerException {
			
		}

		public void handleNamespace(String arg0, String arg1)
				throws RDFHandlerException {
		}

		public void handleStatement(Statement stmt)
		throws RDFHandlerException {
			Resource subj = stmt.getSubject();
			org.openrdf.model.URI pred = stmt.getPredicate();
			Value obj = stmt.getObject();
			addStatement(subj, pred, obj, stmt.getContext());

			/*
			System.out.println(String.format("%s --( %s )--> %s", 
					subj.toString(), pred.toString(), obj.toString()));
			*/
		}

		public void startRDF() throws RDFHandlerException {
		} 		
	}

}
