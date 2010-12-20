package tdanford.rdf.sparql;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import tdanford.rdf.sparql.iterators.DeletionIterator;
import tdanford.rdf.sparql.iterators.InsertionIterator;

import com.hp.hpl.jena.graph.*;
import com.hp.hpl.jena.graph.impl.*;
import com.hp.hpl.jena.graph.query.*;
import com.hp.hpl.jena.util.IteratorCollection;
import com.hp.hpl.jena.util.iterator.*;

import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.shared.*;
import com.hp.hpl.jena.shared.impl.PrefixMappingImpl;

public class SparqlGraphBase extends GraphBase {

	private HTTPSparqlUpdate updater;
	private HTTPSparqlQuery queryer;
	private boolean isVirtuosoEndpoint;
	private String graphName;

	public SparqlGraphBase(HTTPSparqlQuery q, HTTPSparqlUpdate up, String graph, boolean isVirt) { 
		this(ReificationStyle.Minimal, q, up, graph, isVirt); 
	}

	public SparqlGraphBase( ReificationStyle style, HTTPSparqlQuery q, HTTPSparqlUpdate up, String graph, boolean isVirt) { 
		super(style);
		queryer = q;
		updater = up;
		graphName = graph;
		isVirtuosoEndpoint = isVirt;
	}
	
	public static String representNode(Node n) {
		if(n == null) { return null; }
		if (n.isURI()) { 
			return String.format("<%s>", n.toString());
		} else if (n.isBlank()) { 
			return String.format("_:%s", n.getBlankNodeId().toString());
		} else if (n.isLiteral()) {
			String datatype = n.getLiteralDatatypeURI();
			String dataKey = datatype != null && datatype.length() > 0 ? 
					String.format("^^<%s>", datatype) : "";
			String language = n.getLiteralLanguage();
			String langKey = language != null && language.length() > 0 ? 
					String.format("@%s", language) : "";
			String literalValue = n.getLiteralValue().toString();
			return String.format("\"%s\"%s%s", literalValue, langKey, dataKey);
		} else { 
			return n.toString();
		}
	}
	
	public static String representNodeVariable(Node n, String var) { 
		String rep = representNode(n);
		return rep != null ? rep : String.format("?%s", var);
	}
	
	public static String createData(Triple t) {
		String squery = representNode(t.getSubject());
		String pquery = representNode(t.getPredicate());
		String oquery = representNode(t.getObject());
		String condition = String.format("%s %s %s", squery, pquery, oquery);
		if(squery==null || pquery==null || oquery==null) { 
			throw new IllegalArgumentException(condition);
		}
		return condition;
	}

	public static String createCondition(TripleMatch t) { 
		String squery = representNodeVariable(t.getMatchSubject(), "s"); 
		String pquery = representNodeVariable(t.getMatchPredicate(), "p");
		String oquery = representNodeVariable(t.getMatchObject(), "o");
		String condition = String.format("%s %s %s", squery, pquery, oquery);
		return condition;
	}
	
	public static String createVars(TripleMatch t) {
		String vars = "";
		vars += (t.getMatchSubject() == null ? "?s " : "");
		vars += (t.getMatchPredicate() == null ? "?p " : "");
		vars += (t.getMatchObject() == null ? "?o " : "");
		if(vars.length() == 0) { throw new IllegalArgumentException(t.toString()); }
		return vars;
	}

	/**
         Answer a BulkUpdateHandler bound to this graph. The default is a
         SimpleBulkUpdateHandler, which does bulk update by repeated simple
         (add/delete) updates; the same handler is returned on each call. Subclasses
         may override if they have specialised implementations.
	 */
	public BulkUpdateHandler getBulkUpdateHandler() { 
		if (bulkHandler == null) { 
			bulkHandler = new SparqlBulkUpdateHandler(this, updater, queryer); 
		}
		return bulkHandler;
	}

