package com.mathtabolism.api.security;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

@Path("/token")
public class TokenResource {

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response authorize(@Context HttpServletRequest request) throws OAuthSystemException {
    OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
    String clientId = oauthRequest.getClientId();
    String clientSecret = oauthRequest.getClientSecret();
    String requestGrantType = oauthRequest.getGrantType();
    
    if(isClientValid(clientId, clientSecret)) {
      return buildGrantTypeResponse(requestGrantType);
    }
  }
}
