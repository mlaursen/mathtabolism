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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.dto2.AccountSettingDto;
import com.mathtabolism.util.emconverter2.EntityConverter;
import com.mathtabolism.util.unit.UnitSystem;
import com.mathtabolism.view.model2.account.AccountModel;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
  @NamedQuery(
      name = AccountSetting.Q_findCurrentAccountSetting,
      query = "SELECT as1 FROM AccountSetting as1 WHERE as1.account.id = :account_id "
            + "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSetting as2 WHERE as2.account.id = :account_id)"
  ),
  @NamedQuery(
      name = AccountSetting.Q_findLatestSettingsForDate,
      query = "SELECT as1 FROM AccountSetting as1 WHERE as1.account.id = :account_id "
            + "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSetting as2 WHERE as2.account.id = :account_id "
            + "AND as2.dateChanged <= :date)"
  ),
  @NamedQuery(
      name = AccountSetting.Q_findLatestAccountSettingByAccountId,
      query = "SELECT max(as1.dateChanged) FROM AccountSetting as1 WHERE as1.account.id = :account_id"
  )
})
@EntityConverter(converterDto = AccountSettingDto.class, toModel = AccountModel.class)
public class AccountSetting extends AccountIdFK implements AccountSettingDto {
  public static final String Q_findCurrentAccountSetting = "AccountSetting.findCurrentAccountSetting";
  public static final String Q_findLatestSettingsForDate = "AccountSetting.findLatestSettingsForDate";
  public static final String Q_findLatestAccountSettingByAccountId = "AccountSetting.findLatestAccountSettingByAccountId";
  
  public AccountSetting() {
  }
  
  public AccountSetting(Account account, Date dateChanged) {
    this.account = account;
    this.dateChanged = dateChanged;
  }
  
  @Temporal(TemporalType.DATE)
  private Date dateChanged;
  private Integer age;
  private Double height;
  
  @Enumerated(EnumType.ORDINAL)
  private Indicator useAge;
  
  @Enumerated(EnumType.STRING)
  private Weekday recalculationDay;
  
  @Enumerated(EnumType.STRING)
  private ActivityMultiplier activityMultiplier;
  
  @Enumerated(EnumType.STRING)
  private TDEEFormula tdeeFormula;
  
  @Enumerated(EnumType.STRING)
  private UnitSystem unitSystem;

  
  public Date getDateChanged() {
    return dateChanged;
  }
  
  public void setDateChanged(Date dateChanged) {
    this.dateChanged = dateChanged;
  }
  
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
  public Integer getAge() {
    return age;
  }
  
  @Override
  public void setAge(Integer age) {
    this.age = age;
  }
  
  @Override
  public void setUseAge(Indicator useAge) {
    this.useAge = useAge;
  }
  
  @Override
  public Indicator getUseAge() {
    return useAge;
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
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("accountId", account.getId())
        .append("recalculationDay", recalculationDay).append("activityMultiplier", activityMultiplier)
        .append("tdeeFormula", tdeeFormula).append("dateChanged", dateChanged).append("age", age)
        .append("height", height).toString();
  }
}
