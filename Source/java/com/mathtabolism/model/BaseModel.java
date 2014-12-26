/**
 * 
 */
package com.mathtabolism.model;

import com.mathtabolism.emcontract.GeneratedIdContract;

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
