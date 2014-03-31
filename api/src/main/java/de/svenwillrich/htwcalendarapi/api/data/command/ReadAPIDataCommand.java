package de.svenwillrich.htwcalendarapi.api.data.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.svenwillrich.htwcalendarapi.api.data.APIData;
import de.svenwillrich.htwcalendarapi.api.exception.LoginDataNotFoundException;
import de.svenwillrich.htwcalendarapi.api.logik.HTWCalendarAPI;

public class ReadAPIDataCommand extends SuperCommand {
	private String hash = null;

	@Override
	public APIData subWork() throws Exception {
		APIData apiData = null;
		PreparedStatement prepSta = c
				.prepareStatement("SELECT * FROM USER WHERE \"hash\" = ?");
		prepSta.setString(1, hash);
		prepSta.execute();
		ResultSet resultSet = prepSta.getResultSet();
		if (resultSet.next()) {
			apiData = new APIData();
			apiData.setUsername(resultSet.getString("matrikelnr"));
			apiData.setPw(resultSet.getString("password"));
			apiData.setHash(resultSet.getString("hash"));
		} else {
			throw new LoginDataNotFoundException();
		}

		closeStatement(prepSta);

		if (apiData != null) {
			HTWCalendarAPI.getLog().info(
					"read user with hash " + hash + " successfully");
			return apiData;
		} else return null;
	}

	public ReadAPIDataCommand(String hash) {
		this.hash = hash;
	}
}
