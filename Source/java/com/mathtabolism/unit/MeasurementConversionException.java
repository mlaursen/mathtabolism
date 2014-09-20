package com.mathtabolism.unit;

/**
 * @author mlaursen
 *
 */
public class MeasurementConversionException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Unable to convert %s to %s.";
	
	public MeasurementConversionException(Measurement measurement, UnitMeasurement unitMeasurement) {
		super(String.format(MESSAGE, measurement, unitMeasurement));
	}
}
