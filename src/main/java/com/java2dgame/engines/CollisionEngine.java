package com.java2dgame.engines;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.java2dgame.entities.Collisionable;

public class CollisionEngine {
	private float lastCollisionDepthX;
	private float lastCollisionDepthY;
	private Vector<Collisionable> collisionableObjects = new Vector<Collisionable>(100,100);	

	//Initialization On Demand Holder
	private static class CollisionEngineReferenceHolder {
        private static final CollisionEngine INSTANCE = new CollisionEngine();
    }

	private CollisionEngine(){
		Logger.getLogger(this.getClass()).info("Collision engine started.");
	}
	
	public static CollisionEngine getInstance() {
        return CollisionEngineReferenceHolder.INSTANCE;
    }
	
	public float distanceYBetweenObjectsToEdges(Collisionable object1,Collisionable object2){
		float absoluteDifferenceY = Math.abs((float)object1.getWorldPositionY()-(float)object2.getWorldPositionY());
		float objectHalfSizeY1=(float)object1.getSizeHeight()/2;
		float objectHalfSizeY2=(float)object2.getSizeHeight()/2;

		return absoluteDifferenceY-objectHalfSizeY1-objectHalfSizeY2;
	}
	
	public float distanceXBetweenObjectsToEdges(Collisionable object1,Collisionable object2){
		float absoluteDifferenceX = Math.abs((float)object1.getWorldPositionX()-(float)object2.getWorldPositionX());
		float objectHalfSizeX1=(float)object1.getSizeWidth()/2;
		float objectHalfSizeX2=(float)object2.getSizeWidth()/2;

		return (absoluteDifferenceX-objectHalfSizeX1-objectHalfSizeX2);
	}
	
	/**
	 * This will check the distance between 2 objects from their edges
	 * 
	 * @param object1 The first object.
	 * @param object2 The second object.
	 * @return The distance between the nearest edges of the objects. value will be negative if squares overlap.
	 */
	public float distanceBetweenObjectsEdges(Collisionable object1,Collisionable object2){
		return (float)Math.sqrt(Math.pow(distanceXBetweenObjectsToEdges(object1,object2),2)+Math.pow(distanceYBetweenObjectsToEdges(object1,object2),2));
	}
	
	
	/**
	 * This will check the distance between 2 objects from their center
	 * 
	 * @param object1 The first object.
	 * @param object2 The second object.
	 * @return The distance between the center of both objects. cannot be negative.
	 */
	public float distanceBetweenObjectsCenter(Collisionable object1,Collisionable object2){
		return (float)Math.sqrt(Math.pow(object1.getWorldPositionX()-object2.getWorldPositionX(),2)+Math.pow(object1.getWorldPositionY()-object2.getWorldPositionY(),2));
	}
	
	public boolean squareObjectsCollideX(Collisionable object1,Collisionable object2){
		float differenceX = (float)object1.getWorldPositionX()-(float)object2.getWorldPositionX();
			
		float objectHalfSizeX1=(float)object1.getSizeWidth()/2;
		float objectHalfSizeX2=(float)object2.getSizeWidth()/2;
		
		boolean collisionOcurred = false;
		//Means object 1 is <
		if(differenceX<0)
		{
			float collisionCheckX = differenceX+objectHalfSizeX1+objectHalfSizeX2;
			if(collisionCheckX<0){
				collisionOcurred=false;
			}else{
				collisionOcurred=true;
				lastCollisionDepthX=collisionCheckX;
			}
		}
		//Means object 1 is >
		else
		{
			float collisionCheckX = differenceX-objectHalfSizeX1-objectHalfSizeX2;
			if(collisionCheckX>0){
				collisionOcurred=false;
			}else{
				collisionOcurred=true;
			}
		}
		
		return collisionOcurred;
	}
	
	public boolean squareObjectsCollideY(Collisionable object1,Collisionable object2){
		float differenceY = (float)object1.getWorldPositionY()-(float)object2.getWorldPositionY();
			
		float objectHalfSizeY1=(float)object1.getSizeHeight()/2;
		float objectHalfSizeY2=(float)object2.getSizeHeight()/2;
		
		boolean collisionOcurred = false;
		//Means object 1 is <
		if(differenceY<0)
		{
			float collisionCheckX = differenceY+objectHalfSizeY1+objectHalfSizeY2;
			if(collisionCheckX<0){
				collisionOcurred=false;
			}else{
				collisionOcurred=true;
				lastCollisionDepthY=collisionCheckX;
			}
		}
		//Means object 1 is >
		else
		{
			float collisionCheckX = differenceY-objectHalfSizeY1-objectHalfSizeY2;
			if(collisionCheckX>0){
				collisionOcurred=false;
			}else{
				collisionOcurred=true;
			}
		}
		
		return collisionOcurred;
	}
	
	public boolean circularObjectsCollide(Collisionable object1, Collisionable object2) {
		boolean collisionOcurred = false;
		float distance = distanceBetweenObjectsCenter(object1,object2);
	
		float result = distance - object1.getRadius() - object2.getRadius();
		if(result < 0) {
			collisionOcurred = true;
		} else {
			collisionOcurred = false;
		}
		
		return collisionOcurred;
	}
	
	public boolean squareObjectsCollide(Collisionable object1,Collisionable object2){
		boolean collisionOcurred = false;
		
		if(squareObjectsCollideX(object1,object2) && squareObjectsCollideY(object1,object2)){
			collisionOcurred=true;
		}else{
			collisionOcurred=false;
		}
		
		return collisionOcurred;
	}
	
	public float[] getLastCollisionDepth(){
		float[] collisionDepth = {lastCollisionDepthX,lastCollisionDepthY};
		return collisionDepth;
	}
	
	public synchronized void addCollisionObject(Collisionable object){
		collisionableObjects.add(object);
	}
	
	//TODO implement this
	private synchronized void checkAllObjects() {
		for(int index = collisionableObjects.size()-1;index>0;index--){
			Collisionable object = collisionableObjects.get(index);
			if(object.getLife()!=0) {
				checkObjectAgainstAllObjects(object);
			}
			else {
				collisionableObjects.removeElementAt(index);
			}
		}	
	}

	private synchronized void checkObjectAgainstAllObjects(Collisionable object1) {
		for(int index = 0;index<collisionableObjects.size();index++){
			Collisionable object2 = collisionableObjects.get(index);
			object1.getCollisionBehavior().update(object1,object2);
		}
	}

	public void update() {
		checkAllObjects();
	}
}
