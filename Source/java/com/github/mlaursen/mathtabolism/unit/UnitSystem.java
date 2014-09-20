package com.github.mlaursen.mathtabolism.unit;

/**
 * @author mlaursen
 *
 */
public enum UnitSystem {
	METRIC, IMPERIAL;
	
	/**
	 * 
	 * @return true if the current unit system is metric
	 */
	public boolean isMetric() {
		return METRIC.equals(this);
	}
	
	/**
	 * 
	 * @return true if the current unit system is imperial
	 */
	public boolean isImperial() {
		return IMPERIAL.equals(this);
	}
}