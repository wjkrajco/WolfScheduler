/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This is a testing class used to test the implementation of conflict in
 * the activity class
 * @author William Krajcovic
 *
 */
class ActivityTest {

	/**
	 * This method tests the overriding of the check conflict method
	 * the method is looking for activities that share one day and share a time.
	 */
	@Test
	void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * This is a test of the check conflict message with conflicting courses
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
	    Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1200, 1400);
	    Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1300, 1330);
	    
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	    
	    Exception e3 = assertThrows(ConflictException.class, () -> a2.checkConflict(a3));
	    assertEquals("Schedule conflict.", e3.getMessage());
	    
	    Exception e4 = assertThrows(ConflictException.class, () -> a3.checkConflict(a2));
	    assertEquals("Schedule conflict.", e4.getMessage());
	    
	    Exception e5 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
	    assertEquals("Schedule conflict.", e5.getMessage());
	    
	    Exception e6 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
	    assertEquals("Schedule conflict.", e6.getMessage());
	}

}
