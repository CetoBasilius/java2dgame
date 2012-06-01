package com.java2dgame.adapters;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.java2dgame.engines.GraphicsEngine;
import com.java2dgame.engines.InputEngine;


public class GameKeyAdapter implements KeyListener{
	
	boolean altPressed = false;
	boolean enterPressed = false;
	boolean shiftPressed = false;
	
	InputEngine inputEngine = InputEngine.getInstance();
	GraphicsEngine graphicsEngine = GraphicsEngine.getInstance();
	
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
			graphicsEngine.toggleTrueFullScreen();
			e.consume();
		} else {
			if(altPressed && enterPressed) {
				graphicsEngine.toggleFullScreen();
				e.consume();
			} else {
				inputEngine.pressKey(e.getKeyCode());
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
			graphicsEngine.toggleTrueFullScreen();
			e.consume();
		} else {
			if(altPressed && enterPressed) {
				graphicsEngine.toggleFullScreen();
				e.consume();
			} else {
				inputEngine.releaseKey(e.getKeyCode());
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e){}
	
	public boolean isAltPressed() {
		return altPressed;
	}

	public boolean isEnterPressed() {
		return enterPressed;
	}

	public boolean isShiftPressed() {
		return shiftPressed;
	}

}

