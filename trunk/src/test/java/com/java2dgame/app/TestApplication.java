package com.java2dgame.app;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestApplication {
	
	@Before
	public void disableLogger() {
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	//TODO test this
	@Test
	public void testThisClass(){
		
	}

}
