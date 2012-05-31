package com.java2dgame.behaviors;

import org.junit.Test;

import org.junit.Test;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.java2dgame.entities.Collisionable;

public class TestCollisionBehaviors {

	@Test
	public void testEnergyTransferCollision(){
		CollisionEnergyTransfer behavior = new CollisionEnergyTransfer();
		
		Collisionable mock1 = createMock(Collisionable.class);
		Collisionable mock2 = createMock(Collisionable.class);
		
		expect(mock1.getRadius()).andReturn(8.0f).once();
		expect(mock2.getRadius()).andReturn(8.0f).once();
		
		expect(mock1.isCollisionActive()).andReturn(true).once();
		expect(mock2.isCollisionActive()).andReturn(true).once();
		
		expect(mock1.getWorldPositionX()).andReturn(2.0f).times(2);
		expect(mock1.getWorldPositionY()).andReturn(0.0f).times(2);
		expect(mock1.getHorizontalVelocity()).andReturn(-1.0f).once();
		expect(mock1.getVerticalVelocity()).andReturn(0.0f).once();
		
		expect(mock2.getWorldPositionX()).andReturn(-2.0f).times(2);
		expect(mock2.getWorldPositionY()).andReturn(0.0f).times(2);
		expect(mock2.getHorizontalVelocity()).andReturn(1.0f).once();
		expect(mock2.getVerticalVelocity()).andReturn(0.0f).once();
		
		
		mock1.setVelocity(1.0f, 450);
		expectLastCall().once();
		mock2.setVelocity(1.0f, 270);
		expectLastCall().once();
		
		mock1.updateCollision();
		expectLastCall().once();
		mock2.updateCollision();
		expectLastCall().once();
		
		replay(mock1,mock2);
		
		behavior.update(mock1, mock2);
		
		verify(mock1,mock2);
		
		float delta = 1.0f;
		assertEquals(1.0f,behavior.getEnergyTransferLossRatio()+behavior.getEnergyTransferRatio(),delta);
	}
	
	@Test
	public void testDoNothingCollision(){
		CollisionBehavior behavior = new CollisionDoNothing();
		
		Collisionable mock1 = createMock(Collisionable.class);
		Collisionable mock2 = createMock(Collisionable.class);

		replay(mock1,mock2);

		behavior.update(mock1, mock2);

		verify(mock1,mock2);	
	}
}
