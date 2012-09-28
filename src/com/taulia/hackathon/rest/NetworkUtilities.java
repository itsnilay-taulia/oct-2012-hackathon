package com.taulia.hackathon.rest;

import android.util.Log;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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
//  private static final String LOG_TAG = "NetworkUtilities";

  public static final String PROJECT_URI = "http://72.167.4.179/taulia_android_app/index.php";

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

  public static HttpResponse post(String uriExt)
  throws IOException, IllegalStateException {

    final HttpPost post = new HttpPost(PROJECT_URI.concat(uriExt));

    return getHttpClient().execute(post);
  }


  public static Document doHttpPost(String uriExt) {
    HttpResponse resp;

    try {
      resp = NetworkUtilities.post(uriExt);
      HttpEntity ent = resp.getEntity();
      InputStream is = ent.getContent();

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      dbFactory.setIgnoringElementContentWhitespace(true);
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(is);
      return doc;
    }
    catch (Exception ex) {
//      Log.e(LOG_TAG, "Exception occured when getting content from POST", ex);
      return null;
    }
  }
}
