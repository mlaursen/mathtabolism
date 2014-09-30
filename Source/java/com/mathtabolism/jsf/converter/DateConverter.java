/**
 * 
 */
package com.mathtabolism.jsf.converter;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mathtabolism.util.date.DateUtils;

/**
 * 
 * @author mlaursen
 */
@FacesConverter("dateConverter")
public class DateConverter implements Converter {
	
	/**
	 * 
	 */
	public DateConverter() {
	}
	
	/**
	 * @param context
	 * @param component
	 * @param value
	 * @return
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return DateUtils.formatStringAsDate(value);
	}
	
	/**
	 * @param context
	 * @param component
	 * @param object
	 * @return
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		return DateUtils.formatDateAsString((Date) object);
	}
	
}
