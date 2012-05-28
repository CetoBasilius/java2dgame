package com.java2dgame.engines;

import java.util.Vector;

import org.apache.log4j.Logger;

public class CollisionEngine {
	
	private float lastCollisionDepthX;
	private float lastCollisionDepthY;
	private Vector<Collisionable> collisionableObjects = new Vector<Collisionable>(100,100);

	private static class CollisionEngineReferenceHolder {
        private static final CollisionEngine INSTANCE = new CollisionEngine();
    }

	private CollisionEngine(){
		if (Logger.getRootLogger().getAllAppenders().hasMoreElements()) {
			Logger.getLogger(this.getClass()).info("Collision engine started.");
		}
	}
	
	public static CollisionEngine getInstance() {
        return CollisionEngineReferenceHolder.INSTANCE;
    }
	
	public float distanceYBetweenSquareObjects(Collisionable object1,Collisionable object2){
		float absoluteDifferenceY = Math.abs((float)object1.getWorldPositionY()-(float)object2.getWorldPositionY());
		float objectHalfSizeY1=(float)object1.getSizeHeight()/2;
		float objectHalfSizeY2=(float)object2.getSizeHeight()/2;

		return absoluteDifferenceY-objectHalfSizeY1-objectHalfSizeY2;
	}
	
	public float distanceXBetweenSquareObjects(Collisionable object1,Collisionable object2){
		float absoluteDifferenceX = Math.abs((float)object1.getWorldPositionX()-(float)object2.getWorldPositionX());
		float objectHalfSizeX1=(float)object1.getSizeWidth()/2;
		float objectHalfSizeX2=(float)object2.getSizeWidth()/2;

		return (absoluteDifferenceX-objectHalfSizeX1-objectHalfSizeX2);
	}
	
	public float distanceBetweenSquareObjects(Collisionable object1,Collisionable object2){

		return (float)Math.sqrt(Math.pow(distanceXBetweenSquareObjects(object1,object2),2)+Math.pow(distanceYBetweenSquareObjects(object1,object2),2));
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
	
	
	private void checkAllObjects() {
		for(Collisionable object : collisionableObjects){
			//TODO actually check all objects
		}	
	}

	public void update() {
		checkAllObjects();
	}
}
