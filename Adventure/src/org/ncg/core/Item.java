package org.ncg.core;

public class Item extends Identifiable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Access the description
	 * @return - The location's description
	 */
	public String description()
	{
		return(description);
	} /* end description */
	
	/**
	 * Access the name
	 * @return - The Location's name
	 */
	public String name()
	{
		return(name);
	} /* end name */
	
	private String description;
	
	private String name;

}
