package com.taulia.hackathon.rest;

import android.util.Log;
import com.taulia.hackathon.bo.Invoice;
import java.text.SimpleDateFormat;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Matt Zeilenga
 */
public class EarlyPaymentRestHelper {
//  private static final String LOG_TAG = "EarlyPaymentRestHelper";


  public static String REST_METHOD_ID = "qry";

  public static String REST_METHOD_EP_COUNT = "ep_count";
  public static String REST_METHOD_EP_GET = "ep_get";
  public static String REST_METHOD_EP_LIST = "ep_list";
  public static String REST_METHOD_EP_SUBMIT = "ep_submit";


  public static Integer getEarlyPaymentCount() {

    String query = buildQueryString(REST_METHOD_EP_COUNT);

    Document doc = NetworkUtilities.doHttpPost(query);

    return Integer.parseInt(getNodeByTagName(doc, "ep_count").getNodeValue());
  }


  public static Invoice getEarlyPayableInvoice(String invoiceId) {
    Map<String, String> params = new HashMap<String, String>(1);
    params.put("inv_id", invoiceId);

    String query = buildQueryString(REST_METHOD_EP_GET, params);

    Document doc = NetworkUtilities.doHttpPost(query);

    Node invoiceNode = getNodeByTagName(doc, "ep");

    return new Invoice(invoiceNode);
  }


  public static ArrayList<Invoice> getListOfEarlyPayableInvoices() {

    String query = buildQueryString(REST_METHOD_EP_LIST);

    Document doc = NetworkUtilities.doHttpPost(query);

    NodeList elts = getNodeByTagName(doc, "eps_available").getChildNodes();
    int len = elts.getLength();

    ArrayList<Invoice> invoices = new ArrayList<Invoice>(len);

    for (int i = 0; i < elts.getLength(); i++) {
      Invoice inv = new Invoice(elts.item(i));
      invoices.add(inv);
    }

    return invoices;
  }


  public static Boolean submitEarlyPaymentRequest(String invoiceId, Date requestDate) {
    String epDate = new SimpleDateFormat("yyyy-MM-dd").format(requestDate);

    Map<String, String> params = new HashMap<String, String>(2);
    params.put("inv_id", invoiceId);
    params.put("ep_date", epDate);

    String query = buildQueryString(REST_METHOD_EP_SUBMIT);

    Document doc = NetworkUtilities.doHttpPost(query);

    NodeList response = getNodeByTagName(doc, "ep_submit").getChildNodes();

    Boolean success = false;
    for (int i = 0; i < response.getLength(); i++) {
      Node node = response.item(i);
      String nodeName = node.getNodeName();
      String nodeVal = node.getNodeValue();
      if (nodeName == "invoice_id" && nodeVal != invoiceId) {
//        String msg = "Invoice ID [".concat(nodeVal).concat("] in response does not match submitted ID [".concat(invoiceId).concat("]"));
//        Log.e(LOG_TAG, msg);
      }
      else if (nodeName == "payment_date" && nodeVal != epDate) {
//        String msg = "Payment date [".concat(nodeVal).concat("] in response does not match requested date [".concat(epDate).concat("]"));
//        Log.e(LOG_TAG, msg);
      }
      else if (nodeName == "success") {
        success = Boolean.parseBoolean(node.getNodeValue());
      }
    }
    return success;
  }


  private static Node getNodeByTagName(Document doc, String tagName) {
    if (doc != null) {
      NodeList nodes = doc.getElementsByTagName(tagName);
      if (nodes.getLength() > 0) {
        return nodes.item(0);
      }
    }
    throw new IllegalArgumentException("Cannot find node [".concat(tagName).concat("] in null document"));
  }

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
}
