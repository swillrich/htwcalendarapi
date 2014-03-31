package de.svenwillrich.htwcalendarapi.api.data;

import java.sql.Connection;
import java.util.List;

import de.svenwillrich.htwcalendarapi.api.data.command.DelAPIDataCommand;
import de.svenwillrich.htwcalendarapi.api.data.command.GetAllAPIDataCommand;
import de.svenwillrich.htwcalendarapi.api.data.command.ReadAPIDataCommand;
import de.svenwillrich.htwcalendarapi.api.data.command.SaveAPIDataCommand;
import de.svenwillrich.htwcalendarapi.api.data.command.SuperCommand;
import de.svenwillrich.htwcalendarapi.api.exception.DataAlreadyExistsException;
import de.svenwillrich.htwcalendarapi.api.exception.LoginDataNotFoundException;

public class Data implements IData {

	public static String FILE = "src/main/resource/apidata";
	public static String FILEEXTENSION = ".properties";
	private Connection c = null;

	public APIData saveLogin(String matrikelNr, String password)
			throws DataAlreadyExistsException {
		SuperCommand c = new SaveAPIDataCommand(matrikelNr, password);
		try {
			return (APIData) c.work();
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataAlreadyExistsException) {
				throw (DataAlreadyExistsException) e;
			} else {
				return null;
			}
		}
	}

	public boolean deleteLogin(String hash) throws LoginDataNotFoundException {
		SuperCommand c = new DelAPIDataCommand(hash);
		try {
			boolean b = (Boolean) c.work();
			if (!b) {
				throw new LoginDataNotFoundException();
			} else return b;
		} catch (Exception e) {
			throw new LoginDataNotFoundException();
		}
	}

	public APIData readLogin(String hash) throws LoginDataNotFoundException {
		SuperCommand c = new ReadAPIDataCommand(hash);
		try {
			APIData data = (APIData) c.work();
			if (data == null) {
				throw new LoginDataNotFoundException();
			} else return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoginDataNotFoundException();
		}
	}

	public List<APIData> getAllApiData() {
		SuperCommand c = new GetAllAPIDataCommand();
		try {
			return (List<APIData>) c.work();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		Data data = new Data();
		List<APIData> list = data.getAllApiData();

		for (APIData d : list) {
			System.out.println(d);
		}

		// try {
		// APIData apiData = data.saveLogin("s0534022", "SessionName123");
		// System.out.println(apiData);
		// // data.deleteLogin(apiData.getHash());
		// data.deleteLogin(data.getHash("s05340234" + "SessionName123"));
		// } catch (DataAlreadyExistsException e) {
		// e.printStackTrace();
		// }
	}
}
