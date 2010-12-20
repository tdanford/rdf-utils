package tdanford.rdf.sparql.worksheet;

import java.util.*;
import java.util.regex.*;

public class URLFinder {

	/*
	 * From John Gruber's "Improved Regex for Matching URLs"
	 * http://daringfireball.net/2010/07/improved_regex_for_matching_urls
	 */
	private static Pattern dfPattern = Pattern.compile(
			"(?i)\\b((?:[a-z][\\w-]+:(?:/{1,3}|[a-z0-9%])" +
			"|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)" +
			"(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+" +
			"(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()" +
			"\\[\\]{};:'\".,<>?гхрсту]))");

	private ArrayList<String> urls;
	private ArrayList<Integer[]> locations;

	public URLFinder(String text) { 
		Matcher matcher = dfPattern.matcher(text);
		urls = new ArrayList<String>();
		locations = new ArrayList<Integer[]>();
		
		while(matcher.find()) { 
			urls.add(matcher.group(1));
			locations.add(new Integer[] { matcher.start(1), matcher.end(1) });
		}
	}
	
	public Collection<String> urls() { return urls; }
	
	public Collection<Integer[]> locations() { 
		return new LinkedList<Integer[]>(locations);
	}
	
	public int matchCount() { return urls.size(); }

	public String url(int i) { return urls.get(i); }
	
	public Integer[] location(int i) { return locations.get(i); }
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < urls.size(); i++) {
			if(i > 0) { sb.append("|"); }
			sb.append(String.format("%d,%d=\"%s\"",
					locations.get(i)[0],
					locations.get(i)[1],
					urls.get(i)));
		}
		return sb.toString();
	}
}
