package com.google.appinventor.server.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appinventor.server.util.CacheHeaders;
import com.google.appinventor.server.util.CacheHeadersImpl;
import com.google.gson.Gson;

abstract class Api extends HttpServlet {
  private static final CacheHeaders CACHE_HEADERS = new CacheHeadersImpl();
  private static final Gson gson = new Gson();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if ("PATCH".equals(req.getMethod())) {
      doPatch(req, resp);
    } else {
      super.service(req, resp);
    }
  }

  protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
  }

  static <T> T getJson(HttpServletRequest req, Class<T> reqClass) throws IOException {
    final BufferedReader reader = req.getReader();
    final String json = reader.lines().collect(Collectors.joining("\n"));
    return gson.fromJson(json, reqClass);
  }

  static void sendJson(HttpServletResponse resp, Object respObj) throws IOException {
    resp.setContentType("application/json; charset=utf-8");
    resp.getWriter().write(gson.toJson(respObj));
    sendStatus(resp, HttpServletResponse.SC_OK);
  }

  static void sendStatus(HttpServletResponse resp, int status) {
    CACHE_HEADERS.setNotCacheable(resp);
    resp.setStatus(status);
  }
}
