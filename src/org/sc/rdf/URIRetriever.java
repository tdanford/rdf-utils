package org.sc.rdf;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;

/*
 * Turns java.net.URI objects into InputStream or String objects of the value 
 * available at the URI-treated-as-a-URL.  
 * 
 * Currently works for http:// uris.
 * 
 * Handles caching, so the same URI won't be retrieved twice.  
 */
public class URIRetriever {
	
	private Map<URI,Retrieved> retrieved;

	public URIRetriever() { 
		retrieved = new HashMap<URI,Retrieved>();
	}
	
	public String retrieve(URI u) { 
		if(!retrieved.containsKey(u)) {
			lookup(u);
		}
		return retrieved.get(u).getValue();
	}
	
	public InputStream openInputStream(URI u) throws MalformedURLException, IOException { 
		return u.toURL().openStream();
	}
	
	public void lookup(URI u) {
		try {
			InputStream is = openInputStream(u);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine()) != null) {
				if(sb.length() > 0) { sb.append("\n"); }
				sb.append(line);
			}
			br.close();
			
			Date date = Calendar.getInstance().getTime();
			String format = guessFormat(u);
			retrieved.put(u, new Retrieved(u, date, format, sb.toString()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class Retrieved { 
		private URI uri;
		private String value;
		private String format;
		private Date date;
		
		public Retrieved(URI u, Date d, String f, String v) { 
			uri = u;
			value = v;
			format = f;
			date = d;
		}
		
		public Date getDate() { return date; }
		public URI getURI() { return uri; }
		public String getValue() { return value; }
		public String getFormat() { return format; }
	}
	
	public String getFormat(URI uri) { return retrieved.get(uri).getFormat(); }

	public static String guessFormat(URI uri) {
		Pattern fmtPatt = Pattern.compile("^.*(\\.[^.]+)$");
		Matcher m = fmtPatt.matcher(uri.getPath());
		String format = "?";
		if(m.matches()) { 
			format = m.group(1);
		}
		return format;
	}

	public InputStream getInputStream(URI uri) {
		/*
		System.out.println(uri.toASCIIString());
		System.out.println(String.format("Host: \"%s\"", uri.getHost()));
		System.out.println(String.format("Path: \"%s\"", uri.getPath()));
		System.out.println(String.format("Authority: \"%s\"", uri.getAuthority()));
		System.out.println(String.format("Scheme: \"%s\"", uri.getScheme()));
		*/
		
		if(uri.getScheme().equals("file")) { 
			String path = uri.getPath();
			try {
				return new FileInputStream(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return new java.io.ByteArrayInputStream(retrieve(uri).getBytes());
	}
}
