package com.github.mlaursen.mathtabolism.unit;

/**
 * @author mlaursen
 *
 */
public class InvalidMeasurementConvertException extends
    IllegalArgumentException {

  private static final long serialVersionUID = 1L;
  private static final String MESSAGE = "Unable to convert %s to %s.";
  
  public InvalidMeasurementConvertException(Measurement measurement, UnitMeasurement unitMeasurement) {
    super(String.format(MESSAGE, measurement, unitMeasurement));
  }
}
