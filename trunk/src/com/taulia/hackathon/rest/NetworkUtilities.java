package com.taulia.hackathon.rest;

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.w3c.dom.Document;

/**
 * @author Matt Zeilenga
 */
public class NetworkUtilities {

  public static final String PROJECT_URI = "";

  /** Timeout (in ms) we specify for each http request */
  public static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000;

  /**
   * Configures the httpClient to connect to the URL provided.
   */
  public static HttpClient getHttpClient() {
    HttpClient httpClient = new DefaultHttpClient();
    final HttpParams params = httpClient.getParams();
    HttpConnectionParams.setConnectionTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
    HttpConnectionParams.setSoTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
    ConnManagerParams.setTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
    return httpClient;
  }

  public static HttpResponse post(ArrayList<NameValuePair> params)
  throws IOException, IllegalStateException {

    final HttpEntity entity;

    try {
      entity = new UrlEncodedFormEntity(params);
    } catch (final UnsupportedEncodingException e) {
      // this should never happen.
      throw new IllegalStateException(e);
    }
    final HttpPost post = new HttpPost(PROJECT_URI);
    post.addHeader(entity.getContentType());
    post.setEntity(entity);

    return getHttpClient().execute(post);
  }


  public static Document getResponseContent(ArrayList<NameValuePair> params) {
    HttpResponse resp;

    try {
      resp = NetworkUtilities.post(params);

      InputStream is = resp.getEntity().getContent();

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(is);
      doc.getDocumentElement().normalize();
      return doc;
    }
    catch (Exception ex) {
      return null;
    }
  }
}
