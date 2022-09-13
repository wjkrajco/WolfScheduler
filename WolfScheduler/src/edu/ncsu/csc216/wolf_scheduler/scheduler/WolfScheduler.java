package edu.ncsu.csc216.wolf_scheduler.scheduler;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * This is the WolfScheduler class which is what allows the user to edit their schedule,
 * from tasks like adding, removing, and getting activities.
 * @author William Krajcovic
 *
 */
public class WolfScheduler {
	
	/** The title of the schedule */
	private String title;
	
	/** This is a catalog of all courses available */
	private ArrayList<Course> catalog;
	
	/** This is a catalog of all activities in one schedule */
	private ArrayList<Activity> schedule;
	
	/**
	 * This is the constructor that will take the file of activities
	 * and create a schedule
	 * @param fileName the name of the file that contains the list
	 * of courses that will be used to make the schedule
	 * @throws IllegalArgumentException if the FileNotFoundException is caught
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<Activity>();
		this.title = "My Schedule";		
		try	{
			catalog = CourseRecordIO.readCourseRecords(fileName);

		} catch	(FileNotFoundException e){
			throw new IllegalArgumentException("Cannot find file.");
			
		}
		
		
	}
	/**
	 * This is a method that takes the courses in the catalog and puts
	 * it, the name, section and title into a 2d string array
	 * 
	 * @return result is a 2d String array with the each row having a course
	 * and the name, section and title in the next columns
	 */
	public String[][] getCourseCatalog() {
		
		if (catalog.size() == 0)	{
			String [][] empty = new String[0][0];
			
			return empty;
		}
		
		String[][] catalogArray = new String[catalog.size()][3];
		for (int i = 0; i < catalog.size(); i++)	{
			Course c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		
		return catalogArray;
	}

	/**
	 * This is a method that takes the activities in the schedule and puts
	 * it, the name, section and title into a 2d string array
	 * 
	 * @return result is a 2d String array with the each row having a course
	 * and the name, section and title in the next columns
	 */
	public String[][] getScheduledActivities() {
		
		if (schedule.size() == 0)	{
			String [][] empty = new String[0][0];
			
			return empty;
		}
        String [][] scheduleArray = new String[schedule.size()][3];
        for (int i = 0; i < schedule.size(); i++) {
            Activity a = schedule.get(i);
            scheduleArray[i] = a.getShortDisplayArray();
        }
        return scheduleArray;
	}
	
	/**
	 * This is a method that takes the courses in the schedule and puts
	 * the name, section, title, credits, instructorId, and meeting days into a 2d string array
	 * 
	 * @return result is a 2d String array with the each row having a course
	 * and the name, section, title, credits, instructorId, and meetingString in the next columns
	 */
	public String[][] getFullScheduledActivities() {
		
		if (schedule.size() == 0)	{
			String [][] empty = new String[0][0];
			
			return empty;
		}
        String [][] scheduleArray = new String[schedule.size()][3];
        for (int i = 0; i < schedule.size(); i++) {
            Activity a = schedule.get(i);
            scheduleArray[i] = a.getLongDisplayArray();
        }
        return scheduleArray;
		
	}
	
	/**
	 * Getter for the schedule title
	 * @return the title of the schedule
	 */
	public String getScheduleTitle() {
		
		return title;
	}

	/**
	 * This is a method that saves the schedule to a location writing to a file
	 * 
	 * @param fileName the name of the file to save the schedule to
	 * @throws IllegalArgumentException if FileNotFound exception is caught
	 */
	public void exportSchedule(String fileName) {
		
		try	{
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		}
		catch(FileNotFoundException e)	{
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	
	/**
	 * This method allows the user to find a course from the catalog
	 * based if a matching name and section is found
	 * 
	 * @param name the name for the course being looked for
	 * @param section the section of the course being looked for
	 * @return the course with the matching name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section))	{
				return catalog.get(i);
			}
			
		}
		return null;
	}
	
	/**
	 * This is a method that adds courses, that exist in the catalog and doesn't exist in the
	 * schedule to the schedule, it returns true if it can be added and false if it cannot be
	 * or is already in the schedule
	 * 
	 * @param name the name of the course that is looking to be added
	 * @param section the section of the course that is looking to be added
	 * @return returns true if the course is in the catalog and not in the schedule
	 * returns false if not in the catalog, or in the schedule
	 * @throws IllegalArgumentException if the schedule already has the class
	 */
	public Boolean addCourseToSchedule(String name, String section) {
		
		Boolean inCatalog = false;
		int numInCatalog = 0;
		
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section))	{
				inCatalog = true;
				numInCatalog = i;
			}
		}
		if (!inCatalog)	{
			return false;
		}
		Course c = catalog.get(numInCatalog);
		for (int i = 0; i < schedule.size(); i++)	{
			if (c.isDuplicate(schedule.get(i)))	{
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		
		schedule.add(c);
		
		
		return true;
	}

	/**
	 * The method removes activities from the schedule if they exist, and returns true 
	 * if so, and returns false if the activity does not exist
	 * @param idx TODO
	 * @return returns true if the activity can be removed and false if it can not
	 */
	public Boolean removeActivityFromSchedule(int idx) {
		
		try	{
			schedule.remove(idx);
		} catch(IndexOutOfBoundsException e)	{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Resets the schedule ArrayList
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
	}

	/**
	 * Sets the title for the schedule
	 * 
	 * @param title the title of the wanted schedule
	 * @throws IllegalArgumentException if the title provided is null
	 */
	public void setScheduleTitle(String title) {
		if (title == null)	{
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
		
	}
	/**
	 * Adding an event that is unique to the schedule and making sure it is not a duplicate
	 * @param eventTitle the title of the event 
	 * @param eventMeetingDays the meeting days of the event
	 * @param eventStartTime the start time of the event
	 * @param eventEndTime the end time of the event
	 * @param eventDetails the details behind the event
	 * @throws IllegalArgumentException if the event is already in the schedule
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails)	{
		Event e = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		
		for (int i = 0; i < schedule.size(); i++)	{
			if (e.isDuplicate(schedule.get(i)))	{
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
				
			}
		}
		
		schedule.add(e);
		
	}

}
