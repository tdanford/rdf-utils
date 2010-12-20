package tdanford.rdf;

import java.util.*;
import java.util.regex.*;

public class PrefixList {
	
	private SortedMap<String,String> prefixToName, nameToPrefix;

	public PrefixList() { 
		prefixToName = new TreeMap<String,String>();
		nameToPrefix = new TreeMap<String,String>();
	}
	
	public void add(String name, String prefix) { 
		if(nameToPrefix.containsKey(name) && 
				!nameToPrefix.get(name).equals(prefix)) { 
			throw new IllegalArgumentException(String.format("name %s (prefix: %s) doesn't match previously" +
					" existing prefix %s", name, prefix, nameToPrefix.get(name)));
		}
		
		nameToPrefix.put(name, prefix);
		prefixToName.put(prefix, name);
	}
	
	private static Pattern contractedPattern = Pattern.compile(
			"([^:]+):(.*)");
	
	public String expand(String contracted) { 
		Matcher m = contractedPattern.matcher(contracted);
		if(!m.matches()) { return contracted; }

		String name = m.group(1), suffix = m.group(2);
		if(nameToPrefix.containsKey(name)) { 
			return nameToPrefix.get(name) + suffix;
		} else { 
			return contracted;
		}
	}
	
	public String contract(String expanded) { 
		throw new UnsupportedOperationException();
	}
	
	public static boolean isPrefix(String p, String f) { 
		return f.startsWith(p);
	}
}

