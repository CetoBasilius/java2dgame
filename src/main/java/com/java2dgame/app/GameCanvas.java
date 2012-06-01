package com.java2dgame.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.java2dgame.engines.GraphicsEngine;

public class GameCanvas extends JPanel implements Runnable{
	
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	private static final int DEFAULT_FRAMES_PER_SECOND = 60;
	private static final long serialVersionUID = 1L;
	
	private int threadSleepTime;
	private Dimension preferredSize = new Dimension();
	private Image offScreen;
	private Graphics bufferGraphics;
	
	private long lastSecond;
	private int frameCount;
	private int lastFrameCount;

	public GameCanvas(){
		preferredSize.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		super.setPreferredSize(preferredSize);
		this.setIgnoreRepaint(true);
		setMaxFramesPerSecond(DEFAULT_FRAMES_PER_SECOND);
		startPaintingThread();
	}

	private void startPaintingThread() {
		Thread painter = new Thread(null,this,"Canvas");
		painter.start();
	}
	
	public void createBufferGraphics(){
		offScreen = this.createImage(preferredSize.width, preferredSize.height);
		bufferGraphics = offScreen.getGraphics();
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        this.preferredSize = preferredSize;
    }

	@Override
	public void paint(Graphics graphics){
		graphics.clearRect(0, 0, preferredSize.width, preferredSize.height);
		if(bufferGraphics != null){
			GraphicsEngine.getInstance().update((Graphics2D)bufferGraphics, preferredSize.width, preferredSize.height);
			bufferGraphics.setColor(Color.white);
			bufferGraphics.drawString(String.valueOf(getFPS()), 20, 20);
		}
		graphics.drawImage(offScreen, 0, 0,preferredSize.width,preferredSize.height, this);
	}
	
	
	@Override
	public void update(Graphics g){
		paint(g);
	}

	public Graphics getBufferGraphics() {
		return bufferGraphics;
	}

	public Image getOffScreen() {
		return offScreen;
	}

	@Override
	public void run() {
		Logger.getLogger(this.getClass()).info("Painter started");
		while(true){
			sleepPainter();
			countFramesPerSecond();
			this.repaint();
		}
	}

	private void sleepPainter() {
		try {
			Thread.sleep(threadSleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void countFramesPerSecond() {
		if(lastSecond+1000<System.currentTimeMillis()){
			lastSecond=System.currentTimeMillis();
			lastFrameCount = frameCount;
			frameCount = 0;	
		}
		frameCount++;
	}
	
	public int getFPS(){
		return lastFrameCount;
	}

	public void setMaxFramesPerSecond(int FPS) {
		if(FPS<=0){
			FPS=1;
		}
		this.threadSleepTime = 1000/FPS;
	}
}
