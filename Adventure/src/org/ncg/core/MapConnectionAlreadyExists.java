package org.ncg.core;

/**
 * This exception is used when attempting to add a second connection in the map
 * for a given Location in a particular direction.
 */
public class MapConnectionAlreadyExists extends Exception
{
	/**
	 * A constructor for the class
	 * @param key
	 * @param direction
	 * @param toId
	 * @param existingToId
	 */
	public MapConnectionAlreadyExists(Integer key, String direction, Integer toId, Integer existingToId)
	{
		this.key = key;
		this.direction = direction;
		this.toId = toId;
		this.existingToId = existingToId;
	} /* end MapConnectionAlreadyExists */

	/**
	 * Access the message describing this exception
	 * @return - The message
	 */
	@Override
	public String getMessage()
	{
		return("A map connection to the " + direction + " already exists for Location id " + key +
				 ".  Existing to id is " + existingToId + " when trying to add to id " + toId);				
	} /* end get Message */
	
	private String direction;
	private Integer existingToId;
	private Integer key;
	private Integer toId;
	
	/** The "version" of this object's state, required by Serializable */
	private static final long serialVersionUID = 1L;
} /* end MapConnectionAlreadyExists */
