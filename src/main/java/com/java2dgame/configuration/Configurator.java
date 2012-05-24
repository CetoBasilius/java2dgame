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

public final class Configurator {

	private static final int DEFAULT_WIDTH = 640;
	private static final int DEFAULT_HEIGHT = 480;
	private static final String CONFIGURATION_FILE = "configuration.xml";
	
	private static Document configDoc;
	
	private Configurator(){
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
	
	public static void loadConfigurationFile() {
		
		try {
			Logger.getLogger(Configurator.class).info("Loading "+CONFIGURATION_FILE);
			
			File xmlFile = new File(Configurator.class.getResource(CONFIGURATION_FILE).getPath());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			configDoc = dBuilder.parse(xmlFile);
			configDoc.getDocumentElement().normalize();
			
			Logger.getLogger(Configurator.class).info(CONFIGURATION_FILE+" was loaded correctly");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			Logger.getLogger(Configurator.class).error("ParserConfigurationException.");
		} catch (SAXException e) {
			e.printStackTrace();
			Logger.getLogger(Configurator.class).error("SAXException.");
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(Configurator.class).error("IOException.");
		} 
	}

	public static Dimension getConfigurationResolution() {
		
		Dimension returnDimension = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		try {

			NodeList nList = configDoc.getElementsByTagName("window");
			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				returnDimension.width = Integer.parseInt(getTagValue("width", eElement));
				returnDimension.height = Integer.parseInt(getTagValue("height", eElement));
			}
		}catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(Configurator.class).error("There was an error.");
		}

		Logger.getLogger(Configurator.class).info("Width="+(int)returnDimension.getWidth()+",Height="+(int)returnDimension.getHeight());

		return returnDimension;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}
