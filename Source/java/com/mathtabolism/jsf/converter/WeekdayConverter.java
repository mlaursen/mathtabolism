/**
 * 
 */
package com.mathtabolism.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mathtabolism.constants.Weekday;
import com.mathtabolism.util.string.StringUtils;

/**
 * 
 * @author laursenm
 */
@FacesConverter(value="weekdayConverter")
public class WeekdayConverter implements Converter {
	
	/**
	 * 
	 */
	public WeekdayConverter() {
	}
	
	/**
	 * @param context
	 * @param component
	 * @param value
	 * @return
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(StringUtils.isNotBlank(value)) {
			for(Weekday multiplier : Weekday.values()) {
				if(multiplier.toString().equalsIgnoreCase(value)) {
					return multiplier;
				}
			}
		}
		return null;
	}
	
	/**
	 * @param context
	 * @param component
	 * @param object
	 * @return
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(context == null || component == null) {
			
		}
		if(object instanceof Weekday) {
			return ((Weekday) object).toString();
		}
		return null;
	}
	
}
