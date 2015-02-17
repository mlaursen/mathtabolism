package com.mathtabolism.api.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.jboss.logging.Logger;

public class ApiTokenFilter implements Filter {
  private static Logger logger = Logger.getLogger(ApiTokenFilter.class);
  private static final String BEARER_TOKEN_TAG = "bearer";
  @Inject
  private AccessTokenStore accessTokenStore;

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    
    OAuthResponse oauthResponse;
    
    String pathInfo = request.getPathInfo();
    
    if(StringUtils.isBlank(pathInfo) || pathInfo.startsWith(TokenResource.TOKEN_PATH)) {
      if(logger.isDebugEnabled()) {
        logger.debug("doFilter - Skip token validation for " + pathInfo);
      }
      
      chain.doFilter(request, response);
    } else {
      String accessToken = getAccessToken(request);
      
      if(StringUtils.isNotBlank(accessToken)) {
        if(logger.isDebugEnabled()) {
          logger.debug("doFilter - request token = " + accessToken);
        }
        
        TokenStatus tokenStatus = getAccessTokenStatus(accessToken);
        
        switch(tokenStatus) {
          case VALID:
            chain.doFilter(request, response);
            
            break;
          case EXPIRED:
            oauthResponse = ResponseBuilder.buildOAuthErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, OAuthError.ResourceResponse.EXPIRED_TOKEN, "expired token");
            ResponseBuilder.writeErrorResponse(response, oauthResponse);
            
            break;
          case INSUFFICIENT_SCOPE:
            oauthResponse = ResponseBuilder.buildOAuthErrorResponse(HttpServletResponse.SC_FORBIDDEN, OAuthError.ResourceResponse.INSUFFICIENT_SCOPE, "insufficient scope");
            ResponseBuilder.writeErrorResponse(response, oauthResponse);
            
            break;
          case INVALID:
            oauthResponse = ResponseBuilder.buildOAuthErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, OAuthError.ResourceResponse.INVALID_TOKEN, "invalid token");
            ResponseBuilder.writeErrorResponse(response, oauthResponse);
            
            break;
          default:
            oauthResponse = ResponseBuilder.buildOAuthErrorResponse(HttpServletResponse.SC_BAD_REQUEST, OAuthError.ResourceResponse.INVALID_REQUEST, "invalid request");
            ResponseBuilder.writeErrorResponse(response, oauthResponse);
            
            break;
        }
      } else {
        oauthResponse = ResponseBuilder.buildOAuthErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, OAuthError.CodeResponse.INVALID_REQUEST, "no token");
        ResponseBuilder.writeErrorResponse(response, oauthResponse);
      }
    }
  }
  
  private String getAccessToken(HttpServletRequest request) {
    String accessToken = null;
    String header      = request.getHeader(HttpHeaders.AUTHORIZATION);
    
    if(header != null) {
      int spaceIndex = header.indexOf(' ');
      
      if(spaceIndex > 0) {
        String method = header.substring(0, spaceIndex);
        
        if(BEARER_TOKEN_TAG.equalsIgnoreCase(method)) {
          accessToken = header.substring(spaceIndex + 1);
        }
      }
    }
    
    return accessToken;
  }
  
  private TokenStatus getAccessTokenStatus(String token) {
    TokenInfo tokenInfo = accessTokenStore.get(token);
    
    if(tokenInfo != null) {
      if(!tokenInfo.isExpired()) {
        if(logger.isDebugEnabled()) {
          logger.debug("getAccessTokenStatus - Valid token = " + token);
        }
        
        return TokenStatus.VALID;
      } else {
        if(logger.isDebugEnabled()) {
          logger.debug("getAccessTokenStatus - Expired token = " + token);
        }
        
        return TokenStatus.EXPIRED;
      }
    } else {
      if(logger.isDebugEnabled()) {
        logger.debug("getAccessTokenStatus - Did not find token = " + token);
      }
      
      return TokenStatus.INVALID;
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

  private enum TokenStatus {
    VALID, INVALID, EXPIRED, INSUFFICIENT_SCOPE;
  }
}
