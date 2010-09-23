package org.sc.rdf.model;

import info.aduna.iteration.CloseableIteration;
import info.aduna.iteration.EmptyIteration;

import java.io.File;

import org.openrdf.model.Namespace;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.BindingSet;
import org.openrdf.query.Dataset;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.algebra.TupleExpr;
import org.openrdf.query.algebra.evaluation.EvaluationStrategy;
import org.openrdf.query.algebra.evaluation.TripleSource;
import org.openrdf.query.algebra.evaluation.impl.EvaluationStrategyImpl;
import org.openrdf.query.impl.EmptyBindingSet;
import org.openrdf.sail.Sail;
import org.openrdf.sail.SailConnection;
import org.openrdf.sail.SailException;
import org.openrdf.sail.helpers.SailBase;
import org.openrdf.sail.helpers.SailConnectionBase;

public class RDFManagerSail implements Sail {
	
	private RDFObjectManager manager;
	private ValueFactory valueFactory;

	public SailConnection getConnection() throws SailException {
		return null;
	}

	public File getDataDir() {
		return null;
	}

	public ValueFactory getValueFactory() {
		return null;
	}

	public void initialize() throws SailException {
	}

	public boolean isWritable() throws SailException {
		return false;
	}

	public void setDataDir(File arg0) {
	}

	public void shutDown() throws SailException {
	}
	
	private class RDFTripleSource implements TripleSource {

		public CloseableIteration<? extends Statement, QueryEvaluationException> getStatements(
				Resource subj, URI pred, Value obj, Resource... ctxts)
				throws QueryEvaluationException {
			return null;
		}

		public ValueFactory getValueFactory() {
			return valueFactory;
		} 
	}

	private class RDFManagerConnection extends SailConnectionBase {
		
		private boolean closed;

		public RDFManagerConnection(SailBase sailBase) {
			super(sailBase);
			closed = false;
		}

		protected CloseableIteration<? extends Statement, SailException> getStatementsInternal(
				Resource subj, URI pred, Value obj, boolean inferred,
				Resource... ctxts) throws SailException {
			return null;
		}

		protected CloseableIteration<? extends BindingSet, QueryEvaluationException> 
			evaluateInternal(TupleExpr expr, Dataset dset, BindingSet bset, boolean inferred)
				throws SailException {
			
			EvaluationStrategy strategy = new EvaluationStrategyImpl(new RDFTripleSource());
			try {
				return strategy.evaluate(expr, EmptyBindingSet.getInstance());

			} catch (QueryEvaluationException e) {
				e.printStackTrace();
				return new EmptyIteration<BindingSet,QueryEvaluationException>();
			}
		}

		protected void addStatementInternal(Resource arg0, URI arg1,
				Value arg2, Resource... arg3) throws SailException {
			
			throw new SailException("Cannot call addStatement() on RDFManagerConnection");
		}

		protected void removeStatementsInternal(Resource arg0, URI arg1,
				Value arg2, Resource... arg3) throws SailException {
			throw new SailException("Cannot call removeStatements() on RDFManagerConnection");
		}

		protected void clearInternal(Resource... arg0) throws SailException {
		}

		protected void clearNamespacesInternal() throws SailException {
		}

		protected void closeInternal() throws SailException {
			closed = true;
		}

		protected void commitInternal() throws SailException {
		}

		protected CloseableIteration<? extends Resource, SailException> getContextIDsInternal()
				throws SailException {
			return null;
		}

		protected String getNamespaceInternal(String prefix) throws SailException {
			return null;
		}

		protected CloseableIteration<? extends Namespace, SailException> getNamespacesInternal()
				throws SailException {
			return null;
		}

		protected void removeNamespaceInternal(String arg0)
				throws SailException {
		}

		protected void rollbackInternal() throws SailException {
		}

		protected void setNamespaceInternal(String arg0, String arg1)
				throws SailException {
		}

		protected long sizeInternal(Resource... arg0) throws SailException {
			return 0;
		}

		protected void startTransactionInternal() throws SailException {
		}

	}

}

