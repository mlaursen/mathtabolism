package com.github.mlaursen.mathtabolism.constants;

/**
 * 
 * @author laursenm
 */
public enum MeasuringUnit {
	METRIC, IMERIAL;
	
	/**
	 * 
	 * @param measuringUnit
	 * @return
	 */
	public static boolean isMetric(MeasuringUnit measuringUnit) {
		return METRIC.equals(measuringUnit);
	}
}
