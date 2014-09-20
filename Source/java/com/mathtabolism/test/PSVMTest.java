package com.mathtabolism.test;

import com.mathtabolism.unit.Measurement;
import com.mathtabolism.unit.MeasurementConversionException;
import com.mathtabolism.unit.UnitConverter;
import com.mathtabolism.unit.UnitMeasurement;

/**
 * @author mlaursen
 *
 */
public class PSVMTest {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Measurement m = new Measurement(UnitMeasurement.GRAM, 100);
		m = UnitConverter.convert(m, UnitMeasurement.MILLIGRAM);
		System.out.println(m);
	}
	
}
