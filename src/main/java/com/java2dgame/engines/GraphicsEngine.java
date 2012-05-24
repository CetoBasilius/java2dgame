package com.java2dgame.engines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import com.java2dgame.app.Drawable;

public final class GraphicsEngine{
	
	private static Vector<Drawable> drawableObjects = new Vector<Drawable>();
	
	private GraphicsEngine(){
		
	}
	
	public static void update(Graphics g){
		Graphics2D graphics = (Graphics2D)g;
		graphics.setBackground(Color.black);
		
		int resolutionWidth = (int)graphics.getClipBounds().getWidth();
		int resolutionHeight = (int)graphics.getClipBounds().getHeight();
		graphics.clearRect(0, 0, resolutionWidth, resolutionHeight);
		
		//TODO change this to support double buffering
		renderObjects(graphics);
	}
	
	private static void renderObjects(Graphics2D graphics) {
		for(Drawable object : drawableObjects){
			graphics.drawImage(object.getImage(), object.getScreenlocationX(), object.getScreenlocationY(), null);
		}	
	}

	public static void addDrawableObject(Drawable object){
		object.setAssignedIndex(drawableObjects.size());
		drawableObjects.add(object);
	}
	
	public static void removeDrawableObject(Drawable object){
		drawableObjects.removeElementAt(object.getAssignedIndex());
	}

}
