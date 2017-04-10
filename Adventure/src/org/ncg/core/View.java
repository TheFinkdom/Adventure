package org.ncg.core;

public abstract class View
{
	/**
	 * Observer callback for when the player attempts to go an invalid direction
	 * from their current location
	 * @param direction - The direction the player tried to go
	 */
	public abstract void informInvalidGoDirection(String direction);

	/**
	 * Observer callback for when the player has moved (ie changed location)
	 */
	public abstract void informPlayerMoved();
	
	/**
	 * Tell the player where they are.
	 */
   public abstract void look();
   
   
} /* end View */









/*
Determine location direction of, then see whether that a location is returned.
if a location is returned, then obviously a connection exists. If an exception is thrown, then
there is no connection and no need print a location. The location itself does not matter.
*/
