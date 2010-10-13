package tdanford.commons;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtils {

	public static void main(String[] args) {
		String str = "\"Foo		Bar;,Blah Grok ;;%	\"Yaaar.";
		String esc = escapeTabs(str);
		String unesc = unescapeTabs(esc);
		System.out.println(String.format("Original : [%s]", str));
		System.out.println(String.format("Unescaped: [%s]", unesc));
		System.out.println(String.format("Escaped  : [%s]", esc));
	}
	
	public static String escape(String q) {
		try {
			return URLEncoder.encode(q, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(System.err);
			return q;
		}
	}
	
	public static String unescape(String e) { 
		try {
			return URLDecoder.decode(e, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(System.err);
			return e;
		}
	}
	
	/**
	 * @deprecated
	 */
	public static String escapeQuotes(String quoted) { 
		return escape(quoted);
	}

	/**
	 * @deprecated
	 */
	public static String unescapeQuotes(String escaped) { 
		return unescape(escaped);
	}

	/**
	 * @deprecated
	 */
	public static String escapeTabs(String quoted) { 
		return escape(quoted);
	}
	
	/**
	 * @deprecated
	 */
	public static String escapeSpaces(String quoted) { 
		return escape(quoted);
	}

	/**
	 * @deprecated
	 */
	public static String unescapeTabs(String escaped) { 
		return unescape(escaped);
	}

	/**
	 * @deprecated
	 */
	public static String escapeCommas(String quoted) { 
		return escape(quoted);
	}

	/**
	 * @deprecated
	 */
	public static String unescapeCommas(String escaped) { 
		return unescape(escaped);
	}
	
	/**
	 * @deprecated
	 */
	public static String unescapeSpaces(String escaped) { 
		return unescape(escaped);
	}
}
