package com.java2dgame.behaviors;

public interface ShootBehavior {
	public void shoot(float x, float y, int angle, float velocityAdd);
	public void update();
}
