package com.java2dgame.engines;

public interface Collisionable {

	public float getWorldPositionX();
	public float getWorldPositionY();

	public float getSizeWidth();
	public float getSizeHeight();

	public void setSize(int width, int height);
	public void setWorldPosition(int x, int y);
	
	
	
}
