package tdanford.db;

public interface TuplePredicate {

	public boolean accepts(Tuple t);
	
	
	
	public static class KEYEQ implements TuplePredicate { 
		private String name;
		private Object value;
		
		public KEYEQ(String n, Object v) { 
			name = n;
			value = v;
		}

		public boolean accepts(Tuple t) {
			Integer idx = t.schema().nameIndex(name);
			return Tuple.isEqualWithNull(value, t.value(idx));
		}
	}
	
	public static class ALL implements TuplePredicate {
		public boolean accepts(Tuple t) {
			return true;
		} 
	}

	public static class NONE implements TuplePredicate {
		public boolean accepts(Tuple t) {
			return false;
		} 
	}

	public static class NOT implements TuplePredicate { 
		private TuplePredicate inner;
		public NOT(TuplePredicate tp) { 
			inner = tp;
		}
		public boolean accepts(Tuple t) { 
			return !inner.accepts(t);
		}
	}

	public static class AND implements TuplePredicate { 
		private TuplePredicate[] inner;
		public AND(TuplePredicate... tp) { 
			inner = tp.clone();
		}
		public boolean accepts(Tuple t) { 
			for(TuplePredicate tp : inner) { 
				if(!tp.accepts(t)) { 
					return false; 
				}
			}
			return true;
		}
	}

	public static class OR implements TuplePredicate { 
		private TuplePredicate[] inner;
		
		public OR(TuplePredicate... tp) { 
			inner = tp.clone();
		}
		
		public boolean accepts(Tuple t) { 
			for(TuplePredicate tp : inner) { 
				if(tp.accepts(t)) { 
					return true; 
				}
			}
			return false;
		}
	}

}
