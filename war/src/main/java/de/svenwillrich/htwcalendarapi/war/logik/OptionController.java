package de.svenwillrich.htwcalendarapi.war.logik;

import java.util.HashMap;
import java.util.Map;

public class OptionController {

	private Map<String, Option> optionMap = new HashMap<String, Option>();

	public OptionController() {
		optionMap.put(AddAccOption.ID, new AddAccOption());
		optionMap.put(DelAccOption.ID, new DelAccOption());
		optionMap.put(ReadAccOption.ID, new ReadAccOption());
		optionMap.put(ReadCalOption.ID, new ReadCalOption());
	}

	public Map<String, Option> getOptionMap() {
		return optionMap;
	}
}
