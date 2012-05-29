package com.java2dgame.engines;

import java.awt.Image;

public interface Drawable {
	
	public int getAssignedIndex();
	public void setAssignedIndex(int index);
	public Image getImage();
	public int getScreenlocationY();
	public int getScreenlocationX();
	public int getImageAngle();
	
}
