package com.mathtabolism.api.security;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.jboss.logging.Logger;

@Path(TokenResource.TOKEN_PATH)
public class TokenResource {
  public static final String TOKEN_PATH = "/token";
  private static Logger logger = Logger.getLogger(TokenResource.class);
  private static final int TOKEN_EXPIRES_IN_SECONDS = 60 * 30;
  
  @Inject
  private ClientStore clientStore;
  @Inject
  private AccessTokenStore accessTokenStore;
  

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response authorize(@Context HttpServletRequest request) throws OAuthSystemException {
    try {
      OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
      String clientId = oauthRequest.getClientId();
      String clientSecret = oauthRequest.getClientSecret();
      String requestedGrantType = oauthRequest.getGrantType();
      
      if(isClientValid(clientId, clientSecret)) {
        return buildGrantTypeResponse(clientId, requestedGrantType);
      } else {
        return ResponseBuilder.buildErrorResponse(HttpServletResponse.SC_BAD_REQUEST, OAuthError.TokenResponse.INVALID_CLIENT, "client authentication failed");
      }
    } catch (OAuthProblemException e) {
      return ResponseBuilder.buildErrorResponse(HttpServletResponse.SC_BAD_REQUEST, OAuthError.OAUTH_ERROR, "error creating an oauth token request");
    }
  }
  
  private Response buildGrantTypeResponse(String clientId, String requestedGrantType) throws OAuthSystemException {
    GrantType grantType = GrantType.valueOf(requestedGrantType.toUpperCase());
    
    if(isGrantTypeValidForClient(clientId, grantType)) {
      switch(grantType) {
        case CLIENT_CREDENTIALS:
          return getTokenResponse(clientId);
        case REFRESH_TOKEN:
        case AUTHORIZATION_CODE:
        case PASSWORD:
        default:
          return ResponseBuilder.buildErrorResponse(HttpServletResponse.SC_BAD_GATEWAY, OAuthError.TokenResponse.UNSUPPORTED_GRANT_TYPE, "the grant type is currently not supported");
      }
    } else {
      return ResponseBuilder.buildErrorResponse(HttpServletResponse.SC_BAD_REQUEST, OAuthError.TokenResponse.INVALID_GRANT, "the given client does not have the given grant type");
    }
  }
  
  /**
   * Checks if the client id and client secret exist in the CLient store
   * @param clientId the client id to lookup
   * @param clientSecret the client secret to validate
   * @return true if the client id and client secret are valid
   */
  private boolean isClientValid(String clientId, String clientSecret) {
    if(StringUtils.isBlank(clientId) || StringUtils.isBlank(clientSecret)) {
      logger.debug("client id or client secret does not exist");
      
      return false;
    }
    
    ClientInfo client = clientStore.get(clientId);
    
    if(client == null) {
      logger.debug("client id not found in store");
      
      return false;
    }
    
    return clientSecret.equals(client.getClientSecret());
  }
  
  /**
   * Checks if the given client id is able to use the given grant type
   * @param clientId the client id to lookup
   * @param grantType the grant type to verify
   * @return true if the client id is able to use the given grant type
   */
  private boolean isGrantTypeValidForClient(String clientId, GrantType grantType) {
    if(StringUtils.isBlank(clientId) || grantType == null) {
      logger.debug("client id or grant type does not exist");
      
      return false;
    }
    
    ClientInfo client = clientStore.get(clientId);
    
    if(client == null) {
      logger.debug("client id not found in store");
      
      return false;
    }
    
    return grantType == client.getGrantType();
  }
  
  private Response getTokenResponse(String clientId) throws OAuthSystemException {
    OAuthIssuer oauthIssuer = new OAuthIssuerImpl(new MD5Generator());
    
    String accessToken = oauthIssuer.accessToken();
    
    while(accessTokenStore.get(accessToken) != null) {
      accessToken = oauthIssuer.accessToken();
    }
    
    accessTokenStore.add(accessToken, TokenInfo.createTokenInfo(TOKEN_EXPIRES_IN_SECONDS, clientId));
    
    OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK).setTokenType(accessToken)
        .setExpiresIn(Integer.toString(TOKEN_EXPIRES_IN_SECONDS)).buildJSONMessage();
    
    return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
  }
}
