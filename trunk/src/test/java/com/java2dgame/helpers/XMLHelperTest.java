package com.java2dgame.helpers;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLHelperTest {
	
	@Test
	public void testGetTagAmount() {
		Element element = createNiceMock(Element.class);
		NodeList nodeList = createNiceMock(NodeList.class);
		
		expect(element.getElementsByTagName("tag")).andReturn(nodeList);
		expect(nodeList.getLength()).andReturn(5);	
		replay(element,nodeList);
		
		assertEquals(5,XmlHelper.getTagAmount("tag", element));
		
		verify(element,nodeList);	
	}
	
	@Test
	public void testGetTagValue() {
		Element element = createNiceMock(Element.class);
		NodeList nodeList = createNiceMock(NodeList.class);
		Node node = createNiceMock(Node.class);
		NodeList childNodeList = createNiceMock(NodeList.class);
		Node resultingNode = createNiceMock(Node.class);
		
		expect(element.getElementsByTagName("tag")).andReturn(nodeList);
		expect(nodeList.item(0)).andReturn(node);	
		expect(node.getChildNodes()).andReturn(childNodeList);
		expect(childNodeList.item(0)).andReturn(resultingNode);
		expect(resultingNode.getNodeValue()).andReturn("hello");
		replay(element,nodeList,node,childNodeList,resultingNode);
		
		assertEquals("hello",XmlHelper.getTagValue("tag", element));
		
		verify(element,nodeList,node,childNodeList,resultingNode);

	}
	
	@Test
	public void testGetTagValueInt() {
		Element element = createNiceMock(Element.class);
		NodeList nodeList = createNiceMock(NodeList.class);
		Node node = createNiceMock(Node.class);
		NodeList childNodeList = createNiceMock(NodeList.class);
		Node resultingNode = createNiceMock(Node.class);
		
		int index = 20;
		
		expect(element.getElementsByTagName("tag")).andReturn(nodeList);
		expect(nodeList.item(index)).andReturn(node);	
		expect(node.getChildNodes()).andReturn(childNodeList);
		expect(childNodeList.item(0)).andReturn(resultingNode);
		expect(resultingNode.getNodeValue()).andReturn("hello");
		replay(element,nodeList,node,childNodeList,resultingNode);
		
		assertEquals("hello",XmlHelper.getTagValue("tag", element,index));
		
		verify(element,nodeList,node,childNodeList,resultingNode);

	}
	
}
