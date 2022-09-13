/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is the Conflict interface which will allow the activity class
 * to find conflicts in overlapping times of events and courses
 * @author William Krajcovic
 *
 */
public interface Conflict {
	
	/**
	 * This is the method that will handle any conflicting times in the schedule. A conflicting time
	 * is when two activities share at least one day and the times overlap at all. This is compared to the states
	 * activity and the activity given in possibleConflictingActivity
	 * @param pssoibleConflictingActivity An activity that may have conflicting times
	 * @throws ConflictException The created exception that would prevent the creation of 
	 * a conflicting schedule
	 */
	void checkConflict(Activity pssoibleConflictingActivity) throws ConflictException;

}
