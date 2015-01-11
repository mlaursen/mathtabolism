/**
 * 
 */
package com.mathtabolism.util.jsf.validator;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.mathtabolism.bo.account.AccountBO;

/**
 *
 * @author mlaursen
 */
@Named
@RequestScoped
public class UsernameValidator extends BaseMathtabolismValidator {
  private static final String MESSAGE = "account.UsernameExists";
  
  @Inject
  private AccountBO accountBO;
  public UsernameValidator() {
  }
  
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object valueAsObject) throws ValidatorException {
    String value = valueAsObject.toString();
    if(StringUtils.isNotBlank(value) && !accountBO.isUsernameAvailable(value)) {
      ((UIInput) component).setValid(false);
      addMessage(context, MESSAGE, component);
    }
  }

}
