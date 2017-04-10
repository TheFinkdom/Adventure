package org.ncg.core;

/**
 * This exception is thrown if the user attempts to create a second instance
 * of the Application.
 */
public class ApplicationAlreadyExistsException extends Exception
{
	/** The "version" of this object's state, required by Serializable */
	private static final long serialVersionUID = 1L;
} /* end ApplicationAlreadyExistsException */
