package de.svenwillrich.htwcalendarapi.api.data.command;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import de.svenwillrich.htwcalendarapi.api.data.Data;
import de.svenwillrich.htwcalendarapi.api.logik.HTWCalendarAPI;

public abstract class SuperCommand {

	private static String CONNECTIONPROPERTIES = ";shutdown=true";
	private static String USER = "sa";
	private static String PW = "";
	Connection c = null;

	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Object work() throws Exception {
		openConnection();
		try {
			return subWork();
		} catch (Exception e) {
			throw e;
		} finally {
			closeConnection();
		}
	}

	public abstract Object subWork() throws Exception;

	void closeStatement(PreparedStatement st) {
		try {
			if (!st.isClosed()) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeConnection() throws RuntimeException {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void openConnection() throws RuntimeException {
		if (new File(Data.FILE + Data.FILEEXTENSION).exists()) {
			HTWCalendarAPI.getLog().info(
					"using: "
							+ new File(Data.FILE + ".properties")
									.getAbsolutePath());
			try {
				if (c == null || c.isClosed()) {
					try {
						c = DriverManager.getConnection("jdbc:hsqldb:file:"
								+ Data.FILE + CONNECTIONPROPERTIES, USER, PW);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new RuntimeException(
					"database file does not exist: "
							+ new File(Data.FILE + Data.FILEEXTENSION)
									.getAbsolutePath());
		}
	}

	String getHash(String src) {
		try {
			return DigestUtils.md5Hex(src.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
