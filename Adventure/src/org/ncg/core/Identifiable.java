package org.ncg.core;

import java.io.Serializable;

/**
 * This class indicates that the given object contains an identifier.
 */
abstract class Identifiable implements Serializable
{
	/**
	 * Access the id
	 * @return - The Location's id
	 */
	public Integer id()
	{
		return(id);
	} /* end id */

	/**
	 * A setter for the Id
	 * @param id - The identifier for this object
	 */
   protected void id(Integer id)
   {
   	this.id = id;
   } /* end id */
   
	/** The internal identifier */
	private Integer id;
	
	/** The "version" of this object's state, required by Serializable */
	private static final long serialVersionUID = 1L;
} /* end Identifiable */
