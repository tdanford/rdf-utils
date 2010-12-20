package tdanford.rdf.sparql;

import java.util.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

class InternalListener implements DocumentListener {

	public void changedUpdate(DocumentEvent e) {
	}

	public void insertUpdate(DocumentEvent e) {
	}

	public void removeUpdate(DocumentEvent e) {
	} 
}

class CustomElement implements Element {

	@Override
	public AttributeSet getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getElement(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getElementCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getElementIndex(int offset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEndOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getParentElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStartOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	} 
	
}

public class CustomDocument implements Document {
	
	private ArrayList<CustomDocument> children;
	private StringBuilder text;
	private LinkedList<DocumentListener> listeners;
	private LinkedList<UndoableEditListener> undoListeners;
	private Map props;
	
	private class InternalPosition implements Position {
		
		private Integer offset;
		
		public InternalPosition(Integer off) { 
			offset = off;
		}

		public int getOffset() {
			if(offset==START) { 
				return 0;
			} else if (offset==END) { 
				return length();
			} else { 
				return offset;
			}
		} 
	}
	
	public static final Integer START = -1;
	public static final Integer END = -2;
	
	public CustomDocument(String t) { 
		text = new StringBuilder(t);
		children = new ArrayList<CustomDocument>();
		listeners = new LinkedList<DocumentListener>();
		undoListeners = new LinkedList<UndoableEditListener>();
		props = new HashMap();
	}

	public void addDocumentListener(DocumentListener listener) {
		listeners.add(listener);
	}

	public void addUndoableEditListener(UndoableEditListener listener) {
		undoListeners.add(listener);
	}
	
	public int length() { 
		return text.length();
	}

	public Position createPosition(int offs) throws BadLocationException {
		if(offs < 0 || offs > length()) { throw new BadLocationException(String.valueOf(offs), offs); }
		return new InternalPosition(offs);
	}

	public Element getDefaultRootElement() {
		return null;
	}

	public Position getEndPosition() {
		return new InternalPosition(END);
	}

	public int getLength() {
		return length();
	}

	public Object getProperty(Object key) {
		return props.get(key);
	}

	public Element[] getRootElements() {
		return new Element[0];
	}

	public Position getStartPosition() {
		return new InternalPosition(START);
	}

	public String getText(int offset, int length) throws BadLocationException {
		return text.substring(offset, offset+length);
	}

	public void getText(int offset, int length, Segment txt)
			throws BadLocationException {
		txt.array = text.substring(offset,offset+length).toCharArray();
		txt.count = txt.array.length;
		txt.offset = 0;
	}

	public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {
		text.insert(offset, str);
	}

	public void putProperty(Object key, Object value) {
		props.put(key, value);
	}

	public void remove(int offs, int len) throws BadLocationException {
		text.replace(offs, offs+len, "");
	}

	public void removeDocumentListener(DocumentListener listener) {
		listeners.remove(listener);
	}

	public void removeUndoableEditListener(UndoableEditListener listener) {
		undoListeners.remove(listener);
	}

	public void render(Runnable r) {
	}

}
