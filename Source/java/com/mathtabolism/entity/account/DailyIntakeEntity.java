/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.emcontract.DailyIntake;
import com.mathtabolism.entity.food.DailyIntakeMealEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = {
      "account_id", "intakeDate"
  })
})
@NamedQueries({
  @NamedQuery(name = DailyIntakeEntity.Q_findCurrentWeek, query = "SELECT di FROM DailyIntakeEntity di "
      + "WHERE di.accountEntity.id = :account_id AND di.intakeDate BETWEEN :start_date AND :end_date "
      + "ORDER BY di.intakeDate ASC")
})
public class DailyIntakeEntity extends AccountIdFK implements DailyIntake {
  public static final String Q_findCurrentWeek = "DailyIntakeEntity.getCurrentWeek";
  
  public DailyIntakeEntity() {
  }
  
  @OneToOne
  @JoinColumn(name = "account_weight_id")
  private AccountWeightEntity accountWeightEntity;
  
  @Temporal(TemporalType.DATE)
  private Date intakeDate;
  private Integer calorieChange;
  private Double fatMultiplier;
  private Double carbMultiplier;
  private Double proteinMultiplier;
  
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "dailyIntakeEntity")
  private List<DailyIntakeMealEntity> meals;
  
  @Override
  public Date getIntakeDate() {
    return intakeDate;
  }
  
  @Override
  public void setIntakeDate(Date intakeDate) {
    this.intakeDate = intakeDate;
  }
  
  @Override
  public Integer getCalorieChange() {
    return calorieChange;
  }
  
  @Override
  public void setCalorieChange(Integer calorieChange) {
    this.calorieChange = calorieChange;
  }
  
  @Override
  public Double getFatMultiplier() {
    return fatMultiplier;
  }
  
  @Override
  public void setFatMultiplier(Double fatMultiplier) {
    this.fatMultiplier = fatMultiplier;
  }
  
  @Override
  public Double getCarbMultiplier() {
    return carbMultiplier;
  }
  
  @Override
  public void setCarbMultiplier(Double carbMultiplier) {
    this.carbMultiplier = carbMultiplier;
  }
  
  @Override
  public Double getProteinMultiplier() {
    return proteinMultiplier;
  }
  
  @Override
  public void setProteinMultiplier(Double proteinMultiplier) {
    this.proteinMultiplier = proteinMultiplier;
  }
  
  /**
   * 
   * @return
   */
  public List<DailyIntakeMealEntity> getMeals() {
    return meals;
  }
  
  /**
   * 
   * @param meals
   */
  public void setMeals(List<DailyIntakeMealEntity> meals) {
    this.meals = meals;
  }
  
  public AccountWeightEntity getAccountWeightEntity() {
    return accountWeightEntity;
  }
  
  public void setAccountWeightEntity(AccountWeightEntity accountWeightEntity) {
    this.accountWeightEntity = accountWeightEntity;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("intakeDate", intakeDate)
        .append("calorieChange", calorieChange).append("carbMultiplier", carbMultiplier)
        .append("fatMultiplier", fatMultiplier).append("proteinMultiplier")
        .append("meals", meals).toString();
  }
  
}
