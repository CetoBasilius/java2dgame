package com.java2dgame.engines;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.java2dgame.entities.Drawable;


public final class GraphicsEngine{
	
	private static int WINDOW_THICKNESS_Y;
	private static int WINDOW_THICKNESS_X;
	private static Dimension desktopDimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	private Vector<Drawable> drawableObjects = new Vector<Drawable>(100,100);
	private boolean fullScreen;
	private WindowContainer windowReference;
	
	private static class GraphicsEngineReferenceHolder {
        private static final GraphicsEngine INSTANCE = new GraphicsEngine();
    }

	private GraphicsEngine(){
		logInstance();
	}

	private void logInstance() {
		if (Logger.getRootLogger().getAllAppenders().hasMoreElements()) {
			Logger.getLogger(this.getClass()).info("Graphics engine started.");
		}
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

	public synchronized void addDrawableObject(Drawable object){
		object.setDrawableAssignedIndex(drawableObjects.size());
		drawableObjects.add(object);
	}
	
	public synchronized void removeDrawableObject(Drawable object){
		drawableObjects.removeElementAt(object.getDrawableAssignedIndex());
	}
	
	public void toggleFullScreen(JFrame frame, JPanel panel, Dimension resolution) {
		
		if(fullScreen==false) {

			setFullScreen(frame, panel, resolution);
		} else {
			resetWindow(frame, panel, resolution);
		}
	}

	public void toggleTrueFullScreen(JFrame frame, JPanel panel, Dimension resolution) {
		if(fullScreen==false) {
			setTrueFullScreen();
		} else {
			resetWindow();
		}
	}


	private void setFullScreen(JFrame frame, JPanel panel, Dimension resolution) {
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
	
	public void setTrueFullScreen() {
		JFrame frame = windowReference.frame;;
		//Set Fullscreen On
		frame.setVisible(false);
		frame.dispose();
		frame.setUndecorated(true);
		frame.setLocation(0, 0);
		frame.setSize(desktopDimension);
		frame.setVisible(true);
		frame.validate();
		frame.requestFocus();	
		
		JPanel panel = windowReference.panel;
		panel.setLocation(0,0);
		panel.setPreferredSize(desktopDimension);
		panel.setSize(desktopDimension);
		panel.requestFocus();
		fullScreen=true;
		
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
		
		panel.setPreferredSize(new Dimension(gameResolutionX, gameResolutionY));
		panel.setSize(gameResolutionX, gameResolutionY);
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
	
	public void toggleTrueFullScreen() {
		toggleTrueFullScreen(windowReference.frame,windowReference.panel,windowReference.prefferedDimension);
	}
	
	/**
	 * Will draw the given Image with the specified angle, scale and alpha level to the given Graphics object.
	 * <p>
	 * The specified coordinates will be the center of the image.
	 * 
	 * @param angle value in angle.
	 * @param scale default scale is 1.0
	 * @param Alpha value can range from 0.0 to 1.0 <p>
	 * @param x the x coordinate of the rectangle to be drawn.
     * @param y the y coordinate of the rectangle to be drawn.<p>
     * @param image the Image to be drawn.
     * @param graphics the Graphics2D on where the image will be drawn.
	 * 
	 */
	public static void drawImage(Image image, Graphics2D graphics, int x, int y, float angle, float scale, float Alpha) {

		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,Alpha));
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate (x,y);
		affineTransform.scale(scale, scale);
		affineTransform.translate (-image.getWidth(null)/2,-image.getHeight(null)/2);
		affineTransform.rotate(Math.toRadians(angle), image.getWidth(null)/2, image.getHeight(null)/2);

		graphics.drawImage(image, affineTransform, null);

		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));
	}
	
	public void drawGameObjectCircleBounds(Drawable object,Graphics2D graphics){
		int width = object.getImage().getWidth(null);
		int height = object.getImage().getHeight(null);
		graphics.drawOval(object.getScreenlocationX()-width/2, object.getScreenlocationY()-height/2, width, height);
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setWindowReference(JFrame frame,JPanel panel,Dimension dimension) {
		windowReference = new WindowContainer(frame,panel,dimension);
	}
	
	private synchronized void renderObjects(Graphics2D bufferGraphics) {
		for(Drawable object : drawableObjects){
			drawImage(object.getImage(), bufferGraphics, object.getScreenlocationX(), object.getScreenlocationY(), object.getImageAngle(), 1, 1.0f);
			//TODO add graphics debug
			//drawGameObjectCircleBounds(object,bufferGraphics);
		}	
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
