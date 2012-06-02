package com.java2dgame.helpers;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class StringHelperTest {
	
	@Before
	public void disableLogger() {
		Logger.getRootLogger().setLevel(Level.OFF);
	}

	@Test
	public void testCountOccurrences(){
		assertEquals(1,StringHelper.countOccurrences("Hola!",'!'));
		assertEquals(2,StringHelper.countOccurrences("Hola!!",'!'));
		assertEquals(10,StringHelper.countOccurrences("Hola!!!!!!!!!!",'!'));
		assertEquals(0,StringHelper.countOccurrences("Hola!",'#'));
		assertEquals(2,StringHelper.countOccurrences("Hola, como estas?",' '));
		assertEquals(0,StringHelper.countOccurrences(null,'a'));
	}
	
	
	@Test
	public void testReverseString(){
		assertEquals("!aloH",StringHelper.reverseString(("Hola!")));
		assertEquals("basi",StringHelper.reverseString(("isab")));
		assertEquals("",StringHelper.reverseString(null));
	}
}
