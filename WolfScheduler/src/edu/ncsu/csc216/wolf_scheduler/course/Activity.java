package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is activity class which will act as the parent class to event and course
 * allowing the creation for an activity in a schedule
 * 
 * @author William Krajcovic
 *
 */
public abstract class Activity {

	/** Creates a final int for the max number of hours in a day*/
	private static final int UPPER_HOUR = 24;
	/** Creates a final int for the max number of minutes in an hour*/
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	/**
	 * The constructor for the activity class that will be used as the parent class
	 * to the course and event classes
	 * 
	 * @param title the title of an activity
	 * @param meetingDays the days the activity will take place
	 * @param startTime the start time of the activity
	 * @param endTime the end time of the activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * Overrides the original hash code function that will allow the
	 * code to properly provide the hash code given the inheritance
	 * @return the hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	/**
	 * Overrides the equals function to allow for use with the subclasses
	 * @param obj the object from the object class to allow for inheritance
	 * @return true or false if objects are equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	/**
	 * This method will provide a string array with the short version being sent
	 * to the GUI, this will contain the course, name, section, and title
	 * @return String array of the short version of an activity
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * This is a method that is used to compare an activity to a course or event
	 * @param activity the activity that is being compared
	 * @return if the activity is a duplicate of a course or event
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	
	
	/**
	 * This method will provide a string array with the long version being sent
	 * to the GUI, this will contain the course, name, section, credits, instructor Id and title
	 * @return String array of the long version of an activity
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Gets the Course's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title but is invalid if title is null, or an empty string
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is not valid
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Gets the Course's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * This method gets the string showing the days of the class, and the start and end time,
	 * given in traditional time taken from military time. And returning Arranged
	 * if the meeting day is A
	 * @return The meeting days, start time, and end time given as a string formatted
	 *  such as TW 4:00PM - 5:00PM
	 */
	public String getMeetingString() {
		int startHour;
		int endHour;
		int startMinute;
		int endMinute;
		String startMinuteStr;
		String endMinuteStr;
		String startTimeOfDay;
		String endTimeOfDay;
		
	
		startHour = startTime / 100;
		startMinute = startTime % 100;
		endHour = endTime / 100;
		endMinute = endTime % 100;
		
		if (startHour >= 12)	{
			startTimeOfDay = "PM";
		}
		else	{
			startTimeOfDay = "AM";
		}
		if (endHour >= 12)	{
			endTimeOfDay = "PM";
		}
		else	{
			endTimeOfDay = "AM";
		}
		
		if (startHour > 12) {
			startHour = startHour - 12;
		}
		if (endHour > 12) {
			endHour = endHour - 12;
		}
		if(startHour == 0)	{
			startHour = 12;
		}
		if(endHour == 0)	{
			endHour = 12;
		}
		
		if (startMinute < 10)	{
			startMinuteStr = "0" + startMinute;
		}
		else	{
			startMinuteStr = String.valueOf(startMinute);
		}
		
		
		if (endMinute < 10)	{
			endMinuteStr = "0" + endMinute;
		}
		else	{
			endMinuteStr = String.valueOf(endMinute);
			
		}
		if ("A".equals(meetingDays)){
			return "Arranged";
		}
		
		return meetingDays + " " + startHour + ":" + startMinuteStr + startTimeOfDay + "-" + endHour + ":" + endMinuteStr + endTimeOfDay;
	}

	/**
	 * Gets the Course's start time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the Course's end time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
		
	}

	/**
	 * Method that sets the meeting days, start time, and end time for a class. The parameters
	 * for this method are that the meeting days use only M, T, W, H, F, A without duplicates for
	 * days of weeks and arranged. Start time but be a proper set of military time, and the end time
	 * must be less than the start time.
	 * @param meetingDays meetingDays M, T, W, H, F or A for the days of week or A for arranged
	 * @param startTime the time class will start in military time
	 * @param endTime the time class will end in military time.
	 * @throws IllegalArgumentException if the parameters listed above are not followed
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || " ".equals(meetingDays))	{
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
			int startHour = startTime / 100;
			int startMinute = startTime % 100;
			int endHour = endTime / 100;
			int endMinute = endTime % 100;
			
			if (startHour < 0 || startHour >= UPPER_HOUR) 	{
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			if (endHour < 0 || endHour >= UPPER_HOUR) 	{
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			
			if (endMinute < 0 || endMinute >= UPPER_MINUTE) 	{
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			if (startMinute < 0 || startMinute >= UPPER_MINUTE) 	{
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			if (endTime < startTime)	{
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			
		this.meetingDays = meetingDays;
		this.startTime = startTime; 
		this.endTime = endTime;
	}

}