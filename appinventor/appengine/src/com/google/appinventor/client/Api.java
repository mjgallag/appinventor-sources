package com.google.appinventor.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

public class Api {
  private static final Logger LOG = Logger.getLogger(Api.class.getName());

  public static void get(String url, Callback<String> callback) {
    sendRequest(RequestBuilder.GET, url, null, null, callback, response -> response.getText());
  }

  public static void put(String url, String data, Callback<Void> callback) {
    sendRequest(RequestBuilder.PUT, url, data, "application/json; charset=utf-8", callback, response -> null);
  }

  private static <T> void sendRequest(RequestBuilder.Method method, String url, String data, String contentType,
      Callback<T> callback, ResponseConverter<T> converter) {
    try {
      RequestBuilder builder = new RequestBuilder(method, URL.encode(url));
      if (contentType != null) {
        builder.setHeader("Content-Type", contentType);
      }
      builder.sendRequest(data, new RequestCallback() {
        @Override
        public void onResponseReceived(Request request, Response response) {
          int statusCode = response.getStatusCode();
          if (statusCode == Response.SC_OK || statusCode == Response.SC_NO_CONTENT) {
            callback.onSuccess(converter.convert(response));
          } else {
            handleErrorResponse(response, callback);
          }
        }

        @Override
        public void onError(Request request, Throwable exception) {
          handleFailure(exception, callback);
        }
      });
    } catch (RequestException e) {
      handleFailure(e, callback);
    }
  }

  private static <T> void handleFailure(Throwable caught, Callback<T> callback) {
    String errorMessage = callback.failureMessage == null ? caught.getMessage() : callback.failureMessage;
    ErrorReporter.reportError(errorMessage);
    LOG.log(Level.SEVERE, "Got exception", caught);
    callback.onFailure(caught);
  }

  private static <T> void handleErrorResponse(Response response, Callback<T> callback) {
    int statusCode = response.getStatusCode();
    if (statusCode == Response.SC_PRECONDITION_FAILED) {
      Ode.getInstance().sessionDead();
      return;
    }
    String errorMessage = callback.failureMessage;
    if (errorMessage == null) {
      errorMessage = "Server returned error code " + statusCode +
          (response.getText() != null && !response.getText().isEmpty() ? ": " + response.getText() : "");
    }
    ErrorReporter.reportError(errorMessage);
    LOG.log(Level.SEVERE, "ApiRequest error: " + statusCode + " " + response.getText());
    callback.onFailure(new Exception("HTTP " + statusCode + ": " + response.getText()));
  }

  public abstract static class Callback<T> {
    protected String failureMessage;

    public Callback() {
    }

    public Callback(String defaultFailureMessage) {
      failureMessage = defaultFailureMessage;
    }

    public abstract void onSuccess(T result);

    public void onFailure(Throwable caught) {
    }
  }

  private interface ResponseConverter<T> {
    T convert(Response response);
  }
}
