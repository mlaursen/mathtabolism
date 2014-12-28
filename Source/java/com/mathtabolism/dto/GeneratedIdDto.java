/**
 * 
 */
package com.mathtabolism.dto;

import com.mathtabolism.util.emconverter.Convertable;

/**
 * 
 * @author mlaursen
 */
public interface GeneratedIdDto extends Convertable {

  /**
   * Sets the generated Id
   * @param id the id
   */
  void setId(String id);
  
  /**
   * Gets the generated id
   * @return the id
   */
  String getId();
}
