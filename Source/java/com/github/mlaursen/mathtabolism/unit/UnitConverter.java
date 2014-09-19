/**
 * 
 */
package com.github.mlaursen.mathtabolism.unit;


/**
 * 
 * @author mlaursen
 */
public class UnitConverter {
	
	private UnitConverter() {}
	
	public static Measurement convert(Measurement fromMeasurement, UnitMeasurement toUnitMeasurement) throws InvalidMeasurementConvertException {
	  UnitMeasurement from = fromMeasurement.getUnitMeasurement();
		if(from == toUnitMeasurement) {
		  return fromMeasurement;
		} else if(!from.canConvertTo(toUnitMeasurement)) {
	    throw new InvalidMeasurementConvertException(fromMeasurement, toUnitMeasurement);
		}
    double value = fromMeasurement.getValue();
    
    return convertMetric(value, from, toUnitMeasurement);
	}
	
	private static Measurement convertMetric(double value, UnitMeasurement from, UnitMeasurement to) {
	  int conversion = 1000;
	  int comparator = from.compareTo(to);
	  System.out.println(comparator);
	  return new Measurement(to, value * (Math.pow(conversion, comparator)));
	}
}
