package tdanford.rdf;

import java.util.*;
import java.util.regex.*;

public class Trie<E> {

	private Trie<E> parent;
	private String nodeText;
	private Map<Character,Trie<E>> subTries;
	private Set<E> values;

	public Trie() { 
		this("");
	}
	
	public Trie(String txt) {
		parent = null;
		nodeText = txt;
		subTries = new TreeMap<Character,Trie<E>>();
		values = new HashSet<E>();		
	}
	
	public Trie(Trie<E> p, String txt) { 
		this(txt);
		parent = p;
	}
	
	public Trie(Trie<E> p, String txt, E k) {
		this(txt);
		parent = p;
		values.add(k);
	}
	
	public Set<E> values() { return values; }
	
	public Set<E> allValues() { 
		HashSet<E> ks = new HashSet<E>(values);
		for(Character c : subTries.keySet()) { 
			ks.addAll(subTries.get(c).allValues());
		}
		return ks;
	}
	
	public void insert(String key, E value) { 
		insert(key, 0, value);
	}
	
	private void insert(String key, int offset, E value) { 
		String keyText = key.substring(offset, key.length());
		String matching = findLongestPrefix(nodeText, keyText);
		
		boolean fullKeyMatch = matching.length() == keyText.length();
		boolean fullNodeMatch = matching.length() == nodeText.length();
		
		if(fullKeyMatch && fullNodeMatch) {
			
			// if the full length of the key matches the full length of the 
			// node text, then this is the node where the value should live. 
			// add a value to the local set, and we're done.
			values.add(value);
			
		} else if (fullKeyMatch) {

			// if the full key matches only a part of the current node, then 
			// we need to splice in a new node between this one and its parent.
			Trie<E> newParent = new Trie<E>(parent, matching, value);
			parent.subTries.put(matching.charAt(0), newParent);
			parent = newParent;
			nodeText = nodeText.substring(matching.length(), nodeText.length());
			
		} else if (fullNodeMatch) {
	
			// if we've exhausted the length of the text within this node, but not 
			// the length of the text in the key, we look for the sub-trie with the 
			// next matching letter...
			char c = keyText.charAt(matching.length());
			if(subTries.containsKey(c)) {
				
				// ... if it exists, we insert the rest of the key+value there.
				subTries.get(c).insert(key, offset+matching.length(), value);
				
			} else { 
				// .. if it doesn't, we insert it and we're done.
				subTries.put(c, new Trie<E>(this, keyText, value));
			}
			
		} else { 
			// if the match doesn't exhaust *either* the key, *or* the text of the current node,
			// then we need to splice in a new parent node (with the text of the match), and then 
			// add two new children to that parent.

			Trie<E> newParent = new Trie<E>(parent, matching);
			parent.subTries.put(matching.charAt(0), newParent);
			parent = newParent;
			
			String nodeRemainder = nodeText.substring(matching.length(), nodeText.length());
			String keyRemainder = keyText.substring(matching.length(), keyText.length());
			
			nodeText = nodeRemainder;
			parent.subTries.put(keyRemainder.charAt(0), new Trie<E>(newParent, keyRemainder, value));
		}
	}
	
	private static String findLongestPrefix(String s1, String s2) { 
		int len = 0;
		while(len < Math.min(s1.length(), s2.length()) && s1.charAt(len) == s2.charAt(len)) { 
			len++;
		}
		return s1.substring(0, len);
	}
}

