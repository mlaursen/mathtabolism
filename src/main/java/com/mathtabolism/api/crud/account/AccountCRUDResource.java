/**
 * 
 */
package com.mathtabolism.api.crud.account;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.mathtabolism.api.crud.GenericCRUDResource;
import com.mathtabolism.entity.account.Account;

/**
 * The url should be as follows:
 * <code><pre>
 *    /api/crud/account
 * </pre></code>
 * 
 * @author mlaursen
 *
 */
@Stateless
@Path("/account")
public class AccountCRUDResource extends GenericCRUDResource<Account> {

  public AccountCRUDResource() {
    super(Account.class);
  }
  
}
