/**
 * 
 */
package com.mathtabolism.util.jsf.converter;

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
@FacesConverter("europeanDateConverter")
public class EuropeanDateConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    return DateUtils.formatStringAsDate(value, true);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object object) {
    return DateUtils.formatDateAsString((Date) object, true);
  }

}
