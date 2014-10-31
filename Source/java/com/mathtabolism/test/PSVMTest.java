package com.mathtabolism.test;

import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitConverter;
import com.mathtabolism.util.unit.UnitMeasurement;

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
