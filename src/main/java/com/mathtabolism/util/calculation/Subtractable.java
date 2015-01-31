package com.mathtabolism.util.calculation;

/**
 * An interface used to subtract two Subtractable objects
 * 
 * @author mlaursen
 */
public interface Subtractable {
  
  /**
   * Attempts to subtract a subtractable object's value from this object's
   * value.
   * 
   * @param subtractable a Subtractable object
   */
  void subtract(Subtractable subtractable);
}
