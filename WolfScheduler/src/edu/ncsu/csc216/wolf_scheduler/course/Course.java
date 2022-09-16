package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This Course program will allow the scheduling of different
 * NCSU Course's based on name, title, section, credits, instructor ID,
 * and meeting days.
 * 
 * @author William Krajcovic
 */
public class Course extends Activity {
	
	/** Creates a final int for the minimum length of a name */
	private static final int MIN_NAME_LENGTH = 5;
	/** Creates a final int for the maximum length of a name */
	private static final int MAX_NAME_LENGTH = 8;
	/** Creates a final int for the minimum length of the course abbreviation  */
	private static final int MIN_LETTER_COUNT = 1;
	/** Creates a final int for the maximum length of the course abbreviation  */
	private static final int MAX_LETTER_COUNT = 4;
	/** Creates a final int for number of digits needed in a course*/
	private static final int DIGIT_COUNT = 3;
	/** Creates a final int for number of digits required in a section number*/
	private static final int SECTION_LENGTH = 3;
	/** Creates a final int for the max number of credits a class can have*/
	private static final int MAX_CREDITS = 5;
	/** Creates a final int for the min number of credits a class can have*/
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
	        int startTime, int endTime) {
	    super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);	    
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Course's name. With parameter that the name is not null, has a length less than 5
	 * or more than 8, does not contain a space between letters and numbers, has less 1 or more than 4 letter characters,
	 * and not exactly three trailing digit characters an IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name is considered invalid.
	 */
	private void setName(String name) {
		if (name == null)	{
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)	{
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		int countLetters = 0;
		int countDigits = 0;
		boolean spaceFlag = false;
		
		
		for (int i = 0; i < name.length(); ++i)	{
			if (!spaceFlag)	{
				
				if (Character.isLetter(name.charAt(i)))	{
					countLetters += 1;
				}
				else if (name.charAt(i) == ' ')	{
					spaceFlag = true;
				}
				else	{
					throw new IllegalArgumentException ("Invalid course name.");
					
				}
			}
			else if (spaceFlag)	{
				if (Character.isDigit(name.charAt(i)))	{
					countDigits += 1;
				}
				else	{
					throw new IllegalArgumentException ("Invalid course name.");
				}
			}
		}
		
		if (countLetters < MIN_LETTER_COUNT || countLetters > MAX_LETTER_COUNT)	{
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		if (countDigits != DIGIT_COUNT)	{
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		this.name = name;
		
		
	}
	/**
	 * Gets the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Sets the Course's section but is invalid if the section is either null, does not equal
	 * SECTION_LENGTH, or contains a non-digit.
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is not valid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH)	{
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i)))	{
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}
	/**
	 * Gets the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * Sets the Course's credits
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS)	{
			throw new IllegalArgumentException("Invalid credits.");
		}
	
		this.credits = credits;
	}
	/**
	 * Gets the Course's instructor ID
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * Sets the Course's instructor ID
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		
		if (instructorId == null || "".equals(instructorId))	{
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}


	/**
	 * Overrides the hashCode to allow for inheritance
	 * @return returns the has code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Overrides the equals method to allow for inheritance
	 * @param obj uses the object class to allow for inheritance
	 * @return true or false if objects are equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Overrides the method from the Activity class to allow for
	 * a short version of a course to be sent to the GUI 
	 * the override allows for name and section to be used
	 * @return result, which is the short display of the course as a string array
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] result = new String[4];
		result[0] = name;
		result[1] = section;
		result[2] = getTitle();
		result[3] = getMeetingString();
		
		
		return result;
	}

	/**
	 * Overrides the method from the Activity class to allow for
	 * a long version of a course to be sent to the GUI. There
	 * Will also be an empty string sent for an event field. The overriding allows
	 * name, section and instructor id to be used the last spot will
	 * be an empty string for event details not being needed
	 * @return result which is the long dispaly of the course string array
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] result = new String[7];
		result[0] = name;
		result[1] = section;
		result[2] = getTitle();
		result[3] = Integer.toString(credits);
		result[4] = instructorId;
		result[5] = getMeetingString();
		result[6] = "";
		
		return result;
	}
	
	/**
	 * This overrides the setMeetingDaysAndTimes as the requirement for a course and an event are different
	 * the course can have A, and not weekends, the event is any day of the week.
	 * @param meetingDays meetingDays M, T, W, H, F or A for the days of week or A for arranged
	 * @param startTime the time class will start in military time
	 * @param endTime the time class will end in military time.
	 * @throws IllegalArgumentException if the parameters listed above are not followed
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays.charAt(0) == 'A')	{
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		else {
			int mCounter = 0;
			int tCounter = 0;
			int wCounter = 0;
			int hCounter = 0;
			int fCounter = 0;
			
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
			
		}
		
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * This is overriding the isDuplicate method from activity, to check if a course
	 * and activity are the same by comparing the name.
	 * @param activity the activity compared to see if it already a course / duplicate
	 * @return false if not a duplicate and true if is
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course)	{
			Course b = (Course) activity;
			return getName().equals(b.getName());
		}
		
		return false;
	}
	
	

}
