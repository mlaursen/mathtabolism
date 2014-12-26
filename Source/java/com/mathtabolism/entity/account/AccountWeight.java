/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = AccountWeight.Q_findLatestWeight, query = "SELECT aw from AccountWeight aw WHERE aw.account.id = :account_id "
        + "AND aw.weighInDate = (SELECT max(aw2.weighInDate) FROM AccountWeight aw2 WHERE aw2.account.id = :account_id)"),
    @NamedQuery(name = AccountWeight.Q_findTodaysWeight, query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id "
        + "AND aw.weighInDate = :today"),
    @NamedQuery(name = AccountWeight.Q_findCurrentAccountWeightWeek, query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id "
        + "AND aw.weighInDate BETWEEN :start_date AND :end_date ORDER BY aw.weighInDate")
})
public class AccountWeight extends AccountIdFK {
  public static final String Q_findLatestWeight = "AccountWeight.findLatestWeight";
  public static final String Q_findTodaysWeight = "AccountWeight.findTodaysWeight";
  public static final String Q_findCurrentAccountWeightWeek = "AccountWeight.findCurrentAccountWeightWeek";
  
  private Date weighInDate;
  private double weight;
  
  public AccountWeight() {
  }
  
  public AccountWeight(Account account, Date weighInDate) {
    this.account = account;
    this.weighInDate = weighInDate;
  }
  
  /**
   * 
   * @return
   */
  public Date getWeighInDate() {
    return weighInDate;
  }
  
  /**
   * 
   * @param weighInDate
   */
  public void setWeighInDate(Date weighInDate) {
    this.weighInDate = weighInDate;
  }
  
  /**
   * 
   * @return
   */
  public double getWeight() {
    return weight;
  }
  
  /**
   * 
   * @param weight
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }
  
  public void setWeight(String weight) {
    this.weight = Double.valueOf(weight);
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this).append("accountId", account.getId()).append("weight", weight)
        .append("weighInDate", weighInDate).toString();
  }
}
