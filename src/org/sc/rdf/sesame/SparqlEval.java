package org.sc.rdf.sesame;

import info.aduna.iteration.CloseableIteration;

import java.io.IOException;
import java.util.Set;


import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.Value;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.query.Binding;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.Sail;
import org.openrdf.sail.SailConnection;
import org.openrdf.sail.SailException;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.sc.rdf.Prefixes;

import tdanford.resources.Heavy;

public class SparqlEval implements Heavy {
	
	private Sail sail;
	private Repository repos;

	public SparqlEval(Sail s) throws RepositoryException { 
		sail = s;
		
		repos = new SailRepository(sail);
		repos.initialize();
	}
	
	public void dispose() { 
		try {
			repos.shutDown();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		try {
			sail.shutDown();
		} catch (SailException e) {
			e.printStackTrace();
		}
	}

	public String evaluate(String queryString) {
		StringBuilder sb = new StringBuilder();
		try { 
			RepositoryConnection cxn = repos.getConnection();
			SailConnection sConnect = sail.getConnection();
			TupleQuery tupleQuery = cxn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
			TupleQueryResult result = tupleQuery.evaluate();
			try {
				Set<String> names = null;
				int c = 0;
				while(result.hasNext()) { 
					BindingSet bindings = result.next();
					c += 1;
					if(names == null) { 
						names = bindings.getBindingNames();
						for(String n : names) { 
							sb.append("\t" + n);
						}
						sb.append("\n");
					}
					for(String name : names) {
						if(bindings.hasBinding(name)) { 
							Binding binding = bindings.getBinding(name);
							Value value = binding.getValue();
							if(value instanceof Resource) {
								String valueStr = value.toString();
								Resource valueResc = (Resource)value;
								CloseableIteration<? extends Statement,SailException> itr = 
									sConnect.getStatements(valueResc, RDFS.LABEL, null, true);
								if(itr.hasNext()) { 
									Statement labelStmt = itr.next();
									Value labelValue = labelStmt.getObject();
									valueStr = (labelValue instanceof Literal) ? 
											((Literal)labelValue).getLabel() : 
											labelValue.toString();
								}
								itr.close();
								sb.append("\t" + valueStr);
							} else if (value instanceof Literal) {  
								Literal lit = (Literal)value;
								if(lit.getDatatype().toString().equals("http://www.w3.org/2001/XMLSchema#string")) { 
									sb.append("\t" + lit.getLabel());
								} else { 
									sb.append("\t" + value.toString());
								}
							} else { 
								sb.append("\t" + value.toString());
							}
						} else { 
							sb.append("\t??");
						}
					}
					
					sb.append("\n");
				}
				
				System.out.println(String.format("# Results: %d", c));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				result.close();
			}
			cxn.close();
			sConnect.close();

		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} catch (SailException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
