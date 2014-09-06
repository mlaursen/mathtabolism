/**
 * 
 */
package com.github.mlaursen.sample.beans.action;

/**
 * @author mlaursen
 *
 */
public enum DogActions {
  CREATE_DOG,
  REMOVE_DOG,
  UPDATE_DOG,
  LIST_ALL_DOGS,
  NONE;
  
  public String getAction() {
    String[] splitLowered = name().toLowerCase().split("_");
    String s = splitLowered[0];
    for(int i = 1; i < splitLowered.length; i++) {
      s += splitLowered[i].substring(0, 1).toUpperCase() + splitLowered[i].substring(1);
    }
    return s;
  }
}
