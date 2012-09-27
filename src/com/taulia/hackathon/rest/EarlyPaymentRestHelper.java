package com.taulia.hackathon.rest;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * @author Matt Zeilenga
 */
public class EarlyPaymentRestHelper {


  public static String REST_METHOD_ID = "qry";

  public static String REST_METHOD_EP_COUNT = "ep_count";


  public static String getEarlyPaymentCount() {
    ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair(REST_METHOD_ID, REST_METHOD_EP_COUNT));

    Document doc = NetworkUtilities.getResponseContent(params);

    return getTagValue(doc, "ep_count");
  }


  private static String getTagValue(Document doc, String tagName) {
    NodeList nodes = doc.getElementsByTagName(tagName);
    if (nodes.getLength() > 0) {
      return nodes.item(0).getNodeValue();
    }
    return null;
  }


/*
  private static String buildQueryString(String method) {
    return "?qry=".concat(method);
  }

  private static String buildQueryString(String method, Map<String, String> args) {
    String qry = buildQueryString(method);
    Iterator it = args.entrySet().iterator();
    while (it.hasNext()) {
      String key = (String) it.next();
      String val = args.get(key);
      qry = qry.concat("?").concat(key).concat("=").concat(val);
    }
    return qry;
  }

 */
}
