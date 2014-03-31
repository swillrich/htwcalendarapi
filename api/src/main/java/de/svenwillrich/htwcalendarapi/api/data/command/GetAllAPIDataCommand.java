package de.svenwillrich.htwcalendarapi.api.data.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import de.svenwillrich.htwcalendarapi.api.data.APIData;

public class GetAllAPIDataCommand extends SuperCommand {

	@Override
	public List<APIData> subWork() throws Exception {
		PreparedStatement prepSta = c.prepareStatement("SELECT * FROM USER");
		List<APIData> list = new ArrayList<APIData>();
		prepSta.execute();
		ResultSet set = prepSta.getResultSet();

		while (set.next()) {
			APIData data = new APIData();
			data.setUsername(set.getString("matrikelnr"));
			data.setPw(set.getString("password"));
			data.setHash(set.getString("hash"));
			list.add(data);
		}
		closeStatement(prepSta);
		return list;
	}
}
