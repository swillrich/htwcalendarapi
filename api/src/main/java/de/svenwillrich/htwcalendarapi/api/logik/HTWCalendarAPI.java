package de.svenwillrich.htwcalendarapi.api.logik;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import de.svenwillrich.htwcalendarapi.api.data.APIData;
import de.svenwillrich.htwcalendarapi.api.data.Data;
import de.svenwillrich.htwcalendarapi.api.data.IData;
import de.svenwillrich.htwcalendarapi.api.exception.DataCannotReceivedException;
import de.svenwillrich.htwcalendarapi.api.exception.LoginDataNotFoundException;

public class HTWCalendarAPI implements IHTWCalendarAPI {

	private static Logger log = Logger.getRootLogger();

	static {
		log.setLevel(Level.INFO);
		String PATTERN = "%d [%p|%C{1}] %m%n";
		PatternLayout patternLayout = new PatternLayout(PATTERN);
		ConsoleAppender consoleAppender = new ConsoleAppender(patternLayout);
		log.addAppender(consoleAppender);
	}

	public static Logger getLog() {
		return log;
	}

	public String getContent(String username, String pw)
			throws DataCannotReceivedException {
		try {
			return new WebAccess().doAutomatically(username, pw);
		} catch (IOException e) {
			throw new DataCannotReceivedException(e.getMessage());
		}
	}

	public String getContent(String hash) throws DataCannotReceivedException {
		IData data = new Data();
		APIData apiData;
		try {
			apiData = data.readLogin(hash);
			return new WebAccess().doAutomatically(apiData.getUsername(),
					apiData.getPw());
		} catch (LoginDataNotFoundException e) {
			e.printStackTrace();
			throw new DataCannotReceivedException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DataCannotReceivedException(e);
		}
	}
}
