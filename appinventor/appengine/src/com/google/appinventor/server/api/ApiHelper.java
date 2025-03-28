package com.google.appinventor.server.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appinventor.server.util.CacheHeaders;
import com.google.appinventor.server.util.CacheHeadersImpl;

final class ApiHelper {
  private static final CacheHeaders CACHE_HEADERS = new CacheHeadersImpl();

  private ApiHelper() {}

  static String readRequestBody(HttpServletRequest req) throws IOException {
      try (final BufferedReader reader = req.getReader()) {
          return reader.lines().collect(Collectors.joining("\n"));
      }
  }

  static void writeJsonResponseBody(HttpServletResponse resp, String JsonRespBody) throws IOException {
    resp.setContentType("application/json; charset=utf-8");
    resp.getWriter().write(JsonRespBody);
  }

  static void setResponseStatus(HttpServletResponse resp, int status) {
    CACHE_HEADERS.setNotCacheable(resp);
    resp.setStatus(status);
  }
}
