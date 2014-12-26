package com.mathtabolism.util.unit;

/**
 * @author mlaursen
 *
 */
public enum UnitSystem {
  METRIC, IMPERIAL;
  
  public static boolean isMetric(UnitSystem unitSystem) {
    return METRIC.equals(unitSystem);
  }
  
  public static boolean isImperial(UnitSystem unitSystem) {
    return IMPERIAL.equals(unitSystem);
  }
  
  /**
   * 
   * @return true if the current unit system is metric
   */
  public boolean isMetric() {
    return METRIC.equals(this);
  }
  
  /**
   * 
   * @return true if the current unit system is imperial
   */
  public boolean isImperial() {
    return IMPERIAL.equals(this);
  }
}