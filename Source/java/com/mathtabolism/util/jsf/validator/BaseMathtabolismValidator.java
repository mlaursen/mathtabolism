/**
 * 
 */
package com.mathtabolism.util.jsf.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

/**
 *
 * @author mlaursen
 */
public abstract class BaseMathtabolismValidator implements Validator {
  private static final String VALIDATION_MESSAGES = "validation";


  public void addMessage(FacesContext context, String lookup, UIComponent component) {
    ResourceBundle rb = context.getApplication().getResourceBundle(context, VALIDATION_MESSAGES);
    String message = rb.getString(lookup);
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
    context.addMessage(component.getClientId(), msg);
  }
}
