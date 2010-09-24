package org.sc.rdf;

import java.util.*;

public class ResultTable {

	private String[] vars;
	private ArrayList<ResultRow> rows;
	private Map<String,Integer> varIndices;
	
	private int idx;
	
	public ResultTable(String[] vs) { 
		vars = vs.clone();
		varIndices = new TreeMap<String,Integer>();
		for(int i = 0; i < vars.length; i++) { varIndices.put(vars[i], i); }
		rows = new ArrayList<ResultRow>();
		idx = 0;
	}
	
	public ResultTable(Collection<String> vs) { 
		this(vs.toArray(new String[0]));
	}
	
	public void addRow(Object... vals) { 
		rows.add(new ResultRow(vals));
	}
	
	public void addRow(Collection vals) { 
		rows.add(new ResultRow(vals));
	}
	
	public boolean hasNext() {  
		return idx < rows.size();
	}
	
	public Object[] next() { 
		return rows.get(idx++).values.toArray();
	}
	
	public void reset() { idx = 0; }
	
	public String var(int i) { return vars[i]; }
	public Integer varIndex(String var) { return varIndices.get(var); }
	public int size() { return rows.size(); }
	public int numVars() { return vars.length; }
	
	public <T> T value(String v) { 
		return (T)rows.get(idx).values.get(varIndices.get(v));
	}
	
	private class ResultRow { 
		
		public ArrayList values;
		
		public ResultRow(Object... vals) { 
			values = new ArrayList();
			for(Object val : vals) { 
				values.add(val);
			}
		}
		
		public ResultRow(Collection c) { 
			values = new ArrayList(c);
		}
		
	}

	public String[] vars() {
		return vars;
	}
}

