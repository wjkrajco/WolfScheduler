package edu.ncsu.csc216.wolf_scheduler.io;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a file.
 * 
 * @author William Krajcovic
 *
 */
public class CourseRecordIO {

	
    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner (new FileInputStream(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();
		
		while (fileReader.hasNextLine())	{
			try	{
				Course course = readCourse(fileReader.nextLine());
				
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course current = courses.get(i);
					if (course.getName().equals(current.getName()) && 
							course.getSection().equals(current.getSection()))	{
						duplicate = true;
						break;
					}
				}
				if (!duplicate)	{
					courses.add(course);
				}
			} catch (IllegalArgumentException e)	{
				//empty
				
				
			}
		}
		fileReader.close();
		
		
		return courses;
	}

	/**
	 * This method takes in the line from the readCourse method to create the course object.
	 * the method will use the line and find the name, title, section, credits, instructorId,
	 * meetingDays, and if available start time and end time and throw exceptions if the line
	 * is not formated correctly
	 * @param nextLine a line string given from the file read in readCourseRecords
	 * @return course a newly created course object from the nextLine information
	 * @throws throws an IllegalArgumentException if an Excepiton is caught
	 */
    private static Course readCourse(String nextLine) {
    	Scanner in = new Scanner(nextLine);
    	in.useDelimiter(",");
    	String name;
    	String title;
    	String section;
    	int credits;
    	String instructorId;
    	String meetingDays;
    	int startTime;
    	int endTime;
    	
    	
    	
    	try	{
    		name = in.next();	
    		title = in.next();
    		section = in.next();
    		credits = in.nextInt();
    		instructorId = in.next();
    		meetingDays = in.next();
    		
    		if ("A".equals(meetingDays))	{
    			if (in.hasNext())	{
    				in.close();
    				throw new IllegalArgumentException("Too many Tokens");
    			}
    			else	{
    				Course course = new Course(name, title, section, credits, instructorId, meetingDays);
    				in.close();
    				return course;
    				
    			}
    		}
    		else	{
    			startTime = in.nextInt();
    			endTime = in.nextInt();
    			if (in.hasNext())	{
    				in.close();
    				throw new IllegalArgumentException("Too many Tokens");
    			}
    			Course course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
    			in.close();
    			return course;
    		}

    	} catch(Exception e)	{
    		throw new IllegalArgumentException ("");
    	}
	}

}
