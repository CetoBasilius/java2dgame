package com.java2dgame.entities;

import java.awt.Dimension;
import java.awt.Image;

import com.java2dgame.behaviors.CollisionBehavior;
import com.java2dgame.behaviors.CollisionEnergyTransfer;


public class TestObject implements Drawable, Controllable, Collisionable, Updateable{
	
	private int radius;
	private int angle;
	private int assignedUpdateIndex;
	private int assignedDrawIndex;
	private int screenLocationX;
	private int screenLocationY;
	private Image image;
	
	private Dimension size = new Dimension(1,1);
	private float worldPositionX;
	private float worldPositionY;
	
	private float horizontalVelocity;
	private float verticalVelocity;
	private int collisionIndex;
	private CollisionBehavior collisionBehavior = new CollisionEnergyTransfer();
	private int collisionTimer;
	
	public TestObject() {
		size.height=32;
		size.width=32;
		radius = 16;
	}
	
	@Override
	public boolean isCollisionActive() {
		if(collisionTimer==0) {
			return true;
		}
		return false;
	}
	
	@Override
	public CollisionBehavior getCollisionBehavior() {
		return collisionBehavior;
	}
	
	
	@Override
	public int getCollisionAssignedIndex() {
		return collisionIndex;
	}

	@Override
	public void setCollisionAssignedIndex(int index) {
		collisionIndex = index;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	public void setUpdateAssignedIndex(int index) {
		assignedUpdateIndex = index;
	}

	@Override
	public int getUpdateAssignedIndex() {
		return assignedUpdateIndex;
	}
	
	@Override
	public int getImageAngle() {
		return angle;
	}
	

	@Override
	public void setImageAngle(int angle) {
		this.angle = angle;
	}

	@Override
	public void setSize(int radius) {
		this.radius = radius;
		
		size.width = radius*2;
		size.height = radius*2;
	}

	@Override
	public float getRadius() {
		return radius;
	}

	@Override
	public void setSize(int width, int height) {
		size.width = width;
		size.height = height;

		radius = width;
		if(height > radius) {
			radius = height;
		}
	}

	@Override
	public void setWorldPosition(float x, float y) {
		worldPositionX = x;
		worldPositionY = y;
	}
	
	@Override
	public float getWorldPositionX() {
		return worldPositionX;
	}

	@Override
	public float getWorldPositionY() {
		return worldPositionY;
	}


	@Override
	public float getSizeWidth() {
		return size.width;
	}


	@Override
	public float getSizeHeight() {
		return size.height;
	}

	@Override
	public int getDrawableAssignedIndex() {
		return assignedDrawIndex;
	}

	@Override
	public void setDrawableAssignedIndex(int index) {
		assignedDrawIndex = index;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public int getScreenlocationY() {
		return screenLocationY;
	}

	@Override
	public int getScreenlocationX() {
		return screenLocationX;
	}

	public void setScreenLocation(int x, int y) {
		this.screenLocationX = x;
		this.screenLocationY = y;
	}

	public float getHorizontalVelocity() {
		return horizontalVelocity;
	}

	public float getVerticalVelocity() {
		return verticalVelocity;
	}
	
	public void addHorizontalVelocity(float velocity) {
		horizontalVelocity+=velocity;
	}


	public void addVerticalVelocity(float velocity) {
		verticalVelocity+=velocity;
	}
	
	@Override
	public void setHorizontalVelocity(float velocity) {
		horizontalVelocity = velocity;
	}

	@Override
	public void setVerticalVelocity(float velocity) {
		verticalVelocity = velocity;
	}

	@Override
	public void update() {
		/*
		 * This object is a test, remember
		 * that the screen Y coordinate 
		 * system is flipped.
		 */
		updateCollision();

		screenLocationX = (int) worldPositionX;
		screenLocationY = (int) worldPositionY;
		
	}
	
	@Override
	public void updateCollision() {
		worldPositionX += horizontalVelocity;
		worldPositionY -= verticalVelocity;
	}

	@Override
	public void holdingUp() {
		//this.setWorldPosition(this.getWorldPositionX(), this.getWorldPositionY()-5);
		addVerticalVelocity(0.5f);
	}


	@Override
	public void holdingDown() {
//		this.setWorldPosition(this.getWorldPositionX(), this.getWorldPositionY()+5);
		addVerticalVelocity(-0.5f);
	}

	@Override
	public void holdingLeft() {
//		this.setWorldPosition(this.getWorldPositionX()-5, this.getWorldPositionY());
		addHorizontalVelocity(-0.5f);
	}

	@Override
	public void holdingRight() {
//		this.setWorldPosition(this.getWorldPositionX()+5, this.getWorldPositionY());
		addHorizontalVelocity(0.5f);
	}


	@Override
	public void holdingFire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void holdingJump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void holdingReload() {
		angle+=2;
	}


	@Override
	public void holdingAction() {
		angle-=2;
	}

	@Override
	public void pressedUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressedDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressedLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressedRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressedFire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressedJump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressedReload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressedAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVelocity(float velocity, int angle) {
		double theta = Math.toRadians(angle);
		double velocityX = velocity*Math.sin(theta);
		double velocityY = velocity*Math.cos(theta);

		setHorizontalVelocity((float) velocityX);
		setVerticalVelocity((float) velocityY);	
	}

}
