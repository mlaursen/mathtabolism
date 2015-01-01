/**
 * 
 */
package com.mathtabolism.util.jsf.converter;

import java.text.ParseException;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.mathtabolism.view.controller.account.AccountController;
import com.mathtabolism.view.model.account.AccountModel;

/**
 * 
 * @author mlaursen
 */
@Named
@ApplicationScoped
public class DateConverter implements Converter {
  public static final String AMERICAN_DATE_FORMAT = "MM/dd/yyyy";
  public static final String EUROPEAN_DATE_FORMAT = "dd/MM/yyyy";
  
  @Inject
  private AccountController accountController;
  
  public String getDateFormat() {
    AccountModel model = accountController.getAccountModel();
    if(model != null && model.getDefaultedUnitSystem().isMetric()) {
      return EUROPEAN_DATE_FORMAT;
    } else {
      return AMERICAN_DATE_FORMAT;
    }
  }
  
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    try {
      return DateUtils.parseDateStrictly(value, new String[] { AMERICAN_DATE_FORMAT, EUROPEAN_DATE_FORMAT });
    } catch (ParseException e) {
      return null;
    }
  }
  
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object object) {
    return DateFormatUtils.format((Date) object, getDateFormat());
  }
  
}
