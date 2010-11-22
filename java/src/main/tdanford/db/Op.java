package tdanford.db;

public interface Op extends DbItr {
	
	public int length();
	public Op getSubOp(int i);
	public void setSubOp(int i, Op j);
	
	public static abstract class Leaf implements Op {
		public Op getSubOp(int i) {
			return null;
		}

		public int length() {
			return 0;
		}

		public void setSubOp(int i, Op j) {
		}

	}
}
