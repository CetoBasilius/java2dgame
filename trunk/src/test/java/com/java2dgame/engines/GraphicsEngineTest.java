package com.java2dgame.engines;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;


public class GraphicsEngineTest {
	
	GraphicsEngine graphicsEngine;
	
	@Before
	public void disableLogger() {
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	@Before
	public void initTestGraphicEngine() {
		graphicsEngine = GraphicsEngine.getInstance();
	}
	
	@Test
	public void testUpdateDesktopDimension() {
		Dimension dimension =  Toolkit.getDefaultToolkit().getScreenSize();
		graphicsEngine.updateDesktopDimension();
		assertEquals(dimension,graphicsEngine.getDesktopDimension());
	}
	
	@Test
	public void testGetWindowCenterPosition() {
		Dimension dimension =  Toolkit.getDefaultToolkit().getScreenSize();
		assertEquals(new Point(0,0),graphicsEngine.getWindowCenterPosition(dimension));
	}
	
	@Test
	public void testUnclonability() {
		try {
			assertEquals(new CloneNotSupportedException(),graphicsEngine.clone());
		} catch (CloneNotSupportedException e) {}
	}
	
	@Test
	public void testUpdate() {
		Graphics2D mockedGraphics = createMock(Graphics2D.class);
		int width = 200;
		int height = 300;
		
		mockedGraphics.setBackground(Color.black);
		expectLastCall().once();
		mockedGraphics.clearRect(0, 0, width, height);
		expectLastCall().once();
		
		replay(mockedGraphics);
		
		graphicsEngine.update(mockedGraphics,width,height);
		
		verify(mockedGraphics);
	}
	
	@Test
	public void testDrawImage() {
		Image mockedImage = createMock(Image.class);
		Graphics2D mockedGraphics = createMock(Graphics2D.class);
		int x = 10;
		int y = 20;
		float angle = 10;
		float scale = 1.0f;
		float Alpha = 1.0f;
		
		int mockedImageWidth = 20;
		int mockedImageHeight = 32;
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate (x,y);
		affineTransform.scale(scale, scale);
		affineTransform.translate (-mockedImageWidth/2,-mockedImageHeight/2);
		affineTransform.rotate(Math.toRadians(angle), mockedImageWidth/2, mockedImageHeight/2);
		
		mockedGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,Alpha));
		expectLastCall().once();	
		expect(mockedGraphics.drawImage(mockedImage, affineTransform, null)).andReturn(false);
		
		expect(mockedImage.getWidth(null)).andReturn(mockedImageWidth).times(2);
		expect(mockedImage.getHeight(null)).andReturn(mockedImageHeight).times(2);
		
		mockedGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
		expectLastCall().once();
		

		replay(mockedImage,mockedGraphics);

		graphicsEngine.drawImage(mockedImage, mockedGraphics, x, y, angle, scale, Alpha);

		verify(mockedImage,mockedGraphics);

	}
	
}
