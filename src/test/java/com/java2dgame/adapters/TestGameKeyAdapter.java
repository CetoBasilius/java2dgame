package com.java2dgame.adapters;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import org.junit.Test;

import com.java2dgame.engines.GraphicsEngine;

public class TestGameKeyAdapter {

	@Test
	public void testFullscreenCombinationPress() {
		GameKeyAdapter keyAdapter = new GameKeyAdapter();
		GraphicsEngine.getInstance();
		
		assertFalse(keyAdapter.isShiftPressed());
		assertFalse(keyAdapter.isEnterPressed());
		assertFalse(keyAdapter.isAltPressed());
		
		KeyEvent keyEventAlt = createMock(KeyEvent.class);
		expect(keyEventAlt.getKeyCode()).andReturn(KeyEvent.VK_ALT).times(2);
		replay(keyEventAlt);
		
		keyAdapter.keyPressed(keyEventAlt);
		assertTrue(keyAdapter.isAltPressed());
		
		KeyEvent keyEventEnter = createMock(KeyEvent.class);	
		expect(keyEventEnter.getKeyCode()).andReturn(KeyEvent.VK_ENTER).times(1);
		keyEventEnter.consume();
		expectLastCall().once();
		replay(keyEventEnter);
		
		keyAdapter.keyPressed(keyEventEnter);
		assertTrue(keyAdapter.isEnterPressed());
			
		KeyEvent keyEventShift = createMock(KeyEvent.class);	
		expect(keyEventShift.getKeyCode()).andReturn(KeyEvent.VK_SHIFT).times(1);
		keyEventShift.consume();
		expectLastCall().once();
		replay(keyEventShift);
		
		keyAdapter.keyPressed(keyEventShift);
		assertTrue(keyAdapter.isShiftPressed());

		verify(keyEventAlt,keyEventEnter,keyEventShift);
	}
	
	@Test
	public void testFullscreenCombinationRelease() {
		GameKeyAdapter keyAdapter = new GameKeyAdapter();
		keyAdapter.altPressed = true;
		keyAdapter.enterPressed = true;
		keyAdapter.shiftPressed = true;
		GraphicsEngine.getInstance();
		
		KeyEvent keyEventAlt = createMock(KeyEvent.class);
		expect(keyEventAlt.getKeyCode()).andReturn(KeyEvent.VK_ALT).times(2);
		replay(keyEventAlt);
		
		keyAdapter.keyReleased(keyEventAlt);
		assertFalse(keyAdapter.isAltPressed());
		assertTrue(keyAdapter.isEnterPressed());
		assertTrue(keyAdapter.isShiftPressed());
		
		KeyEvent keyEventEnter = createMock(KeyEvent.class);	
		expect(keyEventEnter.getKeyCode()).andReturn(KeyEvent.VK_ENTER).times(2);
		replay(keyEventEnter);
		
		keyAdapter.keyReleased(keyEventEnter);
		assertFalse(keyAdapter.isAltPressed());
		assertFalse(keyAdapter.isEnterPressed());
		assertTrue(keyAdapter.isShiftPressed());
			
		KeyEvent keyEventShift = createMock(KeyEvent.class);	
		expect(keyEventShift.getKeyCode()).andReturn(KeyEvent.VK_SHIFT).times(2);
		replay(keyEventShift);
		
		keyAdapter.keyReleased(keyEventShift);
		assertFalse(keyAdapter.isShiftPressed());
		assertFalse(keyAdapter.isEnterPressed());
		assertFalse(keyAdapter.isAltPressed());

		verify(keyEventAlt,keyEventEnter,keyEventShift);
	}
}
