package com.google.appinventor.server.rest.projects;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appinventor.server.rest.RestServlet;

public class ProjectRestServlet extends RestServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final Long projectId = Optional.ofNullable(getPath(req)).map(Long::parseLong).orElse(null);
    if (projectId == null) {
      setStatus(resp, HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    setBody(resp, PROJECT_SERVICE.getProject(projectId));
    setStatus(resp, HttpServletResponse.SC_OK);
  }
}
