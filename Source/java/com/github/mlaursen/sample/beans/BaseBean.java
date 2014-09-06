/**
 * 
 */
package com.github.mlaursen.sample.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author mlaursen
 * 
 */
public abstract class BaseBean implements Serializable {
  protected void sendInfoMessageToUser(String message) {
    FacesContext context = getContext();
    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
  }
  
  protected void sendErrorMessageToUser(String message) {
    FacesContext context = getContext();
    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
  }
  
  protected FacesContext getContext() {
    FacesContext context = FacesContext.getCurrentInstance();
    return context;
  }
}
