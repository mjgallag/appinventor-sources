package com.google.appinventor.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.StatusCodeException;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

public class Api {
  private static final Logger LOG = Logger.getLogger(Api.class.getName());

  public static <T> void get(String url, Class<T> respClass, AsyncCallback<T> callback) {
    sendRequest("GET", url, null, respClass, callback);
  }

  public static <T> void put(String url, Object reqObj, Class<T> respClass,
      AsyncCallback<T> callback) {
    sendRequest("PUT", url, reqObj, respClass, callback);
  }

  public static <T> void patch(String url, Object reqObj, Class<T> respClass,
      AsyncCallback<T> callback) {
    sendRequest("PATCH", url, reqObj, respClass, callback);
  }

  private static <T> void sendRequest(String method, String url, Object reqObj, Class<T> respClass,
      AsyncCallback<T> callback) {
    final String reqJson = reqObj != null ? JSON.stringify(reqObj) : null;
    final ApiRequestBuilder builder = new ApiRequestBuilder(method, URL.encode(url));
    builder.setHeader("Content-Type", "application/json; charset=utf-8");

    try {
      builder.sendRequest(reqJson, new RequestCallback() {
        @Override
        public void onResponseReceived(Request req, Response resp) {
          final int statusCode = resp.getStatusCode();
          if (statusCode == Response.SC_OK || statusCode == Response.SC_NO_CONTENT) {
            final T respObj = respClass != Void.class ? JSON.parse(resp.getText()) : null;
            callback.onSuccess(respObj);
          } else {
            callback.onFailure(
                new StatusCodeException(statusCode, resp.getStatusText(), resp.getText()));
          }
        }

        @Override
        public void onError(Request req, Throwable e) {
          callback.onFailure(e);
        }
      });
    } catch (RequestException e) {
      callback.onFailure(e);
    }
  }

  public abstract static class AsyncCallback<T> {
    protected String failureMessage;

    public AsyncCallback() {}

    public AsyncCallback(String defaultFailureMessage) {
      failureMessage = defaultFailureMessage;
    }

    public abstract void onSuccess(T result);

    public void onFailure(Throwable caught) {
      if ((caught instanceof StatusCodeException)
          && ((StatusCodeException) caught).getStatusCode() == Response.SC_PRECONDITION_FAILED) {
        Ode.getInstance().sessionDead();
        return;
      }

      final String errorMessage = (failureMessage == null) ? caught.getMessage() : failureMessage;
      ErrorReporter.reportError(errorMessage);
      LOG.log(Level.SEVERE, "Got exception", caught);
    }
  }

  private static class ApiRequestBuilder extends RequestBuilder {
    private ApiRequestBuilder(String httpMethod, String url) {
      super(httpMethod, url);
    }
  }

  @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "JSON")
  private static class JSON {
    private static native String stringify(Object obj);

    private static native <T> T parse(String json);
  }
}
