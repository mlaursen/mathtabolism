package com.mathtabolism.view.model.account;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.dto.AccountSettingDto;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.util.emconverter.EMConverter;
import com.mathtabolism.util.number.NumberUtils;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitConverter;
import com.mathtabolism.util.unit.UnitMeasurement;
import com.mathtabolism.util.unit.UnitSystem;
import com.mathtabolism.view.model.BaseModel;

/**
 * 
 * @author mlaursen
 */
@EMConverter(converter = AccountSettingDto.class, convertTo = AccountSetting.class)
public class AccountSettingModel extends BaseModel implements AccountSettingDto {
  private static final String US_AVERAGE_HEIGHT_CM = "177";
  private static final String US_AVERAGE_HEIGHT_IN = "10";
  private static final String US_AVERAGE_HEIGHT_FT = "5";
  
  private String heightInInches;
  private String heightInFeet;
  private String heightInCentimeters;
  
  private Date dateChanged;
  private Integer age;
  private Indicator useAge;
  private Double height;
  private ActivityMultiplier activityMultiplier;
  private Weekday recalculationDay;
  private TDEEFormula tdeeFormula;
  private UnitSystem unitSystem;
  
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
  
  public boolean isIncompleteSetup() {
    return activityMultiplier == null || tdeeFormula == null || unitSystem == null;
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
  }
  
  @Override
  public void setDateChanged(Date dateChanged) {
    this.dateChanged = dateChanged;
  }

  @Override
  public Date getDateChanged() {
    return dateChanged;
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
    return activityMultiplier == null ? ActivityMultiplier.SEDENTARY : activityMultiplier;
  }

  @Override
  public void setRecalculationDay(Weekday recalculationDay) {
    this.recalculationDay = recalculationDay;
  }

  @Override
  public Weekday getRecalculationDay() {
    return recalculationDay == null ? Weekday.DAILY : recalculationDay;
  }

  @Override
  public void setTdeeFormula(TDEEFormula tdeeFormula) {
    this.tdeeFormula = tdeeFormula;
  }

  @Override
  public TDEEFormula getTdeeFormula() {
    return tdeeFormula == null ? TDEEFormula.MIFFLIN_ST_JEOR : tdeeFormula;
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
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", id).append("dateChanged", dateChanged).append("height", height)
        .append("heightInFeet", heightInFeet).append("heightInInches", heightInInches).append("unitSystem", unitSystem)
        .append("recalculationDay", recalculationDay).append("tdeeFormula", tdeeFormula).append("activityMultiplier", activityMultiplier).toString();
  }
}
