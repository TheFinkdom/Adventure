package org.ncg.core;

import java.util.List;

/**
 * This is the root of the abilities of an Application.  Support for all
 * uses of this "application" framework are supported here.
 */
public abstract class Application
{
   /**
    * A constructor for the class.
    * 
	 * @throws ApplicationAlreadyExistsException - This constructor may only be
	 * called once.  This exception is thrown for any calls after the first
	 * call 
    */
   protected Application() throws ApplicationAlreadyExistsException
   {
   	if (null == instance)
   	{
   	   instance = this;
   	}
   	else
   	{
   		throw new ApplicationAlreadyExistsException();
   	} /* end if */
   } /* end Application */

	/**
	 * Add the specified connection to the map.  Also add its dual.  If the
	 * connection already exists, do nothing.
	 * @param from
	 * @param to
	 * @throws MapConnectionAlreadyExists - If a different connection already
	 * exists to the east for "from" (or to the west from "to"), throw this
	 * exception.
	 */
	protected void addEastConnectionToMap(Location from, Location to) throws MapConnectionAlreadyExists
	{
		map().addEastConnection(from, to);
		map().addWestConnection(to, from);
	} /* end addEastConnectionToMap */
	
	/**
	 * Add the specified location to the hash map
	 * @param location - The location to add
	 */
	protected void addLocation(Location location)
	{
		locations.add(location);
	} /* end addLocation */
	
	/**
	* Add the specified item to the hash map
	* @param item - The item to add
	*/
	protected void addItem(Item item)
	{
		items.add(item);
	} /* end addItem */

	/**
	* Add the specified monster to the hash map
	* @param item - The item to add
	*/
	protected void addMonster(MonsterObject monster)
	{
		monsters.add(monster);
	} /* end addMonster */

	/**
	 * Add the specified connection to the map.  Also add its dual.  If the
	 * connection already exists, do nothing.
	 * @param from
	 * @param to
	 * @throws MapConnectionAlreadyExists - If a different connection already
	 * exists to the north for "from" (or to the south from "to"), throw this
	 * exception.
	 */
	protected void addNorthConnectionToMap(Location from, Location to) throws MapConnectionAlreadyExists
	{
		map().addNorthConnection(from, to);
		map().addSouthConnection(to, from);
	} /* end addNorthConnectionToMap */
	
	/**
	 * Add the specified connection to the map.  Also add its dual.  If the
	 * connection already exists, do nothing.
	 * @param from
	 * @param to
	 * @throws MapConnectionAlreadyExists - If a different connection already
	 * exists to the south for "from" (or to the north from "to"), throw this
	 * exception.
	 */
	protected void addSouthConnectionToMap(Location from, Location to) throws MapConnectionAlreadyExists
	{
		map().addSouthConnection(from, to);
		map().addNorthConnection(to, from);
	} /* end addSouthConnectionToMap */
	
	/**
	 * Add the specified connection to the map.  Also add its dual.  If the
	 * connection already exists, do nothing.
	 * @param from
	 * @param to
	 * @throws MapConnectionAlreadyExists - If a different connection already
	 * exists to the west for "from" (or to the east from "to"), throw this
	 * exception.
	 */
	protected void addWestConnectionToMap(Location from, Location to) throws MapConnectionAlreadyExists
	{
		map().addWestConnection(from, to);
		map().addEastConnection(to, from);
	} /* end addWestConnectionToMap */

	/**
	 * Modify the id of the initial location for the game.  The specified value
	 * must be a valid location id of an existing location.  The default value is
	 * specified in the member's definition.
	 * @param value - The new value for the id of the initial location.
	 * @throws InvalidLocationIdException
	 */
	protected void idForInitialLocation(Integer value) throws InvalidLocationIdException
	{
		if (!locations.containsKey(value)) throw new InvalidLocationIdException(value);
		idForInitialLocation = value;
	} /* end idForInitialLocation */

	/**
	 * Inform observers that the player attempted to go an invalid direction
	 * @param direction - The invalid direction the player tried to go
	 */
	protected void informInvalidGoDirectionObservers(String direction)
	{
		view.informInvalidGoDirection(direction);
	} /* end informInvalidGoDirectionObservers */
	
