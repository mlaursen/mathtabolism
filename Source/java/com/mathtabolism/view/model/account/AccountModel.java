/**
 * 
 */
package com.mathtabolism.view.model.account;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.dto.AccountSettingDto;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.util.emconverter.ModelConverter;
import com.mathtabolism.util.number.NumberUtils;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitConverter;
import com.mathtabolism.util.unit.UnitMeasurement;
import com.mathtabolism.util.unit.UnitSystem;

/**
 *
 * @author mlaursen
 */
@ModelConverter(entities = {Account.class, AccountSetting.class})
public class AccountModel extends BaseAccountModel implements AccountSettingDto {
  private static final String US_AVERAGE_HEIGHT_CM = "177";
  private static final String US_AVERAGE_HEIGHT_IN = "10";
  private static final String US_AVERAGE_HEIGHT_FT = "5";

  private String accountSettingId;
  private String heightInInches;
  private String heightInFeet;
  private String heightInCentimeters;
  private Integer age;
  private Indicator useAge;
  private Double height;
  private ActivityMultiplier activityMultiplier;
  private Weekday recalculationDay;
  private TDEEFormula tdeeFormula;
  private UnitSystem unitSystem;
  private Double currentWeight;
  private Double previousWeight;
  
  public void setCurrentWeight(Double weight) {
    this.currentWeight = weight;
  }
  
  public Double getCurrentWeight() {
    return currentWeight == null ? null : NumberUtils.format(currentWeight, 2);
  }
  
  public void setPreviousWeight(Double weight) {
    this.previousWeight = weight;
  }
  
  public Double getPreviousWeight() {
    return NumberUtils.format(previousWeight, 2);
  }
  
  public Gender getDefaultedGender() {
    return gender == null ? Gender.MALE : gender;
  }
  
  public ActivityMultiplier getDefaultedActivityMultiplier() {
    return activityMultiplier == null ? ActivityMultiplier.SEDENTARY : activityMultiplier;
  }
  
  public TDEEFormula getDefaultedTdeeFormula() {
    return tdeeFormula == null ? TDEEFormula.MIFFLIN_ST_JEOR : tdeeFormula;
  }
  
  public Weekday getDefaultedRecalculationDay() {
    return recalculationDay == null ? Weekday.DAILY : recalculationDay;
  }
  
  public UnitSystem getDefaultedUnitSystem() {
    return unitSystem == null ? UnitSystem.IMPERIAL : unitSystem;
  }
  
  public void setAccountSettingId(String accountSettingId) {
    this.accountSettingId = accountSettingId;
  }
  
  public String getAccountSettingId() {
    return accountSettingId;
  }

  /**
   * Checks if the current weight is set for the account.
   * <p>The current weight is considered set if
   * <ul>
   * <li>The current weight is not null
   * <li>The current weight is greater than 0
   * </ul>
   * @return true if the weight is set
   */
  public boolean isTodayWeightSet() {
    return currentWeight != null && currentWeight > 0;
  }
  
  /**
   * Checks if the account is considered a first time user. (Someone that has no settings set)
   * @return true if the account is considered a first time user
   */
  public boolean isIncompleteSetup() {
    return activityMultiplier == null || tdeeFormula == null || unitSystem == null || gender == null
        || (birthday == null && age == null);
  }
  /**
   * Updates the height value from the small height value and the large height value.
   * If the unit system is metric, only the centimeters will be used.
   */
  public void updateHeight() {
    if(UnitSystem.isImperial(unitSystem)) {
      Measurement inInches = new Measurement(UnitMeasurement.INCH, NumberUtils.stringToDouble(heightInInches));
      Measurement inFeet   = new Measurement(UnitMeasurement.FOOT, NumberUtils.stringToDouble(heightInFeet));
      inInches.add(inFeet);
      height = inInches.getValue();
    } else {
      height = NumberUtils.stringToDouble(heightInCentimeters);
    }
  }
  
  public void splitHeight() {
    if(height != null && UnitSystem.isImperial(unitSystem)) {
      Measurement inInches = new Measurement(UnitMeasurement.INCH, height);
      Measurement inFeet   = UnitConverter.convertToWholeNumber(inInches, UnitMeasurement.FOOT);
      inInches.subtract(inFeet);
      
      setHeightInFeet(NumberUtils.formatAsString(inFeet.getValue(), 0));
      setHeightInInches(NumberUtils.formatAsString(inInches.getValue(), 0));
    }
  }

