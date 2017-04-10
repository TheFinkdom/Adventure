package org.ncg.core;

/**
 * This exception is thrown if an invalid location id is used.
 */
public class InvalidLocationIdException extends Exception
{
	/**
	 * A constructor for the class
	 * @param invalidLocationId
	 */
	public InvalidLocationIdException(Integer invalidLocationId)
	{
		this.invalidLocationId = invalidLocationId;
	} /* end InvalidLocationIdException */

	/**
	 * Access the message describing this exception
	 * @return - The message
	 */
	@Override
	public String getMessage()
	{
		return("An invalid location id was specified: " + invalidLocationId);
	} /* end get Message */

	/** The invalid location id used that generated this exception */
   private Integer invalidLocationId;
   
	/** The "version" of this object's state, required by Serializable */
	private static final long serialVersionUID = 1L;
}
