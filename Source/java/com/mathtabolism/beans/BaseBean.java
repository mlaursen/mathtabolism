/**
 * 
 */
package com.mathtabolism.beans;

import java.io.Serializable;
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
public abstract class BaseBean implements Serializable {
	private static final long serialVersionUID = -6686889317225940807L;
	
	
	/**
	 * Displays an info message to the user
	 * @param lookupString a message String to display
	 */
	protected void displayInfoMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_INFO, lookupString);
	}
	
	/**
	 * Displays a warning message to the user
	 * @param lookupString a message String to display
	 */
	protected void displayWarnMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_WARN, lookupString);
	}
	
	/**
	 * Displays an error message to the user
	 * @param lookupString a message String to display
	 */
	protected void displayErrorMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_ERROR, lookupString);
	}
	
	/**
	 * Displays a message with a given Severity
	 * @param severity the {@link Severity} of the message
	 * @param lookupString a message String to display
	 */
	protected void displayMessage(Severity severity, String lookupString) {
		FacesContext context = getContext();
		String message = getString(lookupString);
		context.addMessage(null, new FacesMessage(severity, message, message));
	}
	
	/**
	 * Gets a String from the Messages Resource bundle by Enum name
	 * @param lookupEnum an enum to convert to a String
	 * @return a String from the resource bundle or null
	 */
	protected String getString(Enum<?> lookupEnum) {
		return getString(lookupEnum.name());
	}
	
	/**
	 * Gets a String from the Messages Resource Bundle
	 * @param lookupString a String to look up from the resource bundle
	 * @return a String from the resource bundle or null
	 */
	protected String getString(String lookupString) {
		return ResourceBundle.getBundle("messages", getContext().getViewRoot().getLocale()).getString(lookupString);
	}
	
	/**
	 * Gets the current instance of FacesContext
	 * @return the FacesContext
	 */
	protected FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Gets the HttpServletRequest from the FacesContext
	 * @return the current HttpServletRequest
	 */
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getContext().getExternalContext().getRequest();
	}
	
	/**
	 * Converts an <tt>Enum.values()</tt> into an array of SelectItem. The label used 
	 * will be a lookup of the enum name from the resources.properties file.
	 * <p>Example:<pre>{@code
	 * getSelectItemEnumArray(Weekday.values())}</pre>
	 * The first item returned would be:<pre>
	 * {@code SelectItem[Weekday.SUNDAY, Sunday]}</pre>
	 * @param values an array of any Enum's values
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
