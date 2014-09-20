package com.github.mlaursen.mathtabolism.test;

import com.github.mlaursen.mathtabolism.unit.MeasurementConversionException;
import com.github.mlaursen.mathtabolism.unit.Measurement;
import com.github.mlaursen.mathtabolism.unit.UnitConverter;
import com.github.mlaursen.mathtabolism.unit.UnitMeasurement;

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
