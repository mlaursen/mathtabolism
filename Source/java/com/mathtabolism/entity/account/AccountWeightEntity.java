/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.mathtabolism.emcontract.AccountWeight;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = AccountWeightEntity.Q_findLatestWeight, query = "SELECT aw from AccountWeightEntity aw WHERE aw.accountEntity.id = :account_id "
        + "AND aw.weighInDate = (SELECT max(aw2.weighInDate) FROM AccountWeightEntity aw2 WHERE aw2.accountEntity.id = :account_id)"),
    @NamedQuery(name = AccountWeightEntity.Q_findTodaysWeight, query = "SELECT aw FROM AccountWeightEntity aw WHERE aw.accountEntity.id = :account_id "
        + "AND aw.weighInDate = :today"),
    @NamedQuery(name = AccountWeightEntity.Q_findCurrentAccountWeightWeek, query = "SELECT aw FROM AccountWeightEntity aw WHERE aw.accountEntity.id = :account_id "
        + "AND aw.weighInDate BETWEEN :start_date AND :end_date ORDER BY aw.weighInDate")
})
public class AccountWeightEntity extends AccountIdFK implements AccountWeight {
  public static final String Q_findLatestWeight = "AccountWeightEntity.findLatestWeight";
  public static final String Q_findTodaysWeight = "AccountWeightEntity.findTodaysWeight";
  public static final String Q_findCurrentAccountWeightWeek = "AccountWeightEntity.findCurrentAccountWeightWeek";
  
  private Date weighInDate;
  private double weight;
  
  public AccountWeightEntity() {
  }
  
  public AccountWeightEntity(AccountEntity accountEntity, Date weighInDate) {
    this.accountEntity = accountEntity;
    this.weighInDate = weighInDate;
  }
  
  @Override
  public Date getWeighInDate() {
    return weighInDate;
  }
  
  @Override
  public void setWeighInDate(Date weighInDate) {
    this.weighInDate = weighInDate;
  }
  
  @Override
  public Double getWeight() {
    return weight;
  }
  
  @Override
  public void setWeight(Double weight) {
    this.weight = weight;
  }
  
  public void setWeight(String weight) {
    this.weight = Double.valueOf(weight);
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this).append("accountId", accountEntity.getId()).append("weight", weight)
        .append("weighInDate", weighInDate).toString();
  }
}
