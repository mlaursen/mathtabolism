/**
 * 
 */
package com.mathtabolism.util.jsf.converter;

import javax.inject.Named;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author mlaursen
 */
@Named(value = "shortDateConverter")
@ApplicationScoped
public class ShortDateConverter extends DateConverter {
  private static final long serialVersionUID = 7274488707270622885L;

  
  @Override
  public String getDateFormat() {
    return isMetric() ? EUROPEAN_SHORT_FORMAT : AMERICAN_SHORT_FORMAT;
  }
}
