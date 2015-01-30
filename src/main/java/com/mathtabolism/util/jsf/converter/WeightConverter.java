/**
 * 
 */
package com.mathtabolism.util.jsf.converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import org.apache.commons.lang3.math.NumberUtils;

import com.mathtabolism.util.number.MathtabolismNumberUtils;
import com.mathtabolism.view.controller.BaseController;

/**
 *
 * @author mlaursen
 */
@Named
@ApplicationScoped
public class WeightConverter extends BaseController implements Converter {
  private static final long serialVersionUID = 1L;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String weightString) {
    return NumberUtils.toDouble(weightString);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object weightObj) {
    return MathtabolismNumberUtils.format((Double) weightObj, 2, 0.0);
  }
  
  public String getAsString(Object weightObject) {
    return MathtabolismNumberUtils.format((Double) weightObject, 2, 0.0);
  }

}