	/**
	 * Inform observers that the player has moved.
	 */
	protected void informPlayerMovedObservers()
	{
		view.informPlayerMoved();
	} /* end informPlayerMovedObservers */
	
	/**
	 * Access the initial location for this game.
	 * @return - The initial location
	 */
	protected Location initialLocation()
	{
		return(locations.get(idForInitialLocation));
	} /* end initialLocation */
	
   /**
    * The Singleton accessor for the class
    * @return
    */
   public static Application instance()
   {
   	return(instance);
   } /* end instance */

   /**
    * Load the locations database from a file with the specified file name
    * @param fileName - The name of the file from which the locations are to be
    * deserialized.
    */
	protected void loadLocationsFromFileNamed(String fileName)
	{
		locations = (AdventureGameHashMap<Location>)loadObjectFromFileNamed(fileName);
	} /* end loadLocationsFromFileNamed */
	
	/**
	* Load the items database from a file with the specified file name
    * @param fileName - The name of the file from which the items are to be
	* deserialized.
	*/
	protected void loadItemsFromFileNamed(String fileName)
	{
		items = (AdventureGameHashMap<Item>)loadObjectFromFileNamed(fileName);
	} /* end loadLocationsFromFileNamed */
	
	/**
	* Load the items database from a file with the specified file name
    * @param fileName - The name of the file from which the items are to be
	* deserialized.
	*/
	protected void loadMonsterFromFileNamed(String fileName)
	{
		monsters = (AdventureGameHashMap<MonsterObject>)loadObjectFromFileNamed(fileName);
	} /* end loadLocationsFromFileNamed */

   /**
    * Load the map from a file with the specified file name
    * @param fileName - The name of the file from which the map is to be
    * deserialized.
    */
   protected void loadMapFromFileNamed(String fileName)
	{
		map = (Map)loadObjectFromFileNamed(fileName);
	} /* end loadMapFromFileNamed */

	/**
	 * Load the single object from the file with the specified file name.
	 * @param fileName - The name of the file from which to read an Object
	 * @return - The Object read 
	 */
	protected Object loadObjectFromFileNamed(String fileName)
	{
		Object objectLoaded = null;
		
   	try ( // AutoCloseable objects go here!  finally not needed to close!
   			java.io.FileInputStream fileInputStream = new java.io.FileInputStream(fileName);
   			java.io.BufferedInputStream bufferedInputStream = new java.io.BufferedInputStream(fileInputStream);
   			java.io.ObjectInputStream objectInputStream = new java.io.ObjectInputStream(bufferedInputStream);
   	    )
   	{
   		objectLoaded = objectInputStream.readObject();
	   }
   	catch(java.lang.ClassNotFoundException e)
	   {
		   System.out.println(e.getLocalizedMessage());
	   }
   	catch(java.io.IOException e)
	   {
		   System.out.println(e.getLocalizedMessage());
   	} /* end try */
   	
   	return(objectLoaded);
	} /* end loadLocationsFromFileNamed */

	/**
	 * Determine the location to the east of the specified location
	 * @param location
	 * @return - The location to the east of the specified location
	 */
	protected Location determineLocationEastOf(Location location) throws NoLocationInDirectionException
	{
		return(map.determineLocationEastOf(location));
	} /* end determineLocationEastOf */
	
	/**
	 * Determine the location to the north of the specified location
	 * @param location
	 * @return - The location to the north of the specified location
	 */
	protected Location determineLocationNorthOf(Location location) throws NoLocationInDirectionException
	{
		return(map.determineLocationNorthOf(location));
	} /* end determineLocationNorthOf */
	
	/**
	 * Determine the location to the south of the specified location
	 * @param location
	 * @return - The location to the south of the specified location
	 */
	protected Location determineLocationSouthOf(Location location) throws NoLocationInDirectionException
	{
		return(map.determineLocationSouthOf(location));
	} /* end determineLocationSouthOf */
	
	/**
	 * Determine the location to the west of the specified location
	 * @param location
	 * @return - The location to the west of the specified location
	 */
	protected Location determineLocationWestOf(Location location) throws NoLocationInDirectionException
	{
		return(map.determineLocationWestOf(location));
	} /* end determineLocationWestOf */
	
	/**
	 * Tell the user where we are
	 */
	protected void look()
	{
		view.look();
	} /* end look */
	
