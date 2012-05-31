package com.java2dgame.helpers;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlHelper {

	public static Document loadXMLFile(Class<?> classCaller,String fileName) {
		Document returnDocument = null;
		try {
			Logger.getLogger(XmlHelper.class).info("Loading "+fileName);
			
			InputStream xmlStream = classCaller.getResourceAsStream(fileName);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			returnDocument = dBuilder.parse(xmlStream);
			returnDocument.getDocumentElement().normalize();

			Logger.getLogger(XmlHelper.class).info(fileName+" was loaded correctly");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(XmlHelper.class).error("Could not find "+fileName);
		}
		return returnDocument; 
	}
	
	public static int getTagAmount(String sTag, Element eElement) {
		return  eElement.getElementsByTagName(sTag).getLength();
	}
	
	public static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		return nlList.item(0).getNodeValue();
	}
	
	public static String getTagValue(String sTag, Element eElement,int index) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(index).getChildNodes();
		return nlList.item(0).getNodeValue();
	}
}
