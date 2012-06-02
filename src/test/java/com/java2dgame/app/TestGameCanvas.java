package com.java2dgame.app;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.awt.Dimension;
import java.awt.Graphics;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class TestGameCanvas {
	
	@Before
	public void disableLogger() {
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	@Test
	public void testSetPrefferedSize(){
		GameCanvas canvas = new GameCanvas();
		
		Dimension resolution1 = new Dimension(1024,768);
		Dimension resolution2 = new Dimension(1440,900);
		
		canvas.setPreferredSize(resolution1);
		
		assertEquals(new Dimension(1024,768),canvas.getPreferredSize());
		
		canvas.setPreferredSize(resolution2);
		
		assertEquals(new Dimension(1440,900),canvas.getPreferredSize());
	}
	
	
	@Test
	public void testPaint(){
		GameCanvas canvas = new GameCanvas();
		canvas.setPreferredSize(new Dimension(1024,768));
		
		assertEquals(new Dimension(1024,768),canvas.getPreferredSize());
		
		Graphics graphics = createMock(Graphics.class);
		graphics.clearRect(0, 0, 1024, 768);
		expectLastCall().once();
		expect(graphics.drawImage(null,0,0,1024,768,canvas)).andReturn(true).once();
		replay(graphics);
		
		canvas.paint(graphics);
		
		verify(graphics);	
	}

}
