package tdanford.streaming;

public class Cast<A,B> implements Transform<A,B> {
	
	public Cast() {}

	public HeavyIterator<B> execute(A value) {
		return new SingleIterator<B>((B)value);
	} 

	public void dispose() {}
	
	public String toString() { return "cast"; }
}
