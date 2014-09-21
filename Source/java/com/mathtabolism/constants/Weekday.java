/**
 * 
 */
package com.mathtabolism.constants;

import com.mathtabolism.util.string.StringUtils;

/**
 * 
 * @author mlaursen
 */
public enum Weekday {
	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
	
	public String formatted() {
		return StringUtils.toCamelCase(name(), true);
	}
}
