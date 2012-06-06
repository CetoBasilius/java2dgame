package com.java2dgame.app;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.java2dgame.adapters.GameKeyAdapter;
import com.java2dgame.configuration.Configurator;

public class TestApplication {
	
	@Before
	public void disableLogger() {
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	@Test
	public void initCanvasShouldPrepareCanvas(){
		Application application = new Application();
		GameCanvas mockedCanvas = createNiceMock(GameCanvas.class);
		GameKeyAdapter gameKeyAdapter = new GameKeyAdapter();
		
		mockedCanvas.setPreferredSize(Configurator.getConfigurationResolution());
		expectLastCall().once();
		mockedCanvas.createBufferGraphics();
		expectLastCall().once();
		mockedCanvas.addKeyListener(gameKeyAdapter);
		expectLastCall().once();
		mockedCanvas.addMouseMotionListener(application);
		expectLastCall().once();
		mockedCanvas.addMouseListener(application); 
		expectLastCall().once();
		mockedCanvas.addMouseWheelListener(application);
		expectLastCall().once();
		

		replay(mockedCanvas);
		
		application.initCanvas(mockedCanvas,gameKeyAdapter);
		
		verify(mockedCanvas);
		
	}

}
