package com.java2dgame.behaviors;

import com.java2dgame.entities.Collisionable;

public interface CollisionBehavior {
	public void update(Collisionable object1, Collisionable object2);
}
