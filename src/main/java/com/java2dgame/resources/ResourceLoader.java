package com.java2dgame.resources;

import com.java2dgame.helpers.XmlHelper;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class ResourceLoader {
	
	private static Document resourceList;
	private final static String RESOURCELIST_FILENAME = "resources.xml";
	
	private ResourceLoader(){
		Logger.getLogger(this.getClass()).info("Resource loader started.");
		resourceList = loadResourceList();
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
	
	public static Document loadResourceList(){
		return XmlHelper.loadXMLFile(ResourceLoader.class, RESOURCELIST_FILENAME);
	}
	
	
	public void getImages() {
		try {

			NodeList nList = resourceList.getElementsByTagName("images");
			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				int numberOfImages = XmlHelper.getTagAmount("image", eElement);
				Logger.getLogger(ResourceLoader.class).info(("There are "+numberOfImages+" images to load."));
				for(int a = 0;a<numberOfImages;a++) {
					//TODO actually load images here
					XmlHelper.getTagValue("image", eElement, a);
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(ResourceLoader.class).error("There was an error.");
		}
	}
	
	public Document getResourceList() {
		return resourceList;
	}

	public static String getResourcelistFilename() {
		return RESOURCELIST_FILENAME;
	}
}
