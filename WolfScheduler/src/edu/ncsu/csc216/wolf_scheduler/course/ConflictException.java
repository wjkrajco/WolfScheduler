/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is the ConflictException class which is where the conflict exception
 * is going to be made to ensure the user cannot create schedule
 * conflicts and the code will still run
 * @author William Krajcovic
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The constructor that will call the parent exception class
	 * with the provided message string
	 * @param message the message that will be sent to exception
	 */
	public ConflictException(String message)	{
		super(message);
		
	}
	/**
	 * The parameterless constructor that will call the parameterized
	 * constructor with the message "Schedule conflict."
	 */
	public ConflictException()	{
		this("Schedule conflict.");
		
	}

}
