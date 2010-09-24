package org.sc.rdf.sesame;

import java.lang.reflect.*;
import java.util.*;
import java.util.regex.*;

import tdanford.autodata.AutoModel;

public class SparqlModel extends AutoModel {

	public SparqlModel() { 
	}
	
	public String createSparqlQuery() { 
		StringBuilder sb = new StringBuilder();
		ArrayList<String[]> fields = new ArrayList<String[]>();
		Field[] fs = getClass().getFields();
		Pattern p = Pattern.compile("([^_]*)_(.+)");
		
		for(int i = 0; i < fs.length; i++) {
			
			int modifiers = fs[i].getModifiers();
			if((modifiers & Modifier.STATIC) == 0 && 
			   (modifiers & Modifier.PUBLIC) != 0) { 
		
				if(AutoModel.isSubclass(fs[i].getType(), String.class)) { 
					String fieldName = fs[i].getName();
					Matcher m = p.matcher(fieldName);
				
					if(m.matches()) { 
						String prefix = m.group(1);
						String name = m.group(2);
						fields.add(new String[] { prefix, name });
					}
				}
			}
		}
		
		sb.append("select");
		String myvar = getClass().getSimpleName();
		sb.append(String.format(" ?%s", myvar));
		for(String[] f : fields) { 
			sb.append(String.format(" ?%s", f[1]));
		}
		
		sb.append(" where { ");
		for(String[] f : fields) { 
			sb.append(String.format("?%s %s:%s ?%s . ", 
					myvar, f[0], f[1], f[1]));
		}
		
		sb.append("}");
		return sb.toString();
	}
}

