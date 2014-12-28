/**
 * 
 */
package com.mathtabolism.model.view;

import com.mathtabolism.model.GeneratedIdContract;

/**
 * @author mlaursen
 *
 */
public abstract class BaseModel implements GeneratedIdContract {

  private String id;
  
  @Override
  public String getId() {
    return id;
  }
  
  @Override
  public void setId(String id) {
    this.id = id;
  }
}
