package de.svenwillrich.htwcalendarapi.api.exception;

public class DataAlreadyExistsException extends Exception {
	public DataAlreadyExistsException(String hash) {
		super(hash);
	}
}
