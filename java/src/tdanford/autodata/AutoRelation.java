package tdanford.autodata;

import java.util.*;
import java.io.*;

import tdanford.autodata.selectors.*;

public class AutoRelation<T> {

	private Class<T> modelClass;
	private ArrayList<Selector> selectors;
	private LinkedList<T> models;
	
	public AutoRelation(Class<T> mclass, Collection<Selector<T,?>> sels) {
		modelClass = mclass;
		selectors = new ArrayList<Selector>(sels);
		models = new LinkedList<T>();
	}
	
	public Class<T> getModelClass() { return modelClass; }
	public int size() { return models.size(); }
	public int width() { return selectors.size(); }
	
	public Iterator<T> models() { return models.iterator(); }
	public Selector<T,?> getSelector(int i) { return selectors.get(i); }
	public void add(T m) { models.addLast(m); }
	public boolean contains(T m) { return models.contains(m); }
	
	public void saveTable(File output) throws IOException { 
		PrintStream ps = new PrintStream(new FileOutputStream(output));
		printTable(ps);
		ps.close();
	}
	
	public void printTable(PrintStream ps) { 
		Selector idsel = new ToStringSelector();
		for(T model : models) { 
			AutoUtils.tabOutput(model, idsel, selectors, ps);
		}
	}
	
	public void loadTable(File input) throws IOException { 
		BufferedReader br = new BufferedReader(new FileReader(input));
		loadTable(br);
		br.close();
	}
	
	public void loadTable(BufferedReader br) throws IOException { 
		String line = null;
		while((line = br.readLine()) != null) { 
			T model = (T)AutoUtils.tabInput(modelClass, selectors, line);
			models.add(model);
		}
	}
}
