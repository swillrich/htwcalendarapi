package de.svenwillrich.htwcalendarapi.api.data.command;

import java.sql.PreparedStatement;

import de.svenwillrich.htwcalendarapi.api.data.APIData;
import de.svenwillrich.htwcalendarapi.api.exception.DataAlreadyExistsException;
import de.svenwillrich.htwcalendarapi.api.exception.LoginDataNotFoundException;
import de.svenwillrich.htwcalendarapi.api.logik.HTWCalendarAPI;

public class SaveAPIDataCommand extends SuperCommand {

	private String user = null;
	private String pw = null;
	private String hash = null;

	@Override
	public APIData subWork() throws Exception {
		String hash = getHash(user + pw);
		try {

			SuperCommand command = new ReadAPIDataCommand(hash);
			command.work();
			throw new DataAlreadyExistsException("hash: " + hash);
		} catch (LoginDataNotFoundException e1) {
		}

		PreparedStatement prepSta = c
				.prepareStatement("INSERT INTO USER VALUES (?, ?, ?);");
		prepSta.setString(1, user);
		prepSta.setString(2, pw);
		prepSta.setString(3, hash);

		int update = prepSta.executeUpdate();

		closeStatement(prepSta);

		if (update > 0) {
			HTWCalendarAPI.getLog().info(
					"user with matrikelnr " + user
							+ " has been addded successfully");
		} else {
			HTWCalendarAPI.getLog().info(
					"failed to insert user with matrikelnr " + user);
		}
		APIData apiData = new APIData();
		apiData.setUsername(user);
		apiData.setPw(pw);
		apiData.setHash(hash);
		return apiData;
	}

	public SaveAPIDataCommand(String user, String pw) {
		this.user = user;
		this.pw = pw;
		this.hash = getHash(user + pw);
	}

}
