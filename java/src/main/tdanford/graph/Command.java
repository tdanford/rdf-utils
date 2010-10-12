package tdanford.graph;

import java.util.Collection;

public class Command {

	private Generator generator;
	private Transform[] transforms;
	
	public Command(Generator g, Collection<Transform> ts) {
		if(g == null) { throw new IllegalArgumentException("null Generator argument"); }
		generator = g;
		transforms = ts.toArray(new Transform[0]);
	}
	
	public Generator getGenerator() { return generator; }
	public int length() { return transforms.length; }
	public Transform getTransform(int i) { return transforms[i]; }
}

class Expression { 
	
	public String key;
	public String[] args;
	
	public Expression(String k, String... as) { 
		key = k;
		args = as.clone();
	}
	
	public Expression(String k, Collection<String> as) {  
		key = k;
		args = as.toArray(new String[0]);
	}
}

class Generator extends Expression { 
	public Generator(String k, String... as) { 
		super(k, as);
	}
}

class Transform extends Expression {
	public Transform(String k, String... as) { 
		super(k, as);
	}
}


