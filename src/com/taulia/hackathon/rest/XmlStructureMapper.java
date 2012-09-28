package com.taulia.hackathon.rest;

import java.util.HashMap;

import org.w3c.dom.Document;
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
      content.put(child.getNodeName(), getTextOfNode(child));
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

  public static String getTextOfNode(Node node) {
    if (node != null && node.hasChildNodes()) {
      Node textnode = node.getFirstChild();
      if (textnode.getNodeType() == Node.TEXT_NODE) {
        return textnode.getNodeValue();
      }
    }
    return null;
  }


  public static Node getNodeByTagName(Document doc, String tagName) {
    if (doc != null) {
      NodeList nodes = doc.getElementsByTagName(tagName);
      if (nodes.getLength() > 0) {
        return nodes.item(0);
      }
    }
    throw new IllegalArgumentException("Cannot find node [".concat(tagName).concat("] in null document"));
  }
}
