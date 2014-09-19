/**
 * 
 */
package com.github.mlaursen.mathtabolism.unit;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author mlaursen
 */
public class UnitConverter {
  private static final int METRIC_CONVERSION = 10;
  private static final Map<UnitMeasurement, Integer> IMPERIAL_DISTANCE_CONVERSION_TABLE = new HashMap<>();
  private static final Map<UnitMeasurement, Integer> IMPERIAL_VOLUME_CONVERSION_TABLE = new HashMap<>();
  private static final Map<UnitMeasurement, Integer> IMPERIAL_WEIGHT_CONVERSION_TABLE = new HashMap<>();
  private static final Map<UnitMeasurement, Integer> SPOON_CONVERSION_TABLE = new HashMap<>();
  static {
    IMPERIAL_DISTANCE_CONVERSION_TABLE.put(UnitMeasurement.INCH, 12);
    
    IMPERIAL_VOLUME_CONVERSION_TABLE.put(UnitMeasurement.CUP, 8);
    IMPERIAL_VOLUME_CONVERSION_TABLE.put(UnitMeasurement.PINT, 8 * 2);
    IMPERIAL_VOLUME_CONVERSION_TABLE.put(UnitMeasurement.QUART, 8 * 4);
    IMPERIAL_VOLUME_CONVERSION_TABLE.put(UnitMeasurement.GALLON, 8 * 4 * 4);
    
    SPOON_CONVERSION_TABLE.put(UnitMeasurement.TEASPOON, 3);
    
    IMPERIAL_WEIGHT_CONVERSION_TABLE.put(UnitMeasurement.OUNCE, 16);
  }
	
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
      return convert(switchUnit(convertToBase(fromMeasurement), toUnitMeasurement.getBaseUnit()), toUnitMeasurement);
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
	  int padding = 2;
	  if(from.name().contains("METER") && comparator < 0) {
	    padding--;
	  }
	  padding *= (comparator > 0 ? 1 : -1);
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
	  UnitMeasurement nonBase = from.isBaseUnit() ? to : from;
	  int conversion;
	  if(from.getBaseUnit().equals(UnitMeasurement.TABLESPOON)) {
	    conversion = SPOON_CONVERSION_TABLE.get(nonBase);
	  } else {
  	  switch(from.getUnitType()) {
  	    case DISTANCE:
  	      conversion = IMPERIAL_DISTANCE_CONVERSION_TABLE.get(nonBase);
  	      break;
  	    case VOLUME:
          conversion = IMPERIAL_VOLUME_CONVERSION_TABLE.get(nonBase);
  	      break;
  	    case WEIGHT:
  	      System.out.println(to.getBaseUnit());
          conversion = IMPERIAL_WEIGHT_CONVERSION_TABLE.get(nonBase);
  	      break;
        default:
          conversion = 0;
  	  }
	  }
	  double updated = from.compareTo(to) < 0 ? value / conversion : value * conversion;
	  return new Measurement(to, updated);
	}
	
	/**
	 * Converts a measurement to the base unit of the current {@link UnitMeasurement}
	 * @param fromMeasurement the {@link Measurement} to convert
	 * @return a new {@link Measurement} with the value and UnitMeasurement updated
	 */
	public static Measurement convertToBase(Measurement fromMeasurement) {
	  if(fromMeasurement.getUnitMeasurement().equals(fromMeasurement.getUnitMeasurement().getBaseUnit())) {
	    return fromMeasurement;
	  }
	  
	  UnitMeasurement um = fromMeasurement.getUnitMeasurement();
	  if(fromMeasurement.getUnitMeasurement().isMetric()) {
	    return convert(fromMeasurement, um.getBaseUnit());
	  }
	  return convertImperial(fromMeasurement.getValue(), um, um.getBaseUnit());
	}
	
	private static Measurement switchUnit(Measurement baseMeasurement, UnitMeasurement baseOtherUnit) {
	  double multiplier = 0;
	  double value = baseMeasurement.getValue();
	  switch(baseMeasurement.getUnitMeasurement()) {
	    case METER:
	      multiplier = 3.28;
	      break;
	    case FOOT:
	      multiplier = 0.30;
	      break;
	    case GRAM:
	      multiplier = 2.2046 / Math.pow(METRIC_CONVERSION, 3);
	      break;
	    case POUND:
	      return new Measurement(UnitMeasurement.KILOGRAM, value * 0.454);
	    case LITER:
	      return new Measurement(UnitMeasurement.QUART, value * 1.057);
	    case FLUID_OUNCE:
	      return new Measurement(UnitMeasurement.LITER, value * 29.57);
      default:
        throw new InvalidMeasurementConvertException(baseMeasurement, baseOtherUnit);
	  }
	  return new Measurement(baseOtherUnit, value * multiplier);
	}
}
