package org.sc.rdf;

import java.util.*;
import java.util.regex.*;
import java.io.*;

import javax.swing.JFrame;

import org.sc.parsing.CLex;
import org.sc.parsing.CToken;
import org.sc.parsing.ListingParserHandler;
import org.sc.parsing.ParserHandler;
import org.sc.parsing.PushbackIterator;
import org.sc.rdf.model.RDFObject;
import org.sc.rdf.model.RDFObjectManager;
import org.sc.rdf.model.RDFSelection;

import tdanford.commons.Predicate;

public class InteractiveSelection {
	
	private Map<String,RDFSelection> saved;
	private Stack<RDFSelection> selstack;
	private Map<String,LinkedList<String>> savedCommands;
	private RDFSelection current;
	private RDFObjectManager manager;

	public InteractiveSelection(RDFObjectManager m) { 
		manager = m;
		current = null;
		saved = new TreeMap<String,RDFSelection>();
		selstack = new Stack<RDFSelection>();
		savedCommands = new TreeMap<String,LinkedList<String>>();
	}
	
	public int size() { 
		return current == null ? 0 : current.size();
	}
	
	// neblod zin!
	public boolean interpret(LinkedList<String> commands, PrintWriter ps) { 
		String cmd = commands.removeFirst();
		if(cmd.equals("p")) { 
			if(current != null) { 
				if(!commands.isEmpty()) {  
					current = current.p(commands.removeFirst());
				} else { 
					current = current.p();
				}
			}
		} else if (cmd.equals("rawpreds")) { 
			current = current.rawpreds();
			
		} else if (cmd.equals("p*")) { 
			if(!commands.isEmpty()) {  
				current = current.pstar(commands.removeFirst());
			} else { 
				current = current.pstar();
			}
			
		} else if (cmd.equals("pp")) { 
			if(current != null) { 
				if(!commands.isEmpty()) { 
					current = current.pp(commands.removeFirst());
				} else { 
					current = current.pp();					
				}
			}

		} else if (cmd.equals("pp*")) { 
			if(!commands.isEmpty()) {  
				current = current.ppstar(commands.removeFirst());
			} else { 
				current = current.ppstar();
			}
			
			
		} else if (cmd.equals("t")) {
			if(current != null) { 
				current = current.t();
			}

		} else if (cmd.equals("tt")) {
			if(current != null) { 
				current = current.tt();
			}
			
		} else if (cmd.equals("ch")) { 
			chooseAndPrint(ps);
			
		} else if (cmd.equals("has")) {
			if(selstack.isEmpty() && commands.size() >= 2) { 
				String key = commands.removeFirst();
				String value = commands.removeFirst();
				current = current.hasData(key, value);					
			}
			
		} else if (cmd.equals("hasstack")) {
			if(!commands.isEmpty() && !selstack.isEmpty()) {
				String name = commands.removeFirst();
				current = current.hasValueIn(name, selstack.peek());
			}
			
		} else if (cmd.equals("cmd")) { 
			if(!commands.isEmpty()) { 
				String name = commands.removeFirst();
				LinkedList<String> command = new LinkedList<String>(commands);
				savedCommands.put(name, command);
				commands.clear();
			}
			
		} else if (cmd.equals("tableout")) { 
			if(commands.size() >= 2 && current != null) { 
				String filename = commands.removeFirst();
				String[] cols = commands.removeFirst().split(",");
				try {
					current.printTable(new File(filename), cols);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} else if (cmd.equals("push")) {
			if(current != null) { 
				selstack.push(new RDFSelection(current));
			}
		} else if (cmd.equals("pop")) { 
			if(!selstack.isEmpty()) { 
				current = selstack.pop();
			}
		} else if (cmd.equals("dup")) { 
			if(!selstack.isEmpty()) { 
				current = new RDFSelection(selstack.peek());
			}
			
		} else if (cmd.equals("ls")) {
			if(current != null) { 
				current.list(ps);
			}
			
		} else if (cmd.equals("pr")) {
			if(current != null) { 
				current.print(ps);
			}
			
		} else if (cmd.equals("triples")) { 
			if(current != null && commands.size() > 0) {
				int depth = Integer.parseInt(commands.removeFirst());
				current.printTriples(ps, depth);
			}
			
		} else if (cmd.equals("load")) { 
			if(!commands.isEmpty() && saved.containsKey(commands.getFirst())) {
				current = new RDFSelection(saved.get(commands.removeFirst()));
			}
						
		} else if (cmd.equals("save")) {
			if(current != null && !commands.isEmpty()) { 
				saved.put(commands.removeFirst(), new RDFSelection(current));
			}
			
		} else if (cmd.equals("f")) { 
			if(!commands.isEmpty()) { 
				current = manager.select(commands.removeFirst());
			}
			
		} else if (cmd.equals("get")) { 
			if(current != null && !commands.isEmpty()) {
				String[] narray = commands.removeFirst().split(",");
				Integer[] nums = new Integer[narray.length];
				for(int i =0; i < nums.length; i++) { nums[i] = Integer.parseInt(narray[i]); }
				current = current.pick(nums);
			}
			
		} else if (cmd.equals("choose")) { 
			if(!commands.isEmpty()) { 
				int idx = Integer.parseInt(commands.removeFirst());
				current = current.choose(idx);
			}
			
		} else if (cmd.equals("where")) { 
			if(current != null && !commands.isEmpty() && !selstack.isEmpty()) { 
				RDFSelection targets = selstack.peek();
				current = current.filter(commands.removeFirst(), targets);
			}
			
		} else if (cmd.equals("-")) { 
			if(!commands.isEmpty()) { 
				current = current.remove(new KeyMatchPredicate(commands.removeFirst()));
			}
			
		} else if (cmd.equals("+")) {
			if(!commands.isEmpty()) { 
				String matchString = commands.removeFirst();
				RDFSelection add = manager.selectAll(); 
				add.subset(new KeyMatchPredicate(matchString));
				current = current.add(add.objects());
			}
			
		} else if (cmd.equals("append")) { 
			if(current != null && !selstack.isEmpty()) { 
				selstack.peek().add(current.objects());
			}
			
		} else if (cmd.equals("subj")) { 
			if(current != null) { 
				current = current.subj();
			}
			
		} else if (cmd.equals("obj")) { 
			if(current != null) { 
				current = current.obj();
			}
			
		} else if (cmd.equals("in")) { 
			if(current != null) { 
				current = current.inp();
			}

		} else if (cmd.equals("out")) { 
			if(current != null) { 
				current = current.outp();
			}
			
		} else if (cmd.equals("swap")) { 
			if(selstack.size() >= 2) { 
				RDFSelection top = selstack.pop();
				RDFSelection next = selstack.pop();
				selstack.push(top);
				selstack.push(next);
			}
			
		} else if (cmd.equals("intersect")) {
			if(selstack.size() >= 2) { 
				RDFSelection d1 = selstack.elementAt(1);
				RDFSelection d2 = selstack.elementAt(0);
				current = new RDFSelection(d1).intersect(d2);
			}
			
		} else if (cmd.equals("diff")) { 
			if(selstack.size() >= 2) { 
				RDFSelection d1 = selstack.elementAt(1);
				RDFSelection d2 = selstack.elementAt(0);
				current = new RDFSelection(d1).subtract(d2);
			}
			
		} else if (cmd.equals("@")) { 
			if(commands.size() > 0) { 
				int idx = Integer.parseInt(commands.removeFirst());
				if(idx >= 0 && selstack.size() > idx) { 
					current = new RDFSelection(selstack.get(idx));
				}
			}
			
		} else if (cmd.equals("subset")) { 
			if(current != null && !selstack.isEmpty()) { 
				Object value = String.valueOf(current.isSubsetOf(selstack.peek()));
				selstack.push(current);
				current = new RDFSelection(manager, value);
				System.out.println(value.toString());
			}
			
		} else if (cmd.equals("equals")) { 
			if(current != null && !selstack.isEmpty()) { 
				Object value = String.valueOf(current.isEqualTo(selstack.peek()));
				selstack.push(current);
				current = new RDFSelection(manager, value);
				System.out.println(value.toString());
			}			
			
		} else if (cmd.equals("quote")) { 
			if(!commands.isEmpty()) { 
				current = new RDFSelection(manager, commands.removeFirst());
			}
						
		} else if (cmd.equals("sparql")) { 
			if(commands.size() >= 1) { 
				String whereString = commands.removeFirst();
				String query = String.format("select ?x where { %s . }", whereString);
				
			}
			
		} else if (cmd.equals("with")) { 
			if(current != null && commands.size() >= 1) { 
				String pred = commands.removeFirst();
				current = current.withPredicate(pred);
			}
			
		} else if (cmd.equals("all")) { 	
			current = manager.selectAll();
			
		} else if (cmd.equals("?")) {
			if(!savedCommands.isEmpty()) { 
				ps.println("Commands: ");
				for(String c : savedCommands.keySet()) { 
					ps.println(String.format("%s = %s", c, savedCommands.get(c)));
				}
			}
			if(!saved.isEmpty()) { 
				ps.println("Saved Data: ");
				for(String k : saved.keySet()) { 
					ps.println(String.format("%s (#%d)", k, saved.get(k).size()));
				}
			}
			if(!selstack.isEmpty()) { 
				ps.println("Stack: ");
				int i = 0;
				for(RDFSelection sel : selstack) { 
					ps.println(String.format("%d : (#%d) %s", i++, sel.size(), sel.choose().toString()));
				}
			}
			
			
		} else if (savedCommands.containsKey(cmd)) {
			Iterator<String> itr = savedCommands.get(cmd).descendingIterator();
			while(itr.hasNext()) { 
				commands.addFirst(itr.next());
			}
			
		} else { 
			System.err.println("Unknown command: \"" + cmd + "\"");
			return false;
		}
		
		return commands.size() > 0;
	}
	
	private static class KeyPredicate implements Predicate {
		private String key;
		public KeyPredicate(String k) { key = k; }
		public boolean accepts(Object o) { 
			return o instanceof RDFObject && 
				((RDFObject)o).key().equals(key);
		}
	}
	
	private static class KeyMatchPredicate implements Predicate {
		private Pattern pattern;
		public KeyMatchPredicate(String k) { pattern = Pattern.compile(k); }
		public boolean accepts(Object o) { 
			return o instanceof RDFObject &&
				pattern.matcher(((RDFObject)o).key()).matches();
		}
	}
	
	// this should be using the methods in Console. 
	public void repl() {
		try { 
			InputStream is = System.in;
			PrintWriter ps = new PrintWriter(System.out);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			ps.print(String.format("%d >", size())); ps.flush();
			while((line = br.readLine()) != null) {
				line = line.trim();
				if(line.length() > 0) { 
					interpretCommand(line, ps);
				}
				ps.print(String.format("%d >", size())); ps.flush();
			}
		} catch(IOException e) { 
			e.printStackTrace(System.err);
		}
	}
	
	public String interpretAndReturnCommand(String line) { 
		StringWriter w = new StringWriter();
		interpretCommand(line, new PrintWriter(w)); 
		return w.toString();
	}
	
	public void interpretCommand(String line, PrintWriter ps) { 
		String[] array = parseCommand(line);

		LinkedList<String> commands = new LinkedList<String>();
		for(int i = 0; i < array.length; i++) { 
			commands.addLast(array[i]);
		}
		
		while(interpret(commands, ps)) 
			{}		
	}
	
	public RDFSelection getCurrentSelection() { 
		return current;
	}
	
	public String[] parseCommand(String c) { 
		//return c.split("\\s+");
		ListingParserHandler lph = new ListingParserHandler();
		new CommandParser(c, lph).parse();
		return lph.list.toArray(new String[0]);
	}
	
	private void chooseAndPrint(PrintWriter ps) { 
		if(current != null) { 
			Object value = current.choose();
			if(value != null) { 
				if(value instanceof RDFObject) { 
					ps.println(((RDFObject)value).toTurtleString());
				} else { 
					ps.println(value.toString());
				}
			}
		}
	}

	public void setSelection(RDFSelection rdfSelection) {
		current = rdfSelection;
	}
}

class CommandStringTokenizer implements Iterator<CToken> {
	
	private String input;
	private int idx;
	public CommandStringTokenizer(String i) { 
		input = i;
		idx = 0;
	}

	public boolean hasNext() {
		return idx < input.length();
	}

	public CToken next() {
		char c = input.charAt(idx++);
		CToken.CType type = CToken.CType.CHAR;
		if(Character.isWhitespace(c)) { 
			type = CToken.CType.SPACE;
		} else if (c == '"') { 
			type = CToken.CType.QUOTE;
		} else if (c == '\\') {  
			type = CToken.CType.ESCAPE;
		} else if (c == '(') {  
			type = CToken.CType.GROUPSTART;
		} else if (c == ')') {  
			type = CToken.CType.GROUPEND;
		}
		return new CToken(type, c);
	}

	public void remove() { throw new UnsupportedOperationException(); } 
	
}

class CommandParser { 
	
	private Iterator<CLex> lexitr;
	private ParserHandler handler;
	
	public CommandParser(String c, ParserHandler h) { 
		this(new CommandStringLexer(new CommandStringTokenizer(c)), h);
	}
	
	public CommandParser(Iterator<CLex> ls, ParserHandler h) { 
		lexitr = ls;
		handler = h;
	}
	
	public void parse() { 
		int groupDepth = 0;
		while(lexitr.hasNext()) { 
			CLex lex = lexitr.next();
			if(lex.tags.contains(CLex.CTag.GROUPSTART)) {
				groupDepth += 1;
				handler.handleGroupStart();
			} else if (lex.tags.contains(CLex.CTag.GROUPEND)) {
				groupDepth -= 1;
				if(groupDepth < 0) { 
					throw new IllegalArgumentException("Extra group-end token.");
				}
				handler.handleGroupEnd();
			} else if (lex.tags.contains(CLex.CTag.QUOTED)) { 
				handler.handleQuotedValue(lex.value);
			} else { 
				handler.handleValue(lex.value);
			}
		}
		if(groupDepth != 0) { 
			throw new IllegalArgumentException("Unclosed group.");
		}
	}
}

class CommandStringLexer implements Iterator<CLex> {
	
	private PushbackIterator<CToken> tokens;
	private LinkedList<CLex> pending;
	
	public CommandStringLexer(Iterator<CToken> t) { 
		tokens = new PushbackIterator<CToken>(t);
		pending = new LinkedList<CLex>();
		findNextLex();
	}

	public void remove() { throw new UnsupportedOperationException(); }

	public boolean hasNext() {
		return !pending.isEmpty();
	}

	private void findNextLex() { 
		lexloop: while(tokens.hasNext()) {
	
			StringBuilder v = new StringBuilder();
			Set<CLex.CTag> tags = new HashSet<CLex.CTag>();
			CToken token = tokens.next();
	
			switch(token.type) { 
			case CHAR:
				v.append(token.value);
				while(tokens.hasNext() && (token = tokens.next()).type.equals(CToken.CType.CHAR)) {  
					v.append(token.value);
				}
				if(!token.type.equals(CToken.CType.CHAR)) { 
					tokens.pushback(token);
				}
				
				pending.addLast(new CLex(v.toString()));
				break lexloop;

			case SPACE:
				while(tokens.hasNext() && 
						(token = tokens.next()).type.equals(CToken.CType.SPACE)) { 
				}
				if(!token.type.equals(CToken.CType.SPACE)) { 
					tokens.pushback(token);
				}
				break;

			case QUOTE:
				while(tokens.hasNext() && !(token = tokens.next()).type.equals(CToken.CType.QUOTE)) {  
					if(token.type.equals(CToken.CType.ESCAPE)) {
						if(tokens.hasNext()) { 
							token = tokens.next();
						} else { 
							throw new IllegalArgumentException("Can't end a stream with an escape!");
						}
					}
					v.append(token.value);
				}
				pending.addLast(new CLex(v.toString()));
				break lexloop;
				
			case ESCAPE:
				throw new IllegalArgumentException("No escape marks outside of quotes!");
				
			case GROUPSTART:
				pending.addLast(new CLex(String.valueOf(token.value), CLex.CTag.GROUPSTART));
				break lexloop;
				
			case GROUPEND:
				pending.addLast(new CLex(String.valueOf(token.value), CLex.CTag.GROUPEND));
				break lexloop;
			}
		}
	}

	public CLex next() {
		CLex l = pending.removeFirst();
		if(pending.isEmpty()) { findNextLex(); }
		return l;
	} 

}
