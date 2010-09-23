package tdanford.commons;

public interface Predicate<T> {
	public boolean accepts(T value);
	
	public static class All<V> implements Predicate<V> { 
		public boolean accepts(V val) { return true; }
	}
	public static class None<V> implements Predicate<V> { 
		public boolean accepts(V val) { return false; }
	}
	public static class And<V> implements Predicate<V> {
		private Predicate<V>[] fs;
		public And(Predicate<V>... ffs) { 
			fs = ffs.clone();
		}
		public boolean accepts(V val) { 
			for(Predicate<V> f : fs) { 
				if(!f.accepts(val)) { return false; }
			}
			return true;
		}
	}
}
