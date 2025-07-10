package com.google.appinventor.server.rest.projects;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appinventor.server.rest.RestServlet;

public class ProjectsTemplatesRestServlet extends RestServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final String pathToTemplates = Objects.requireNonNullElse(getPath(req), "templates/");
    setBody(resp, PROJECT_SERVICE.retrieveTemplateData(pathToTemplates));
    setStatus(resp, HttpServletResponse.SC_OK);
  }
}
