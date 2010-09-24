package org.sc.rdf.sesame;

import java.util.*;
import java.io.*;
import java.net.URI;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.Value;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.RDFParserFactory;
import org.openrdf.rio.rdfxml.RDFXMLParserFactory;
import org.openrdf.rio.turtle.TurtleParserFactory;
import org.openrdf.sail.*;
import org.openrdf.sail.memory.MemoryStore;

import org.sc.rdf.URIRetriever;

public class RDFLoader {

	public static void main(String[] args) {
		String uri = args.length > 0 ? args[0] : 
			"file:///Users/tdanford/Documents/Ontologies/OBI.owl";
		new RDFLoader().load(URI.create(uri));
	}
	
	private static void printUsage() { 
		
	}
	
	private Sail sail;
	private URI starting;
	private URIRetriever retriever;
	
	public RDFLoader() { 
		this(new MemoryStore());
		try {
			sail.initialize();
		} catch (SailException e) {
			e.printStackTrace();
		}
	}
	
	public RDFLoader(Sail s) { 
		sail = s;
		retriever = new URIRetriever();
	}
	
	public void load(URI starting) { 
		String fmt = URIRetriever.guessFormat(starting);
		System.out.println(String.format("Format: %s", fmt));
		RDFFormat format = RDFFormat.RDFXML;
		RDFParserFactory factory = new RDFXMLParserFactory();
		
		if(fmt.endsWith(".ttl")) { 
			format = RDFFormat.TURTLE;
			factory = new TurtleParserFactory();
		}
		
		if(fmt.endsWith(".owl")) { 
			format = RDFFormat.RDFXML;
			factory = new RDFXMLParserFactory();
		}
		
		if(fmt.endsWith(".xml")) { 
			format = RDFFormat.RDFXML;
			factory = new RDFXMLParserFactory();
		}
		
		RDFParser parser = factory.getParser();
		parser.setRDFHandler(new SailRDFHandler());
		
		try {
			parser.parse(retriever.getInputStream(starting), starting.toASCIIString());

		} catch (RDFParseException e) {
			e.printStackTrace();
		} catch (RDFHandlerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class SailRDFHandler implements RDFHandler {
		private SailConnection cxn;
		
		public void endRDF() throws RDFHandlerException {
			try {
				cxn.commit();
				cxn.close();
			} catch (SailException e) {
				e.printStackTrace();
				throw new RDFHandlerException(e);
			}
		}

		public void handleComment(String arg0) throws RDFHandlerException {
			
		}

		public void handleNamespace(String arg0, String arg1)
				throws RDFHandlerException {
			try {
				cxn.setNamespace(arg0, arg1);
			} catch (SailException e) {
				e.printStackTrace();
				throw new RDFHandlerException(e);
			}
		}

		public void handleStatement(Statement stmt)
				throws RDFHandlerException {
			try {
				Resource subj = stmt.getSubject();
				org.openrdf.model.URI pred = stmt.getPredicate();
				Value obj = stmt.getObject();
				cxn.addStatement(subj, pred, obj, stmt.getContext());
				
				System.out.println(String.format("%s --( %s )--> %s", 
						subj.toString(), pred.toString(), obj.toString()));
				
			} catch (SailException e) {
				e.printStackTrace();
				throw new RDFHandlerException(e);
			}
		}

		public void startRDF() throws RDFHandlerException {
			try {
				cxn = sail.getConnection();
			} catch (SailException e) {
				e.printStackTrace();
				throw new RDFHandlerException(e);
			}
		} 		
	}
	
	/*
	public void ontologyValidate() { 
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLReasonerFactory reasonerFactory = new PelletReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(manager);
        
		try {
			OWLOntology ontology = manager.loadOntologyFromPhysicalURI(starting);
			
            for(OWLClass cls : ontology.getReferencedClasses()) {
                System.out.println(cls);
                
            }
            
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}
	*/
}
