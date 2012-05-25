package com.java2dgame.resources;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.java2dgame.helpers.XMLHelper;

public final class ResourceLoader {
	
	private static Document resourceList;
	private final String RESOURCELIST_FILENAME = "resources.xml";
	
	private ResourceLoader(){
		Logger.getLogger(this.getClass()).info("Resource loader started.");
		loadResourceList();
	}
	
	private static class ResourceLoaderReferenceHolder {
        private static final ResourceLoader INSTANCE = new ResourceLoader();
    }
	
	public static ResourceLoader getInstance() {
        return ResourceLoaderReferenceHolder.INSTANCE;
    }

	@Override
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
	
	public void loadResourceList(){
		resourceList = XMLHelper.loadXMLFile(ResourceLoader.class,RESOURCELIST_FILENAME);
	}
	
	
	public void getImages() {
		
		try {

			NodeList nList = resourceList.getElementsByTagName("images");
			
			
			//TODO iterate through all of the items and store them in a URL or load them directly
			Node nNode = nList.item(0);
			
			//TODO this is a test space where anything can change 
			
			

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				Logger.getLogger(ResourceLoader.class).error("There are "+eElement.getChildNodes().getLength()+" images to load");
				
				//System.out.println(XMLHelper.getTagValue("image", eElement,1));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(ResourceLoader.class).error("There was an error.");
		}
	}
}
