package com.java2dgame.adapters;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.java2dgame.engines.GraphicsEngine;


public class FullScreenCombinationAdapter implements KeyListener{
	
	boolean altPressed = false;
	boolean enterPressed = false;

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			enterPressed=true;

			break;
		case KeyEvent.VK_ALT:
			altPressed=true;
			break;
		}

		if(altPressed && enterPressed) {
			GraphicsEngine.getInstance().toggleFullScreen();
			e.consume();// Stop the event from propagating.
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			enterPressed=false;

			break;
		case KeyEvent.VK_ALT:
			altPressed=false;
			break;
		}

		if(altPressed && enterPressed) {
			GraphicsEngine.getInstance().toggleFullScreen();
			e.consume();// Stop the event from propagating.
		}
	}

	@Override
	public void keyTyped(KeyEvent e){}
}

