package com.java2dgame.engines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import com.java2dgame.app.Drawable;

public final class GraphicsEngine{
	
	private static Vector<Drawable> drawableObjects = new Vector<Drawable>();
	
	private GraphicsEngine(){}
	
	public static void update(Graphics bufferGraphicsIn,int width, int height){

		Graphics2D bufferGraphics = (Graphics2D)bufferGraphicsIn;
		bufferGraphics.setBackground(Color.black);
		bufferGraphics.clearRect(0, 0, width, height);
		renderObjects(bufferGraphics);
	}
	
	private static void renderObjects(Graphics2D bufferGraphics) {
		for(Drawable object : drawableObjects){
			bufferGraphics.drawImage(object.getImage(), object.getScreenlocationX(), object.getScreenlocationY(), null);
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
