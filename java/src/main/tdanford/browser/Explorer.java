package tdanford.browser;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Explorer {
	
	public static void main(String[] args) throws IOException { 
		Model m = ModelFactory.createDefaultModel();
		File f = new File(args[0]);
		String filename = f.getName().toLowerCase();
		FileInputStream fis = new FileInputStream(f);
		String lang = 
			filename.endsWith(".ttl") ? "TURTLE" : "RDF/XML";
		
		m.read(fis, null, lang);
		fis.close();
		
		JenaRecQuery query = new JenaRecQuery(m);
		Explorer explorer = new Explorer(query);
		
		System.out.print(String.format("(%d) > ", explorer.current().size())); System.out.flush();
		String line;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while((line = br.readLine()) != null) { 
			try {
				System.out.println(String.format("Interpreting: \"%s\"", line));
				explorer.interpret(line);
			} catch (RecognitionException e) {
				e.printStackTrace(System.err);
			}
			System.out.print(String.format("(%d) > ", explorer.current().size())); System.out.flush();
		}
	}

	public static int MAX_HISTORY = 20;

	private RecQuery query;
	private PrefixSet prefixes;
	
	private LinkedList<RecSet> history;
	private Map<String,RecSet> namedSets;

	private Map<Integer,ExplorerCommand> commands;
	
	public Explorer(RecQuery query) { 
		this.query = query;
		prefixes = PrefixSet.createStandardPrefixSet();
		
		prefixes.addPrefix("mgi", "http://sw.neurocommons.org/mgi#");
		
		history = new LinkedList<RecSet>();
		history.add(new RecSet());
		
		namedSets = new TreeMap<String,RecSet>();
		commands = new TreeMap<Integer,ExplorerCommand>();
		
		addCommand(new ForwardCommand());
		addCommand(new ReverseCommand());
		addCommand(new ForwardPropsCommand());
		addCommand(new ReversePropsCommand());
		addCommand(new ForwardStarCommand());
		addCommand(new ReverseStarCommand());
		addCommand(new LookupCommand());
		addCommand(new EvalCommand());

		addCommand(new AllCommand());
		addCommand(new ChooseCommand());

		addCommand(new InterpreterCommand());
	}
	
	public void addCommand(ExplorerCommand cmd) { 
		commands.put(cmd.getASTType(), cmd);
	}
	
	public RecSet current() { return history.getFirst(); }
	
	public void setCurrent(RecSet newCurrent) { 
		history.addFirst(newCurrent);
		while(history.size() > MAX_HISTORY) { 
			history.removeLast();
		}
	}
	
	private ExploreTransform compile(Collection<CommonTree> trees) { 
		ExploreTransform compiled = new StartingTransform();
		for(CommonTree t : trees) { 
			compiled = new Compose(compiled, compile(t));
		}
		return compiled;
	}
	
	private ExploreTransform compile(CommonTree astTree) { 
		assert astTree != null;
		
		int type = astTree.getType();
		
		ExplorerCommand command = commands.get(type);
		if(command==null) { 
			throw new IllegalArgumentException(
					String.format("Unrecognized command: %d", type));
		} else { 
			return command.compile(astTree);
		}
	}
	
	public void interpret(String commandLine) throws IOException, RecognitionException { 
		
		CommonTree ast = getAST(new StringReader(commandLine));
		printTree(ast, 0, null);
		
		try {
			ExploreTransform transform = null;
			
			if(commands.containsKey(ast.getType())) {
				transform = compile(ast);
				
			} else { 
				LinkedList<CommonTree> commands = new LinkedList<CommonTree>();
				for(int i = 0; i < ast.getChildCount(); i++) {
					CommonTree tree = (CommonTree) ast.getChild(i);
					commands.add(tree);
				}

				transform = compile(commands);
			}

			setCurrent(transform.evaluate(current()));
			
		} catch(Throwable t) { 
			t.printStackTrace(System.err);
		}
	}
	
	private void printTree(CommonTree ast, int depth, PrintWriter out) { 
		for(int i = 0; i < depth; i++) { System.out.print("  "); }
		int type = ast.getType();
		String typeName = type >= 0 ? ExploreGrammarParser.tokenNames[type] : "EOF";

		System.out.println(depth + ": " + typeName);
		List childList = ast.getChildren();
		if(childList != null) { 
			for(Object child : childList) { 
				CommonTree childTree = (CommonTree)child;
				printTree(childTree, depth+1, out);
			}
		}
	}
	
    private CommonTree getAST(Reader reader) throws IOException, RecognitionException {
        ExploreGrammarParser tokenParser = new ExploreGrammarParser(getTokenStream(reader));
        ExploreGrammarParser.program_return parserResult = tokenParser.program(); 
        reader.close();
        return (CommonTree) parserResult.getTree();
    }
    
    private CommonTokenStream getTokenStream(Reader reader) throws IOException {
        ExploreGrammarLexer lexer = new ExploreGrammarLexer(new ANTLRReaderStream(reader));
        return new CommonTokenStream(lexer);
    }

	public static Pattern iriPattern = Pattern.compile("<(.*)>");
	public static Pattern varPattern = Pattern.compile("\\$(.*)");

	public static String extractIRI(String iri) { 
		Matcher m = iriPattern.matcher(iri);
		if(m.matches()) { 
			return m.group(1);
		} else { 
			return iri;
		}
	}
	
	class RecSetTransformCommand implements ExplorerCommand { 

		private int type;
		private RecSetTransform transform; 
		
		public RecSetTransformCommand(int type, RecSetTransform t) { 
			this.type = type;
			transform = t;
		}

		public ExploreTransform compile(CommonTree cmd) {
			Name[] ns = new Name[cmd.getChildCount()];
			for(int i = 0; i < ns.length; i++) { 
				ns[i] = new Name(cmd.getChild(i));
			}
			return new ExploreTransformArgs(transform, ns);
		}

		public int getASTType() { return type; } 
	}

	class ForwardCommand extends RecSetTransformCommand {
		public ForwardCommand() { 
			super(ExploreGrammarParser.FORWARD, new ForwardTransform());
		}
	}

	class ReverseCommand extends RecSetTransformCommand {
		public ReverseCommand() { 
			super(ExploreGrammarParser.REVERSE, new ReverseTransform());
		}
	}

	class ForwardPropsCommand extends RecSetTransformCommand {
		public ForwardPropsCommand() { 
			super(ExploreGrammarParser.FORWARDPROPS, new ForwardPropsTransform());
		}
	}

	class ReversePropsCommand extends RecSetTransformCommand {
		public ReversePropsCommand() { 
			super(ExploreGrammarParser.REVERSEPROPS, new ReversePropsTransform());
		}
	}

	class ForwardStarCommand extends RecSetTransformCommand {
		public ForwardStarCommand() { 
			super(ExploreGrammarParser.FORWARDSTAR, new ForwardStarTransform());
		}
	}

	class ReverseStarCommand extends RecSetTransformCommand {
		public ReverseStarCommand() { 
			super(ExploreGrammarParser.REVERSESTAR, new ReverseStarTransform());
		}
	}

	class LookupCommand extends RecSetTransformCommand {
		public LookupCommand() { 
			super(ExploreGrammarParser.LOOKUP, new LookupTransform());
		}
	}
	
	class AllCommand extends RecSetTransformCommand {
		public AllCommand() { 
			super(ExploreGrammarParser.ALL, new AllTransform());
		}
	}
	
	class ChooseCommand extends RecSetTransformCommand {
		public ChooseCommand() { 
			super(ExploreGrammarParser.CHOOSE, new ChooseTransform());
		}
	}

	public static Pattern historyVariable = Pattern.compile("\\$(\\d+)");
	
	private class StartingTransform implements ExploreTransform {
		public RecSet evaluate(RecSet input) {
			return input;
		} 
	}
	
	private class EvalCommand implements ExplorerCommand { 
		
		public ExploreTransform compile(CommonTree cmd) {
			return new EvalTransform(new Name(cmd.getChild(0)));
		}

		public int getASTType() { return ExploreGrammarParser.EVAL; } 
	}
	
	private class InterpreterCommand implements ExplorerCommand {
		
		private DefineCommand define = new DefineCommand();
		private ListCommand list = new ListCommand();
		private PrettyPrintCommand prettyPrint = new PrettyPrintCommand();
		private SizeCommand size = new SizeCommand();
		private PrefixCommand prefix = new PrefixCommand();

		public ExploreTransform compile(CommonTree cmd) {
			int type = cmd.getChild(0).getType();
			switch(type) { 
			case ExploreGrammarParser.DEFINE:
				return define.compile(cmd);
				
			case ExploreGrammarParser.PRETTYPRINT:
				return prettyPrint.compile(cmd);
				
			case ExploreGrammarParser.PREFIX:
				return prefix.compile(cmd);
				
			case ExploreGrammarParser.LIST:
				return list.compile(cmd);
				
			case ExploreGrammarParser.SIZE:
				return size.compile(cmd);
				
				default: 
					throw new IllegalArgumentException(String.format(
							"Unknown interpreter command type %s", 
							ExploreGrammarParser.tokenNames[type]));
			}
		}

		public int getASTType() { return ExploreGrammarParser.DEFINE; } 
	}

	private class DefineCommand implements ExplorerCommand { 
		
		public ExploreTransform compile(CommonTree cmd) {
			return new DefineTransform(new Name(cmd.getChild(0)));
		}

		public int getASTType() { return ExploreGrammarParser.DEFINE; } 
	}

	private class PrefixCommand implements ExplorerCommand { 
		
		public ExploreTransform compile(CommonTree cmd) {
			return new PrefixTransform(
					cmd.getChild(0).getText(),
					extractIRI(cmd.getChild(1).getText()));
		}

		public int getASTType() { return ExploreGrammarParser.PREFIX; } 
	}

	private class SizeCommand implements ExplorerCommand, ExploreTransform { 
		
		public ExploreTransform compile(CommonTree cmd) {
			return this;
		}

		public int getASTType() { return ExploreGrammarParser.SIZE; } 

		public RecSet evaluate(RecSet input) {
			System.out.println(input.size());
			return input;
		}
	}

	private class ListCommand implements ExplorerCommand, ExploreTransform { 
		
		public ExploreTransform compile(CommonTree cmd) {
			return this;
		}

		public int getASTType() { return ExploreGrammarParser.LIST; } 

		public RecSet evaluate(RecSet input) {
			Iterator<Rec> itr = input.values();
			while(itr.hasNext()) { 
				Rec rec = itr.next();
				System.out.println(rec.value());
			}
			return input;
		}
	}

	private class PrettyPrintCommand implements ExplorerCommand, ExploreTransform { 
		
		public ExploreTransform compile(CommonTree cmd) {
			return this;
		}

		public int getASTType() { return ExploreGrammarParser.PRETTYPRINT; } 

		public RecSet evaluate(RecSet input) { 
			Iterator<Rec> itr = input.values();
			while(itr.hasNext()) { 
				Rec rec = itr.next();
				prettyPrint(rec);
			}
			return input;
		}
	}

	private void prettyPrint(Rec rec) {
		assert rec != null; 
		
		System.out.println(prefixes.contract(rec.value()));
		
		RecSet propset = query.forwardProperties(rec);
		Iterator<Rec> props = propset.values();
		while(props.hasNext()) {
			
			Rec prop = props.next();
			RecSet objects = query.forward(rec, prop);
			Iterator<Rec> objectitr = objects.values();
			ArrayList<Rec> objectlist = new ArrayList<Rec>();
			
			while(objectitr.hasNext()) { 
				objectlist.add(objectitr.next());
			}
			
			if(objectlist.size() > 1) { 
				System.out.println(String.format("\t%s", 
						prefixes.contract(prop.value())));
				for(Rec obj : objectlist) { 
					System.out.println(String.format("\t\t%s", 
							prefixes.contract(obj.value())));					
				}
				
			} else { 
				System.out.println(String.format("\t%s\t%s",
						prefixes.contract(prop.value()),
						prefixes.contract(objectlist.get(0).value())));
			}
		}
	}

	private class EvalTransform implements ExploreTransform {
		
		private RecSetValue value;

		public EvalTransform(Name n) {
			value = n.value();
		}

		public RecSet evaluate(RecSet input) { return value.evaluate(); }
	}

	private class DefineTransform implements ExploreTransform {
		
		private Name name;

		public DefineTransform(Name n) {
			name = n;
		}

		public RecSet evaluate(RecSet input) { 
			namedSets.put(name.value, input);
			return input;
		}
	}
	
	private class PrefixTransform implements ExploreTransform {
		
		private String prefix, iri;

		public PrefixTransform(String prefix, String iri) {
			this.prefix = prefix;
			this.iri = iri;
		}

		public RecSet evaluate(RecSet input) { 
			prefixes.addPrefix(prefix, iri);
			return input;
		}
	}
	
	private class ExploreTransformArgs implements ExploreTransform {
		
		private RecSetValue[] params;
		private RecSetTransform inner;
		
		public ExploreTransformArgs(RecSetTransform t, Name... ns) { 
			params = new RecSetValue[ns.length];
			for(int i = 0; i < ns.length; i++) { 
				params[i] = ns[i].value();
			}
			inner = t;
		}

		public RecSet evaluate(RecSet input) {
			RecSet[] args = new RecSet[params.length];
			for(int i = 0; i < params.length; i++) { 
				args[i] = params[i].evaluate();
			}
			return inner.transform(query, input, args);
		}		
	}

	private class Name { 
		
		public String type;
		public String value;
				
		public Name(Object t) { 
			this((CommonTree)t);
		}
		
		public Name(CommonTree t) {
			assert t != null;
			
			value = t.getText();
			type = ExploreGrammarParser.tokenNames[t.getType()];
		}
		
		public RecSetValue value() { 
			if(type.equals("PREFIXED")) { 
				return new RecSetLiteralValue( 
						new RecSet(query.identified(prefixes.expand(value))));
				
			} else if (type.equals("RESOURCE")) {
				return new RecSetLiteralValue(
						new RecSet(query.identified(extractIRI(value))));
			
			} else if(type.equals("VARIABLE")) {

				return new RecSetVariableValue(value);

			
			} else { 
				throw new IllegalArgumentException(type);
			}
		}
	}
	
	class RecSetVariableValue implements RecSetValue { 
		
		private Integer idx;
		private String name; 
		
		public RecSetVariableValue(String n) { 
			Matcher historyMatcher = historyVariable.matcher(n);
			if(historyMatcher.matches()) { 
				int ref = Integer.parseInt(historyMatcher.group(1));
				idx = ref;
				name = null;
				
			} else { 
				idx = null;
				name = n;
			}
		}

		public RecSet evaluate() { 
			return name != null ? namedSets.get(name) : history.get(idx); 
		}
	}

}

interface RecSetValue { 
	public RecSet evaluate();
}

class RecSetLiteralValue implements RecSetValue { 
	private RecSet set; 
	public RecSetLiteralValue(RecSet s) { set = s; }
	public RecSet evaluate() { return set; }
}

interface ExploreTransform { 
	public RecSet evaluate(RecSet input);
}

class Compose implements ExploreTransform  {
	
	private ExploreTransform first, second;
	
	public Compose(ExploreTransform first, ExploreTransform second) {
		this.first = first;
		this.second = second;
	}

	public RecSet evaluate(RecSet input) {
		return second.evaluate(first.evaluate(input));
	}
}

interface ExplorerCommand { 
	public int getASTType();
	public ExploreTransform compile(CommonTree cmd);
}