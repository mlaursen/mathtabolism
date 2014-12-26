/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.util.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
  @NamedQuery(name = AccountSettingEntity.Q_findCurrentAccountSetting, query = "SELECT as1 FROM AccountSettingEntity as1 WHERE as1.accountEntity.id = :account_id "
      + "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSettingEntity as2 WHERE as2.accountEntity.id = :account_id)"),
  @NamedQuery(name = AccountSettingEntity.Q_findLatestSettingsForDate, query = "SELECT as1 FROM AccountSettingEntity as1 WHERE as1.accountEntity.id = :account_id "
      + "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSettingEntity as2 WHERE as2.accountEntity.id = :account_id "
      + "AND as2.dateChanged <= :date)")
})
public class AccountSettingEntity extends AccountIdFK {
  public static final String Q_findCurrentAccountSetting = "AccountSettingEntity.findCurrentAccountSetting";
  public static final String Q_findLatestSettingsForDate = "AccountSettingEntity.findLatestSettingsForDate";
  
  public AccountSettingEntity() {
  }
  
  public AccountSettingEntity(AccountEntity accountEntity, Date dateChanged) {
    this.accountEntity = accountEntity;
    this.dateChanged = dateChanged;
  }
  
  private Date dateChanged;
  
  @Enumerated(EnumType.STRING)
  private Weekday recalculationDay;
  
  @Enumerated(EnumType.STRING)
  private ActivityMultiplier activityMultiplier;
  
  @Enumerated(EnumType.STRING)
  private TDEEFormula tdeeFormula;
  
  @Enumerated(EnumType.STRING)
  private UnitSystem unitSystem;
  
  private Integer age;
  private Double height;
  
  
  /**
   * 
   * @return
   */
  public Weekday getRecalculationDay() {
    return recalculationDay;
  }
  
  /**
   * 
   * @param recalculationDay
   */
  public void setRecalculationDay(Weekday recalculationDay) {
    this.recalculationDay = recalculationDay;
  }
  
  /**
   * 
   * @return
   */
  public ActivityMultiplier getActivityMultiplier() {
    return activityMultiplier;
  }
  
  /**
   * 
   * @param activityMultiplier
   */
  public void setActivityMultiplier(ActivityMultiplier activityMultiplier) {
    this.activityMultiplier = activityMultiplier;
  }
  
  /**
   * 
   * @return
   */
  public TDEEFormula getTdeeFormula() {
    return tdeeFormula;
  }
  
  /**
   * 
   * @param tdeeFormula
   */
  public void setTdeeFormula(TDEEFormula tdeeFormula) {
    this.tdeeFormula = tdeeFormula;
  }
  
  /**
   * 
   * @return
   */
  public Date getDateChanged() {
    return dateChanged;
  }
  
  /**
   * 
   * @param dateChanged
   */
  public void setDateChanged(Date dateChanged) {
    this.dateChanged = dateChanged;
  }
  
  /**
   * 
   * @return
   */
  public Integer getAge() {
    return age;
  }
  
  /**
   * 
   * @param age
   */
  public void setAge(Integer age) {
    this.age = age;
  }
  
  public Double getHeight() {
    return height;
  }
  
  public void setHeight(Double height) {
    this.height = height;
  }
  
  /**
   * @return the unitSystem
   */
  public UnitSystem getUnitSystem() {
    return unitSystem;
  }

  /**
   * @param unitSystem the unitSystem to set
   */
  public void setUnitSystem(UnitSystem unitSystem) {
    this.unitSystem = unitSystem;
  }

  /**
   * @return
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("accountId", accountEntity.getId())
        .append("recalculationDay", recalculationDay).append("activityMultiplier", activityMultiplier)
        .append("tdeeFormula", tdeeFormula).append("dateChanged", dateChanged).append("age", age)
        .append("height", height).toString();
  }
}
