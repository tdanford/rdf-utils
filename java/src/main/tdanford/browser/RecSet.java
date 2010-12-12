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
	
	public Iterator<Rec> values() { return recs.iterator(); }
	
	public Rec choose() { 
		Random rand = new Random();
		int k = rand.nextInt(size());
		Iterator<Rec> rs = recs.iterator();
		Rec chosen = null;
		for(; k > 0 && rs.hasNext(); chosen = rs.next()) {}
		return chosen;
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




