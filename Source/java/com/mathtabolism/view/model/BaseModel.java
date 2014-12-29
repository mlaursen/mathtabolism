/**
 * 
 */
package com.mathtabolism.view.model;

import com.mathtabolism.dto.GeneratedIdDto;

/**
 * 
 * @author mlaursen
 */
public abstract class BaseModel implements GeneratedIdDto {

  protected String id;
  
  @Override
  public String getId() {
    return id;
  }
  
  @Override
  public void setId(String id) {
    this.id = id;
  }
}
