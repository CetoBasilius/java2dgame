package com.java2dgame.configuration;

import java.awt.Dimension;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.java2dgame.helpers.XmlHelper;

public final class Configurator {

	private static final int DEFAULT_WIDTH = 640;
	private static final int DEFAULT_HEIGHT = 480;
	private static final String CONFIGURATION_FILE = "configuration.xml";
	
	private static Document configDoc;
	
	private Configurator(){}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}

	public static Dimension getConfigurationResolution() {
		
		Dimension returnDimension = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		try {

			NodeList nList = configDoc.getElementsByTagName("window");
			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				returnDimension.width = Integer.parseInt(XmlHelper.getTagValue("width", eElement));
				returnDimension.height = Integer.parseInt(XmlHelper.getTagValue("height", eElement));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(Configurator.class).warn("There was an error. default values will be used.");
		}

		Logger.getLogger(Configurator.class).info("Width="+(int)returnDimension.getWidth()+",Height="+(int)returnDimension.getHeight());

		return returnDimension;
	}

	public static void loadConfigurationFile() {
		configDoc = XmlHelper.loadXMLFile(Configurator.class,CONFIGURATION_FILE);
	}
}
