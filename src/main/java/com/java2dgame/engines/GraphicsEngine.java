package com.java2dgame.engines;

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
		
		

		
	}
	
	public static void addDrawableObject(Drawable object){
		object.setAssignedIndex(drawableObjects.size());
		drawableObjects.add(object);
	}
	
	public static void removeDrawableObject(Drawable object){
		drawableObjects.removeElementAt(object.getAssignedIndex());
	}

}
