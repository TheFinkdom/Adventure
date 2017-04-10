package org.ncg.core;

import java.util.HashMap;

/**
 * This class is a HashMap that is capable of generating an unused key for
 * newly-added items to use.  The key is always an Integer for this class.
 * @param <V> - The type variable of the value
 */
class AdventureGameHashMap<V extends Identifiable> extends HashMap<Integer, V>
{
	/**
	 * A constructor for the class
	 */
   public AdventureGameHashMap()
   {
   } /* end AdventureGameHashMap */

   /**
    * Give the specified value a unique id and then add it to the hash map.
    * @param value - The value that needs a new id and to be added to the map
    */
   public void add(V value)
   {
   	value.id(assignUniqueId());
   	this.put(value.id(), value);
   } /* end add */
   
	/**
	 * Assign a unique id to this location.  Yeah, this is O(n), but it's not
	 * used generally...
    * @return - The unique Integer to use as the key for the newly-added
    * item
	 */
	private Integer assignUniqueId()
	{
	   // This is one value prior to the actual first id (0 is the first)
		Integer potentialId = -1;
		boolean isPotentialIdAlreadyUsed = this.containsKey(potentialId);

		// Loop until we find a non-negative id that isn't being used yet
		do
		{
			++potentialId;
			isPotentialIdAlreadyUsed = this.containsKey(potentialId);
		} while (isPotentialIdAlreadyUsed);
		
		return(potentialId);
	} /* end assigneUniqueId */
	
	/** The "version" of this object's state, required by Serializable */
	private static final long serialVersionUID = 1L;
} /* end AdventureGameHashMap */
