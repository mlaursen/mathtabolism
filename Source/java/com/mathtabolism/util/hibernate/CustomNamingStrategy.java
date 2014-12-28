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
  private static final String ENTITY = "";
  private static final String EMPTY = "";
  
  @Override
  public String classToTableName(String className) {
    className = className.replace(ENTITY, EMPTY);
    return StringUtils.toDatabaseFormat(className);
  }
}
