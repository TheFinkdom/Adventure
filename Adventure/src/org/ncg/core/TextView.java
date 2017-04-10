package org.ncg.core;

/**
 * This view is a simple console view, displaying text directly.
 */
public class TextView extends View 
{
	/**
	 * Observer callback for when the player attempts to go an invalid direction
	 * from their current location
	 * @param direction - The direction the player tried to go
	 */
	public void informInvalidGoDirection(String direction)
	{
		System.out.println("You hit your nose on the wall trying to go " + direction);
	} /* end informInvalidGoDirection */

	/**
	 * Observer callback for when the player has moved (ie changed location)
	 */
	public void informPlayerMoved()
	{
		look();
	} /* end informPlayerMoved */
	
	/**
	 * Tell the player where they are.
	 */
   public void look()  
   {
	//Strings for the entrance/exit description
	//as well as monster and item presence
	String ItemPresence = "";
	String MonsterPresence = "";
	String NorthText = "";
	String EastText = "";
	String SouthText = "";
	String WestText = "";
	   
   	Location currentLocation = Application.instance().playerCurrentLocation();
   	
   	if(locationExistsTowards(currentLocation, "North"))
   	{
   		NorthText = "You may go North.";
   	}
   	if(locationExistsTowards(currentLocation, "East"))
   	{
   		EastText = "You may go East.";
   	}
   	if(locationExistsTowards(currentLocation, "South"))
   	{
   		SouthText = "You may go South.";
   	}
   	if(locationExistsTowards(currentLocation, "West"))
   	{
   		WestText = "You may go West.";
   	}
   	System.out.println(currentLocation.description() 
   			+ ItemPresence + MonsterPresence + NorthText + EastText + SouthText + WestText);
   	
   	//Resets the look strings for the next call.
   	ItemPresence = "";
	MonsterPresence = "";
	NorthText = "";
	EastText = "";
	SouthText = "";
	WestText = "";
   } /* end look */
   
   
   /**
	 * Informs the program if a connection exists in a given direction
	 * @param location - location you want to look from
	 * @param direction - direction you want to look towards
	 * @return  - Boolean indicating whether a connection exists
	 */
	public boolean locationExistsTowards(Location location, String direction)
	{
		//Specific selection made using the direction param, then tries the method.
		//If exception is found, it says no connection exists. Otherwise, connection is assumed true.
		
		//WEST
		if(direction == "West" || direction == "west")
		{
			try 
			{ 
				Application.instance().determineLocationWestOf(location);
			} 
			
			catch (NoLocationInDirectionException e) {
				return false;
			}
		}
		
		//NORTH
		if(direction == "North" || direction == "north")
		{
			try 
			{ 
				Application.instance().determineLocationWestOf(location);
			} 
			
			catch (NoLocationInDirectionException e) {
				return false;
			}
		}
		
		//EAST
		if(direction == "East" || direction == "east")
		{
			try 
			{ 
				Application.instance().determineLocationWestOf(location);
			} 
			
			catch (NoLocationInDirectionException e) {
				return false;
			}
		}
		
		//SOUTH
		if(direction == "South" || direction == "south")
		{
			try 
			{ 
				Application.instance().determineLocationWestOf(location);
			} 
			
			catch (NoLocationInDirectionException e) {
				return false;
			}
		}
		
		return true;
	}
   
} /* end TextView */
