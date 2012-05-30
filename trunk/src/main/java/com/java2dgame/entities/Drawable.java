package com.java2dgame.entities;

import java.awt.Image;

public interface Drawable {
	
	public int getDrawableAssignedIndex();
	public void setDrawableAssignedIndex(int index);
	
	public Image getImage();
	public int getScreenlocationY();
	public int getScreenlocationX();
	public int getImageAngle();
	
}
