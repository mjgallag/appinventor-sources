package com.google.appinventor.server.api;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appinventor.server.UserInfoServiceImpl;

public class UserSettingsApi extends HttpServlet {
  private static final UserInfoServiceImpl USER_INFO_SERVICE = new UserInfoServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final String data = USER_INFO_SERVICE.loadUserSettings();
    ApiHelper.writeJsonResponseBody(resp, data);
    ApiHelper.setResponseStatus(resp, HttpServletResponse.SC_OK);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final String userSettings = ApiHelper.readRequestBody(req);
    USER_INFO_SERVICE.storeUserSettings(userSettings);
    ApiHelper.setResponseStatus(resp, HttpServletResponse.SC_NO_CONTENT);
  }
}
