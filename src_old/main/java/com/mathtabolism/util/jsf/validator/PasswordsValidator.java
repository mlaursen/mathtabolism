/**
 * 
 */
package com.mathtabolism.util.jsf.validator;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author mlaursen
 */
@FacesValidator
public class PasswordsValidator extends BaseMathtabolismValidator {
  private static final String PASSWORD_MATCH = "account.PasswordMatch";

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    String password = value.toString();
    UIInput inputPassword = (UIInput) component;
    String clientId = inputPassword.getClientId();
    String otherId = clientId.replace(inputPassword.getId(), "");
    String lookupId = clientId.contains("confirm") ? "password" : "confirm-password";
    UIInput confirmed = (UIInput) context.getViewRoot().findComponent(otherId + lookupId);
    
    String confirmPassword = (String) confirmed.getSubmittedValue();
    if(StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
      return;
    } else if(!password.equals(confirmPassword)) {
      inputPassword.setValid(false);
      confirmed.setValid(false);
      addMessage(context, PASSWORD_MATCH, component);
      addMessage(context, PASSWORD_MATCH, confirmed);
    }
  }

}
