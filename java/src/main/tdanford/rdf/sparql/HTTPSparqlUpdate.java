package tdanford.rdf.sparql;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.io.*;

import org.xml.sax.SAXException;

import tdanford.rdf.sparql.HTTPSparqlQuery.ResponseTable;
import tdanford.rdf.sparql.iterators.DeletionIterator;
import tdanford.rdf.sparql.iterators.InsertionIterator;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * HTTPSparqlUpdate communicates via HTTP with Sparql/Update-capable endpoints.
 * 
 * It speaks Jena, and tries to handle the syntax differences in Virtuoso vs. the Sparql/Update draft
 * standard.
 * 
 * @author tdanford
 */
public class HTTPSparqlUpdate {
	
	public static String propsName = "joseki";
	//public static String propsName = "ashby";
	
	public static URL updateURL;
	static { 		
		updateURL = new SparqlUpdateProperties(propsName).getUpdateURL();
	}

	public static void main(String[] args) {
		try { 
			if(args.length > 0) { 			
				SparqlUpdateProperties props = new SparqlUpdateProperties(propsName);
				HTTPSparqlUpdate updater = new HTTPSparqlUpdate(props);

				if(args[0].equals("create")) {
					String graph = args.length > 1 ? args[1] : null;
					updater.createGraph(graph, false);
					
				} else if (args[0].equals("graphlist")) { 
					Collection<Resource> graphs = updater.listGraphs();
					for(Resource g : graphs) { 
						System.out.println(g.toString());
					}
					
				} else if (args[0].equals("nodelist")) { 
					String graph = args.length > 1 ? args[1] : null;
					ResponseTable tbl = updater.query("select distinct ?s where { ?s ?p ?o . }");
					int sidx = tbl.header.indexOf("s");
					for(int i = 0; i < tbl.rows.size(); i++) { 
						System.out.println(tbl.rows.get(i)[sidx].toString());
					}
					
				} else if (args[0].equals("describe")) { 
					String node = args[1];
					String graph = args.length > 2 ? args[2] : null;
					ResponseTable tbl = updater.query(
							String.format("select distinct ?p ?o where { <%s> ?p ?o . } order by ?p", 
									node));
					int pidx = tbl.header.indexOf("p");
					int oidx = tbl.header.indexOf("o");
					String p = null;
					for(int i = 0; i < tbl.rows.size(); i++) { 
						if(p != null && tbl.rows.get(i)[pidx].toString().equals(p)) { 
							System.out.println(String.format("\t%s", tbl.rows.get(i)[oidx].toString()));
						} else { 
							p = tbl.rows.get(i)[pidx].toString();
							System.out.println(p);
							System.out.println(String.format("\t%s", tbl.rows.get(i)[oidx].toString()));
						}
					}
					
				} else if (args[0].equals("clear")) {
					String graph = args.length > 1 ? args[1] : null;
					updater.clearGraph(graph);

				} else if (args[0].equals("drop")) { 
					String graph = args.length > 1 ? args[1] : null;
					updater.dropGraph(graph, false);

				} else if (args[0].equals("upload")) {
					Model m = ModelFactory.createDefaultModel();
					InputStream is = new FileInputStream(new File(args[1]));
					m.read(is, null, "RDF/XML");
					is.close();

					String graph = args.length > 2 ? args[2] : null;

					updater.uploadModel(graph, m);

				} else if (args[0].equals("extract")) {
					String graph = args.length > 1 ? args[1] : null;
					Model m = updater.constructModel(graph);
					m.write(System.out);
					
				} else { 
					System.err.println(String.format("Unknown command: %s", args[0]));
				}
			}
		} catch(IOException e) { 
			e.printStackTrace(System.err);
		} catch (SAXException e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void testmain(String[] args) { 
		boolean isVirtuoso = true;
		SparqlUpdateProperties props = new SparqlUpdateProperties(propsName);
		HTTPSparqlUpdate updater = new HTTPSparqlUpdate(props);

		String graph = "http://neurocommons.org/test-graph";
		Model m = ModelFactory.createDefaultModel();

		try {
			Model m2 = updater.constructModel(graph);
			
			StmtIterator itr = m2.listStatements();
			while(itr.hasNext()) { 
				Statement stmt = itr.next();
				System.out.println(stmt.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	private URL sparqlEndpoint;
	private URL updateEndpoint;
	private boolean isVirtuosoEndpoint;
	
	private String username, password;
	private int blockSize; 
	
	public HTTPSparqlUpdate(SparqlUpdateProperties props) {
		sparqlEndpoint = props.getSparqlURL();
		updateEndpoint = props.getUpdateURL();
		
		username = props.getUsername();
		password = props.getPassword();
		
		Authenticator.setDefault(new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		    	System.out.println(String.format("Retrieving credentials: %s:%s",
		    			getRequestingScheme(),
		    			getRequestingPrompt()));
		    	String pword = 
		    		password;
		        return new PasswordAuthentication (username, password.toCharArray());
		    }
		});
		
		isVirtuosoEndpoint = props.isVirtuosoEndpoint();
		blockSize = props.getUploadBlockSize();
	}
	
	public HTTPSparqlUpdate(URL query, URL update, boolean isVirtuoso) { 
		sparqlEndpoint = query;
		updateEndpoint = update;
		username = password = null;
		isVirtuosoEndpoint = isVirtuoso;
		blockSize = 100;
	}
	
	public boolean hasGraph(String graph) throws IOException { 
		String response = sparql(String.format("ASK { GRAPH <%s> { ?s ?p ?o . } }", graph));
		return response.trim().toLowerCase().equals("true");
	}
	
	public void createGraph(String graph, boolean silent) throws IOException { 
		update(String.format("CREATE %sGRAPH <%s>", (silent ? "SILENT " : ""), graph));
	}
	
	public void dropGraph(String graph, boolean silent) throws IOException { 
		update(String.format("DROP %sGRAPH <%s>", (silent ? "SILENT " : ""), graph));
	}
	
	public void clearGraph(String graph) throws IOException { 
		update(String.format("CLEAR%s", 
				(graph != null ? String.format(" GRAPH <%s>", graph) : "")));		
	}
	
	public void insert(String graph, ExtendedIterator<Statement> stmts) throws IOException {
		InsertionIterator itr = new InsertionIterator(stmts, graph, isVirtuosoEndpoint);
		try { 
			while(!itr.isFinished()) { 
				String cmd = itr.nextStatement();
				update(cmd);
			}
		} finally { 
			itr.close();
		}
	}

	public void delete(String graph, ExtendedIterator<Statement> stmts) throws IOException { 
		DeletionIterator itr = new DeletionIterator(stmts, graph, isVirtuosoEndpoint);
		try { 
			while(!itr.isFinished()) { 
				String cmd = itr.nextStatement();
				update(cmd);
			}
		} finally { 
			itr.close();
		}
	}
	
	public void uploadModel(String graph, Model model) throws IOException { 
		insert(graph, model.listStatements());
	}
	
	public Model constructModel(String graph) throws IOException { 
		return constructModel(graph, "?s ?p ?o", "?s ?p ?o");
	}
	
	public Model constructModel(String graph, 
			String constructPattern, 
			String filterPattern) throws IOException {
		
		String query = null;
		
		if(graph != null) { 
			query = String.format("CONSTRUCT { %s } WHERE { GRAPH <%s> { %s } }",
					constructPattern, graph, filterPattern);
		} else { 
			query = String.format("CONSTRUCT { %s } WHERE { %s }",
					constructPattern, filterPattern);			
		}
		
		String response = execute(sparqlEndpoint, "query", query);
		Model responseModel = ModelFactory.createDefaultModel();
		
		responseModel.read(new StringReader(response), null, "RDF/XML");
		
		return responseModel;
	}
	
	/*
	public String getQueryKey() { 
		return isVirtuosoEndpoint ? "query" : "request";
	}
	*/

	public Collection<Resource> listGraphs() throws IOException, SAXException {
		LinkedList<Resource> graphList = new LinkedList<Resource>();
		HTTPSparqlQuery query = new HTTPSparqlQuery(sparqlEndpoint);
		ResponseTable tbl = query.query("select distinct ?g where { graph ?g { ?s ?p ?o . } }");
		for(int i = 0; i < tbl.rows.size(); i++) { 
			Resource r = (Resource)tbl.rows.get(i)[tbl.header.indexOf("g")];
			graphList.add(r);
		}
		return graphList;
	}
	
	public String sparql(String cmd) throws IOException { 
		return execute(sparqlEndpoint, "query", cmd);
	}
	
	public ResponseTable query(String cmd) throws IOException, SAXException { 
		HTTPSparqlQuery queryer = new HTTPSparqlQuery(sparqlEndpoint);
		return queryer.query(cmd);
	}

	public String update(String cmd) throws IOException { 
		return execute(updateEndpoint, "request", cmd);
	}
	

	private void printConnection(HttpURLConnection cxn, PrintStream ps) throws IOException {
		try { 
			ps.println(String.format("RESPONSE (%d) : %s", 
					cxn.getResponseCode(), cxn.getResponseMessage()));
		} catch(IOException e) { 
			e.printStackTrace(System.err);
			ps.println("CONNECTION:");
		}
		Map map = cxn.getHeaderFields();

		for(Object key : map.keySet()) {
			Object value = map.get(key);
			ps.println(String.format("\t%s: %s", String.valueOf(key), String.valueOf(value)));
		}				
	}
	
	public String execute(URL url, String queryKey, String cmd) throws IOException {
		System.err.println(String.format("Cmd:\n%s", cmd));
		
		HttpURLConnection cxn = (HttpURLConnection)url.openConnection();
		cxn.setDoOutput(true);
		cxn.setRequestMethod("POST");

		cxn.connect();
		Writer output = new OutputStreamWriter(cxn.getOutputStream());
		
		try { 
			String postContent = String.format("%s=%s", queryKey, URLEncoder.encode(cmd, "UTF-8"));
			//System.err.println(String.format("Content:\n%s", postContent));
			
			output.write(postContent);
			output.flush();
			
			if(cxn.getResponseCode() / 100 != 2) {
				printConnection(cxn, System.err);
				return cxn.getResponseMessage();
			}
			
			BufferedReader input = new BufferedReader(new InputStreamReader(cxn.getInputStream()));
			try {
				StringBuilder sb = new StringBuilder();
				String line;
				while((line = input.readLine()) != null) { 
					sb.append(line);
					sb.append("\n");
				}
				
				//System.err.println(String.format("Response:\n%s", sb.toString()));
				return sb.toString();
				
			} finally { 
				input.close();
			}
			
		} finally { 
			output.close();
		}
	}

}
