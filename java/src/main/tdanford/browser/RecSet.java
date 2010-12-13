package tdanford.browser;

import java.util.*;
import java.util.regex.*;

import tdanford.commons.Trie;

public class RecSet {
	
	private TreeSet<Rec> recs;
	
	public RecSet(Rec... rs) { 
		this(Arrays.asList(rs));
	}
	
	public RecSet(Collection<Rec> rs) { 
		recs = new TreeSet<Rec>(rs);
	}
	
	public int size() { return recs.size(); }
	
	public boolean isEmpty() { return recs.isEmpty(); }
	
	public Iterator<Rec> values() { return recs.iterator(); }
	
	public Rec choose() { 
		Random rand = new Random();
		int k = rand.nextInt(size());
		Iterator<Rec> rs = recs.iterator();
		Rec chosen = null;
		for(; k > 0 && rs.hasNext(); chosen = rs.next(), k--) {}
		return chosen;
	}
	
	public RecSet union(RecSet set) {
		TreeSet<Rec> union = new TreeSet<Rec>(recs);
		union.addAll(set.recs);
		return new RecSet(union);
	}
	
	public RecSet subtract(RecSet set) { 
		TreeSet<Rec> subtraction = new TreeSet<Rec>(recs);
		subtraction.removeAll(set.recs);
		return new RecSet(subtraction);		
	}
	
	public void addAll(RecSet set) { 
		recs.addAll(set.recs);
	}
	
	public void add(Rec... rs) { 
		recs.addAll(Arrays.asList(rs));
	}
	
	public RecSet forward(RecQuery query, Rec property) { 
		RecSet result = new RecSet();
		for(Rec subject : recs) { 
			result.addAll(query.forward(subject, property));
		}
		return result;
	}
	
	public RecSet backward(RecQuery query, Rec property) { 
		RecSet result = new RecSet();
		for(Rec object : recs) { 
			result.addAll(query.backward(object, property));
		}
		return result;
	}
}

interface RecSetTransform { 
	public RecSet transform(RecQuery query, RecSet input, RecSet... params);
}

class AllTransform implements RecSetTransform {
	public RecSet transform(RecQuery query, RecSet input, RecSet... params) {
		return query.all();
	} 
}

class ChooseTransform implements RecSetTransform {
	public RecSet transform(RecQuery query, RecSet input, RecSet... params) {
		return new RecSet(input.choose());
	} 
}

class LookupTransform implements RecSetTransform {

	public RecSet transform(RecQuery query, RecSet input, RecSet... params) {
		RecSet labelSet = params[0];
		RecSet output = new RecSet();
		Iterator<Rec> labels = labelSet.values();
		while(labels.hasNext()) { 
			Rec label = labels.next();
			output.addAll(query.labeled(label.value()));
		}
		return output;		
	} 
}

class ForwardPropsTransform implements RecSetTransform {
	public RecSet transform(RecQuery query, RecSet input, RecSet... params) {
		RecSet output = new RecSet();
		Iterator<Rec> subject = input.values();
		while(subject.hasNext()) { 
			output.addAll(query.forwardProperties(subject.next()));
		}
		return output;
	} 
}

class ReversePropsTransform implements RecSetTransform {
	public RecSet transform(RecQuery query, RecSet input, RecSet... params) {
		RecSet output = new RecSet();
		Iterator<Rec> subject = input.values();
		while(subject.hasNext()) { 
			output.addAll(query.backwardProperties(subject.next()));
		}
		return output;
	} 
}

class ForwardTransform implements RecSetTransform {
	public RecSet transform(RecQuery query, RecSet input, RecSet... params) {
		RecSet propSet = params[0];
		Iterator<Rec> props = propSet.values();
		
		RecSet output = new RecSet();
		while(props.hasNext()) { 
			Rec prop = props.next();
			output.addAll(input.forward(query, prop));
		}
		
		return output;
	} 
}

class ForwardStarTransform implements RecSetTransform {
	public RecSet transform(RecQuery query, RecSet input, RecSet... params) {
		
		ForwardTransform forward = new ForwardTransform();
		
		RecSet output = new RecSet();
		RecSet nextSet = forward.transform(query, input, params).subtract(output);
		
		while(!nextSet.isEmpty()) { 
			output.addAll(nextSet);
			nextSet = forward.transform(query, nextSet, params).subtract(output);
		}
		
		return output;
	} 
}

class ReverseTransform implements RecSetTransform { 
	public RecSet transform(RecQuery query, RecSet input, RecSet... params) { 
		RecSet propSet = params[0];
		Iterator<Rec> props = propSet.values();
		
		RecSet output = new RecSet();
		while(props.hasNext()) { 
			Rec prop = props.next();
			output.addAll(input.backward(query, prop));
		}
		
		return output;		
	}
}

class ReverseStarTransform implements RecSetTransform {
	public RecSet transform(RecQuery query, RecSet input, RecSet... params) {
		
		ForwardTransform reverse = new ForwardTransform();
		
		RecSet output = new RecSet();
		RecSet nextSet = reverse.transform(query, input, params).subtract(output);
		
		while(!nextSet.isEmpty()) { 
			output.addAll(nextSet);
			nextSet = reverse.transform(query, nextSet, params).subtract(output);
		}
		
		return output;
	} 
}




