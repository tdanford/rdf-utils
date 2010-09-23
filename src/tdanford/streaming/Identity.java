package tdanford.streaming;

public class Identity<A> implements Transform<A, A> {
	public Identity() {}
	
	public HeavyIterator<A> execute(A value) {
		return new SingleIterator<A>(value);
	}
	
	public void dispose() {}
	
	public String toString() { return "identity"; }
}
