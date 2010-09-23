package tdanford.resources;

/**
 * Generic interface for resources that need to be explicitly freed
 * upon completion.  Different resource-handling classes will dynamically
 * check for "mix-in" of the Heavy interface, and free those resources as 
 * necessary.
 *  
 * @author tdanford
 */
public interface Heavy {
	
	/*
	 * Frees internal resources.  Use of the (derived) class is 
	 * undefined after dispose() has been called.
	 */
	public void dispose();   
}
