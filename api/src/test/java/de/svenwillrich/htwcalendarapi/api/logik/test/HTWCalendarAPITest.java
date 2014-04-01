package de.svenwillrich.htwcalendarapi.api.logik.test;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class HTWCalendarAPITest {
	@Test
	public void testLoginAndReceiveViaHash() {
		// IHTWCalendarAPI api = new HTWCalendarAPI();
		// String content = api.getContent("s0534022", "");
		// Assert.assertTrue(content.length() > 0);
		try {
			String string = "SessionName123";
			String string2 = "53657373696f6e4e616d65313233";
			System.out.println(Hex.decodeHex(string2.toCharArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
