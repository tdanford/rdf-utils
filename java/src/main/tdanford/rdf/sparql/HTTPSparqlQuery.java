package tdanford.rdf.sparql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.*;
import java.util.*;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HTTPSparqlQuery {
	
	private URL endpoint;
	private String queryKey;
	
	public HTTPSparqlQuery(URL endpoint) { 
		this(endpoint, "query");
	}

	public HTTPSparqlQuery(URL endpoint, String queryKey) { 
		this.endpoint = endpoint;
		this.queryKey = queryKey;
	}

	public static class ResponseTable { 

		public ArrayList<String> header;
		public ArrayList<Object[]> rows;
		private Map<String,Integer> cols;

		public ResponseTable() { 
			header = new ArrayList<String>();
			rows = new ArrayList<Object[]>();
			cols = new HashMap<String,Integer>();
		}

		public void setHeader(String... hs) { 
			header.addAll(Arrays.asList(hs));
			for(int i = 0; i < header.size(); i++) { 
				cols.put(header.get(i), i);
			}
		}

		public ResponseTable(String... hs) { 
			this();
			setHeader(hs);
		}

		public Integer findNameColumn(String name) { 
			return cols.get(name);
		}
	}
	
	public ResponseTable parseResponse(String response) throws SAXException, IOException {
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

			ResponseTable tbl = new ResponseTable(ns);

			XPathExpression resultExpr = xpath.compile("//sparql:result");
			XPathExpression bindingExpr = xpath.compile("sparql:binding");
			XPathExpression dataExpr = xpath.compile("sparql:uri | sparql:literal | sparql:bnode");

			NodeList resultList = (NodeList)resultExpr.evaluate(doc, XPathConstants.NODESET);
			for(int i = 0; i < resultList.getLength(); i++) { 
				Node result = resultList.item(i);

				NodeList bindingList = (NodeList)bindingExpr.evaluate(result, XPathConstants.NODESET);
				
				Object[] array = new Object[ns.length];

				for(int j = 0; j < bindingList.getLength(); j++) { 
					Node binding = bindingList.item(j);
					
					String name = binding.getAttributes().getNamedItem("name").getNodeValue();
					Integer idx = tbl.findNameColumn(name);
					if(idx == null) { throw new IllegalStateException(name); }
					
					Node child = (Node)dataExpr.evaluate(binding, XPathConstants.NODE);
					Object value = null;

					Node valueXML = child.getFirstChild();

					if(child.getNodeName().equals("literal")) { 
						
						String nodeValue = valueXML != null ? valueXML.getNodeValue() : "";
						value = nodeValue;

					} else if (child.getNodeName().equals("uri")) { 

						try { 
							value = new URI(valueXML.getNodeValue());
						} catch(URISyntaxException e) { 
							throw new IllegalStateException(valueXML.getNodeValue(), e);
						}

					} else if (child.getNodeName().equals("bnode")) { 

						value = valueXML.getNodeValue();
						//value = model.createResource(new AnonId(valueXML.getNodeValue()));

					} else { 
						System.err.println(String.format("Unrecognized node type: \"%s\"", child.getNodeName()));
					}

					array[idx] = value;
				}

				tbl.rows.add(array);
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
	
	public String makeQuery(String queryString) throws IOException { 
		String postContent = String.format("%s=%s", queryKey, URLEncoder.encode(queryString, "UTF-8"));
		postContent += String.format("&format=%s", URLEncoder.encode("application/sparql-results+xml", "UTF-8"));
		
		//System.out.println(String.format("POST:\n%s", postContent));
		HttpURLConnection cxn = (HttpURLConnection)endpoint.openConnection();
		cxn.setDoOutput(true);
		Writer output = new OutputStreamWriter(cxn.getOutputStream());
		try { 
			output.write(postContent);
			output.flush();
			
			Reader input = new InputStreamReader(cxn.getInputStream());
			try {
				BufferedReader br = new BufferedReader(input);
				String line = null;
				StringBuilder sb =new StringBuilder();
				while((line = br.readLine()) != null) { 
					sb.append(line); 
					sb.append("\n");
				}
				
				//System.out.println(String.format("Response:\n%s", sb.toString()));
				return sb.toString();
			   				
			} finally { 
				input.close();
			}
		} finally { 
			output.close();
		}		
	}
	
	public ResponseTable query(String queryString) throws IOException, SAXException {
		return parseResponse(makeQuery(queryString));		
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

	public int count(String graph, String condition) throws IOException, SAXException {
		if(condition==null) { condition = "?s ?p ?o ."; }
		String graphLocator = "";
		if(graph != null) { 
			graphLocator = String.format(" FROM <%s>", graph);
		}
		String conditionClause = condition != null ? String.format(" WHERE { %s }", condition) : "";
		ResponseTable tbl = query(String.format("select count(*)%s%s", graphLocator, conditionClause));
		return Integer.parseInt(tbl.rows.get(0).toString());
	}

}
