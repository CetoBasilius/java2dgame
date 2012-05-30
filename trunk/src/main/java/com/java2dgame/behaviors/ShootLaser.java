package com.java2dgame.behaviors;

import java.awt.Image;
import java.awt.Toolkit;

import com.java2dgame.app.Game;
import com.java2dgame.engines.CollisionEngine;
import com.java2dgame.engines.GraphicsEngine;
import com.java2dgame.entities.Laser;
import com.java2dgame.resources.ResourceLoader;

public class ShootLaser implements ShootBehavior{
	
	private static final int LASER_SHOOT_COOLDOWN = 10;
	private int currentCoolDown = LASER_SHOOT_COOLDOWN;
	private float laserVelocity = 8.0f;

	@Override
	public void shoot(float x, float y, int angle) {
		if(currentCoolDown == 0) {
			Image laserImage = Toolkit.getDefaultToolkit().getImage(ResourceLoader.class.getResource("laser1.png"));
			Laser testObject = new Laser(laserImage);
			testObject.setWorldPosition(x, y);
			testObject.setImageAngle(angle);
			testObject.addForwardVelocity(laserVelocity);

			GraphicsEngine.getInstance().addDrawableObject(testObject);
			Game.getInstance().addUpdateableObject(testObject);
			CollisionEngine.getInstance().addCollisionObject(testObject);
			startCoolingOff();
			
		}
	}

	private void startCoolingOff() {
		currentCoolDown = 1;
	}

	@Override
	public void update() {
		if(currentCoolDown>0) {
			currentCoolDown++;
		}
		
		if(currentCoolDown >= LASER_SHOOT_COOLDOWN) {
			currentCoolDown = 0;
		}
	}
	
	

}
