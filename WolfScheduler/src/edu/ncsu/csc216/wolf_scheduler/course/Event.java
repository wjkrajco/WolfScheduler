/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The event class is a sub class of activity, which will allow for more than just
 * courses to be added to ones schedule
 * 
 * @author William Krajcovic
 *
 */
public class Event extends Activity {
	
	/** The details of the event*/
	public String eventDetails;

	/**
	 * This is he constructor of event that uses the activity parent class
	 * and its own eventDetails to provide details of the specific event
	 * 
	 * @param title the title of the event
	 * @param meetingDays the meeting days for the event
	 * @param startTime the time when the event will start
	 * @param endTime the time the event will end
	 * @param eventDetails the details that will be behind the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}
	
	

	/**
	 * Gets the details of the event
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * This sets the event details as long as they are not null
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if the eventDetails are null
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null)	{
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Overrides the method from the Activity class to allow for
	 * a short version of an event to be sent to the GUI 
	 * the override sends the array with two blanks since there is not name or section
	 * then the title and meeting String
	 * @return result the short display of the event string array
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] result = new String[4];
		
		result[0] = "";
		result[1] = "";
		result[2] = getTitle();
		result[3] = getMeetingString();

		return result;
	}

	/**
	 * Overrides the method from the Activity class to allow for
	 * a long version of an event to be sent to the GUI. There
	 * The only information needed is event details, title, and meeting String
	 * the rest of the spots are left as empty strings
	 * @return result the long display of the string array
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] result = new String[7];
		
		result[0] = "";
		result[1] = "";
		result[2] = getTitle();
		result[3] = "";
		result[4] = "";
		result[5] = getMeetingString();
		result[6] = eventDetails;
		
		return result;
	}

	/**
	 * Overrides the toString from Activity to provide a specialized string
	 * for events.
	 * @return A string of title, meetingDays, startTime, endTime
	 * and event details
	 */
	@Override
	public String toString() {
		String result = "";
		
		result += getTitle() + ",";
		result += getMeetingDays() + ",";
		result += getStartTime() + ",";
		result += getEndTime() + ",";
		result += eventDetails;
		
		return result;
	}


	/**
	 * This override method is overrides the setMeetingDaysAndTimes method so it allows events
	 * to use weekends and takes away the A for arranged course times.
	 * 
	 * @param meetingDays meetingDays M, T, W, H, F, S or U for the days of week.
	 * @param startTime the time class will start in military time
	 * @param endTime the time class will end in military time.
	 * @throws IllegalArgumentException if the parameters listed above are not followed
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays))	{
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		int mCounter = 0;
		int tCounter = 0;
		int wCounter = 0;
		int hCounter = 0;
		int fCounter = 0;
		int sCounter = 0;
		int uCounter = 0;
		
		for (int i = 0; i < meetingDays.length(); i++)	{
			
			if (meetingDays.charAt(i) == 'M')	{
				mCounter++;
			}
			else if (meetingDays.charAt(i) == 'T')	{
				tCounter++;
			}
			else if (meetingDays.charAt(i) == 'W')	{
				wCounter++;
			}
			else if (meetingDays.charAt(i) == 'H')	{
				hCounter++;
			}
			else if (meetingDays.charAt(i) == 'F')	{
				fCounter++;
			}
			else if (meetingDays.charAt(i) == 'S')	{
				sCounter++;
			}
			else if (meetingDays.charAt(i) == 'U')	{
				uCounter++;
			}
			
			else	{
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}			
		}
		if (mCounter > 1)	{
			throw new IllegalArgumentException ("Invalid meeting days and times.");
		}
		
		if (tCounter > 1)	{
			throw new IllegalArgumentException ("Invalid meeting days and times.");
		}
		if (wCounter > 1)	{
			throw new IllegalArgumentException ("Invalid meeting days and times.");
		}
		if (hCounter > 1)	{
			throw new IllegalArgumentException ("Invalid meeting days and times.");
		}
		if (fCounter > 1)	{
			throw new IllegalArgumentException ("Invalid meeting days and times.");
		}
		if (sCounter > 1)	{
			throw new IllegalArgumentException ("Invalid meeting days and times.");
		}
		if (uCounter > 1)	{
			throw new IllegalArgumentException ("Invalid meeting days and times.");
		}
		
		
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * This is overriding the isDuplicate method from activity, to check if an event
	 * and activity are the same by comparing the title.
	 * @param activity the activity compared to see if it already an event / duplicate
	 * @return false if not a duplicate and true if is
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event)	{
			Event b = (Event) activity;
			return getTitle().equals(b.getTitle());
		}
		
		return false;
	}
	
	

}
