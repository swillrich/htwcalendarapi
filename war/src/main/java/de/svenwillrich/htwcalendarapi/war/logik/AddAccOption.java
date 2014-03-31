package de.svenwillrich.htwcalendarapi.war.logik;

import java.util.Map;

import de.svenwillrich.htwcalendarapi.api.data.APIData;
import de.svenwillrich.htwcalendarapi.api.exception.DataAlreadyExistsException;

public class AddAccOption extends Option {

	public static String ID = "ADDACC";

	private APIData addAccData(String nr, String pw)
			throws DataAlreadyExistsException {
		return getData().saveLogin(nr, pw);
	}

	@Override
	public String work(Map<String, String[]> map) throws Exception {
		String username = map.get("user")[0];
		String pw = map.get("pw")[0];

		APIData addAccData = addAccData(username, pw);

		return "account added<br>" + addAccData.toString();
	}
}
