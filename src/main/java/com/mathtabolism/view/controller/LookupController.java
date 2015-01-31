/**
 * 
 */
package com.mathtabolism.view.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mlaursen
 */
@Named
@RequestScoped
public class LookupController extends BaseController {
  private static final long serialVersionUID = -1824206921719737616L;
  
  public String lookup(String lookupString) {
    String value = getString(lookupString);
    return value == null ? lookupString : value;
  }

  public String lookup(String lookupString, Object... params) {
    String value = getString(lookupString, params);
    return value == null ? lookupString : value;
  }
  
  public String lookup(Enum<?> lookupEnum) {
    return getString(lookupEnum);
  }
  
  public String lookup(Enum<?> lookupEnum, Object... params) {
    return getString(lookupEnum, params);
  }
}
