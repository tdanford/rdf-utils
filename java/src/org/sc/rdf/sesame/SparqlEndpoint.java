package org.sc.rdf.sesame;

import info.aduna.iteration.CloseableIteration;

import java.io.IOException;
import java.util.Collection;
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

import tdanford.autodata.AutoModel;
import tdanford.autodata.selectors.Selector;
import tdanford.resources.Heavy;

public class SparqlEndpoint implements Heavy {
	
	private Prefixes prefixes;
	private Repository repos;

	public SparqlEndpoint(Repository s) throws RepositoryException {
		prefixes = Prefixes.DEFAULT;
		repos = s;
	}
	
	public Prefixes getPrefixes() { return prefixes; }
	
	public void dispose() { 
	}
	
	public SesameBindingTable<Value> evaluate(String queryString) {
		SesameBindingTable<Value> table = null;
		RepositoryConnection cxn = null;
		try { 
			cxn = repos.getConnection();
			
			TupleQuery tupleQuery = 
				cxn.prepareTupleQuery(QueryLanguage.SPARQL, 
						String.format(
								"%s\n%s", 
								prefixes.getSparqlPrefixStanza(), 
								queryString));
			
			TupleQueryResult result = tupleQuery.evaluate();
			table = new SesameBindingTable<Value>(Value.class, result);
			
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} finally { 

			if(cxn != null) { 
				try {
					cxn.close();
				} catch (RepositoryException e) {
					e.printStackTrace();
				}
			}
		}

		return table;
	}
}
