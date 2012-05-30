package com.java2dgame.adapters;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.java2dgame.engines.GraphicsEngine;
import com.java2dgame.engines.InputEngine;


public class GameKeyAdapter implements KeyListener{
	
	boolean altPressed = false;
	boolean enterPressed = false;
	boolean shiftPressed = false;
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			enterPressed=true;
			break;
		case KeyEvent.VK_ALT:
			altPressed=true;
			break;
		case KeyEvent.VK_SHIFT:
			shiftPressed=true;
			break;
		}
		if(altPressed && enterPressed && shiftPressed) {
			GraphicsEngine.getInstance().toggleTrueFullScreen();
		} else {
			if(altPressed && enterPressed) {
				GraphicsEngine.getInstance().toggleFullScreen();
				e.consume();// Stop the event from propagating.
			} else {
				InputEngine.getInstance().pressKey(e.getKeyCode());
			}
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
		case KeyEvent.VK_SHIFT:
			shiftPressed=false;
			break;
		}

		if(altPressed && enterPressed && shiftPressed) {
			GraphicsEngine.getInstance().toggleTrueFullScreen();
		} else {
			if(altPressed && enterPressed) {
				GraphicsEngine.getInstance().toggleFullScreen();
				e.consume();// Stop the event from propagating.
			} else {
				InputEngine.getInstance().releaseKey(e.getKeyCode());
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e){}
}

