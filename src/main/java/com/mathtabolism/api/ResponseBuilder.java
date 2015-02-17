package com.mathtabolism.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

public class ResponseBuilder {
  private ResponseBuilder(){
  }

  /**
   * Builds a {@link Response} from the given status code, error type and description.
   * @param status the status code
   * @param error the error type
   * @param errorDescription the description of the error
   * @return a built Response
   * @throws OAuthSystemException
   */
  public static Response buildErrorOAuthResponse(int status, String error, String errorDescription) throws OAuthSystemException {
    OAuthResponse.OAuthErrorResponseBuilder oauthResponse = OAuthASResponse.errorResponse(status).setError(error);
    
    if(null != errorDescription) {
      oauthResponse.setErrorDescription(errorDescription);
    }

    OAuthResponse response = oauthResponse.buildJSONMessage();
    if(HttpServletResponse.SC_UNAUTHORIZED == status) {
      OAuthResponse header = oauthResponse.buildHeaderMessage();
      
      return Response.status(response.getResponseStatus())
          .header(OAuth.HeaderType.WWW_AUTHENTICATE, header.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE))
          .entity(response.getBody()).build();
    } else {
      return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
    }
  }
  
  /**
   * 
   * @param status
   * @param error
   * @param errorDescription
   * @return
   * @throws ServletException
   */
  public static OAuthResponse buildOAuthErrorResponse(int status, String error, String errorDescription) throws ServletException {
    try {
      OAuthResponse.OAuthErrorResponseBuilder oauthResponse = OAuthASResponse.errorResponse(status).setError(error);
      
      if(errorDescription != null) {
        oauthResponse.setErrorDescription(errorDescription);
      }
      
      return oauthResponse.buildHeaderMessage();
    } catch(OAuthSystemException e) {
      throw new ServletException(e);
    }
    
  }

  /**
   * Writes a HttpServletResponse with the given oauthResponse
   * @param response the HttpServletResponse
   * @param oauthResponse the OAuthResponse
   * @throws IOException when attempting to flush the response buffer
   */
  public static void writeErrorResponse(HttpServletResponse response, OAuthResponse oauthResponse) throws IOException {
    response.setStatus(oauthResponse.getResponseStatus());
    response.addHeader(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
    response.flushBuffer();
  }
  
  /**
   * 
   * @param status
   * @param entity
   * @return
   */
  public static Response buildResponse(Status status, Object entity) {
    return Response.status(status).entity(entity).build();
  }
}
