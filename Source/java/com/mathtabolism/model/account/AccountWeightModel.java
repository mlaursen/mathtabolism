/**
 * 
 */
package com.mathtabolism.model.account;

import java.util.Date;

import com.mathtabolism.emcontract.AccountWeight;
import com.mathtabolism.model.BaseModel;
import com.mathtabolism.util.number.NumberUtils;

/**
 * @author mlaursen
 *
 */
public class AccountWeightModel extends BaseModel implements AccountWeight {
  
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

  /**
   * Gets the weight formatted to 2 decimal places.
   * 
   * @return the weight as a String
   */
  public String getWeightStr() {
    return NumberUtils.formatAsString(weight);
  }
}
