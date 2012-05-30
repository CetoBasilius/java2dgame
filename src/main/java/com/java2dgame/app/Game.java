package com.java2dgame.app;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.java2dgame.engines.CollisionEngine;
import com.java2dgame.engines.GraphicsEngine;
import com.java2dgame.engines.InputEngine;
import com.java2dgame.entities.SpaceShip;
import com.java2dgame.entities.TestObject;
import com.java2dgame.entities.Updateable;
import com.java2dgame.resources.ResourceLoader;

public final class Game implements Runnable {
	
	private static Thread mainLoop;
	private static long GAME_LOGIC_INTERVAL = 20;
	
	private Vector<Updateable> updateableObjects = new Vector<Updateable>(100,100);	

	//TODO remove this, its a test
	TestObject testobject;
	SpaceShip spaceShip;
	
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
		makeTestObject();
		makeSpaceShip();

		gameLoop();
	}
	
	private void makeSpaceShip() {
		Image spaceShipImage = Toolkit.getDefaultToolkit().getImage(ResourceLoader.class.getResource("spaceship.png"));
		spaceShip = new SpaceShip(spaceShipImage);
		spaceShip.setWorldPosition(200, 200);
		
		GraphicsEngine.getInstance().addDrawableObject(spaceShip);
		InputEngine.getInstance().setControllableObject(spaceShip);
		addUpdateableObject(spaceShip);
	}

	private void makeTestObject() {
		testobject = new TestObject();
		testobject.setImage(Toolkit.getDefaultToolkit().getImage(ResourceLoader.class.getResource("testimage.jpg")));
		
		GraphicsEngine.getInstance().addDrawableObject(testobject);
		InputEngine.getInstance().setControllableObject(testobject);
		addUpdateableObject(testobject);
	}
	
	private void sleepThread() {
		try {
			Thread.sleep(GAME_LOGIC_INTERVAL );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void updateObjects() {
		for(Updateable object : updateableObjects){
			object.update();
		}	
	}
	
	public void addUpdateableObject(Updateable object) {
		object.setUpdateAssignedIndex(updateableObjects.size());
		updateableObjects.add(object);
	}
	
	public void removeUpdateableObject(Updateable object){
		updateableObjects.removeElementAt(object.getUpdateAssignedIndex());
	}
	
	private void gameLoop(){
		Logger.getLogger(this.getClass()).info("Main loop started.");
		while(true){
			sleepThread();
			InputEngine.getInstance().update();
			CollisionEngine.getInstance().update();
			
			updateObjects();
			
		}
	}
}
