/**
 * 
 */
package com.mathtabolism.util.jsf.converter;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * @author mlaursen
 */
@FacesConverter("dateConverter")
public class DateConverter implements Converter {
  public static final String AMERICAN_DATE_FORMAT = "MM/dd/yyyy";
  public static final String EUROPEAN_DATE_FORMAT = "dd/MM/yyyy";
  
  public DateConverter() {
  }
  
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    try {
      return DateUtils.parseDateStrictly(value, new String[] {AMERICAN_DATE_FORMAT, EUROPEAN_DATE_FORMAT});
    } catch (ParseException e) {
      return null;
    }
  }
  
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object object) {
    Map<String, Object> attributes = component.getAttributes();
    String pattern = AMERICAN_DATE_FORMAT;
    if(attributes.containsKey("pattern")) {
      pattern = (String) attributes.get("pattern");
    }
    
    return DateFormatUtils.format((Date) object, pattern);
  }
  
}
