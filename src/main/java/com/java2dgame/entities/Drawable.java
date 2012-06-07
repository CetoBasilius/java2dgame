package com.java2dgame.entities;

import java.awt.Image;

public interface Drawable extends Perishable{
	
	public Image getImage();
	public int getScreenlocationY();
	public int getScreenlocationX();
	
	//TODO remve this 2 and make a new interface rotation
	public int getImageAngle();
	public void setImageAngle(int angle);
	
}
