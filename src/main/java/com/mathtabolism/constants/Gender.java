/**
 * 
 */
package com.mathtabolism.constants;

/**
 * An enum for the gender of an Account. This is used in the calculations of TDEE and BMR.
 * 
 * @author mlaursen
 */
public enum Gender {
  MALE, FEMALE;
  
  /**
   * Checks if a given gender is male. If the given gender is null,
   * <tt>false</tt> will be returned.
   * @param gender the gender to check
   * @return true if the gender is male
   */
  public static boolean isMale(Gender gender) {
    return MALE.equals(gender);
  }
  
  /**
   * Checks if a given gender is female.  If the given gender is null,
   * <tt>false</tt> will be returned.
   * @param gender the gender to check
   * @return trie of tje gender is female
   */
  public static boolean isFemale(Gender gender) {
    return FEMALE.equals(gender);
  }
}
