package org.sc.rdf.sesame;

import info.aduna.iteration.CloseableIteration;

import java.lang.reflect.Array;
import java.util.*;

import java.util.regex.*;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.Binding;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryResult;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.sail.*;
import org.sc.rdf.BindingTable;

public class SesameBindingTable<Value> extends BindingTable<Value> {
	
	public SesameBindingTable(Class<Value> cls, TupleQueryResult result) throws QueryEvaluationException {
		super(cls);
		int c = 0;
		while(result.hasNext()) { 
			BindingSet set = result.next();
			if(c==0) { 
				createNames(set);
			}
			
			Value[] row = (Value[])Array.newInstance(cls, width());
			for(int i = 0; i < row.length; i++) { 
				Value value = (Value)set.getBinding(getNames()[i]).getValue();
				row[i] = value;
			}
			addRow(row);
			c += 1;
		}
		
		result.close();
		//System.out.println(String.format("BindingTable # Rows: %d", c));
	}
	
	public SesameBindingTable(Class<Value> cls, CloseableIteration<? extends Statement,SailException> itr) throws SailException {
		super(cls);
		setNames(new String[] { "s", "p", "o" });
		while(itr.hasNext()) { 
			Statement stmt = itr.next();
			Value[] row = (Value[])Array.newInstance(cls, 3);
			row[0] = (Value)stmt.getSubject(); 
			row[1] = (Value)stmt.getPredicate();
			row[2] = (Value)stmt.getObject();
			addRow(row);
		}
		
		itr.close();
	}
	
	public SesameBindingTable(Class<Value> cls, RepositoryResult<Statement> result) throws RepositoryException {
		super(cls);
		setNames(new String[] { "s", "p", "o" });
		while(result.hasNext()) { 
			Statement stmt = result.next();
			Value[] row = (Value[])Array.newInstance(cls, 3);
			row[0] = (Value)stmt.getSubject(); 
			row[1] = (Value)stmt.getPredicate();
			row[2] = (Value)stmt.getObject();
			addRow(row);
		}
		
		result.close();
	}
	
	protected void createNames(BindingSet set) { 
		setNames(set.getBindingNames().toArray(new String[0]));
	}
}
