package com.java2dgame.app;

import java.awt.Toolkit;

import org.apache.log4j.Logger;

import com.java2dgame.engines.GraphicsEngine;
import com.java2dgame.engines.InputEngine;
import com.java2dgame.resources.ResourceLoader;

public final class Game implements Runnable {
	
	private static Thread mainLoop;
	
	//TODO remove this, its a test
	TestObject testobject;
	
	private Game(){}
	
	public static void startGame(){
		if(mainLoop == null){
			Logger.getLogger(Game.class).info("Game created.");
			mainLoop = new Thread(null,new Game(),"Game");
			mainLoop.start();
		}else{
			Logger.getLogger(Game.class).warn("Game already exists!");
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new CloneNotSupportedException();
	}

	@Override
	public void run() {
		Logger.getLogger(this.getClass()).info("Game started.");
		//TODO initialize game stuff here
		ResourceLoader.getInstance().getImages();
		
		//TODO this is a test, remove it later
		testGraphicsObjectPool();

		gameLoop();
	}

	private void testGraphicsObjectPool() {
		testobject = new TestObject();
		
		testobject.setImage(Toolkit.getDefaultToolkit().getImage(ResourceLoader.class.getResource("testimage.jpg")));
		GraphicsEngine.getInstance().addDrawableObject(testobject);
	}
	
	private void gameLoop(){
		Logger.getLogger(this.getClass()).info("Main loop started.");
		while(true){
			sleepThread();
			InputEngine.getInstance().update();
			
			//TODO this is a test, remove it
			testobject.setScreenLocation(testobject.getScreenlocationX()+1, testobject.getScreenlocationY()+1);

		}
	}
	
	private void sleepThread() {
		try {
			//TODO fix this and add a constant
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
