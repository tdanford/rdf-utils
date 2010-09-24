package tdanford.streaming;

import java.util.*;

import tdanford.resources.Heavy;

public class Stream<A,B> implements Heavy {
	
	private Transform<A,B> transform;
	private Iterator<A> input;
	private Sink<B> output;
	
	public Stream(Iterator<A> inp, Transform<A,B> trans, Sink<B> out) { 
		transform = trans; 
		input = inp;
		output = out;
	}
	
	public Stream(Iterator<A> inp, Transform<A,B> trans) { 
		this(inp, trans, new DrainingSink<B>());
	}
	
	public Sink<B> getSink() { return output; }
	public Transform<A,B> getTransform() { return transform; }
	public boolean hasNext() { return input.hasNext(); }
	
	public void execute() { 
		output.consume(transform.execute(input.next()));
	}
	
	public void complete() { 
		while(hasNext()) {  
			execute();
		}
	}
	
	public void dispose() { 
		if(input instanceof Heavy) { ((Heavy)input).dispose(); }
		input = null;
		transform.dispose(); transform = null;
		output.dispose(); output = null;
	}
}
