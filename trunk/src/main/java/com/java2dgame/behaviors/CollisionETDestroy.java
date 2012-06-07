package com.java2dgame.behaviors;

import com.java2dgame.engines.CollisionEngine;
import com.java2dgame.entities.Collisionable;

public class CollisionETDestroy implements CollisionBehavior{

	private static final int HALF_CIRCLE = 180;
	private static final int FULL_CIRCLE = 360;
	private final double ENERGY_TRANSFER_RATIO = 0.8;
	private final double ENERGY_TRANSFER_LOSS_RATIO = 1.0f - ENERGY_TRANSFER_RATIO;

	
	@Override
	public void update(Collisionable object1, Collisionable object2) {
		if(!object1.equals(object2)) {
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

					double angleBetweenBothObjects = FULL_CIRCLE - Math.toDegrees(Math.atan2(object1X-object2X, object1Y-object2Y)) + HALF_CIRCLE;
					
					object2.setVelocity((float)(object2Energy*getEnergyTransferLossRatio()+object1Energy*getEnergyTransferRatio()),(int)angleBetweenBothObjects - HALF_CIRCLE);
					object2.updateCollision();
					
					object1.setLife(0);
				}
			}
		}
	}
	
	public double getEnergyTransferRatio() {
		return ENERGY_TRANSFER_RATIO;
	}

	public double getEnergyTransferLossRatio() {
		return ENERGY_TRANSFER_LOSS_RATIO;
	}
}
