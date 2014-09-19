package com.github.mlaursen.mathtabolism.unit;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author mlaursen
 *
 */
public enum UnitMeasurement {
  INCH(UnitType.DISTANCE, UnitSystem.IMPERIAL, false),
  FOOT(UnitType.DISTANCE, UnitSystem.IMPERIAL, false),
  OUNCE(UnitType.WEIGHT, UnitSystem.IMPERIAL),
  POUND(UnitType.WEIGHT, UnitSystem.IMPERIAL),
  TEASPOON(UnitType.VOLUME, UnitSystem.IMPERIAL),
  TABLESPOON(UnitType.VOLUME, UnitSystem.IMPERIAL),
  CUP(UnitType.VOLUME, UnitSystem.IMPERIAL),
  FLUID_OUNCE(UnitType.VOLUME, UnitSystem.IMPERIAL),
  PINT(UnitType.VOLUME, UnitSystem.IMPERIAL),
  QUART(UnitType.VOLUME, UnitSystem.IMPERIAL),
  GALLON(UnitType.VOLUME, UnitSystem.IMPERIAL),
  MILLILITER(UnitType.VOLUME, UnitSystem.METRIC),
  LITER(UnitType.VOLUME, UnitSystem.METRIC),
  MILLIGRAM(UnitType.WEIGHT, UnitSystem.METRIC),
  GRAM(UnitType.WEIGHT, UnitSystem.METRIC),
  KILOGRAM(UnitType.WEIGHT, UnitSystem.METRIC),
  METER(UnitType.DISTANCE, UnitSystem.METRIC),
  KILOMETER(UnitType.DISTANCE, UnitSystem.METRIC);
  
  private UnitType unitType;
  private UnitSystem unitSystem;
  private boolean isCookingType;
  private UnitMeasurement(UnitType unitType, UnitSystem unitSystem) {
    this(unitType, unitSystem, true);
  }
  
  private UnitMeasurement(UnitType unitType, UnitSystem unitSystem, boolean isCookingType) {
    this.unitType = unitType;
    this.unitSystem = unitSystem;
    this.isCookingType = isCookingType;
  }
  
  /**
   * 
   * @return true if the
   */
  public UnitType getUnitType() {
    return unitType;
  }
  
  /**
   * 
   * @return true if the unit system is {@link UnitSystem#METRIC}
   */
  public boolean isMetric() {
    return unitSystem.isMetric();
  }
  
  /**
   * 
   * @return true if the unit system is {@link UnitSystem#IMPERIAL}
   */
  public boolean isImperial() {
    return unitSystem.isImperial();
  }
  
  /**
   * 
   * @return true if this is a cooking type measurement
   */
  public boolean isCookingType() {
    return isCookingType;
  }
  
  /**
   * Checks if a UnitMeasurement is a cooking measurement.
   * Allows nulls.
   * @param unit the UnitMeasurement to compare to
   * @return true if the unit is a cooking type
   */
  public static boolean isCookingType(UnitMeasurement unit) {
    return unit != null && unit.isCookingType;
  }
  
  /**
   * Checks if this UnitMeasurement can be converted to the given
   * UnitMeasurement.
   * @param unitMeasurement the UnitMeasurement to check with
   * @return true if the {@link UnitType}'s are equal
   */
  public boolean canConvertTo(UnitMeasurement unitMeasurement) {
    return this.unitType.equals(unitMeasurement.unitType);
  }
  
  /**
   * Checks if two given UnitMeasurement are in the same {@link UnitSystem}.
   * Allows for nulls.
   * @param unitMeasurement1 the first UnitMeasurement
   * @param unitMeasurement2 the second UnitMeasurement
   * @return true if the UnitMeasurements have the same unit system
   */
  public static boolean isSameUnitSystem(UnitMeasurement unitMeasurement1, UnitMeasurement unitMeasurement2) {
    if(unitMeasurement1 == null || unitMeasurement2 == null) {
      return false;
    }
    return unitMeasurement1.unitSystem.equals(unitMeasurement2.unitSystem);
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("unitType", unitType)
        .append("unitSystemType", unitSystem)
        .append("isCookingType", isCookingType)
        .toString();
  }
}