  public String getHeightInInches() {
    return heightInInches == null ? US_AVERAGE_HEIGHT_IN : heightInInches;
  }

  public void setHeightInInches(String heightInInches) {
    this.heightInInches = heightInInches;
    updateHeight();
  }

  public String getHeightInFeet() {
    return heightInFeet == null ? US_AVERAGE_HEIGHT_FT : heightInFeet;
  }

  public void setHeightInFeet(String heightInFeet) {
    this.heightInFeet = heightInFeet;
    updateHeight();
  }
  
  public String getHeightInCentimeters() {
    return heightInCentimeters == null ? US_AVERAGE_HEIGHT_CM : heightInCentimeters;
  }
  
  public void setHeightInCentimeters(String heightInCentimeters) {
    this.heightInCentimeters = heightInCentimeters;
    updateHeight();
  }
  
  public boolean isUsingAge() {
    return Indicator.isTrue(useAge);
  }
  
  public void setUsingAge(boolean isUsingAge) {
    this.useAge = Indicator.fromBoolean(isUsingAge);
  }
  
  public void convertUnitsTo(UnitSystem toUnitSystem) {
    if(height != null && height > 0) {
      if(toUnitSystem.isImperial()) {
        Measurement centimeters = new Measurement(UnitMeasurement.CENTIMETER, height);
        Measurement inches = UnitConverter.convertToWholeNumber(centimeters, UnitMeasurement.INCH);
        Measurement feet   = UnitConverter.convertToWholeNumber(inches, UnitMeasurement.FOOT);
        inches.subtract(feet);
        heightInInches = NumberUtils.formatAsString(inches.getValue(), 0);
        heightInFeet   = NumberUtils.formatAsString(feet.getValue(), 0);
      } else {
        Measurement inches = new Measurement(UnitMeasurement.INCH, height);
        Measurement centimeters = UnitConverter.convertToWholeNumber(inches, UnitMeasurement.CENTIMETER);
        heightInCentimeters = NumberUtils.formatAsString(centimeters.getValue(), 0);
      }
    }
    
    if(currentWeight != null && currentWeight > 0) {
      if(unitSystem.isImperial()) {
        Measurement kg  = new Measurement(UnitMeasurement.KILOGRAM, currentWeight);
        Measurement lbs = UnitConverter.convert(kg, UnitMeasurement.POUND);
        
        currentWeight = lbs.getValue();
      } else {
        Measurement lbs = new Measurement(UnitMeasurement.POUND, currentWeight);
        Measurement kg  = UnitConverter.convert(lbs, UnitMeasurement.KILOGRAM);
        
        currentWeight = kg.getValue();
      }
    }
  }

  @Override
  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public Integer getAge() {
    return age;
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
  public void setHeight(Double height) {
    this.height = height;
  }

  @Override
  public Double getHeight() {
    return height;
  }

  @Override
  public void setActivityMultiplier(ActivityMultiplier activityMultiplier) {
    this.activityMultiplier = activityMultiplier;
  }

  @Override
  public ActivityMultiplier getActivityMultiplier() {
    return activityMultiplier;
  }

  @Override
  public void setRecalculationDay(Weekday recalculationDay) {
    this.recalculationDay = recalculationDay;
  }

  @Override
  public Weekday getRecalculationDay() {
    return recalculationDay;
  }

  @Override
  public void setTdeeFormula(TDEEFormula tdeeFormula) {
    this.tdeeFormula = tdeeFormula;
  }

  @Override
  public TDEEFormula getTdeeFormula() {
    return tdeeFormula;
  }

  @Override
  public void setUnitSystem(UnitSystem unitSystem) {
    this.unitSystem = unitSystem;
  }

  @Override
  public UnitSystem getUnitSystem() {
    return unitSystem;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("accountId", accountId).append("accountSettingId", accountSettingId)
        .append("username", username).append("email", email).append("password", password).append("role", role)
        .append("birthday", birthday).append("gender", gender).append("lastLogin", lastLogin).append("activeSince", activeSince)
        .append("heightInInches", heightInInches).append("heightInFeet", heightInFeet).append("heightInCentimeters", heightInCentimeters)
        .append("height", height).append("age", age).append("useAge", useAge).append("activityMultiplier", activityMultiplier)
        .append("recalculationDay", recalculationDay).append("tdeeFormula", tdeeFormula).append("unitSystem", unitSystem)
        .append("currentWeight", currentWeight).append("previousWeight", previousWeight)
        .toString();
  }
}
