package com.java2dgame.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import org.junit.Test;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestGameCanvas {
	
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
