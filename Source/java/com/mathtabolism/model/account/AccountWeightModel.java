/**
 * 
 */
package com.mathtabolism.model.account;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

  /**
   * Gets the weight formatted to 2 decimal places.
   * @return the weight as a String
   */
  public String getWeightStr() {
    return NumberUtils.formatAsString(weight);
  }
  
  /**
   * Sets the weight amount from a String
   * @param weight the weight
   */
  public void setWeightFromString(String weight) {
    this.weight = NumberUtils.stringToDouble(weight);
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
    return new ToStringBuilder(this).append("weighInDate", weighInDate).append("weight", weight).toString();
  }
}
