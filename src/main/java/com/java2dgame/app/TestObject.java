package com.java2dgame.app;

import java.awt.Dimension;
import java.awt.Image;

import com.java2dgame.engines.Collisionable;

public class TestObject implements Drawable, Controllable, Collisionable{
	
	private int assignedIndex;
	private int screenLocationX;
	private int screenLocationY;
	private Image image;
	
	private Dimension size = new Dimension(1,1);
	private float worldPositionX;
	private float worldPositionY;
	
	private float horizontalVelocity;
	private float verticalVelocity;
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	public void setSize(int width, int height) {
		size.width = width;
		size.height = height;
	}

	@Override
	public void setWorldPosition(int x, int y) {
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
	public int getAssignedIndex() {
		return assignedIndex;
	}

	@Override
	public void setAssignedIndex(int index) {
		assignedIndex = index;
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

	public void update() {
		/*
		 * This object is a test, remember
		 * that the screen Y coordinate 
		 * system is flipped.
		 */
		
		worldPositionX += horizontalVelocity;
		worldPositionY -= verticalVelocity;
		
		screenLocationX = (int) worldPositionX;
		screenLocationY = (int) worldPositionY;
	}



	@Override
	public void holdingUp() {
		addVerticalVelocity(0.5f);
	}


	@Override
	public void holdingDown() {
		addVerticalVelocity(-0.5f);
	}


	@Override
	public void holdingLeft() {
		addHorizontalVelocity(-0.5f);
	}


	@Override
	public void holdingRight() {
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void holdingAction() {
		// TODO Auto-generated method stub
		
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


}
