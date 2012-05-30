package com.java2dgame.entities;

public interface Controllable {
	
	public void holdingUp();
	public void holdingDown();
	public void holdingLeft();
	public void holdingRight();

	public void holdingFire();
	public void holdingJump();
	public void holdingReload();
	public void holdingAction();
	
	public void pressedUp();
	public void pressedDown();
	public void pressedLeft();
	public void pressedRight();

	public void pressedFire();
	public void pressedJump();
	public void pressedReload();
	public void pressedAction();
	
}
