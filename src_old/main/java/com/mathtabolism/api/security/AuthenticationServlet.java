package com.mathtabolism.api.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static Logger     logger           = Logger.getLogger(AuthenticationServlet.class);

  public AuthenticationServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    logger.info("START doGet");

    logger.info("END doGet");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    logger.info("START doPost");
    HttpSession session = request.getSession();
    String originalURL = (String) session.getAttribute("origURL");
    String username    = request.getParameter("username");
    String password    = request.getParameter("password");
    if(request.getRemoteUser() != null) {
      request.logout();
    }
    
    request.login(username, password);
    
    if(logger.isDebugEnabled()) {
      logger.debug(String.format("authentication - redirecting to: %s", originalURL));
    }
    
    response.sendRedirect(originalURL);
    
    logger.info("END doPost");
  }

}
