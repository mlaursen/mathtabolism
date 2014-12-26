/**
 * 
 */
package com.mathtabolism.model.account;

import java.util.Date;

import com.mathtabolism.emcontract.AccountWeight;

/**
 * @author mlaursen
 *
 */
public class AccountWeightModel implements AccountWeight {
  
  private Double weight;
  private Date weighInDate;

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

}
