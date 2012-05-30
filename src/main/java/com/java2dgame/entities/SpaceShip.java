package com.java2dgame.entities;

import java.awt.Dimension;
import java.awt.Image;

import com.java2dgame.behaviors.CollisionBehavior;
import com.java2dgame.behaviors.CollisionEnergyTransfer;
import com.java2dgame.behaviors.ShootBehavior;
import com.java2dgame.behaviors.ShootLaser;


public class SpaceShip implements Drawable, Controllable, Collisionable, Updateable{
	
	private int radius;
	private int angle;
	private int assirgnedUpdateIndex;
	private int assignedDrawableIndex;
	private int screenLocationX;
	private int screenLocationY;
	private Image image;
	
	private Dimension size = new Dimension(64,64);
	private float worldPositionX;
	private float worldPositionY;
	
	private float horizontalVelocity;
	private float verticalVelocity;
	
	private ShootBehavior shootBehavior;
	private double spaceShipAcceleration = 0.5;
	private int collisionIndex;
	private CollisionBehavior collisionBehavior = new CollisionEnergyTransfer();
	
	public SpaceShip(Image image){
		this.image = image;
		radius = 32;
		size.width = 64;
		size.height = 64;
		shootBehavior = new ShootLaser();
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
	
	@Override
	public void setUpdateAssignedIndex(int index) {
		assirgnedUpdateIndex = index;
	}

	@Override
	public int getUpdateAssignedIndex() {
		return assirgnedUpdateIndex;
	}
	

	public void setImage(Image image) {
		this.image = image;
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
	public void setSize(int radius) {}

	@Override
	public void setSize(int width, int height) {}
	
	@Override
	public float getRadius() {
		return radius;
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
		return assignedDrawableIndex;
	}

	@Override
	public void setDrawableAssignedIndex(int index) {
		assignedDrawableIndex = index;
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

	@Override
	public float getHorizontalVelocity() {
		return horizontalVelocity;
	}

	@Override
	public float getVerticalVelocity() {
		return verticalVelocity;
	}
	
	@Override
	public void addHorizontalVelocity(float velocity) {
		horizontalVelocity+=velocity;
	}

	@Override
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

	public void addForwardVelocity(double velocity) {
		double theta = Math.toRadians(angle);
		double velocityX = velocity*Math.sin(theta);
		double velocityY = velocity*Math.cos(theta);
		
		addHorizontalVelocity((float) velocityX);
		addVerticalVelocity((float) velocityY);
	}
	
	

	public void brakeForwardVelocity() {

	}

	@Override
	public void updateCollision() {
		worldPositionX += horizontalVelocity;
		worldPositionY -= verticalVelocity;
	}

	@Override
	public void update() {
		/*
		 *  remember that the screen Y coordinate 
		 * system is flipped.
		 */
		
		if(angle<0) {
			angle = 360 +angle;
		}
		
		if(angle>360) {
			angle = 360 -angle;
		}
		updateCollision();
		
		screenLocationX = (int) worldPositionX;
		screenLocationY = (int) worldPositionY;
		
		shootBehavior.update();
	}

	//--------------------------------------------------- CONTROLS -----------------------------------------------------------//
	
	@Override
	public void holdingUp() {
		addForwardVelocity(spaceShipAcceleration);
	}


	@Override
	public void holdingDown() {
		brakeForwardVelocity();
	}

	@Override
	public void holdingLeft() {
		angle-=4;
	}

	@Override
	public void holdingRight() {
		angle+=4;
	}


	@Override
	public void holdingFire() {
		shootBehavior.shoot(this.worldPositionX,this.worldPositionY,this.angle);
	}

	@Override
	public void holdingJump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void holdingReload() {
		
	}


	@Override
	public void holdingAction() {
		
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
