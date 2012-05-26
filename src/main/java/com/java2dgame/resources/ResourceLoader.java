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
			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				int numberOfImages = XMLHelper.getTagAmount("image", eElement);
				Logger.getLogger(ResourceLoader.class).info(("There are "+numberOfImages+" images to load."));
				for(int a = 0;a<numberOfImages;a++) {
					//TODO actually load images here
					XMLHelper.getTagValue("image", eElement,a);
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(ResourceLoader.class).error("There was an error.");
		}
	}
}
