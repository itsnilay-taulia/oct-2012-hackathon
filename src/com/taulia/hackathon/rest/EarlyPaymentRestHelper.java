package com.taulia.hackathon.rest;

import android.util.Log;
import com.taulia.hackathon.bo.Invoice;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Matt Zeilenga
 */
public class EarlyPaymentRestHelper {
  private static final String LOG_TAG = "EarlyPaymentRestHelper";


  public static String REST_METHOD_ID = "qry";

  public static String REST_METHOD_EP_COUNT = "ep_count";
  public static String REST_METHOD_EP_GET = "ep_get";
  public static String REST_METHOD_EP_LIST = "ep_list";
  public static String REST_METHOD_EP_SUBMIT = "ep_submit";


  public static Integer getEarlyPaymentCount() {
    ArrayList<NameValuePair> params = createHttpPostParams(REST_METHOD_EP_COUNT);

    Document doc = NetworkUtilities.doHttpPost(params);

    return Integer.parseInt(getNodeByTagName(doc, "ep_count").getNodeValue());
  }


  public static Invoice getEarlyPayableInvoice(String invoiceId) {
    ArrayList<NameValuePair> params = createHttpPostParams(REST_METHOD_EP_GET);
    params.add(new BasicNameValuePair("inv_id", invoiceId));

    Document doc = NetworkUtilities.doHttpPost(params);

    Node invoiceNode = getNodeByTagName(doc, "ep");

    return new Invoice(invoiceNode);
  }


  public static ArrayList<Invoice> getListOfEarlyPayableInvoices() {
    ArrayList<NameValuePair> params = createHttpPostParams(REST_METHOD_EP_LIST);

    Document doc = NetworkUtilities.doHttpPost(params);

    NodeList elts = doc.getElementsByTagName("eps_available");
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

    ArrayList<NameValuePair> params = createHttpPostParams(REST_METHOD_EP_SUBMIT);
    params.add(new BasicNameValuePair("inv_id", invoiceId));
    params.add(new BasicNameValuePair("ep_date", epDate));

    Document doc = NetworkUtilities.doHttpPost(params);

    NodeList response = getNodeByTagName(doc, "ep_submit").getChildNodes();

    Boolean success = false;
    for (int i = 0; i < response.getLength(); i++) {
      Node node = response.item(i);
      String nodeName = node.getNodeName();
      String nodeVal = node.getNodeValue();
      if (nodeName == "invoice_id" && nodeVal != invoiceId) {
        String msg = "Invoice ID [".concat(nodeVal).concat("] in response does not match submitted ID [".concat(invoiceId).concat("]"));
        Log.e(LOG_TAG, msg);
      }
      else if (nodeName == "payment_date" && nodeVal != epDate) {
        String msg = "Payment date [".concat(nodeVal).concat("] in response does not match requested date [".concat(epDate).concat("]"));
        Log.e(LOG_TAG, msg);
      }
      else if (nodeName == "success") {
        success = Boolean.parseBoolean(node.getNodeValue());
      }
    }
    return success;
  }


  private static ArrayList<NameValuePair> createHttpPostParams(String method) {
    ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair(REST_METHOD_ID, REST_METHOD_EP_COUNT));
    return params;
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
