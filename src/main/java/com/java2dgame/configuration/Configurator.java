package com.java2dgame.configuration;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.java2dgame.app.Application;

public abstract class Configurator {

	private static final int DEFAULT_WIDTH = 640;
	private static final int DEFAULT_HEIGHT = 480;

	public static Dimension getConfigurationResolution() {
		
		Dimension returnDimension = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
		try {

			File fXmlFile = new File(Configurator.class.getResource("configuration.xml").getPath());
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("window");
			Node nNode = nList.item(0);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				returnDimension.width = Integer.parseInt(getTagValue("width", eElement));
				returnDimension.height = Integer.parseInt(getTagValue("height", eElement));
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			Logger.getLogger(Application.class).error("ParserConfigurationException");
		} catch (SAXException e) {
			e.printStackTrace();
			Logger.getLogger(Application.class).error("SAXException");
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(Application.class).error("IOException");
		} 

		Logger.getLogger(Application.class).info("Width="+(int)returnDimension.getWidth()+",Height="+(int)returnDimension.getWidth());
		
		return returnDimension;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}
