/**
 * 
 */
package com.mathtabolism.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.util.string.StringUtils;

/**
 * 
 * @author laursenm
 */
@FacesConverter(value="")
public class ActivityMultiplierConverter implements Converter {
	
	/**
	 * 
	 */
	public ActivityMultiplierConverter() {
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
			for(ActivityMultiplier multiplier : ActivityMultiplier.values()) {
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
		if(object instanceof ActivityMultiplier) {
			return ((ActivityMultiplier) object).toString();
		}
		return null;
	}
	
}
