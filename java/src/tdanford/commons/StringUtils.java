package tdanford.commons;

import java.util.*;
import java.util.regex.*;
import java.io.*;

public class StringUtils {

	public static void main(String[] args) {
		String str = "\"Foo		Bar;,Blah Grok ;;%	\"Yaaar.";
		String esc = escapeTabs(str);
		String unesc = unescapeTabs(esc);
		System.out.println(String.format("Original : [%s]", str));
		System.out.println(String.format("Unescaped: [%s]", unesc));
		System.out.println(String.format("Escaped  : [%s]", esc));
	}
	
	public static String escape(String q, char target) {
		int ascii = (int)target;
		String rep = String.format("%%%d;", ascii);
		return q.replaceAll(String.valueOf(target), rep);
	}
	
	public static String unescape(String e, char target) { 
		int ascii = (int)target;
		String rep = String.format("%%%d;", ascii);
		return e.replaceAll(rep, String.valueOf(target));		
	}
	
	public static String escapeQuotes(String quoted) { 
		return escape(quoted, '"');
	}

	public static String unescapeQuotes(String escaped) { 
		return unescape(escaped, '"');
	}

	public static String escapeTabs(String quoted) { 
		return escape(quoted, '\t');
	}
	
	public static String escapeSpaces(String quoted) { 
		return escape(quoted, ' ');
	}

	public static String unescapeTabs(String escaped) { 
		return unescape(escaped, '\t');
	}

	public static String escapeCommas(String quoted) { 
		return escape(quoted, ',');
	}

	public static String unescapeCommas(String escaped) { 
		return unescape(escaped, ',');
	}
	
	public static String unescapeSpaces(String escaped) { 
		return unescape(escaped, ' ');
	}
}
