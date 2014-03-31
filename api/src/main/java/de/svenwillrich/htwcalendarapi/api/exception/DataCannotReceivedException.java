/**
 * 
 */
package de.svenwillrich.htwcalendarapi.api.exception;

/**
 * @author Sven Willrich Spezielle Programmierung: Android Datum: 22.10.2013
 */

public class DataCannotReceivedException extends RuntimeException {
	public DataCannotReceivedException(String m) {
		super(m);
	}
	
	public DataCannotReceivedException(Exception e) {
		super(e);
	}
}
