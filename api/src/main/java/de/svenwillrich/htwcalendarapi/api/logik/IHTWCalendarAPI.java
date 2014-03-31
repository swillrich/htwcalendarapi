package de.svenwillrich.htwcalendarapi.api.logik;

import de.svenwillrich.htwcalendarapi.api.exception.DataCannotReceivedException;

public interface IHTWCalendarAPI {
	public String getContent(String username, String pw)
			throws DataCannotReceivedException;

	public String getContent(String hash) throws DataCannotReceivedException;
}
