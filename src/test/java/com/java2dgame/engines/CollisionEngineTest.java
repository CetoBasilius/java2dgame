package com.java2dgame.engines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.java2dgame.app.TestObject;



public class CollisionEngineTest {

	@Test
	public void testObjectsCollide(){
		CollisionEngine collisionEngine = CollisionEngine.getInstance();
		
		
		TestObject object1 = new TestObject();
		TestObject object2 = new TestObject();
		
		object1.setSize(100,100);
		object2.setSize(100,100);
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(500, 500);

		//Objects are too far away
		assertFalse(collisionEngine.squareObjectsCollide(object1, object2));
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(100, 500);
		
		//Objects are still too far away
		assertFalse(collisionEngine.squareObjectsCollide(object1, object2));
		
		object1.setWorldPosition(100, 100);
		object2.setWorldPosition(120, 120);
		
		//Objects are colliding
		assertTrue(collisionEngine.squareObjectsCollide(object1, object2));
		
		object1.setWorldPosition(-500, -500);
		object2.setWorldPosition(-450, -500);
		
		//Objects are colliding
		assertTrue(collisionEngine.squareObjectsCollide(object1, object2));
		
		object1.setWorldPosition(-500, -500);
		object2.setWorldPosition(-1450, -1500);
		
		//Objects are too far away to collide
		assertFalse(collisionEngine.squareObjectsCollide(object1, object2));

	}
	
	@Test
	public void testDistanceBetweenObjects(){
		CollisionEngine collisionEngine = CollisionEngine.getInstance();

		TestObject object1 = new TestObject();
		TestObject object2 = new TestObject();

		object1.setSize(100,100);
		object2.setSize(100,100);

		object1.setWorldPosition(100,100);
		object2.setWorldPosition(500,500);
		
		int delta = 1;
		
		assertEquals(300,collisionEngine.distanceXBetweenSquareObjects(object1, object2),delta);
		assertEquals(300,collisionEngine.distanceYBetweenSquareObjects(object1, object2),delta);
		assertEquals(424,collisionEngine.distanceBetweenSquareObjects(object1, object2),delta);
		
		
	}
}
