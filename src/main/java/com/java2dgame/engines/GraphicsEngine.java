package com.java2dgame.engines;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.java2dgame.app.Drawable;

public final class GraphicsEngine{
	
	private static int WINDOW_THICKNESS_Y;
	private static int WINDOW_THICKNESS_X;
	private static Dimension desktopDimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	private Vector<Drawable> drawableObjects = new Vector<Drawable>();
	private boolean fullScreen;
	private WindowContainer windowReference;
	
	private static class GraphicsEngineReferenceHolder {
        private static final GraphicsEngine INSTANCE = new GraphicsEngine();
    }

	private GraphicsEngine(){
		Logger.getLogger(this.getClass()).info("Graphics engine started.");
	}
	
	public static GraphicsEngine getInstance() {
        return GraphicsEngineReferenceHolder.INSTANCE;
    }
	
	public static void updateDesktopDimension() {
		desktopDimension = Toolkit.getDefaultToolkit().getScreenSize();
	}

	private static Point getWindowCenterPosition(Dimension resolution){
		int positionX = (int)((desktopDimension.getWidth()/2)-(resolution.getWidth()/2));
		int positionY = (int)((desktopDimension.getHeight()/2)-(resolution.getHeight()/2));
		return new Point(positionX,positionY);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}

	
	public void update(Graphics bufferGraphicsIn,int width, int height){

		Graphics2D bufferGraphics = (Graphics2D)bufferGraphicsIn;
		bufferGraphics.setBackground(Color.black);
		bufferGraphics.clearRect(0, 0, width, height);
		renderObjects(bufferGraphics);
	}
	
	private void renderObjects(Graphics2D bufferGraphics) {
		for(Drawable object : drawableObjects){
			bufferGraphics.drawImage(object.getImage(), object.getScreenlocationX(), object.getScreenlocationY(), null);
		}	
	}

	public void addDrawableObject(Drawable object){
		object.setAssignedIndex(drawableObjects.size());
		drawableObjects.add(object);
	}
	
	public void removeDrawableObject(Drawable object){
		drawableObjects.removeElementAt(object.getAssignedIndex());
	}
	
	public void toggleFullScreen(JFrame frame, JPanel panel, Dimension resolution) {
		
		if(fullScreen==false) {

			//Set Fullscreen On
			frame.setVisible(false);
			frame.dispose();
			frame.setUndecorated(true);
			frame.setLocation(0, 0);
			frame.setSize(desktopDimension);
			frame.setVisible(true);
			frame.validate();
			frame.requestFocus();	
			panel.setLocation(getWindowCenterPosition(resolution));
			panel.requestFocus();
			fullScreen=true;
		}
		else
		{
			resetWindow(frame, panel, resolution);
		}
	}
	
	public void resetWindow(JFrame frame, JPanel panel,Dimension resolution) {
		int gameResolutionX = (int)resolution.getWidth();
		int gameResolutionY = (int)resolution.getHeight();
		
		//Set Window on
		frame.setVisible(false);
		frame.dispose();
		frame.setUndecorated(false);
		frame.setLocation(getWindowCenterPosition(resolution));
		frame.setSize(gameResolutionX+WINDOW_THICKNESS_X,gameResolutionY+WINDOW_THICKNESS_Y);
		frame.setVisible(true);
		frame.validate();
		frame.requestFocus();
		panel.setLocation(0,0);
		panel.requestFocus();
		fullScreen=false;
	}
	
	public void resetWindow() {
		resetWindow(windowReference.frame,windowReference.panel,windowReference.prefferedDimension);
	}
	
	public void toggleFullScreen() {
		toggleFullScreen(windowReference.frame,windowReference.panel,windowReference.prefferedDimension);
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setWindowReference(JFrame frame,JPanel panel,Dimension dimension) {
		windowReference = new WindowContainer(frame,panel,dimension);
	}

	public class WindowContainer{
		private JFrame frame;
		private JPanel panel;
		private Dimension prefferedDimension;
		
		public WindowContainer(JFrame frame,JPanel panel,Dimension dimension) {
			this.frame = frame;
			this.panel = panel;
			this.prefferedDimension = dimension;
		}
	}

}
