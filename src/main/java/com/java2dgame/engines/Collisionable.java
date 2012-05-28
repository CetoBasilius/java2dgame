package com.java2dgame.engines;

public interface Collisionable {

	float getWorldPositionX();
	float getWorldPositionY();

	float getSizeWidth();
	float getSizeHeight();

	public void setSize(int width, int height);
	public void setWorldPosition(int x, int y);
}
