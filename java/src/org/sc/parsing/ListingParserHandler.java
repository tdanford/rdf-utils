package org.sc.parsing;

import java.util.LinkedList;

public class ListingParserHandler implements ParserHandler {
	
	public LinkedList<String> list;
	
	public ListingParserHandler() { 
		list = new LinkedList<String>();
	}

	public void handleGroupEnd() {
		throw new IllegalArgumentException("GROUPEND");
	}

	public void handleGroupStart() {
		throw new IllegalArgumentException("GROUPSTART");
	}

	public void handleQuotedValue(String v) {
		list.addLast(v);
	}

	public void handleValue(String v) {
		list.addLast(v);
	} 
	
}