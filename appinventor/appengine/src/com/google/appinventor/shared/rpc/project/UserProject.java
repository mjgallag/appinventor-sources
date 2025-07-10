// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.project;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.appinventor.shared.rpc.RestJSONObject;
import jsinterop.annotations.JsProperty;

/**
 * Bundles user specific information about a project to send it over an RPC.
 *
 * @author lizlooney@google.com (Liz Looney)
 */
public class UserProject extends RestJSONObject implements IsSerializable {
  /**
   * The project's ID.
   */
  private long projectId;

  /**
   * The project's name.
   */
  private String projectName;

  /**
   * The project's type.
   */
  private String projectType;

  /**
   * The moved to Trash flag.
   */
  private boolean projectMovedToTrashFlag;

  /**
   * The date the project was created expressed in milliseconds since
   * January 1, 1970 UTC
   */
  private long creationDate;

  /**
   * The last date the project was modified expressed in milliseconds since
   * January 1, 1970 UTC
   */
  private long modificationDate;

  /**
   * The last date the project was exported expressed in milliseconds since
   * January 1, 1970 UTC
   */
  private long buildDate;

  private static final String DELIM = "#DELIM#";

  /**
   * Default constructor. This constructor is required by GWT.
   */
  @SuppressWarnings("unused")
  private UserProject() {
  }

  /**
   * Creates a new project info object.
   *
   * @param projectId the project id
   * @param projectName the project name
   * @param projectType the project type
   */
  public UserProject(long projectId, String projectName, String projectType, long creationDate, boolean projectMovedToTrashFlag) {
    this(projectId, projectName, projectType, creationDate, creationDate, 0,
        projectMovedToTrashFlag);
  }

  /**
   * Creates a new project info object.
   *
   * @param projectId the project id
   * @param projectName the project name
   * @param projectType the project type
   */
  public UserProject(long projectId, String projectName, String projectType, long creationDate,
      long modificationDate, boolean projectMovedToTrashFlag) {
    this(projectId, projectName, projectType, creationDate, modificationDate, 0,
        projectMovedToTrashFlag);
  }

  /**
   * Creates a new project info object.
   *
   * @param projectId the project id
   * @param projectName the project name
   * @param projectType the project type
   */
  public UserProject(long projectId, String projectName, String projectType, long creationDate,
      long modificationDate, long buildDate, boolean projectMovedToTrashFlag) {
    this.projectId = projectId;
    this.projectName = projectName;
    this.projectType = projectType;
    this.creationDate = creationDate;
    this.modificationDate = modificationDate;
    this.projectMovedToTrashFlag = projectMovedToTrashFlag;
    this.buildDate = buildDate;
  }

  public UserProject(Object json) {
    super(json);
  }

  /**
   * Returns the project ID.
   *
   * @return the projectId
   */
  public long getProjectId() {
    return projectId;
  }

  @JsProperty(name = "projectId")
  public double getProjectIdDouble() {
    return (double) getProjectId();
  }

  @JsProperty(name = "projectId")
  public void setProjectIdDouble(double projectId) {
    this.projectId = (long) projectId;
  }

  /**
   * Returns the project name.
   *
   * @return the projectName
   */
  @JsProperty
  public String getProjectName() {
    return projectName;
  }

  @JsProperty
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  /**
   * Returns the project type.
   *
   * @return the projectType
   */
  @JsProperty
  public String getProjectType() {
    return projectType;
  }

  @JsProperty
  public void setProjectType(String projectType) {
    this.projectType = projectType;
  }

  public long getDateCreated() {
    return creationDate;
  }

  @JsProperty(name = "creationDate")
  public double getDateCreatedDouble() {
    return (double) getDateCreated();
  }

  @JsProperty(name = "creationDate")
  public void setDateCreatedDouble(double creationDate) {
    this.creationDate = (long) creationDate;
  }

  public long getDateModified() {
    return modificationDate;
  }

  public void setDateModified(long modificationDate) {
    if (modificationDate != 0) {
      this.modificationDate = modificationDate;
    }
  }

  @JsProperty(name = "modificationDate")
  public double getDateModifiedDouble() {
    return (double) getDateModified();
  }

  @JsProperty(name = "modificationDate")
  public void setDateModifiedDouble(double modificationDate) {
    setDateModified((long) modificationDate);
  }

  public long getDateBuilt() {
    return buildDate;
  }

  public void setDateBuilt(long buildDate) {
    if (buildDate != 0) {
      this.buildDate = buildDate;
    }
  }

  @JsProperty(name = "buildDate")
  public double getDateBuiltDouble() {
    return (double) getDateBuilt();
  }

  @JsProperty(name = "buildDate")
  public void setDateBuiltDouble(double buildDate) {
    setDateBuilt((long) buildDate);
  }

  public void moveToTrash() {
    this.projectMovedToTrashFlag = true;
  }

  public void restoreFromTrash() {
    this.projectMovedToTrashFlag = false;
  }

  @JsProperty(name = "projectMovedToTrashFlag")
  public boolean isInTrash() {
    return projectMovedToTrashFlag;
  }

  @JsProperty(name = "projectMovedToTrashFlag")
  public void setIsInTrash(boolean isInTrash) {
    this.projectMovedToTrashFlag = isInTrash;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof UserProject)) {
      return false;
    }
    UserProject otherUserProject = (UserProject) other;
    return projectId == otherUserProject.projectId &&
        projectName.equals(otherUserProject.projectName) &&
        projectType.equals(otherUserProject.projectType) &&
        creationDate == otherUserProject.creationDate &&
        modificationDate == otherUserProject.modificationDate &&
        buildDate == otherUserProject.buildDate;
  }

  @Override
  public int hashCode() {
    return (int) (projectId ^ (projectId >>> 32));
  }

  @Override
  public String toString() {
    return projectId + DELIM + projectName + DELIM + projectType + DELIM + creationDate +
        DELIM + modificationDate + DELIM + buildDate;
  }

  public static UserProject valueOf(String text) {
    String[] parts = text.split(DELIM);
    if (parts.length < 5) {
      throw new IllegalArgumentException();
    }
    UserProject userProject = new UserProject();
    userProject.projectId = Long.parseLong(parts[0]);
    userProject.projectName = parts[1];
    userProject.projectType = parts[2];
    userProject.creationDate = Long.parseLong(parts[3]);
    userProject.modificationDate = Long.parseLong(parts[4]);
    userProject.buildDate = Long.parseLong(parts[5]);
    return userProject;
  }
}
