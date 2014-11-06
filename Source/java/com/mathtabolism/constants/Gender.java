/**
 * 
 */
package com.mathtabolism.constants;

/**
 * 
 * 
 * @author mlaursen
 */
public enum Gender {
  MALE, FEMALE;
  
  /**
   * 
   * @param gender
   * @return
   */
  public static boolean isMale(Gender gender) {
    return MALE.equals(gender);
  }
}
