package com.mathtabolism.constants;

public enum Indicator {
  TRUE, FALSE;
  
  public static boolean isTrue(Indicator indicator) {
    return indicator != null && TRUE.equals(indicator);
  }
  
  public static boolean isFalse(Indicator indicator) {
    return !isTrue(indicator);
  }
  
  public static Indicator fromBoolean(boolean bool) {
    return bool ? TRUE : FALSE;
  }
}
