package tdanford.db;

public interface JoinPredicate {
	public boolean shouldJoin(Tuple t1, Tuple t2);
	
	public static class Inner implements JoinPredicate { 
		private String key;
		
		public Inner(String k) { key = k; }

		public boolean shouldJoin(Tuple t1, Tuple t2) {
			int t1idx = t1.schema().nameIndex(key);
			int t2idx = t2.schema().nameIndex(key);
			return Tuple.isEqualWithNull(t1.value(t1idx), t2.value(t2idx));
		}
	}
}
