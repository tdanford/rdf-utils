package tdanford.browser;

public interface RecQuery {

	public RecSet forwardProperties(Rec subject);
	public RecSet backwardProperties(Rec object);
	
	public RecSet forward(Rec subject, Rec property);
	public RecSet backward(Rec object, Rec property);
	
	public Rec identified(String id);
	public RecSet labeled(String lbl);
}

