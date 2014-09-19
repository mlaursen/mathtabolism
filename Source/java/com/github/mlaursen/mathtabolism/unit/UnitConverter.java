/**
 * 
 */
package com.github.mlaursen.mathtabolism.unit;


/**
 * 
 * @author mlaursen
 */
public class UnitConverter {
  private static final int METRIC_CONVERSION = 10;
	
	private UnitConverter() {}
	
	/**
	 * 
	 * @param fromMeasurement a {@link Measurement} to convert
	 * @param toUnitMeasurement a {@link UnitMeasurement} to convert to
	 * @return a new Measurement with an updated value and UnitMeasurement.
	 * @throws InvalidMeasurementConvertException when the Measurement can not be converted to the given
	 *     <tt>toUnitMeasurement</tt>
	 */
	public static Measurement convert(Measurement fromMeasurement, UnitMeasurement toUnitMeasurement) throws InvalidMeasurementConvertException {
	  UnitMeasurement from = fromMeasurement.getUnitMeasurement();
		if(from == toUnitMeasurement) {
		  return fromMeasurement;
		} else if(!from.canConvertTo(toUnitMeasurement)) {
	    throw new InvalidMeasurementConvertException(fromMeasurement, toUnitMeasurement);
		}
    double value = fromMeasurement.getValue();
    if(UnitMeasurement.isSameUnitSystem(from, toUnitMeasurement)) {
      return toUnitMeasurement.isMetric()
          ? convertMetric(value, from, toUnitMeasurement)
          : convertImperial(value, from, toUnitMeasurement);
    } else {
      return convert(convertToBase(value, from, toUnitMeasurement), toUnitMeasurement);
    }
    
	}
	
	/**
	 * Thank goodness Metric is so easy..
	 * <p>Note: This formula takes value and multiplies it by
	 * the metric conversion of {@value #METRIC_CONVERSION} to the power of
	 * the {@link UnitMeasurement#compareTo(UnitMeasurement)} <tt>+/- 2</tt>. We add the additional <tt>2</tt>
	 * because the conversion is really always 1000 instead of 10 since the middle Units are not needed.
	 * <p>If more Units are needed, that number must be changed or removed.
	 * 
	 * @param value the value to update
	 * @param from the {@link UnitMeasurement} to convert from
	 * @param to the {@link UnitMeasurement} to convert to
	 * @return the new {@link Measurement} with a converted value and {@link UnitMeasurement}
	 */
	private static Measurement convertMetric(double value, UnitMeasurement from, UnitMeasurement to) {
	  int comparator = from.compareTo(to);
	  int padding = 2 * (comparator > 0 ? 1 : -1);
	  return new Measurement(to, value * (Math.pow(METRIC_CONVERSION, comparator + padding)));
	}
	
	/**
	 * 
	 * @param value
	 * @param from
	 * @param to
	 * @return
	 */
	private static Measurement convertImperial(double value, UnitMeasurement from, UnitMeasurement to) {
	  
	  return new Measurement(to, value);
	}
	
	/**
	 * Converts a measurement to the base unit of the current {@link UnitMeasurement}
	 * @param fromMeasurement the {@link Measurement} to convert
	 * @return a new {@link Measurement} with the value and UnitMeasurement updated
	 */
	public static Measurement convertToBase(Measurement fromMeasurement) {
	  UnitMeasurement um = fromMeasurement.getUnitMeasurement();
	  if(fromMeasurement.getUnitMeasurement().isMetric()) {
	    return convert(fromMeasurement, um.getBaseUnit());
	  }
	  return convertToBase(fromMeasurement.getValue(), um, um.getBaseUnit());
	}
	
	/**
	 * 
	 * @param value
	 * @param from
	 * @param to
	 * @return
	 */
	private static Measurement convertToBase(double value, UnitMeasurement from, UnitMeasurement to) {
	  return new Measurement(to, value);
	}
}
