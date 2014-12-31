/**
 * 
 */
package com.mathtabolism.view.model.account;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.mathtabolism.dto.AccountWeightDto;
import com.mathtabolism.entity.account.AccountWeight;
import com.mathtabolism.util.emconverter.ModelConverter;
import com.mathtabolism.view.model.BaseModel;

/**
 * 
 * @author mlaursen
 */
@ModelConverter(entities = {AccountWeight.class})
public class AccountWeightModel extends BaseModel implements AccountWeightDto {
  
  private String accountWeightId;
  private Double weight;
  private Date weighInDate;
  public AccountWeightModel() {
  }
  
  public AccountWeightModel(Double weight) {
    this.weight = weight;
  }
  
  public AccountWeightModel(Date weightInDate, Double weight) {
    this.weighInDate = weightInDate;
    this.weight = weight;
  }
  
  public void setAccountWeightId(String accountWeightId) {
    this.accountWeightId = accountWeightId;
  }
  
  public String getAccountWeightId() {
    return accountWeightId;
  }

  @Override
  public void setWeight(Double weight) {
    this.weight = weight;
  }

  @Override
  public Double getWeight() {
    return weight;
  }

  @Override
  public void setWeighInDate(Date weighInDate) {
    this.weighInDate = weighInDate;
  }

  @Override
  public Date getWeighInDate() {
    return weighInDate;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this).append("accountWeightId", accountWeightId).append("weighInDate", weighInDate)
        .append("weight", weight).toString();
  }
}
