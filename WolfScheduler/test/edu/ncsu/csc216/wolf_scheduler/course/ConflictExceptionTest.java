/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This is the test class for conflict exception to make sure the custom
 * exception is working properly
 * 
 * @author William Krajcovic
 *
 */
class ConflictExceptionTest {

	/**
	 * Tests the conflict exception constructor with the string parameter
	 * to ensure the message is coming through properly
	 */
	@Test
	void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
		
	}
	/**
	 * Tests the parameterless Conflict Exception to make sure the
	 * "Schedule conflict." message goes through
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
