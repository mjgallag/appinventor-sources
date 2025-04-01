// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2014-2015 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.user;

import com.google.gwt.user.client.rpc.IsSerializable;
import jsinterop.annotations.JsProperty;
import java.io.Serializable;

/**
 * Data Transfer Object representing user data.
 *
 */
public class Config implements IsSerializable, Serializable {
  // Unique identifier for the user
  @JsProperty
  public User user;

  @JsProperty
  public String rendezvousServer = null;

  @JsProperty
  public SplashConfig splashConfig;

  @JsProperty
  public String libraryUrl;
  @JsProperty
  public String getStartedUrl;
  @JsProperty
  public String tutorialsUrl;
  @JsProperty
  public String extensionsUrl;
  @JsProperty
  public String troubleshootingUrl;
  @JsProperty
  public String forumsUrl;
  @JsProperty
  public String feedbackUrl;
  @JsProperty
  public String releaseNotesUrl;
  @JsProperty
  public String tosUrl;
  @JsProperty
  public String logoUrl;
  @JsProperty
  public String guideUrl;
  @JsProperty
  public String referenceComponentsUrl;
  @JsProperty
  public String firebaseURL;   // Default Firebase URL
  @JsProperty
  public String defaultCloudDBserver;
  @JsProperty
  public int noop;            // No-op interval
  @JsProperty
  public boolean secondBuildserver; // Whether or not we have a second
                                     // buildserver (used for a different
                                     // target SDK).
  @JsProperty
  public boolean galleryEnabled;
  @JsProperty
  public String galleryLocation;
  @JsProperty
  public boolean galleryReadOnly;
  @JsProperty
  public String[] tutorialUrlAllowed;
  @JsProperty
  public boolean serverExpired;
  @JsProperty
  public boolean deleteAccountAllowed;
  @JsProperty
  public String iosExtensions;
  @JsProperty
  public String surveyUrl;

  public Config() {
  }
}
