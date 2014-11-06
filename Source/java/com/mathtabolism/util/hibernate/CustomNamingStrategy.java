/**
 * 
 */
package com.mathtabolism.util.hibernate;

import org.hibernate.cfg.ImprovedNamingStrategy;

import com.mathtabolism.util.string.StringUtils;

/**
 * Converts
 * 
 * @author mlaursen
 */
public class CustomNamingStrategy extends ImprovedNamingStrategy {
  private static final long serialVersionUID = 1L;
  
  @Override
  public String classToTableName(String className) {
    return StringUtils.toDatabaseFormat(className);
  }
}
