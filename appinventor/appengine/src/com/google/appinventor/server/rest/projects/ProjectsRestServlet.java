package com.google.appinventor.server.rest.projects;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appinventor.server.rest.RestServlet;

public class ProjectsRestServlet extends RestServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    setBody(resp, PROJECT_SERVICE.getProjectInfos());
    setStatus(resp, HttpServletResponse.SC_OK);
  }
}
