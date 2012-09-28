package com.taulia.hackathon.rest;

import java.util.HashMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Matt Zeilenga
 */
public class XmlStructureMapper {

  private HashMap<String, String> content;

  public XmlStructureMapper(Node xmlContent) {
    this.content = new HashMap<String, String>();

    NodeList children = xmlContent.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child.hasChildNodes()) {
        Node subchild = child.getFirstChild();
        if (subchild.getNodeType() == Node.TEXT_NODE) {
          content.put(child.getNodeName(), subchild.getNodeValue());
        }
      }
    }
  }

  public String getFieldValue(String fieldName) {
    if (content.containsKey(fieldName)) {
      return content.get(fieldName);
    }
    else {
      return null;
    }
  }
}
