package tdanford.rdf.sparql;

import java.util.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SparqlUpdateProperties {
	
	private ResourceBundle bundle;

	public SparqlUpdateProperties() { 
		this("tdanford.rdf.sparql.update");
	}
	
	public SparqlUpdateProperties(String n) { 
		bundle = ResourceBundle.getBundle(n);
	}
	
	public String getStringValue(String key) { 
		return bundle.getString(key);
	}
	
	public String getUsername() {
		return getStringValue("username");
	}
	
	public String getPassword() { 
		return getStringValue("password");
	}

	public String getUpdateURI() {
		return getStringValue("updateURL");
	}
	
	public String getSparqlURI() {
		return getStringValue("sparqlURL");
	}
	
	public boolean isVirtuosoEndpoint() { 
		return getStringValue("isVirtuoso").equals("true");
	}
	
	public URL getUpdateURL() { 
		try {
			return new URL(getUpdateURI());
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(getUpdateURI(), e);
		}
	}

	public URL getSparqlURL() { 
		try {
			return new URL(getSparqlURI());
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(getUpdateURI(), e);
		}
	}

	public int getUploadBlockSize() {
		return Integer.parseInt(getStringValue("uploadBlockSize"));
	}
}
