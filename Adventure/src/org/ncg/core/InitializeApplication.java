package org.ncg.core;

import org.ncg.core.ApplicationAlreadyExistsException;
import org.ncg.core.Location;
import org.ncg.core.MapConnectionAlreadyExists;

/**
 * This class is responsible for initializing the application's files.
 */
public class InitializeApplication extends Application
{
	/**
	 * The driver for the program
	 * @param args
	 */
   public static void main(String[] args) throws ApplicationAlreadyExistsException
   {
   	InitializeApplication instance = new InitializeApplication();
   	instance.run();   	
   } /* end main */
	
   /**
    * A constructor for the class
    * @throws ApplicationAlreadyExistsException
    */
   protected InitializeApplication() throws ApplicationAlreadyExistsException
   {
   } /* end InitializeApplication */

   // TODO : Drive this from a text file possibly?
   /**
    * Instantiate the classroom location
    */
   private void createClassroomLocation()
   {
   	classroomLocation = new Location(
   			"This is a classroom. The lighting is terrible. It looks like a grey prison cell.",
   			"a classroom");
   } /* end createClassroomLocation */

   /**
    * Instantiate the hallway location
    */
   private void createHallwayLocation()
   {
   	hallwayLocation = new Location("This is a hallway, long and empty.", "a hallway");
   } /* end createHallwayLocation */

   /**
    * Instantiate the outside Walker Building location
    */
   private void createOutsideWalkerBuildingLocation()
   {
   	outsideWalkerBuildingLocation = new Location(
   			"You are standing outside of Walker Building, a large white structure.",
   			"outside Walker Building");
   } /* end createOutsideWalkerBuildingLocation */
   
   /**
    * Generate the locations for this game
    */
   private void generateLocations()
   {
   	createClassroomLocation();
   	createHallwayLocation();
   	createOutsideWalkerBuildingLocation();
   	
   	this.addLocation(classroomLocation);
   	this.addLocation(hallwayLocation);
   	this.addLocation(outsideWalkerBuildingLocation);

   	saveLocationsToFileNamed("locations.dat");   	
   } /* end generateLocations */

   /**
    * Generate the map of locations for this game
    */
   private void generateMap() throws MapConnectionAlreadyExists
   {
   	this.addNorthConnectionToMap(classroomLocation, hallwayLocation);
   	this.addWestConnectionToMap(hallwayLocation, outsideWalkerBuildingLocation);
   	
   	saveMapToFileNamed("map.dat");
   } /* end generateMap */
   
   /**
    * The tasks of the application are performed here.
    */
   public void run()
   {
   	try
   	{
      	generateLocations();
      	generateMap();
   	}
   	catch(Exception e)
   	{
   		System.out.println("GAH!");
   		System.out.println(e.getLocalizedMessage());
   	} /* end try */
   	
   	System.out.println("Done Initializing");
   } /* end run */

	private Location classroomLocation;
	private Location hallwayLocation;
	private Location outsideWalkerBuildingLocation;
} /* end InitializeApplication */
