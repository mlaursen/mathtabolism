/**
 * 
 */
package com.mathtabolism.controller;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author mlaursen
 */
public abstract class BaseController implements Serializable {
  private static final long serialVersionUID = -6686889317225940807L;
  
  /**
   * Displays an info message to the user
   * 
   * @param lookupString a message String to display
   * @param params optional parameters to pass to the message
   */
  protected void displayInfoMessage(String lookupString, Object... params) {
    displayMessage(FacesMessage.SEVERITY_INFO, lookupString, params);
  }
  
  /**
   * Displays a warning message to the user
   * 
   * @param lookupString a message String to display
   * @param params optional parameters to pass to the message
   */
  protected void displayWarnMessage(String lookupString, Object... params) {
    displayMessage(FacesMessage.SEVERITY_WARN, lookupString, params);
  }
  
  /**
   * Displays an error message to the user
   * 
   * @param lookupString a message String to display
   * @param params optional parameters to pass to the message
   */
  protected void displayErrorMessage(String lookupString, Object... params) {
    displayMessage(FacesMessage.SEVERITY_ERROR, lookupString, params);
  }
  
  /**
   * Displays a message with a given Severity
   * 
   * @param severity the {@link Severity} of the message
   * @param lookupString a message String to display
   * @param params optional parameters to pass to the message
   */
  protected void displayMessage(Severity severity, String lookupString, Object... params) {
    FacesContext context = getContext();
    String message = getString(lookupString, params);
    context.addMessage(null, new FacesMessage(severity, message, message));
  }
  
  protected void displayDebugMessage(Object object) {
    getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, object.toString(), object.toString()));
  }
  
  /**
   * Gets a String from the Messages Resource bundle by Enum name
   * 
   * @param lookupEnum an enum to convert to a String
   * @param params optional parameters to pass to the message
   * @return a String from the resource bundle or null
   */
  protected String getString(Enum<?> lookupEnum, Object... params) {
    return getString(lookupEnum.name(), params);
  }
  
  /**
   * Gets a String from the Messages Resource Bundle
   * 
   * @param lookupString a String to look up from the resource bundle
   * @param params optional parameters to pass to the message
   * @return a String from the resource bundle or null
   */
  protected String getString(String lookupString, Object... params) {
    String msg = ResourceBundle.getBundle("messages", getContext().getViewRoot().getLocale()).getString(lookupString);
    return MessageFormat.format(msg, params);
  }
  
  /**
   * Gets the current instance of FacesContext
   * 
   * @return the FacesContext
   */
  protected FacesContext getContext() {
    return FacesContext.getCurrentInstance();
  }
  
  /**
   * Gets the HttpServletRequest from the FacesContext
   * 
   * @return the current HttpServletRequest
   */
  protected HttpServletRequest getRequest() {
    return (HttpServletRequest) getContext().getExternalContext().getRequest();
  }
  
  /**
   * Converts an <tt>Enum.values()</tt> into an array of SelectItem. The label used will be a lookup of the enum name
   * from the resources.properties file.
   * <p>
   * Example:
   * 
   * <pre>
   * {@code
   * getSelectItemEnumArray(Weekday.values())}
   * </pre>
   * 
   * The first item returned would be:
   * 
   * <pre>
   * {@code SelectItem[Weekday.SUNDAY, Sunday]}
   * </pre>
   * 
   * @param values
   *          an array of any Enum's values
   * @return an array of a converted enums into SelectItem
   */
  protected SelectItem[] convertEnumToSelectItems(Enum<?>[] values) {
    SelectItem[] items = new SelectItem[values.length];
    for(Enum<?> value : values) {
      items[value.ordinal()] = new SelectItem(value, getString(value));
    }
    return items;
  }
}
