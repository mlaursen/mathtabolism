package com.github.mlaursen.mathtabolism.unit;

/**
 * @author mlaursen
 *
 */
public enum UnitSystem {
  METRIC, IMPERIAL;

  public boolean isMetric() {
    return METRIC.equals(this);
  }

  public boolean isImperial() {
    return IMPERIAL.equals(this);
  }
}