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

import com.mathtabolism.view.controller.BaseController;
import com.mathtabolism.view.controller.account.AccountController;
import com.mathtabolism.view.model.account.AccountModel;

/**
 * 
 * @author mlaursen
 */
@Named
@ApplicationScoped
public class DateConverter extends BaseController implements Converter {
  private static final long serialVersionUID = 7802757537465807408L;
  public static final String AMERICAN_DATE_FORMAT = "MM/dd/yyyy";
  public static final String EUROPEAN_DATE_FORMAT = "dd/MM/yyyy";
  public static final String AMERICAN_SHORT_FORMAT = "EEE, MMM d, yy";
  public static final String EUROPEAN_SHORT_FORMAT = "EEE, d MMM yy";
  
  public static final String[] DATE_FORMATS = new String[] {
    AMERICAN_DATE_FORMAT, EUROPEAN_DATE_FORMAT, AMERICAN_SHORT_FORMAT, EUROPEAN_SHORT_FORMAT
  };
  
  @Inject
  private AccountController accountController;
  
  /**
   * Checks if the current account is using the metric unit systems
   * @return true if the current account is using the metric unit systems
   */
  protected boolean isMetric() {
    AccountModel model = accountController.getAccountModel();
    return model != null && model.getDefaultedUnitSystem().isMetric();
  }
  
  /**
   * Gets the current date format
   * @return
   */
  public String getDateFormat() {
    return isMetric() ? AMERICAN_DATE_FORMAT : EUROPEAN_DATE_FORMAT;
  }
  
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    try {
      return DateUtils.parseDateStrictly(value, DATE_FORMATS);
    } catch (ParseException e) {
      return null;
    }
  }
  
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object object) {
    return DateFormatUtils.format((Date) object, getDateFormat());
  }
  
}
