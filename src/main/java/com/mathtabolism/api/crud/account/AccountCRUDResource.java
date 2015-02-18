/**
 * 
 */
package com.mathtabolism.api.crud.account;

import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.jboss.logging.Logger;

import com.mathtabolism.api.ResponseBuilder;
import com.mathtabolism.api.crud.GenericCRUDResource;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.util.string.StringUtils;

/**
 * The url should be as follows: <code><pre>
 *    /api/crud/account
 * </pre></code>
 * 
 * @author mlaursen
 *
 */
@Stateless
@Path("/account")
public class AccountCRUDResource extends GenericCRUDResource<Account> {
  private static Logger logger = Logger.getLogger(AccountCRUDResource.class);
  
  public AccountCRUDResource() {
    super(Account.class);
  }

  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response doLogin(@Context HttpServletRequest request) throws OAuthSystemException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
      if(logger.isDebugEnabled()) {
        logger.debug("doLogin - missing username or password");
      }
      
      return ResponseBuilder.buildErrorOAuthResponse(HttpServletResponse.SC_BAD_REQUEST,
          OAuthError.ResourceResponse.INVALID_REQUEST,
          "missing the username or password");
    }
    
    Status status;
    try {
      if (request.getRemoteUser() != null) {
        if(logger.isDebugEnabled()) {
          logger.debug("doLogin - someone is already logged in. Logging them out.");
        }
        
        request.logout();
      }

      if(logger.isDebugEnabled()) {
        logger.debug("doLogin - attempting to log in user = " + username);
      }
      request.login(username, password);
      
      status = Status.ACCEPTED;
    } catch (ServletException e) {
      logger.error(e);
      
      status = Status.UNAUTHORIZED;
    }
    
    return Response.status(status).build();
  }
}
