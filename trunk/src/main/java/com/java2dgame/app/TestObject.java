package com.java2dgame.app;

import java.awt.Image;

public class TestObject implements Drawable{
	
	private int assignedIndex;
	private int screenLocationX;
	private int screenLocationY;
	

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
		return null;
	}

	@Override
	public int getScreenlocationY() {
		return screenLocationY;
	}

	@Override
	public int getScreenlocationX() {
		return screenLocationX;
	}

}
