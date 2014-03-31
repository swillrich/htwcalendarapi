package de.svenwillrich.htwcalendarapi.war.logik;

import java.util.Map;

import de.svenwillrich.htwcalendarapi.api.exception.LoginDataNotFoundException;

public class DelAccOption extends Option {
	
	public static String ID = "DELACC";
	
	private boolean delAccDataByHash(String hash)
			throws LoginDataNotFoundException {
		return getData().deleteLogin(hash);
	}

	@Override
	public String work(Map<String, String[]> map) throws Exception {
		String hash = map.get("id")[0];
		
		boolean delAccDataByHash = delAccDataByHash(hash);
		
		if (delAccDataByHash) {
			return "user with hash " + hash + " has been deleted";
		} else {
			return "failed to delete user wit hash " + hash;
		}
	}
}
