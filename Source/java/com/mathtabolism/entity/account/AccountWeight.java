/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.mathtabolism.dto.AccountWeightDto;
import com.mathtabolism.util.emconverter.EntityConverter;
import com.mathtabolism.view.model.account.AccountWeightModel;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
    @NamedQuery(
        name = AccountWeight.Q_findLatestWeight,
        query = "SELECT aw from AccountWeight aw WHERE aw.account.id = :account_id "
              + "AND aw.weighInDate = (SELECT max(aw2.weighInDate) FROM AccountWeight aw2 WHERE aw2.account.id = :account_id)"
    ),
    @NamedQuery(
        name = AccountWeight.Q_findTodaysWeight,
        query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id "
              + "AND aw.weighInDate = :today"
    ),
    @NamedQuery(
        name = AccountWeight.Q_findCurrentAccountWeightWeek,
        query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id "
            + "AND aw.weighInDate BETWEEN :start_date AND :end_date ORDER BY aw.weighInDate"
    ),
    @NamedQuery(
        name = AccountWeight.Q_findAccountWeightByDate,
        query = "SELECT aw FROM AccountWeight aw WHERE aw.account.id = :account_id AND aw.weighInDate = :weigh_in_date"
    )
})
@EntityConverter(converterDto = AccountWeightDto.class, toModel = AccountWeightModel.class)
public class AccountWeight extends AccountIdFK implements AccountWeightDto {
  public static final String Q_findLatestWeight = "AccountWeight.findLatestWeight";
  public static final String Q_findTodaysWeight = "AccountWeight.findTodaysWeight";
  public static final String Q_findCurrentAccountWeightWeek = "AccountWeight.findCurrentAccountWeightWeek";
  public static final String Q_findAccountWeightByDate = "AccountWeight.findAccountWeightByDate";
  
  @Temporal(TemporalType.DATE)
  private Date weighInDate;
  private Double weight;
  
  public AccountWeight() {
  }
  
  public AccountWeight(Account account, Date weighInDate) {
    this.account = account;
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
  
  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("accountId", account.getId())
        .append("weight", weight).append("weighInDate", weighInDate).toString();
  }
}
