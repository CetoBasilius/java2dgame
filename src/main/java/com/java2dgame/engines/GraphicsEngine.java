package com.java2dgame.engines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import com.java2dgame.app.Drawable;

public final class GraphicsEngine{
	
	private static Vector<Drawable> drawableObjects = new Vector<Drawable>();
	private boolean fullScreen;
	
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
	

	//TODO fix this code
//	public void toggleFullScreen(JFrame frame, JPanel panel) {
//		//TODO get actual default window position
//		int defaultWindowPositionX = 0;
//		int defaultWindowPositionY = 0;
//		
//		if(fullScreen==false) {
//
//			//Set Fullscreen On
//			frame.setVisible(false);
//			frame.dispose();
//			frame.setUndecorated(true);
//			frame.setLocation(0, 0);
//			//TODO get full desktop resolution
//			frame.setSize(Global.DESKTOP_RESOLUTION);
//			frame.setVisible(true);
//			frame.validate();
//			frame.requestFocus();
//			panel.setLocation(defaultWindowPositionX,defaultWindowPositionY);
//			panel.requestFocus();
//			fullScreen=true;
//		}
//		else
//		{
//			resetWindow(frame, panel);
//		}
//	}
//	
//	public void resetWindow(JFrame frame, JPanel panel) {
//		int gameResolutionX = Global.getGlobals().getGameResolutionX();
//		int gameResolutionY = Global.getGlobals().getGameResolutionY();
//		int defaultWindowPositionX = Global.getGlobals().getDefaultWindowPositionX();
//		int defaultWindowPositionY = Global.getGlobals().getDefaultWindowPositionY();
//		
//		//Set Window on
//		frame.setVisible(false);
//		frame.dispose();
//		frame.setUndecorated(false);
//		frame.setLocation(defaultWindowPositionX,defaultWindowPositionY);
//		frame.setSize(gameResolutionX+Global.WINDOW_THICKNESS_X,gameResolutionY+Global.WINDOW_THICKNESS_Y);
//		frame.setVisible(true);
//		frame.validate();
//		frame.requestFocus();
//		panel.setLocation(0,0);
//		panel.requestFocus();
//		fullScreen=false;
//	}

	public boolean isFullScreen() {
		return fullScreen;
	}


}
