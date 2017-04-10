package org.ncg.core;

/**
 * This exception is thrown if there is no location in the specified direction
 * from the specified location.
 */
public class NoLocationInDirectionException extends Exception
{
	/**
	 * A constructor for the class
	 * @param location
	 * @param direction
	 */
	public NoLocationInDirectionException(Location location, String direction)
	{
		this.location = location;
		this.direction = direction;
	} /* end NoLocationInDirectionException */

	/**
	 * Access the message describing this exception
	 * @return - The message
	 */
	@Override
	public String getMessage()
	{
		return("There is no location to the " + direction + " of location " + location.name());
	} /* end getMessage */
	
	private String direction;
	private Location location;

	/** The "version" of this object's state, required by Serializable */
	private static final long serialVersionUID = 1L;
}
