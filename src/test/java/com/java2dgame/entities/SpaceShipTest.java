package com.java2dgame.entities;



import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Image;

import org.junit.Before;
import org.junit.Test;


public class SpaceShipTest {
	
	private SpaceShip spaceship;
	private Image mockedImage;
	private int mockedImageHeight = 32;
	private int mockedImageWidth = 32;
	
	@Before
	public void makeSpaceShip() {
		mockedImage = createMock(Image.class);
		spaceship = new SpaceShip(mockedImage);
		
	}
	
	@Test
	public void testImageSquare() {
		
		expect(mockedImage.getHeight(null)).andReturn(mockedImageHeight).once();
		expect(mockedImage.getWidth(null)).andReturn(mockedImageWidth).once();
		
		replay(mockedImage);
		
		assertTrue(spaceship.imageIsSquare());
		
		verify(mockedImage);
	}

	@Test
	public void testGetRadius() {
		float delta = 1.0f;
		assertEquals(24,spaceship.getRadius(),delta);
	}
	
}
