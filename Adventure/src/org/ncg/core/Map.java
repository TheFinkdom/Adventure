package org.ncg.core;

import java.util.HashMap;
import java.io.Serializable;

/**
 * This class represents the map of locations in the game.  The location
 * objects must all exist in the application's instance before this class can be
 * instantiated.
 */
public class Map implements Serializable
{
	/**
	 * Add the connection (from, to) to the specified map.  If a connection
	 * already exists in the specified map for "from", verify that connection is
	 * with "to".  If so, quietly just act as if we just made the connection.
	 * If not, then throw MapConnectionAlreadyExists
	 * @param from - The tail of the connection
	 * @param to - The head of the connection
	 * @param map - The map where the connection is being stored
	 * @param direction - The direction of the connection, for the exception if
	 *    it is needed.
	 * @throws MapConnectionAlreadyExists
	 */
	protected void addConnection(Location from, Location to, HashMap<Integer, Location> map, String direction) throws MapConnectionAlreadyExists
	{
		Integer key = from.id();
		
		if (map.containsKey(key))
		{
			Location existingTo = map.get(key);
         if (!to.equals(existingTo))
         {
         	throw new MapConnectionAlreadyExists(key, direction, to.id(), existingTo.id());
         } /* end if */
		}
		else
		{
			map.put(key, to);
		} /* end if */
	} /* end addConnection */

	/**
	 * Add a connection in the east direction
	 * @param from
	 * @param to
	 * @throws MapConnectionAlreadyExists
	 */
	public void addEastConnection(Location from, Location to) throws MapConnectionAlreadyExists
	{
		addConnection(from, to, eastMap, "east");
	} /* end addEastConnection */

	/**
	 * Add a connection in the north direction
	 * @param from
	 * @param to
	 * @throws MapConnectionAlreadyExists
	 */
	public void addNorthConnection(Location from, Location to) throws MapConnectionAlreadyExists
	{
		addConnection(from, to, northMap, "north");
	} /* end addNorthConnection */

	/**
	 * Add a connection in the south direction
	 * @param from
	 * @param to
	 * @throws MapConnectionAlreadyExists
	 */
	public void addSouthConnection(Location from, Location to) throws MapConnectionAlreadyExists
	{
		addConnection(from, to, southMap, "south");
	} /* end addSouthConnection */

	/**
	 * Add a connection in the west direction
	 * @param from
	 * @param to
	 * @throws MapConnectionAlreadyExists
	 */
	public void addWestConnection(Location from, Location to) throws MapConnectionAlreadyExists
	{
		addConnection(from, to, westMap, "west");
	} /* end addWestConnection */

	/**
	 * Determine the new location in the specified map given the direction from
	 * the given location.  If there is no entry in the map, then throw a 
	 * NoLocationInDirectionException.
	 * @param location
	 * @param map
	 * @param direction
	 * @return
	 * @throws NoLocationInDirectionException
	 */
	protected Location determineLocationFromMap(Location location, HashMap<Integer, Location> map, String direction) throws NoLocationInDirectionException
	{
		Location newLocation = map.get(location.id());
		if (null == newLocation) throw new NoLocationInDirectionException(location, direction);
		return(newLocation);
	} /* end determineLocationFromMap */
	
	/**
	 * Determine the location to the east of the specified location
	 * @param location
	 * @return - The location to the east of the specified location
	 * @throws NoLocationInDirectionException
	 */
	public Location determineLocationEastOf(Location location) throws NoLocationInDirectionException
	{
		return(determineLocationFromMap(location, eastMap, "east"));
	} /* end determineLocationEastOf */
	
	/**
	 * Determine the location to the north of the specified location
	 * @param location
	 * @return - The location to the north of the specified location
	 * @throws NoLocationInDirectionException
	 */
	public Location determineLocationNorthOf(Location location) throws NoLocationInDirectionException
	{
		return(determineLocationFromMap(location, northMap, "north"));
	} /* end determineLocationNorthOf */
	
	/**
	 * Determine the location to the south of the specified location
	 * @param location
	 * @return - The location to the south of the specified location
	 * @throws NoLocationInDirectionException
	 */
	public Location determineLocationSouthOf(Location location) throws NoLocationInDirectionException
	{
		return(determineLocationFromMap(location, southMap, "south"));
	} /* end determineLocationSouthOf */
	
	/**
	 * Determine the location to the west of the specified location
	 * @param location
	 * @return - The location to the west of the specified location
	 * @throws NoLocationInDirectionException
	 */
	public Location determineLocationWestOf(Location location) throws NoLocationInDirectionException
	{
		return(determineLocationFromMap(location, westMap, "west"));
	} /* end determineLocationWestOf */
	
   private HashMap<Integer, Location> eastMap = new HashMap<>();
   private HashMap<Integer, Location> northMap = new HashMap<>();
   private HashMap<Integer, Location> southMap = new HashMap<>();
   private HashMap<Integer, Location> westMap = new HashMap<>();
} /* end Map */
