/**
 * 
 */
package com.mathtabolism.util.jsf.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
  private static final String EXISTS = "account.UsernameExists";
  private static final String INVALID_CHARSET = "account.UsernameCharset";
  private static final String REGEX = "^[A-z0-9\\_]+$";
  
  @Inject
  private AccountBO accountBO;
  private Pattern pattern;
  public UsernameValidator() {
    pattern = Pattern.compile(REGEX);
  }
  
  
  @Override
  public void validate(FacesContext context, UIComponent component, Object valueAsObject) throws ValidatorException {
    String value = valueAsObject.toString();
    UIInput username = (UIInput) component;
    Matcher matcher = pattern.matcher(value);
    if(StringUtils.isNotBlank(value)) {
      if(!matcher.matches()) {
        username.setValid(false);
        addMessage(context, INVALID_CHARSET, component);
      } else if(!accountBO.isUsernameAvailable(value)) {
        username.setValid(false);
        addMessage(context, EXISTS, component);
      }
    }
  }

}
