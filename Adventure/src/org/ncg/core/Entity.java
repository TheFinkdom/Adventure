package org.ncg.core;

public class Entity extends Identifiable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * An accessor for the player's current location.  The location will be
	 * lazy initialized if needed. 
	 * @return - The player's current location.
	 */
	protected Location currentLocation()
	{
		// We use direct member access here so we don't get the side-effect of
		// informing observers of the location change (since we're initializing
		// it).
		if (null == currentLocation) currentLocation = Application.instance().initialLocation();
		return(currentLocation);
	} /* end currentLocation */

	/**
	 * Move the entity to the specified location
	 * @param value - The new location where the entity will be moved
	 */
	protected void currentLocation(Location value)
	{
		currentLocation = value;
		informPlayerMovedObservers();
	} /* end currentLocation */
	
	/**
	 * Inform any/all observers that the entity has moved
	 */
	protected void informPlayerMovedObservers()
	{
		Application.instance().informPlayerMovedObservers();
	} /* end informPlayerMovedObservers */
	
	 /** The player's current location */
	   protected Location currentLocation = null;
}