	/**
         Add a triple to the triple store. The default implementation throws an
         AddDeniedException; subclasses must override if they want to be able to
         add triples.
	 */
	public void performAdd( Triple t ) {
		String graphLocator = "";
		if(graphName != null) { 
			if(isVirtuosoEndpoint) { 
				graphLocator = String.format(" INTO GRAPH <%s>", graphName);
			} else { 
				graphLocator = String.format(" INTO <%s>", graphName);
			}
		}
		String data = createData(t);
		String cmd = String.format("INSERT DATA%s { %s }",
				graphLocator, data);
		
		try {
			updater.update(cmd);
			getEventManager().notifyAddTriple(this, t);
			
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
         Remove a triple from the triple store. The default implementation throws
         a DeleteDeniedException; subclasses must override if they want to be able
         to remove triples.
	 */
	public void performDelete( Triple t ) { 
		String graphLocator = "";
		if(graphName != null) { 
			if(isVirtuosoEndpoint) { 
				graphLocator = String.format(" INTO GRAPH <%s>", graphName);
			} else { 
				graphLocator = String.format(" INTO <%s>", graphName);
			}
		}

		String data = createData(t);
		String cmd = String.format("DELETE DATA%s { %s }",
				graphLocator, data);
		
		try {
			updater.update(cmd);
			getEventManager().notifyDeleteTriple(this, t);
			
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
        Answer an iterator over all the triples held in this graph's non-reified triple store
        that match <code>m</code>. Subclasses <i>must</i> override; it is the core
        implementation for <code>find(TripleMatch)</code>.
	 */
	protected ExtendedIterator<Triple> graphBaseFind( TripleMatch t ) { 

		String graphLocator = "";
		if(graphName != null) { 
			if(isVirtuosoEndpoint) { 
				graphLocator = String.format(" FROM GRAPH <%s>", graphName);
			} else { 
				graphLocator = String.format(" FROM <%s>", graphName);
			}
		}

		int pageSize = 10000;

		String varDecl = createVars(t);
		String queryTemplate = 
			String.format("SELECT %s%s WHERE { %s } ORDER BY %s OFFSET %%d LIMIT %%d", 
					varDecl, graphLocator, createCondition(t), varDecl);

		return new PagingQueryIterator(queryer, t, queryTemplate, pageSize);
	}
	
	private class PagingQueryIterator extends NiceIterator<Triple> {
		
		private ExtendedIterator<Triple> pageItr;
		private String queryTemplate;
		private TripleMatch matcher;
		private HTTPSparqlQuery queryer;
		private int offset, limit, retrieved;
		
		public PagingQueryIterator(HTTPSparqlQuery queryer, TripleMatch matcher, String template, int limit) { 
			this.queryer = queryer;
			this.queryTemplate = template;
			this.offset = 0;
			this.limit = limit;
			this.matcher = matcher;
			pageItr = null;
			
			try {
				loadNextPageItr();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		}
		
		private void loadNextPageItr() throws IOException { 
			String query = String.format(queryTemplate, offset, limit);
			offset += limit;
			String response = queryer.makeQuery(query);
			pageItr = null;
			try {
				pageItr = new TripleIterator(matcher, parseResponse(response));
				retrieved = 0;
			} catch (SAXException e) {
				e.printStackTrace(System.err);
				throw new IllegalStateException("Couldn't parse XML response", e);
			}
		}
		
		public void close() {
			if(pageItr != null) { 
				pageItr.close();
			}
		}
		
		public boolean hasNext() { 
			return pageItr != null && pageItr.hasNext();
		}
		
		public Triple next() { 
			Triple t = pageItr.next();
			retrieved++;
			if(!pageItr.hasNext() && retrieved >= limit) { 
				try {
					loadNextPageItr();
				} catch (IOException e) {
					e.printStackTrace(System.err);
					pageItr = null;
				}
			}
			return t;
		}
	}

	/**
         Answer true if the graph contains any triple matching <code>t</code>.
         The default implementation uses <code>find</code> and checks to see
         if the iterator is non-empty.
	 */
	protected boolean graphBaseContains( Triple t ) {
		
		String graphLocator = "";
		if(graphName != null) { 
			if(isVirtuosoEndpoint) { 
				graphLocator = String.format(" FROM GRAPH <%s>", graphName);
			} else { 
				graphLocator = String.format(" FROM <%s>", graphName);
			}
		}

		String query = String.format("ASK%s { %s }", 
					graphLocator, createCondition(t));
		try {
			String response = queryer.makeQuery(query);
			return response.contains("yes");
			
		} catch (IOException e) {
			e.printStackTrace(System.err);
			return false;
		}
	}

	/**
         Answer the number of triples in this graph. Default implementation counts its
         way through the results of a findAll. Subclasses must override if they want
         size() to be efficient.
	 */
	protected int graphBaseSize() {
		try {
			return queryer.count(graphName, null);
			
		} catch (IOException e) {
			e.printStackTrace(System.err);

		} catch (SAXException e) {
			e.printStackTrace(System.err);
		}
		return 0;
	}
	
	private class TripleIterator extends NiceIterator<Triple> {
		
		private TripleMatch match;
		private BindingTable<Node> nodeTable;
		private int idx;
		
		public TripleIterator(TripleMatch m, BindingTable<Node> ns) {
			match = m;
			nodeTable = ns;
			idx = 0;
		}

		public Triple removeNext() {
			throw new UnsupportedOperationException("removeNext() on TripleIterator is not allowed");
		}

		public void close() {
			nodeTable = null;
			idx = -1;
		}

		public boolean hasNext() {
			return nodeTable != null && idx < nodeTable.size();
		}

		public Triple next() {
			Triple t = constructTriple();
			idx += 1;
			return t;
		}
		
		public Triple constructTriple() { 
			return Triple.create(
					getSubject(), getPredicate(), getObject());
		}
		
		public Node getSubject() { 
			return match.getMatchSubject() == null ? 
					nodeTable.get(idx, "s") : match.getMatchSubject();
		}

		public Node getPredicate() { 
			return match.getMatchPredicate() == null ? 
					nodeTable.get(idx, "p") : match.getMatchPredicate();
		}

		public Node getObject() { 
			return match.getMatchObject() == null ? 
					nodeTable.get(idx, "o") : match.getMatchObject();
		}

		public void remove() {
			throw new UnsupportedOperationException("remove() on TripleIterator is not allowed");
		} 
	}
	

	public BindingTable<Node> parseResponse(String response) throws SAXException, IOException {
		try { 
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true); // never forget this!

			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(response)));

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			xpath.setNamespaceContext(new PersonalNamespaceContext());

			XPathExpression variableNameExpr = xpath.compile("//sparql:variable/@name");

			NodeList nameList = (NodeList)variableNameExpr.evaluate(doc, XPathConstants.NODESET);
			String[] ns = new String[nameList.getLength()];
			for(int i = 0; i < nameList.getLength(); i++) { 
				ns[i] = nameList.item(i).getNodeValue();
			}

			BindingTable<Node> tbl = new BindingTable<Node>(Node.class, ns);

			XPathExpression resultExpr = xpath.compile("//sparql:result");
			XPathExpression bindingExpr = xpath.compile("sparql:binding");
			XPathExpression dataExpr = xpath.compile("sparql:uri | sparql:literal | sparql:bnode");

			NodeList resultList = (NodeList)resultExpr.evaluate(doc, XPathConstants.NODESET);
			for(int i = 0; i < resultList.getLength(); i++) { 
				org.w3c.dom.Node result = resultList.item(i);

				NodeList bindingList = (NodeList)bindingExpr.evaluate(result, XPathConstants.NODESET);
				
				Node[] array = new Node[ns.length];

				for(int j = 0; j < bindingList.getLength(); j++) { 
					org.w3c.dom.Node binding = bindingList.item(j);
					
					String name = binding.getAttributes().getNamedItem("name").getNodeValue();
					int idx = tbl.findNameColumn(name);
					
					org.w3c.dom.Node child = 
						(org.w3c.dom.Node)
							dataExpr.evaluate(binding, XPathConstants.NODE);
					Node value = null;

					org.w3c.dom.Node valueXML = child.getFirstChild();

					if(child.getNodeName().equals("literal")) { 
						
						String nodeValue = valueXML != null ? valueXML.getNodeValue() : "";
						//value = model.createLiteral(nodeValue);
						value = Node.createLiteral(nodeValue);

					} else if (child.getNodeName().equals("uri")) { 
						//value = model.createResource(valueXML.getNodeValue());
						value = Node.createURI(valueXML.getNodeValue());

					} else if (child.getNodeName().equals("bnode")) { 
						//value = model.createResource(new AnonId(valueXML.getNodeValue()));
						value = Node.createAnon(new AnonId(valueXML.getNodeValue()));

					} else { 
						System.err.println(String.format("Unrecognized node type: \"%s\"", child.getNodeName()));
					}

					array[idx] = value;
				}

				tbl.addRow(array);
			}

			return tbl;
			
		} catch(ParserConfigurationException e) { 
			e.printStackTrace(System.err);
			assert false : e.getMessage();
			
		} catch (XPathExpressionException e) {
			e.printStackTrace(System.err);
			assert false : e.getMessage();
		}

		return null;
	}
	
	public class PersonalNamespaceContext implements NamespaceContext {

	    public String getNamespaceURI(String prefix) {
	        if (prefix == null) throw new NullPointerException("Null prefix");
	        else if ("sparql".equals(prefix)) return "http://www.w3.org/2005/sparql-results#";
	        else if ("xml".equals(prefix)) return XMLConstants.XML_NS_URI;
	        return XMLConstants.NULL_NS_URI;
	    }

	    // This method isn't necessary for XPath processing.
	    public String getPrefix(String uri) {
	        throw new UnsupportedOperationException();
	    }

	    // This method isn't necessary for XPath processing either.
	    public Iterator getPrefixes(String uri) {
	        throw new UnsupportedOperationException();
	    }
	}
}

class SparqlBulkUpdateHandler implements BulkUpdateHandler {
	protected GraphWithPerform graph;
	protected GraphEventManager manager;
	
	private String graphName;
	private HTTPSparqlQuery queryer;
	private HTTPSparqlUpdate updater;
	private boolean isVirtuosoEndpoint;

	public SparqlBulkUpdateHandler( GraphWithPerform graph, HTTPSparqlUpdate up, HTTPSparqlQuery q ) { 
		this.graph = graph; 
		this.manager = graph.getEventManager();
		this.queryer = q;
		this.updater = up;
		this.graphName = null;
		this.isVirtuosoEndpoint = false;
	}
	
	public static String createCondition(Node s, Node p, Node o) { 
		return createCondition(Triple.createMatch(s, p, o));
	}
	
	public static String createCondition(TripleMatch t) { 
		String squery = t.getMatchSubject() != null ? 
				String.format("<%s>", t.getMatchSubject().toString()) : "?s"; 
		String pquery = t.getMatchPredicate() != null ? 
				String.format("<%s>", t.getMatchPredicate().toString()) : "?p"; 
		String oquery = t.getMatchObject() != null ? 
				String.format("<%s>", t.getMatchObject().toString()) : "?o";
		String condition = String.format("%s %s %s", squery, pquery, oquery);
		return condition;
	}

	public void add( Triple[] triples ) {
		InsertionIterator itr = new InsertionIterator(Arrays.asList(triples), graphName, isVirtuosoEndpoint);
		try {
			while(!itr.isFinished()) {  
				String cmd = itr.nextStatement();
				updater.update(cmd);
			}
			manager.notifyAddArray( graph, triples );
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally { 
			itr.close();
		}
	}

	public void add( List<Triple> triples ) { 
		add( triples, true ); 
	}

	protected void add( List<Triple> triples, boolean notify ) {
		InsertionIterator itr = new InsertionIterator(triples, graphName, isVirtuosoEndpoint);
		try {
			while(!itr.isFinished()) {  
				String cmd = itr.nextStatement();
				updater.update(cmd);
			}
			if(notify) { 
				manager.notifyAddList( graph, triples );
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally { 
			itr.close();
		}
	}

	public void add(Iterator<Triple> it) { 
		addIterator(it,true); 
	}

	public void addIterator( Iterator<Triple> it, boolean notify ) { 
		List<Triple> s = IteratorCollection.iteratorToList( it );
		add( s, false );
		if (notify) { 
			// TODO: fix me.
			// this needs to be fixed, because the notification will be performed
			// even if the add() call dies from an exception...
			manager.notifyAddIterator( graph, s );
		}
	}

	public void add( Graph g ) { 
		add( g, false ); 
	}

	public void add( Graph g, boolean withReifications ) { 
		addIterator( GraphUtil.findAll( g ), false );  
		if (withReifications) { 
			addReifications( graph, g );
		}
		manager.notifyAddGraph( graph, g );
	}

	public static void addReifications( Graph ours, Graph g ) {
		Reifier r = g.getReifier();
		Iterator<Node> it = r.allNodes();
		while (it.hasNext()) {
			Node node = it.next();
			ours.getReifier().reifyAs( node, r.getTriple( node ) );
		}
	}

	public static void deleteReifications( Graph ours, Graph g ) {
		Reifier r = g.getReifier();
		Iterator<Node> it = r.allNodes();
		while (it.hasNext()) {
			Node node = it.next();
			ours.getReifier().remove( node, r.getTriple( node ) );
		}
	}

	public void delete( Triple [] triples ) { 
		DeletionIterator itr = new DeletionIterator(Arrays.asList(triples), graphName, isVirtuosoEndpoint);
		try {
			while(!itr.isFinished()) {  
				String cmd = itr.nextStatement();
				updater.update(cmd);
			}
			manager.notifyDeleteArray( graph, triples );
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally { 
			itr.close();
		}
	}

	public void delete( List<Triple> triples ) { 
		delete( triples, true ); 
	}

	protected void delete( List<Triple> triples, boolean notify ) { 
		DeletionIterator itr = new DeletionIterator(triples, graphName, isVirtuosoEndpoint);
		try {
			while(!itr.isFinished()) {  
				String cmd = itr.nextStatement();
				updater.update(cmd);
			}
			if(notify) { 
				manager.notifyDeleteList( graph, triples );
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		} finally { 
			itr.close();
		}
	}

	public void delete( Iterator<Triple> it ) { 
		deleteIterator( it, true ); 
	}

	public void deleteIterator( Iterator<Triple> it, boolean notify ) {  
		List<Triple> L = IteratorCollection.iteratorToList( it );
		delete( L, false );
		if (notify) { 
			// TODO: fix me.
			// same problem as in addIterator, above.
			manager.notifyDeleteIterator( graph, L );
		}
	}

	private List<Triple> triplesOf( Graph g ) {
		ArrayList<Triple> L = new ArrayList<Triple>();
		Iterator<Triple> it = g.find( Triple.ANY );
		while (it.hasNext()) L.add( it.next() );
		return L;
	}

	public void delete( Graph g ) { 
		delete( g, false ); 
	}

	public void delete( Graph g, boolean withReifications ) { 
		if (g.dependsOn( graph )) { 
			delete( triplesOf( g ) );
		} else { 
			deleteIterator( GraphUtil.findAll( g ), false );
		}
		if (withReifications) { 
			deleteReifications( graph, g );
		}
		manager.notifyDeleteGraph( graph, g );
	}

	public void removeAll() { 
		try {
			updater.clearGraph(graphName);
			manager.notifyEvent( graph, GraphEvents.removeAll ); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void remove( Node s, Node p, Node o ) { 	
		String condition = createCondition(s, p, o);
		String graphCondition = "";
		if(graphName != null) { 
			if(isVirtuosoEndpoint) { 
				graphCondition = String.format(" GRAPH <%s>", graphName);
			} else { 
				graphCondition = String.format(" <%s>", graphName);
			}
		}
		String cmd = String.format("MODIFY%s DELETE { %s } INSERT {} WHERE {}",
				graphCondition, condition, condition);
		try { 
			updater.update(cmd);
	
			manager.notifyEvent( graph, GraphEvents.remove( s, p, o ) ); 
		} catch(IOException e) { 
			e.printStackTrace(System.err);
		}
	}
}