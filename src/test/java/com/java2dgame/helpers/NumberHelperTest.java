package com.java2dgame.helpers;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class NumberHelperTest {
	
	@Before
	public void disableLogger() {
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	@Test
	public void testCountDigits(){

		assertEquals(1,NumberHelper.countDigits(1));
		assertEquals(2,NumberHelper.countDigits(11));

		assertEquals(5,NumberHelper.countDigits(11111));
		assertEquals(5,NumberHelper.countDigits(12345));
	}
}
