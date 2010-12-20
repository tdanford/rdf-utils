package tdanford.rdf.sparql.worksheet;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.regex.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent.EventType;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Position;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.rtf.RTFEditorKit;

import tdanford.rdf.sparql.HTTPSparqlQuery;

public class SparqlWorksheet extends JPanel {
	
	public static void main(String[] args) throws MalformedURLException { 
		//HTTPSparqlQuery query = new HTTPSparqlQuery(new URL(args[0]));
		HTTPSparqlQuery query = null;
		SparqlWorksheetFrame f = new SparqlWorksheetFrame(new SparqlWorksheet(query));
	}
	
	private HTTPSparqlQuery query;
	private StyledEditorKit editorKit;
	private JEditorPane editor;
	
	private Pattern selectPattern;
	private MutableAttributeSet bgAttr, selectAttr;

	private Set<AddAttrs> selectRanges;
	
	public Collection<TextRange> overlappingRanges(int pos) { 
		LinkedList<TextRange> rs = new LinkedList<TextRange>();
		for(TextRange r : selectRanges) { 
			if(r.contains(pos)) { 
				rs.add(r);
			}
		}
		return rs;
	}

	public Collection<TextRange> overlappingRanges(TextRange rr) { 
		LinkedList<TextRange> rs = new LinkedList<TextRange>();
		for(TextRange r : selectRanges) { 
			if(r.overlaps(rr)) { 
				rs.add(r);
			}
		}
		return rs;
	}
	
	public void paint(Graphics g) {
		StyledDocument doc = (StyledDocument)editor.getDocument();
		
		doc.setParagraphAttributes(0, doc.getLength(), bgAttr, true);
		for(AddAttrs change : selectRanges) { 
			change.makeChange(doc);
		}
		
		super.paint(g);
	}
	
	private void updateDocument() {
		
		Document doc = editor.getDocument();
		int windowRadius = 6;
		int len = editor.getDocument().getLength();
		selectRanges.clear();
		
		try {
			boolean repaint = false;
			
			Matcher m = selectPattern.matcher(getText());
			int start = 0;
			
			while(m.find(start)) {
				System.out.println("\tFound match...");
				int offset = m.start();
				int selEnd = m.end();						
				
				TextRange rr = new TextRange(doc, offset, selEnd);
				AddAttrs addRange = new AddAttrs(rr, selectAttr);
				selectRanges.add(new AddAttrs(rr, selectAttr));
		
				System.out.println(String.format("\tAdded: %s", rr.toString()));
				repaint = true;
				
				start = selEnd;
			}

			if(repaint) { repaint(); } 				

		} catch (BadLocationException e1) {
			e1.printStackTrace(System.err);
			throw new IllegalStateException(e1);
		} 
	}

	public SparqlWorksheet(HTTPSparqlQuery q) { 
		super(new BorderLayout());
		
		query = q;
		
		editor = new JEditorPane();
		
		//editorKit = new HTMLEditorKit();
		editorKit = new RTFEditorKit();
		
		editor.setEditorKit(editorKit);
		
		selectRanges = new TreeSet<AddAttrs>();
		selectPattern = Pattern.compile("(select)");
		
		bgAttr = new SimpleAttributeSet();
		StyleConstants.setForeground(bgAttr, Color.black);
		StyleConstants.setBackground(bgAttr, Color.white);
		
		selectAttr = new SimpleAttributeSet(bgAttr);
		StyleConstants.setForeground(selectAttr, Color.red);
				
		add(new JScrollPane(editor), BorderLayout.CENTER);
		
		editor.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				System.out.println(String.format("Document changed: %s", e.toString()));
				//updateDocument();
			}

			public void insertUpdate(DocumentEvent e) {
				updateDocument();
			}

			public void removeUpdate(DocumentEvent e) {
				updateDocument();
			} 
			
		});
		
		editor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			} 
		});
	}
	
	public String getText() { 
		Document doc = editor.getDocument();
		int len = doc.getLength();
		try {
			return doc.getText(0, len);
		} catch (BadLocationException e) {
			throw new IllegalArgumentException(String.format("%d,%d", 0, len));
		}
	}	
}

class SparqlWorksheetFrame extends JFrame {
	
	private SparqlWorksheet worksheet;
	
	public SparqlWorksheetFrame(SparqlWorksheet sw) {
		super("SPARQL");
		worksheet = sw;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = (Container)getContentPane();
		c.setLayout(new BorderLayout());
		c.add(worksheet, BorderLayout.CENTER);
		
		worksheet.setPreferredSize(new Dimension(600, 850));
		setVisible(true);
		pack();
	}
}

interface AttrChange { 
	public void makeChange(StyledDocument doc);
}

class AddAttrs extends TextRange implements AttrChange { 
	
	private AttributeSet attrs;
	
	public AddAttrs(TextRange r, AttributeSet as) {
		super(r);
		attrs = as;
	}

	public void makeChange(StyledDocument doc) {
		int start = getStart();
		int width = width();
		
		AttributeSet set = doc.getCharacterElement(start).getAttributes();
		
		//if(!set.equals(attrs)) { 
		System.out.println(String.format("Adding attributes %s -> %s", super.toString(), attrs.toString()));
		doc.setCharacterAttributes(start, width, attrs, true);
		//}
	}
}

class TextRange implements Comparable<TextRange> { 
	
	private Position start, end;
	
	public TextRange(Document doc, int s, int e) throws BadLocationException { 
		start = doc.createPosition(s);
		end = doc.createPosition(e);
	}
	
	public TextRange(TextRange r) { 
		start = r.start;
		end = r.end;
	}
	
	public TextRange(Position s, Position e) { 
		start = s; 
		end = e;
	}
	
	public int getStart() { return start.getOffset(); }
	public int getEnd() { return end.getOffset(); }
	
	public int hashCode() { 
		int code = 17; 
		code += start.hashCode(); code *= 37;
		code += end.hashCode(); code *= 37;
		return code;
	}
	
	public boolean equals(Object o) { 
		if(!(o instanceof TextRange)) { return false; }
		TextRange r = (TextRange)o;
		return r.start.equals(start) && r.end.equals(end);
	}
	
	public int width() { return end.getOffset() - start.getOffset(); }
	
	public boolean contains(int pos) { 
		return start.getOffset() <= pos && end.getOffset() > pos;
	}
	
	public boolean contains(Position p) { 
		return contains(p.getOffset());
	}
	
	public boolean overlaps(TextRange r) { 
		return contains(r.start) || r.contains(start);
	}
	
	public int compareTo(TextRange r) {
		int so = start.getOffset(), eo = end.getOffset();
		int rso = r.start.getOffset(), reo = r.end.getOffset();
		if(so < rso) { return -1; }
		if(so > rso) { return 1; }
		if(eo < reo) { return -1; }
		if(eo > reo) { return 1; }
		return 0;
	}
	
	public String toString() { return String.format("%s,%s", start.toString(), end.toString()); }
}

