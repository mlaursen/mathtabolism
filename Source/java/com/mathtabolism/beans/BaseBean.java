/**
 * 
 */
package com.mathtabolism.beans;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * 
 * @author mlaursen
 */
public abstract class BaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Adds an Info Message to the FacesContext
	 * @param lookupString the String to lookup
	 */
	protected void displayInfoMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_INFO, lookupString);
	}
	
	/**
	 * Adds an Error Message to the FacesContext
	 * @param lookupString the String to lookup
	 */
	protected void displayErrorMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_ERROR, lookupString);
	}
	
	/**
	 * Adds a Warning Message to the FacesContext
	 * @param lookupString the String to lookup
	 */
	protected void displayWarnMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_WARN, lookupString);
	}
	
	
	/**
	 * <pre>{@code String bundleName = context.getApplication().getMessageBundle();}</pre>
	 * This was returning null. I don't know why. SO using "messages" instead of this.
	 * 
	 * @param severity the {@link FacesMessage} Severity of the message
	 * @param lookupString the String to get from the <tt>messages.properties</tt> file
	 */
	private void displayMessage(Severity severity, String lookupString) {
		FacesContext context = getContext();
		Locale locale = context.getViewRoot().getLocale();
		ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
		String message = messages.getString(lookupString);
		context.addMessage(null, new FacesMessage(severity, message, message));
	}
	
	/**
	 * Gets the FacesContext
	 * @return the FacesContext
	 */
	protected FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}
}
