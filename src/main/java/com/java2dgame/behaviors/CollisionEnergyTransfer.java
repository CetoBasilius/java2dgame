package com.java2dgame.behaviors;

import com.java2dgame.engines.CollisionEngine;
import com.java2dgame.entities.Collisionable;

public class CollisionEnergyTransfer implements CollisionBehavior{

	@Override
	public void update(Collisionable object1, Collisionable object2) {
		if(object1!=object2) {
			if(CollisionEngine.getInstance().circularObjectsCollide(object1, object2)) {
				float oldHorizontalVelocity = object2.getHorizontalVelocity();
				float oldVerticalVelocity = object2.getVerticalVelocity();
				
				object2.setHorizontalVelocity(object1.getHorizontalVelocity());
				object2.setVerticalVelocity(object1.getVerticalVelocity());
				object2.updateCollision();
				
				object1.setHorizontalVelocity(oldHorizontalVelocity);
				object1.setVerticalVelocity(oldVerticalVelocity);
				object1.updateCollision();
			}
		}
	}
}
