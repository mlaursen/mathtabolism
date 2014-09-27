/**
 * 
 */
package com.mathtabolism.test.unit;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitMeasurement;

/**
 * 
 * @author mlaursen
 */
public class MeasurementUTest {
	
	/**
	 * Test method for {@link com.mathtabolism.util.unit.Measurement#subtract(com.github.mlaursen.mathtabolism.util.calculation.Subtractable)}.
	 */
	@Test
	public void testSubtract() {
		Measurement m = new Measurement(UnitMeasurement.GRAM, 127);
		m.subtract(m);
		assertThat(new Measurement(UnitMeasurement.GRAM, 0), is(m));
		
		m.setValue(32.540);
		m.subtract(new Measurement(UnitMeasurement.MILLIGRAM, 540));
		assertThat(new Measurement(UnitMeasurement.GRAM, 32), is(m));
		
	}
	
	/**
	 * Test method for {@link com.mathtabolism.util.unit.Measurement#add(com.github.mlaursen.mathtabolism.util.calculation.Addable)}.
	 */
	@Test
	public void testAdd() {
		Measurement m = new Measurement(UnitMeasurement.GRAM, 127);
		m.add(m);
		assertThat(new Measurement(UnitMeasurement.GRAM, 254), is(m));
		
		m.setValue(32);
		m.add(new Measurement(UnitMeasurement.MILLIGRAM, 540));
		assertThat(new Measurement(UnitMeasurement.GRAM, 32.540), is(m));
		
		Measurement lbs = new Measurement(UnitMeasurement.POUND, 178.35);
		lbs.add(new Measurement(UnitMeasurement.KILOGRAM, 3));
		assertThat(new Measurement(UnitMeasurement.POUND, 178.35 + 3 * 2.2046), is(lbs));
	}
	
}
