/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

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
public class DailyIntakeEntity extends AccountIdFK {
  public static final String Q_findCurrentWeek = "DailyIntakeEntity.getCurrentWeek";
  
  public DailyIntakeEntity() {
  }
  
  @Temporal(TemporalType.DATE)
  private Date intakeDate;
  private Integer calorieChange;
  private Double fatMultiplier;
  private Double carbMultiplier;
  private Double proteinMultiplier;
  
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "dailyIntakeEntity")
  private List<DailyIntakeMealEntity> meals;
  
  public Date getIntakeDate() {
    return intakeDate;
  }
  
  public void setIntakeDate(Date intakeDate) {
    this.intakeDate = intakeDate;
  }
  
  /**
   * 
   * @return
   */
  public Integer getCalorieChange() {
    return calorieChange;
  }
  
  /**
   * 
   * @param calorieChange
   */
  public void setCalorieChange(Integer calorieChange) {
    this.calorieChange = calorieChange;
  }
  
  /**
   * 
   * @return
   */
  public Double getFatMultiplier() {
    return fatMultiplier;
  }
  
  /**
   * 
   * @param fatMultiplier
   */
  public void setFatMultiplier(Double fatMultiplier) {
    this.fatMultiplier = fatMultiplier;
  }
  
  /**
   * 
   * @return
   */
  public Double getCarbMultiplier() {
    return carbMultiplier;
  }
  
  /**
   * 
   * @param carbMultiplier
   *          the new Carbohydrate multiplier
   */
  public void setCarbMultiplier(Double carbMultiplier) {
    this.carbMultiplier = carbMultiplier;
  }
  
  /**
   * 
   * @return
   */
  public Double getProteinMultiplier() {
    return proteinMultiplier;
  }
  
  /**
   * 
   * @param proteinMultiplier
   */
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
  
}
