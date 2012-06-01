package com.java2dgame.entities;

import com.java2dgame.behaviors.CollisionBehavior;


public interface Collisionable{

	public boolean isCollisionActive();
	
	public float getWorldPositionX();
	public float getWorldPositionY();
	
	public float getHorizontalVelocity();
	public float getVerticalVelocity() ;
	public void addHorizontalVelocity(float velocity); 
	public void addVerticalVelocity(float velocity);
	public void setHorizontalVelocity(float velocity);
	public void setVerticalVelocity(float velocity) ;
	

	public float getSizeWidth();
	public float getSizeHeight();

	public void setSize(int width, int height);
	public void setSize(int radius);
	public void setWorldPosition(float x, float y);
	
	public float getRadius();
	
	public int getCollisionAssignedIndex();
	public void setCollisionAssignedIndex(int index);
	
	public CollisionBehavior getCollisionBehavior();
	
	public void updateCollision();
	
	public void setVelocity(float velocity,int angle);

}
