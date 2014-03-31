package de.svenwillrich.htwcalendarapi.war.logik;

import java.util.Map;

import de.svenwillrich.htwcalendarapi.api.data.Data;
import de.svenwillrich.htwcalendarapi.api.data.IData;
import de.svenwillrich.htwcalendarapi.api.logik.HTWCalendarAPI;
import de.svenwillrich.htwcalendarapi.api.logik.IHTWCalendarAPI;

public abstract class Option {
	private String description = "";
	private StringBuilder builder = new StringBuilder();
	private IHTWCalendarAPI api = null;
	private IData data = null;

	public Option() {
		api = new HTWCalendarAPI();
		data = new Data();
	}

	public abstract String work(Map<String, String[]> map) throws Exception;

	public IHTWCalendarAPI getApi() {
		return api;
	}

	public IData getData() {
		return data;
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
