package tdanford.graph;

import java.net.URI;
import java.util.*;

public interface PropertyGraph {
	public Iterator<Identifier> allIdentifiers();
	public boolean hasIdentifier(Identifier id);
	public Set<Identifier> followForward(Identifier id, Identifier prop);
	public Set<Identifier> followReverse(Identifier id, Identifier prop);
	public boolean hasForward(Identifier id, Value value);
	public boolean hasReverse(Identifier id, Value value);
}

