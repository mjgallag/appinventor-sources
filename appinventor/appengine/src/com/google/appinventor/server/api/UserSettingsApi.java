package com.google.appinventor.server.api;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSettingsApi extends UserApi {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final String userSettingsJson = USER_INFO_SERVICE.loadUserSettings();
    sendJson(resp, userSettingsJson);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final String userSettingsJson = Api.getJson(req, String.class);
    USER_INFO_SERVICE.storeUserSettings(userSettingsJson);
    sendStatus(resp, HttpServletResponse.SC_NO_CONTENT);
  }
}
