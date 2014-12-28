/**
 * 
 */
package com.mathtabolism.util.unit;

import com.mathtabolism.model.Ingredient;

/**
 * 
 * @author mlaursen
 */
public class UnitConverter {
  private static final int METRIC_CONVERSION = 10;
  /**
   * <ol>
   * <li>{@link UnitMeasurement#FLUID_OUNCE}
   * <li>{@link UnitMeasurement#CUP}
   * <li>{@link UnitMeasurement#PINT}
   * <li>{@link UnitMeasurement#QUART}
   * <li>{@link UnitMeasurement#GALLON}
   * </ol>
   */
  private static final int[] IMPERIAL_VOLUME_CONVERSION = {
      8, 2, 2, 4, 4
  };
  
  private UnitConverter() {
  }
  
  /**
   * 
   * @param fromMeasurement
   *          a {@link Measurement} to convert
   * @param toUnitMeasurement
   *          a {@link UnitMeasurement} to convert to
   * @return a new Measurement with an updated value and UnitMeasurement.
   * @throws MeasurementConversionException
   *           when the Measurement can not be converted to the given <tt>toUnitMeasurement</tt>
   */
  public static Measurement convert(Measurement fromMeasurement, UnitMeasurement toUnitMeasurement)
      throws MeasurementConversionException {
    UnitMeasurement from = fromMeasurement.getUnitMeasurement();
    if(from == toUnitMeasurement) {
      return fromMeasurement;
    }
    else if(!from.canConvertTo(toUnitMeasurement)) {
      throw new MeasurementConversionException(fromMeasurement, toUnitMeasurement);
    }
    double value = fromMeasurement.getValue();
    if(UnitMeasurement.isSameUnitSystem(from, toUnitMeasurement)) {
      return toUnitMeasurement.isMetric() ? convertMetric(value, from, toUnitMeasurement) : convertImperial(value,
          from, toUnitMeasurement);
    }
    else {
      return convert(switchUnit(convertToBase(fromMeasurement), toUnitMeasurement.getBaseUnit()), toUnitMeasurement);
    }
    
  }
  
  /**
   * Gets the Ingredient's Serving that has the same unit system as the given measurement. This is used to calculate the
   * total calories, fat, carbs, and protein for an ingredient based on the meal's measurement.
   * 
   * @param mealMeasurement
   *          a {@link Measurement} to find a matching measurement for in the ingredient
   * @param ingredient
   *          the {@link Ingredient} to find the serving for
   * @return a {@link Measurement} of the serving for the ingredient
   */
  public static Measurement getServing(Measurement mealMeasurement, Ingredient ingredient) {
    Measurement match = ingredient.getServing();
    if(UnitMeasurement.isSameUnitSystem(mealMeasurement.getUnitMeasurement(), match.getUnitMeasurement())) {
      return match;
    }
    else {
      return ingredient.getAlternateServing();
    }
  }
  
  /**
   * Thank goodness Metric is so easy..
   * <p>
   * Note: This formula takes value and multiplies it by the metric conversion of {@value #METRIC_CONVERSION} to the
   * power of the {@link UnitMeasurement#compareTo(UnitMeasurement)} <tt>+/- 2</tt>. We add the additional <tt>2</tt>
   * because the conversion is really always 1000 instead of 10 since the middle Units are not needed.
   * <p>
   * If more Units are needed, that number must be changed or removed.
   * 
   * @param value
   *          the value to update
   * @param from
   *          the {@link UnitMeasurement} to convert from
   * @param to
   *          the {@link UnitMeasurement} to convert to
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
    int conversion = 1;
    int comparator = from.compareTo(to);
    if(from.getBaseUnit().equals(UnitMeasurement.TABLESPOON)) {
      conversion = 3;
    }
    else {
      switch(from.getUnitType()) {
        case DISTANCE:
          conversion = 12;
          break;
        case VOLUME:
          int shiftFrom = from.ordinal() - from.getBaseUnit().ordinal();
          int shiftTo = to.ordinal() - from.getBaseUnit().ordinal();
          if(shiftFrom > shiftTo) {
            int tmp = shiftFrom;
            shiftFrom = shiftTo;
            shiftTo = tmp;
          }
          for(int i = shiftFrom; i < shiftTo; i++) {
            conversion *= IMPERIAL_VOLUME_CONVERSION[i];
          }
          break;
        case WEIGHT:
          conversion = 16;
          break;
      }
    }
    double updated = comparator < 0 ? value / conversion : value * conversion;
    return new Measurement(to, updated);
  }
  
  /**
   * Converts a measurement to the base unit of the current {@link UnitMeasurement}
   * 
   * @param fromMeasurement
   *          the {@link Measurement} to convert
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
        multiplier = 39.3701;
        break;
      case INCH:
        multiplier = 2.54 / Math.pow(METRIC_CONVERSION, 3);
        break;
      case GRAM:
        multiplier = 2.2046 / Math.pow(METRIC_CONVERSION, 3);
        break;
      case POUND:
        return new Measurement(UnitMeasurement.KILOGRAM, value * 0.453592);
      case LITER:
        return new Measurement(UnitMeasurement.QUART, value * 1.057);
      case FLUID_OUNCE:
        return new Measurement(UnitMeasurement.LITER, value * 29.57);
      default:
        throw new MeasurementConversionException(baseMeasurement, baseOtherUnit);
    }
    return new Measurement(baseOtherUnit, value * multiplier);
  }
}
