package com.java2dgame.behaviors;

import com.java2dgame.engines.CollisionEngine;
import com.java2dgame.entities.Collisionable;

public class CollisionEnergyTransfer implements CollisionBehavior{
	
	private final double ENERGY_TRANSFER_RATIO = 0.8;
	private final double ENERGY_TRANSFER_LOSS_RATIO = 0.5;

	@Override
	public void update(Collisionable object1, Collisionable object2) {
		if(object1!=object2) {
			if(object1.isCollisionActive() && object2.isCollisionActive()) {
				if(CollisionEngine.getInstance().circularObjectsCollide(object1, object2)) {

					float object1X = object1.getWorldPositionX();
					float object1Y = object1.getWorldPositionY();
					float object1VX = object1.getHorizontalVelocity();
					float object1VY = object1.getVerticalVelocity();

					float object2X = object2.getWorldPositionX();
					float object2Y = object2.getWorldPositionY();
					float object2VX = object2.getHorizontalVelocity();
					float object2VY = object2.getVerticalVelocity();


					double object1Energy = Math.abs(Math.hypot(object1VX, object1VY));
					double object2Energy = Math.abs(Math.hypot(object2VX, object2VY));

					double angleBetweenBothObjects = 360 - Math.toDegrees(Math.atan2(object1X-object2X, object1Y-object2Y))+180;


					object2.setVelocity((float)(object2Energy*ENERGY_TRANSFER_LOSS_RATIO+object1Energy*ENERGY_TRANSFER_RATIO),(int)angleBetweenBothObjects-180);
					object2.updateCollision();

					object1.setVelocity((float)(object1Energy*ENERGY_TRANSFER_LOSS_RATIO+object2Energy*ENERGY_TRANSFER_RATIO), (int)angleBetweenBothObjects);
					object1.updateCollision();

				}
			}
		}
	}
}
