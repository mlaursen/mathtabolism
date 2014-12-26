/**
 * 
 */
package com.mathtabolism.entity.account;

import javax.persistence.FetchType;
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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  protected AccountEntity accountEntity;
  
  public void setAccountEntity(AccountEntity accountEntity) {
    this.accountEntity = accountEntity;
  }
  
  public AccountEntity getAccountEntity() {
    return accountEntity;
  }
}
