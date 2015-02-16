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
public abstract class AccountFKEntity extends BaseGeneratedEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  protected Account account;
  
  /**
   * Sets the associated account that is a Foreign Key
   * @param account the account
   */
  public void setAccount(Account account) {
    this.account = account;
  }
  
  /**
   * Gets the associated account that is a Foreign Key
   * @return the account
   */
  public Account getAccount() {
    return account;
  }
}
