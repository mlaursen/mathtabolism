package com.mathtabolism.util.unit;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.mathtabolism.util.calculation.Addable;
import com.mathtabolism.util.calculation.Subtractable;

/**
 * A Measurement is a class that has a {@link UnitMeasurement} and a value.
 * 
 * @author mlaursen
 *
 */
@Embeddable
public class Measurement implements Addable, Subtractable {
  @Enumerated(EnumType.STRING)
  private UnitMeasurement unitMeasurement;
  private double value;
  
  public Measurement() {
  }
  
  public Measurement(UnitMeasurement unitMeasurement, double value) {
    this.unitMeasurement = unitMeasurement;
    this.value = value;
  }
  
  /**
   * 
   * @param subtractable
   */
  @Override
  public void subtract(Subtractable subtractable) {
    if(subtractable != null && subtractable instanceof Measurement) {
      Measurement m = (Measurement) subtractable;
      this.value -= UnitConverter.convert(m, unitMeasurement).value;
    }
  }
  
  /**
   * 
   * @param addable
   */
  @Override
  public void add(Addable addable) {
    if(addable != null && addable instanceof Measurement) {
      Measurement m = (Measurement) addable;
      this.value += UnitConverter.convert(m, unitMeasurement).value;
    }
  }
  
  public double getValue() {
    return value;
  }
  
  /**
   *
   * @param value
   *          the value to set
   */
  public void setValue(double value) {
    this.value = value;
  }
  
  public UnitMeasurement getUnitMeasurement() {
    return unitMeasurement;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object != null && object instanceof Measurement) {
      Measurement m = (Measurement) object;
      return value == m.value && unitMeasurement == m.unitMeasurement;
    }
    return false;
  }
  
  @Override
  public String toString() {
    return "Measurement [unitMeasurement = " + unitMeasurement + ", value = " + value + "]";
  }
}
