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

/**
 * 
 * @author mlaursen
 */
public abstract class BaseBean implements Serializable {
	private static final long serialVersionUID = -6686889317225940807L;
	
	
	/**
	 * 
	 * @param lookupString
	 */
	protected void displayInfoMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_INFO, lookupString);
	}
	
	/**
	 * 
	 * @param lookupString
	 */
	protected void displayWarnMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_WARN, lookupString);
	}
	
	/**
	 * 
	 * @param lookupString
	 */
	protected void displayErrorMessage(String lookupString) {
		displayMessage(FacesMessage.SEVERITY_ERROR, lookupString);
	}
	
	/**
	 * 
	 * @param severity
	 * @param lookupString
	 */
	protected void displayMessage(Severity severity, String lookupString) {
		FacesContext context = getContext();
		String message = getString(lookupString);
		context.addMessage(null, new FacesMessage(severity, message, message));
	}
	
	protected String getString(String lookupString) {
		return ResourceBundle.getBundle("messages", getContext().getViewRoot().getLocale()).getString(lookupString);
	}
	
	/**
	 * 
	 * @return
	 */
	protected FacesContext getContext() {
		return FacesContext.getCurrentInstance();
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
			items[value.ordinal()] = new SelectItem(value, getString(value.name()));
		}
		return items;
	}
}
