package com.mathtabolism.api.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.jboss.logging.Logger;

import com.mathtabolism.util.string.StringUtils;

public class ApiTokenFilter implements Filter {
  private static Logger logger = Logger.getLogger(ApiTokenFilter.class);

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    
    OAuthResponse oathResponse;
    
    String pathInfo = request.getPathInfo();
    
    if(StringUtils.isBlank(pathInfo) || pathInfo.startsWith(TokenResource.TOKEN_PATH)) {
      
      chain.doFilter(request, response);
    } else {
      String accessToken = getAccessToken(request);
      
      if(StringUtils.isNotBlank(accessToken)) {
        
      }
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

}
