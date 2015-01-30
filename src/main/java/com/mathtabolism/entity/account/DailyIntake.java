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

import com.mathtabolism.dto.DailyIntakeDto;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.util.emconverter.EntityConverter;
import com.mathtabolism.view.model.food.DailyIntakeModel;

/**
 * 
 * @author mlaursen
 */
@Entity
@Table(uniqueConstraints={ @UniqueConstraint(columnNames = { "account_id", "intakeDate" }) })
@NamedQueries({
  @NamedQuery(
      name = DailyIntake.Q_findCurrentWeek,
      query = "SELECT di FROM DailyIntake di WHERE di.account.id = :account_id AND di.intakeDate BETWEEN :start_date AND :end_date "
            + "ORDER BY di.intakeDate ASC"
  )
})
@EntityConverter(converterDto = DailyIntakeDto.class, toModel = DailyIntakeModel.class)
public class DailyIntake extends AccountIdFK implements DailyIntakeDto {
  public static final String Q_findCurrentWeek = "DailyIntake.getCurrentWeek";
  
  public DailyIntake() {
  }
  
  @OneToOne
  @JoinColumn(name = "account_weight_id")
  private AccountWeight accountWeight;
  
  @Temporal(TemporalType.DATE)
  private Date intakeDate;
  private Integer calorieChange;
  private Double fatMultiplier;
  private Double carbMultiplier;
  private Double proteinMultiplier;
  
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "dailyIntake")
  private List<DailyIntakeMeal> meals;
  
  public List<DailyIntakeMeal> getMeals() {
    return meals;
  }
  
  public void setMeals(List<DailyIntakeMeal> meals) {
    this.meals = meals;
  }
  
  public AccountWeight getAccountWeight() {
    return accountWeight;
  }
  
  public void setAccountWeight(AccountWeight accountWeight) {
    this.accountWeight = accountWeight;
  }
  
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
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", id).append("intakeDate", intakeDate)
        .append("calorieChange", calorieChange).append("carbMultiplier", carbMultiplier)
        .append("fatMultiplier", fatMultiplier).append("proteinMultiplier")
        .append("meals", meals).toString();
  }
  
}