	/**
	 * Provide lazy initialization access to the map
	 * @return - A valid map object
	 */
	protected Map map()
	{
		if (null == map) map(new org.ncg.core.Map());
		return(map);
	} /* end map */
	
	/**
	 * Replace the existing map with the specified one.
	 * @param map - The new map to use.
	 */
	protected void map(Map map)
	{
      this.map = map;
	} /* end map */

	/**
	 * Move the player east from their current location
	 */
	protected void movePlayerEast()
	{
		player.goEast();
	} /* end movePlayerEast */
	/**
	 * Move the player north from their current location
	 */
	protected void movePlayerNorth()
	{
		player.goNorth();
	} /* end movePlayerNorth */
	
	/**
	 * Move the player south from their current location
	 */
	protected void movePlayerSouth()
	{
		player.goSouth();
	} /* end movePlayerSouth */
	
	/**
	 * Move the player west from their current location
	 */
	protected void movePlayerWest()
	{
		player.goWest();
	} /* end movePlayerWest */
	
	/**
	 * An accessor for the player's current location.  The location will be
	 * lazy initialized if needed. 
	 * @return - The player's current location.
	 */
	protected Location playerCurrentLocation()
	{
		return(player.currentLocation());
	} /* end playerCurrentLocation */
	
   /**
    * This is the starting point of the application.  Since each individual
    * game that uses this framework is distinct, each is responsible for
    * providing an implementation of this method
    */
   public abstract void run();

   /**
    * Save the current locations database to a file with the specified file
    * name
    * @param fileName - The name of the file where the locations are to be
    * serialized.
    */
	protected void saveLocationsToFileNamed(String fileName)
	{
		saveObjectToFileNamed(locations, fileName);
	} /* end saveLocationsToFileNamed */
	
	/**
	* Save the current locations database to a file with the specified file
	* name
	* @param fileName - The name of the file where the monsters are to be
	* serialized.
	*/
    protected void saveMonsterToFileNamed(String fileName)
	{
		saveObjectToFileNamed(monsters, fileName);
	} /* end saveLocationsToFileNamed */
	
	/**
	* Save the current items database to a file with the specified file
	* name
	* @param fileName - The name of the file where the items are to be
	* serialized.
	*/
		protected void saveItemsToFileNamed(String fileName)
		{
			saveObjectToFileNamed(items, fileName);
		} /* end saveLocationsToFileNamed */

   /**
    * Save the map to a file with the specified file name
    * @param fileName - The name of the file where the map is to be serialized.
    */
	protected void saveMapToFileNamed(String fileName)
	{
		saveObjectToFileNamed(map, fileName);
	} /* end saveMapToFileNamed */

	/**
	 * Save the specified objectToSave to a file with the specified file name
	 * @param objectToSave - The object to save (duh!)
	 * @param fileName - The name of the file where the object is to be saved
	 */
	protected void saveObjectToFileNamed(Object objectToSave, String fileName)
	{
   	try ( // AutoCloseable objects go here!  finally not needed to close!
            java.io.FileOutputStream fileOutputStream = new java.io.FileOutputStream(fileName);
            java.io.BufferedOutputStream bufferedOutputStream = new java.io.BufferedOutputStream(fileOutputStream);
            java.io.ObjectOutputStream objectOutputStream = new java.io.ObjectOutputStream(bufferedOutputStream);
   	    )
   	{
         objectOutputStream.writeObject(objectToSave);
	   }
   	catch(java.io.IOException e)
	   {
		   System.out.println(e.getLocalizedMessage());
   	} /* end try */		
	} /* end saveObjectToFileNamed */

	/** The id of the initial location */
	private Integer idForInitialLocation = 0;
	
   /** The Singleton instance */
   private static Application instance = null;

   /** The list of all locations */
   private static AdventureGameHashMap<Location> locations = new AdventureGameHashMap<>(); 
   
   /** The list of all monsters */
   private static AdventureGameHashMap<MonsterObject> monsters = new AdventureGameHashMap<>(); 
   
   /** The list of all items */
   private static AdventureGameHashMap<Item> items = new AdventureGameHashMap<>();

   /** The map of locations */
   private Map map = null;
   
   private List<String> itemList = null;
   
   /** The player */
   private Player player = new Player();
   
   /** The view */
   private View view = new TextView();
} /* end Application */
