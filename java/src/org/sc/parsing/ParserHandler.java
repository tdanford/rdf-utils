package org.sc.parsing;

public interface ParserHandler { 
	public void handleValue(String v);
	public void handleQuotedValue(String v);
	public void handleGroupStart();
	public void handleGroupEnd();
}
