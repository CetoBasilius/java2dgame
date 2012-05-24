package com.java2dgame.app;

import com.java2dgame.engines.GraphicsEngine;
import com.java2dgame.engines.InputEngine;

public final class MainLoop {
	
	private MainLoop(){}
	
	public void Loop(){
		TestObject testObject = new TestObject();
		GraphicsEngine.addDrawableObject(testObject);
		while(true){
			InputEngine.getInstance().update();
		}	
	}
}
