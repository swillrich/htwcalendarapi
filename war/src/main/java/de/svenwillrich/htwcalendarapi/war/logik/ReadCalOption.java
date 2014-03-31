package de.svenwillrich.htwcalendarapi.war.logik;

import java.util.Map;

public class ReadCalOption extends Option {
	
	public static String ID = "READCAL";
	
	private String readCal(String hash) {
		return getApi().getContent(hash);
	}
	
	private String readCalByUsernameAndPassword(String username, String pw) {
		return getApi().getContent(username, pw);
	}

	@Override
	public String work(Map<String, String[]> map) throws Exception {
		if (map.size() == 3) {
			String user = map.get("user")[0];
			String pw = map.get("pw")[0];
			return readCalByUsernameAndPassword(user, pw);
		} else {
			String hash = map.get("id")[0];
			return readCal(hash);
		}
	}
}
