package com.java2dgame.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestConfigurator {
	
	@Before
	public void disableLogger() {
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	@Test
	public void testDefaultResolutionConfigurator(){
		assertNotNull(Configurator.getConfigurationResolution());
		assertEquals(Configurator.getDefaultConfigurationResolution(),Configurator.getConfigurationResolution());
	}
	
	@Test
	public void testGetConfiguration(){
		Configurator.loadConfigurationFile();
		assertNotNull(Configurator.getConfigDoc());
	}
}
