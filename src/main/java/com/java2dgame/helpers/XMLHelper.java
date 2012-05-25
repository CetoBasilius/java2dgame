package com.java2dgame.helpers;

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

public class XMLHelper {

	public static Document loadXMLFile(Class<?> classCaller,String fileName) {
		Document returnDocument = null;
		try {
			Logger.getLogger(XMLHelper.class).info("Loading "+fileName);

			File xmlFile = new File(classCaller.getResource(fileName).getPath());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			returnDocument = dBuilder.parse(xmlFile);
			returnDocument.getDocumentElement().normalize();

			Logger.getLogger(XMLHelper.class).info(fileName+" was loaded correctly");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			Logger.getLogger(XMLHelper.class).error("ParserConfigurationException.");
		} catch (SAXException e) {
			e.printStackTrace();
			Logger.getLogger(XMLHelper.class).error("SAXException.");
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(XMLHelper.class).error("IOException.");
		}
		return returnDocument; 
	}

	public static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
	
	public static String getTagValue(String sTag, Element eElement,int index) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(index).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}
