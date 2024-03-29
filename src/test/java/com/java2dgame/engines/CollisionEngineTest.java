package com.java2dgame.engines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.java2dgame.entities.Asteroid;



public class CollisionEngineTest {
	
	CollisionEngine collisionEngine;
	int delta = 1;
	
	
	public void disableLogger() {
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	@Before
	public void initEngine() {
		disableLogger();
		collisionEngine = CollisionEngine.getInstance();
	}
	
	@Test
	public void testDistanceBetweenObjectsEdges() {
		Asteroid object1 = new Asteroid();
		Asteroid object2 = new Asteroid();
		
		object1.setSize(100, 100);
		object2.setSize(100, 100);
		
		object1.setWorldPosition(200, 0);
		object2.setWorldPosition(-100, 0);
		
		assertEquals(200,collisionEngine.distanceXBetweenObjectsToEdges(object1, object2),delta);
		assertEquals(-100,collisionEngine.distanceYBetweenObjectsToEdges(object1, object2),delta);

		object1.setWorldPosition(0, 200);
		object2.setWorldPosition(0, -100);
		
		assertEquals(-100,collisionEngine.distanceXBetweenObjectsToEdges(object1, object2),delta);
		assertEquals(200,collisionEngine.distanceYBetweenObjectsToEdges(object1, object2),delta);

	}
	
	@Test
	public void testCircularObjectsCollide(){
		
		Asteroid object1 = new Asteroid();
		Asteroid object2 = new Asteroid();
		
		object1.setSize(100);
		object2.setSize(100);
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(500, 500);
		assertFalse(collisionEngine.circularObjectsCollide(object1, object2));
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(100, 500);
		assertFalse(collisionEngine.circularObjectsCollide(object1, object2));
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(120, 120);
		assertTrue(collisionEngine.circularObjectsCollide(object1, object2));
		
		object1.setWorldPosition(150, 150);
		object2.setWorldPosition(100, 100);
		assertTrue(collisionEngine.circularObjectsCollide(object1, object2));
		
		object1.setWorldPosition(550, 550);
		object2.setWorldPosition(100, 100);
		assertFalse(collisionEngine.circularObjectsCollide(object1, object2));
		
		object1.setWorldPosition(-500, -500);
		object2.setWorldPosition(-450, -500);
		assertTrue(collisionEngine.circularObjectsCollide(object1, object2));
		
		object1.setWorldPosition(-500, -500);
		object2.setWorldPosition(-1450, -1500);
		assertFalse(collisionEngine.circularObjectsCollide(object1, object2));
		
	}
	
	@Test
	public void testSquareAndCircularSpecialCase() {

		Asteroid object1 = new Asteroid();
		Asteroid object2 = new Asteroid();
		
		object1.setSize(50);
		object2.setSize(50);
		
		assertEquals(100.0f,object1.getSizeHeight(),delta);
		assertEquals(100.0f,object1.getSizeWidth(),delta);

		object1.setWorldPosition(210, 210);
		object2.setWorldPosition(300, 300);
		
		assertFalse(collisionEngine.circularObjectsCollide(object1, object2));
		
		assertTrue(collisionEngine.squareObjectsCollide(object1, object2));
	}

	@Test
	public void testSquareObjectsCollide(){
		
		Asteroid object1 = new Asteroid();
		Asteroid object2 = new Asteroid();
		
		object1.setSize(100,100);
		object2.setSize(100,100);
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(500, 500);

		assertFalse(collisionEngine.squareObjectsCollide(object1, object2));
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(100, 500);
		
		assertFalse(collisionEngine.squareObjectsCollide(object1, object2));
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(120, 120);
		
		assertTrue(collisionEngine.squareObjectsCollide(object1, object2));
		
		object1.setWorldPosition(-500, -500);
		object2.setWorldPosition(-450, -500);
		
		assertTrue(collisionEngine.squareObjectsCollide(object1, object2));
		
		object1.setWorldPosition(-500, -500);
		object2.setWorldPosition(-1450, -1500);
		
		assertFalse(collisionEngine.squareObjectsCollide(object1, object2));

	}
	
	@Test
	public void testDistanceBetweenObjects(){
		
		Asteroid object1 = new Asteroid();
		Asteroid object2 = new Asteroid();

		object1.setSize(100,100);
		object2.setSize(100,100);

		object1.setWorldPosition(100,100);
		object2.setWorldPosition(500,500);
		

		assertEquals(300,collisionEngine.distanceXBetweenObjectsToEdges(object1, object2),delta);
		assertEquals(300,collisionEngine.distanceYBetweenObjectsToEdges(object1, object2),delta);
		assertEquals(424,collisionEngine.distanceBetweenObjectsEdges(object1, object2),delta);
		
		
	}
}
