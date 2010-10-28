package tdanford.commandline;

import java.util.*;
import java.io.*;

import org.xml.sax.SAXException;

import com.hp.hpl.jena.rdf.arp.ALiteral;
import com.hp.hpl.jena.rdf.arp.ARP;
import com.hp.hpl.jena.rdf.arp.ARPHandlers;
import com.hp.hpl.jena.rdf.arp.AResource;
import com.hp.hpl.jena.rdf.arp.StatementHandler;

public class RDFCat {
	
	public static void main(String[] args) { 
		ARP arp = new ARP();
		InputStream is = System.in;
		ARPHandlers handlers = ARPHandlers.createNewHandlers();
		
		StatementHandler shandler = 
			args.length == 0 || args[0].equals("res") ? 
					new ResourceHandler(System.out) : 
						args[0].equals("pred") ? 
								new PredicateHandler(System.out) : null;
		
		handlers.setStatementHandler(shandler);
		arp.setHandlersWith(handlers);
		
		try {
			arp.load(is);
			
		} catch (SAXException e) {
			e.printStackTrace(System.err);
			
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

}

class ResourceHandler implements StatementHandler {
	
	private PrintStream out;
	
	public ResourceHandler(PrintStream ps) { 
		out = ps;
	}
	
	public void statement(AResource s, AResource p, AResource o) {
		if(!s.isAnonymous()) { out.println(s.getURI()); }
		if(!o.isAnonymous()) { out.println(o.getURI()); }
	}

	public void statement(AResource s, AResource p, ALiteral o) {
		if(!s.isAnonymous()) { out.println(s.getURI()); }
	}	
}

class PredicateHandler implements StatementHandler {
	
	private PrintStream out;
	
	public PredicateHandler(PrintStream ps) { 
		out = ps;
	}
	
	public void statement(AResource s, AResource p, AResource o) {
		out.println(p.getURI());
	}

	public void statement(AResource s, AResource p, ALiteral o) {
		out.println(p.getURI());
	}	
}
