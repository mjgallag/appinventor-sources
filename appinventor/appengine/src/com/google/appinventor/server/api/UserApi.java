package com.google.appinventor.server.api;

import com.google.appinventor.server.UserInfoServiceImpl;

abstract class UserApi extends Api {
  protected static final UserInfoServiceImpl USER_INFO_SERVICE = new UserInfoServiceImpl();
}
