package de.svenwillrich.htwcalendarapi.api.logik;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebAccess {

	private final static String URL_START_LSF = "https://lsf.htw-berlin.de/qisserver/rds?state=user&type=1";
	private final static String URL_CORRECT_VIEW = "https://lsf.htw-berlin.de/qisserver/rds?state=wplan&act=&pool=&show=plan&P.vx=kurz";

	AbstractMap.SimpleEntry<String, String> cookieValue = new SimpleEntry<String, String>(
			"JSESSIONID", "");
	private final static Map<String, String> loginMap;
	private final static Map<String, String> calendarViewMap;
	private static final int TIMEOUT = 60 * 1000;

	static {
		loginMap = new HashMap<String, String>();
		loginMap.put("username", "");
		loginMap.put("password", "");
		loginMap.put("submit", "Jetzt+einloggen");
		loginMap.put("state", "user");
		loginMap.put("type", "1");

		calendarViewMap = new HashMap<String, String>();
		calendarViewMap.put("state", "wplan");
		calendarViewMap.put("act", "");
		calendarViewMap.put("pool", "Jetzt+einloggen");
		calendarViewMap.put("show", "plan");
		calendarViewMap.put("P.vx", "kurz");
		calendarViewMap.put("week", "-2");
		calendarViewMap.put("P.vx", "kurz");
		calendarViewMap.put("work", "go");
	}

	public WebAccess() {

	}

	public String doAutomatically(String username, String pw)
			throws IOException {
		HTWCalendarAPI.getLog().info("attampt to run through automatically");
		String content = getICalContent(getCalendarWebView(login(username, pw)));
		HTWCalendarAPI.getLog().info(
				"content successfully received, it contains "
						+ content.length() + " chars");
		return content;
	}

	public Document login(String username, String pw) throws IOException {
		loginMap.put("username", username);
		loginMap.put("password", pw);
		HTWCalendarAPI.getLog().info(
				"attampt to login with given username and password");

		Response response = Jsoup.connect(URL_START_LSF).timeout(TIMEOUT)
				.data(loginMap).method(Method.POST).execute();
		cookieValue.setValue(response.cookie(cookieValue.getKey()));
		return response.parse();
	}

	public Document getCalendarWebView(Document doc) throws IOException {
		HTWCalendarAPI.getLog().info("attampt to get view with calendar");
		Document receivedDoc = Jsoup.connect(URL_CORRECT_VIEW).timeout(TIMEOUT)
				.cookie(cookieValue.getKey(), cookieValue.getValue())
				.data(calendarViewMap).method(Method.POST).execute().parse();

		return receivedDoc;
	}

	public String getICalContent(Document doc) throws IOException {
		HTWCalendarAPI.getLog().info("attampt to get content of iCal document");
		Response calContent = null;

		String calendarLink = doc.getElementsByAttributeValueContaining("href",
				"moduleCall=iCalendarPlan").attr("href");
		calContent = null;
		calContent = Jsoup.connect(calendarLink).timeout(TIMEOUT)
				.cookie(cookieValue.getKey(), cookieValue.getValue())
				.method(Method.POST).execute();

		return calContent.body();
	}
}
