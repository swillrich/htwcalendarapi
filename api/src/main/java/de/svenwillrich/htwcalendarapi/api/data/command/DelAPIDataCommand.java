package de.svenwillrich.htwcalendarapi.api.data.command;

import java.sql.PreparedStatement;

import de.svenwillrich.htwcalendarapi.api.logik.HTWCalendarAPI;

public class DelAPIDataCommand extends SuperCommand {

	private String hash = null;

	@Override
	public Boolean subWork() throws Exception {
		PreparedStatement prepSta = c
				.prepareStatement("DELETE FROM USER WHERE \"hash\" = ?");
		prepSta.setString(1, hash);

		int update = prepSta.executeUpdate();

		if (update > 0) {
			HTWCalendarAPI.getLog().info(
					"user with hash " + hash + " has been deleted");
			return true;
		} else {
			HTWCalendarAPI.getLog().info(
					"failed to delete user with hash " + hash);
			return false;
		}
	}

	public DelAPIDataCommand(String hash) {
		this.hash = hash;
	}

}
