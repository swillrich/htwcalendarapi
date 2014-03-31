package de.svenwillrich.htwcalendarapi.api.logik.test;

import junit.framework.Assert;

import org.junit.Test;

import de.svenwillrich.htwcalendarapi.api.logik.HTWCalendarAPI;
import de.svenwillrich.htwcalendarapi.api.logik.IHTWCalendarAPI;

public class HTWCalendarAPITest {
	@Test
	public void testLoginAndReceiveViaHash() {
		IHTWCalendarAPI api = new HTWCalendarAPI();
		String content = api.getContent("d4c8940c13f88278bae9f2901d6c8ce8");
		Assert.assertTrue(content.length() > 0);
	}
}
