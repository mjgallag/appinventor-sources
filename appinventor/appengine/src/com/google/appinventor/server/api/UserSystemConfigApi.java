package com.google.appinventor.server.api;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appinventor.shared.rpc.user.Config;
import com.google.appinventor.shared.rpc.user.User;

public class UserSystemConfigApi extends UserApi {
  @Override
  protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final Config userSystemConfigPatch = Api.getJson(req, Config.class);
    final String userSessionId = Optional.ofNullable(userSystemConfigPatch)
        .map(config -> config.user).map(User::getSessionId).orElse(null);
    final Config updatedUserSystemConfig = USER_INFO_SERVICE.getSystemConfig(userSessionId);
    sendJson(resp, updatedUserSystemConfig);
    sendStatus(resp, HttpServletResponse.SC_OK);
  }
}
