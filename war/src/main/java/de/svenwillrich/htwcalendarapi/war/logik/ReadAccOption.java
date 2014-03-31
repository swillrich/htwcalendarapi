package de.svenwillrich.htwcalendarapi.war.logik;

import java.util.Map;

import de.svenwillrich.htwcalendarapi.api.data.APIData;
import de.svenwillrich.htwcalendarapi.api.exception.LoginDataNotFoundException;

public class ReadAccOption extends Option {
	
	public static String ID = "READACC";
	
	private APIData readAccDataByHash(String hash) throws LoginDataNotFoundException {
		return super.getData().readLogin(hash);
	}

	@Override
	public String work(Map<String, String[]> map) throws Exception {
		String hash = map.get("id")[0];
		
		APIData apiData = readAccDataByHash(hash);
		
		return "account: \n" + apiData.toString();
	}
}
