package com.google.appinventor.client.rest;

import java.util.List;
import com.google.appinventor.shared.rpc.RpcResult;
import com.google.appinventor.shared.rpc.project.ChecksumedLoadFile;
import com.google.appinventor.shared.rpc.project.FileDescriptor;
import com.google.appinventor.shared.rpc.project.FileDescriptorWithContent;
import com.google.appinventor.shared.rpc.project.NewProjectParameters;
import com.google.appinventor.shared.rpc.project.ProjectRootNode;
import com.google.appinventor.shared.rpc.project.ProjectServiceAsync;
import com.google.appinventor.shared.rpc.project.TextFile;
import com.google.appinventor.shared.rpc.project.UserProject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

public class ProjectsRestService extends RestService implements ProjectServiceAsync {
  private static final String PROJECTS_BASE = REST_BASE + "/projects";

  @Override
  public void newProject(String projectType, String projectName, NewProjectParameters params,
      AsyncCallback<UserProject> callback) {
    Console.log("newProject called");
  }

  @Override
  public void newProjectFromTemplate(String projectName, String pathToZip,
      AsyncCallback<UserProject> callback) {
    Console.log("newProjectFromTemplate called");
  }

  @Override
  public void newProjectFromExternalTemplate(String projectName, String zipData,
      AsyncCallback<UserProject> callback) {
    Console.log("newProjectFromExternalTemplate called");
  }

  @Override
  public void retrieveTemplateData(String pathToTemplates, AsyncCallback<String> callback) {
    if ("templates/".equals(pathToTemplates)) {
      get(PROJECTS_BASE + "/templates", String.class, callback);
    } else {
      get(PROJECTS_BASE + "/templates/" + pathToTemplates, String.class, callback);
    }
  }

  @Override
  public void copyProject(long oldProjectId, String newName, AsyncCallback<UserProject> callback) {
    Console.log("copyProject called");
  }

  @Override
  public void moveToTrash(long projectId, AsyncCallback<UserProject> callback) {
    Console.log("moveToTrash called");
  }

  @Override
  public void restoreProject(long projectId, AsyncCallback<UserProject> callback) {
    Console.log("restoreProject called");
  }

  @Override
  public void loginToGallery(AsyncCallback<RpcResult> callback) {
    Console.log("loginToGallery called");
  }

  @Override
  public void sendToGallery(long projectId, AsyncCallback<RpcResult> callback) {
    Console.log("sendToGallery called");
  }

  @Override
  public void loadFromGallery(String galleryId, AsyncCallback<UserProject> callback) {
    Console.log("loadFromGallery called");
  }

  @Override
  public void deleteProject(long projectId, AsyncCallback<Void> callback) {
    Console.log("deleteProject called");
  }

  @Override
  public void getProjects(AsyncCallback<long[]> callback) {
    Console.log("getProjects called");
  }

  @Override
  public void getProjectInfos(AsyncCallback<List<UserProject>> callback) {
    getList(PROJECTS_BASE, UserProject.class, callback);
  }

  @Override
  public void getProject(long projectId, AsyncCallback<ProjectRootNode> callback) {
    Console.log("getProject called");
  }

  @Override
  public void loadProjectSettings(long projectId, AsyncCallback<String> callback) {
    Console.log("loadProjectSettings called");
  }

  @Override
  public void storeProjectSettings(String sessionId, long projectId, String settings,
      AsyncCallback<Void> callback) {
    Console.log("storeProjectSettings called");
  }

  @Override
  public void deleteFile(String sessionId, long projectId, String fileId,
      AsyncCallback<Long> callback) {
    Console.log("deleteFile called");
  }

  @Override
  public void deleteFiles(String sessionId, long projectId, String directory,
      AsyncCallback<Long> callback) {
    Console.log("deleteFiles called");
  }

  @Override
  public void deleteFolder(String sessionId, long projectId, String directory,
      AsyncCallback<Long> callback) {
    Console.log("deleteFolder called");
  }

  @Override
  public void load(long projectId, String fileId, AsyncCallback<String> callback) {
    Console.log("load called");
  }

  @Override
  public void loadDataFile(long projectId, String fileId,
      AsyncCallback<List<List<String>>> callback) {
    Console.log("loadDataFile called");
  }

  @Override
  public void load2(long projectId, String fileId, AsyncCallback<ChecksumedLoadFile> callback) {
    Console.log("load2 called");
  }

  @Override
  public void recordCorruption(long ProjectId, String fileId, String message,
      AsyncCallback<Void> callback) {
    Console.log("recordCorruption called");
  }

  @Override
  public void loadraw(long projectId, String fileId, AsyncCallback<byte[]> callback) {
    Console.log("loadraw called");
  }

  @Override
  public void loadraw2(long projectId, String fileId, AsyncCallback<String> callback) {
    Console.log("loadraw2 called");
  }

  @Override
  public void load(List<FileDescriptor> files,
      AsyncCallback<List<FileDescriptorWithContent>> callback) {
    Console.log("load(List) called");
  }

  @Override
  public void save(String sessionId, long projectId, String fileId, String source,
      AsyncCallback<Long> callback) {
    Console.log("save called");
  }

  @Override
  public void save2(String sessionId, long projectId, String fileId, boolean force, String source,
      AsyncCallback<Long> callback) {
    Console.log("save2 called");
  }

  @Override
  public void save(String sessionId, List<FileDescriptorWithContent> filesAndContent,
      AsyncCallback<Long> callback) {
    Console.log("save(List) called");
  }

  @Override
  public void screenshot(String sessionId, long projectId, String fileId, String content,
      AsyncCallback<RpcResult> callback) {
    Console.log("screenshot called");
  }

  @Override
  public void build(long projectId, String nonce, String target, boolean secondBuildserver,
      boolean isAab, AsyncCallback<RpcResult> callback) {
    Console.log("build called");
  }

  @Override
  public void getBuildResult(long projectId, String target, AsyncCallback<RpcResult> callback) {
    Console.log("getBuildResult called");
  }

  @Override
  public void addFile(long projectId, String fileId, AsyncCallback<Long> callback) {
    Console.log("addFile called");
  }

  @Override
  public void importMedia(String sessionId, long projectId, String url, boolean save,
      AsyncCallback<TextFile> odeAsyncCallback) {
    Console.log("importMedia called");
  }

  @Override
  public void log(String message, AsyncCallback<Void> callback) {
    Console.log("log called");
  }

  @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "console")
  private static class Console {
    private static native void log(Object obj);
  }
}
