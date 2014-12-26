/**
 * 
 */
package com.mathtabolism.util.string;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author mlaursen
 */
public class StringUtils {
  private StringUtils() {
  }
  
  public static final int DEFAULT_REPEAT_AMOUNT = 2;
  public static final String DEFAULT_CAMEL_CASE_SPLIT = "_";
  
  /**
   * Checks if a CharSequence is not empty (""), not null and not whitespace only.
   * <p>
   * Convenience method for {@link org.apache.commons.lang3.StringUtils#isNotBlank(CharSequence)}
   * 
   * @param cs
   *          the CharSequence to check, may be null
   * @return true if the CharSequence is not empty (""), not null and not whitespace only.
   */
  public static boolean isNotBlank(CharSequence cs) {
    return org.apache.commons.lang3.StringUtils.isNotBlank(cs);
  }
  
  /**
   * Checks if a CharSequence is not empty ("") and not null.
   * <p>
   * Convenience method for {@link org.apache.commons.lang3.StringUtils#isNotEmpty(CharSequence)}
   * 
   * @param cs
   *          the CharSequence to check, may be null
   * @return <tt>true</tt> if the CharSequence is not empty ("") and not null.
   */
  public static boolean isNotEmpty(CharSequence cs) {
    return org.apache.commons.lang3.StringUtils.isNotEmpty(cs);
  }
  
  /**
   * Checks if a CharSequence is whitespace, empty ("") or null.
   * <p>
   * Convenience method for {@link org.apache.commons.lang3.StringUtils#isBlank(CharSequence)}
   * 
   * @param cs
   *          the CharSequence to check, may be null
   * @return <tt>true</tt> if the CharSequence is whitespace, empty ("") or null.
   */
  public static boolean isBlank(CharSequence cs) {
    return org.apache.commons.lang3.StringUtils.isBlank(cs);
  }
  
  /**
   * Checks if a CharSequence is empty ("") or null.
   * <p>
   * Convenience method for {@link org.apache.commons.lang3.StringUtils#isEmpty(CharSequence)}
   * 
   * @param cs
   *          the CharSequence to check, may be null
   * @return <tt>true</tt> if the CharSequence is empty or null
   */
  public static boolean isEmpty(CharSequence cs) {
    return org.apache.commons.lang3.StringUtils.isEmpty(cs);
  }
  
  /**
   * Formats a database attribute (table name, column, sequence, etc) from camel case to all lowercase and underscores
   * 
   * @param attribute the attribute to format
   * @return a String formated as <tt>words_with_more_words</tt>
   */
  public static String toDatabaseFormat(String attribute) {
    String s = "";
    String[] splitOnUppers = attribute.split("(?=\\p{Upper})");
    boolean first = true;
    for(String split : splitOnUppers) {
      if(!first) {
        s += "_";
      }
      s += split.toLowerCase();
      first = false;
    }
    return s;
  }
  
  /**
   * Checks if a CharSequence contains a character more than {@value #DEFAULT_REPEAT_AMOUNT}. This does not count spaces
   * as a repeatable character
   * 
   * @param cs
   *          the CharSequence to check
   * @return true if a Character repeats at least once
   */
  public static boolean repeatsACharacter(CharSequence cs) {
    return repeatsACharacter(cs, DEFAULT_REPEAT_AMOUNT, false);
  }
  
  /**
   * Checks if a CharSequence contains a character for a given number of times. This does not count spaces as a
   * repeatable character
   * 
   * @param cs
   *          the CharSequence to check
   * @param numberOfTimes
   *          the number of times a character must exist in the CharSequence
   * @return true if a non-space Character exists in the CharSequence for at least the given number of times
   */
  public static boolean repeatsACharacter(CharSequence cs, int numberOfTimes) {
    return repeatsACharacter(cs, numberOfTimes, false);
  }
  
  /**
   * Checks if a CharSequence contains a character for a given number of times.
   * 
   * @param cs
   *          the CharSequence to check
   * @param allowSpace
   *          boolean if a space counts as a repeatable Character
   * @return true if a Character exists in the CharSequence for at least the given number of times
   */
  public static boolean repeatsACharacter(CharSequence cs, boolean allowSpace) {
    return repeatsACharacter(cs, DEFAULT_REPEAT_AMOUNT, allowSpace);
  }
  
  /**
   * Checks if a CharSequence contains a character for a given number of times.
   * 
   * @param cs
   *          the CharSequence to check
   * @param numberOfTimes
   *          a number of times a character has to repeat to return true
   * @param allowSpace
   *          boolean if a space counts as a repeatable Character
   * @return true if a Character exists in the CharSequence for at least the given number of times
   */
  public static boolean repeatsACharacter(CharSequence cs, int numberOfTimes, boolean allowSpace) {
    if(!allowSpace && StringUtils.isBlank(cs)) {
      return false;
    }
    
    if(cs != null) {
      for(int i = 0; i < cs.length(); i++) {
        char c = cs.charAt(i);
        if(!allowSpace && c == ' ')
          continue;
        
        int letterCount = 1;
        for(int x = i + 1; x < cs.length(); x++) {
          if(c == cs.charAt(x) && ++letterCount == numberOfTimes) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  /**
   * 
   * @param toMatch
   * @param matchesAny
   * @return
   */
  public static boolean equalsAny(String toMatch, List<String> matchesAny) {
    return matchesAny != null && matchesAny.contains(toMatch);
  }
  
  /**
   * 
   * @param toMatch
   * @param matchesAny
   * @return
   */
  public static boolean equalsAny(String toMatch, String... matchesAny) {
    return equalsAny(toMatch, Arrays.asList(matchesAny));
  }
  
  /**
   * Checks if a given String starts with any of the given Strings
   * 
   * @param toMatch
   *          a String to check for matches
   * @param startsWithAny
   * @return true if the String starts with any of the given Strings
   */
  public static boolean startsWithAny(String toMatch, String... startsWithAny) {
    for(String s : startsWithAny) {
      return toMatch.startsWith(s);
    }
    return false;
  }
  
  /**
   * 
   * @param s
   * @return
   */
  public static String toCamelCase(String s) {
    return toCamelCase(s, DEFAULT_CAMEL_CASE_SPLIT, false);
  }
  
  /**
   * 
   * @param s
   * @param capitalizeFirst
   * @return
   */
  public static String toCamelCase(String s, boolean capitalizeFirst) {
    return toCamelCase(s, DEFAULT_CAMEL_CASE_SPLIT, capitalizeFirst);
  }
  
  /**
   * 
   * @param s
   * @param regex
   * @return
   */
  public static String toCamelCase(String s, String regex) {
    return toCamelCase(s, regex, false);
  }
  
  /**
   * 
   * @param s
   *          the String to format
   * @param regex
   *          the regex to split the String on for camel case
   * @param capitalizeFirst
   *          boolean if the first letter should be capitalized
   * @return a String in camel case
   */
  public static String toCamelCase(String s, String regex, boolean capitalizeFirst) {
    String[] split = s.split(regex);
    StringBuilder sb = new StringBuilder();
    for(String current : split) {
      String first = current.substring(0, 1);
      String last = current.substring(1).toLowerCase();
      sb.append(capitalizeFirst ? first.toUpperCase() : first.toLowerCase());
      sb.append(last);
      capitalizeFirst = true;
    }
    return sb.toString();
  }
  
  public static boolean isBetween(String toCheck, int min, int max) {
    return StringUtils.isNotEmpty(toCheck) && toCheck.length() >= min && toCheck.length() <= max;
  }
}
