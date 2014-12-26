/**
 * 
 */
package com.mathtabolism.entity.account;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.mathtabolism.entity.BaseGeneratedEntity;

/**
 * <p>An Abstract class used by any Entities that use the <tt>account_id</tt> column as a
 * reference as a foreign key.
 * 
 * @author mlaursen
 * @see BaseGeneratedEntity
 */
@MappedSuperclass
public abstract class AccountIdFK extends BaseGeneratedEntity {

  @ManyToOne
  @JoinColumn(name = "account_id")
  protected Account account;
  
  public void setAccount(Account account) {
    this.account = account;
  }
  
  public Account getAccount() {
    return account;
  }
}
