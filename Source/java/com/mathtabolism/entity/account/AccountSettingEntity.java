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
import com.mathtabolism.emcontract.AccountSetting;
import com.mathtabolism.util.unit.UnitSystem;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
  @NamedQuery(
      name = AccountSettingEntity.Q_findCurrentAccountSetting,
      query = "SELECT as1 FROM AccountSettingEntity as1 WHERE as1.accountEntity.id = :account_id "
            + "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSettingEntity as2 WHERE as2.accountEntity.id = :account_id)"
  ),
  @NamedQuery(
      name = AccountSettingEntity.Q_findLatestSettingsForDate,
      query = "SELECT as1 FROM AccountSettingEntity as1 WHERE as1.accountEntity.id = :account_id "
            + "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSettingEntity as2 WHERE as2.accountEntity.id = :account_id "
            + "AND as2.dateChanged <= :date)"
  ),
  @NamedQuery(
      name = AccountSettingEntity.Q_findLatestAccountSettingByAccountId,
      query = "SELECT max(as1.dateChanged) FROM AccountSettingEntity as1 WHERE as1.accountEntity.id = :account_id"
  )
})
public class AccountSettingEntity extends AccountIdFK implements AccountSetting {
  public static final String Q_findCurrentAccountSetting = "AccountSettingEntity.findCurrentAccountSetting";
  public static final String Q_findLatestSettingsForDate = "AccountSettingEntity.findLatestSettingsForDate";
  public static final String Q_findLatestAccountSettingByAccountId = "AccountSettingEntity.findLatestAccountSettingByAccountId";
  
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
  
  
  @Override
  public Weekday getRecalculationDay() {
    return recalculationDay;
  }
  
  @Override
  public void setRecalculationDay(Weekday recalculationDay) {
    this.recalculationDay = recalculationDay;
  }
  
  @Override
  public ActivityMultiplier getActivityMultiplier() {
    return activityMultiplier;
  }
  
  @Override
  public void setActivityMultiplier(ActivityMultiplier activityMultiplier) {
    this.activityMultiplier = activityMultiplier;
  }
  
  @Override
  public TDEEFormula getTdeeFormula() {
    return tdeeFormula;
  }
  
  @Override
  public void setTdeeFormula(TDEEFormula tdeeFormula) {
    this.tdeeFormula = tdeeFormula;
  }
  
  @Override
  public Date getDateChanged() {
    return dateChanged;
  }
  
  @Override
  public void setDateChanged(Date dateChanged) {
    this.dateChanged = dateChanged;
  }
  
  @Override
  public Integer getAge() {
    return age;
  }
  
  @Override
  public void setAge(Integer age) {
    this.age = age;
  }
  
  @Override
  public Double getHeight() {
    return height;
  }
  
  @Override
  public void setHeight(Double height) {
    this.height = height;
  }
  
  @Override
  public UnitSystem getUnitSystem() {
    return unitSystem;
  }

  @Override
  public void setUnitSystem(UnitSystem unitSystem) {
    this.unitSystem = unitSystem;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("accountId", accountEntity.getId())
        .append("recalculationDay", recalculationDay).append("activityMultiplier", activityMultiplier)
        .append("tdeeFormula", tdeeFormula).append("dateChanged", dateChanged).append("age", age)
        .append("height", height).toString();
  }
}
