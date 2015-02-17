package com.mathtabolism.api.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

/**
 * Servlet implementation class AuthorizationServlet
 */
@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(AuthenticationServlet.class);
  private static final int AUTH_CODE_EXPIRES_IN_SECONDS = 60 * 15;
  
  @Inject
  private AuthorizationCodeStore authCodeStore;

  public AuthorizationServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

}
