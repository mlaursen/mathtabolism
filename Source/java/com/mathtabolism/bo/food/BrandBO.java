/**
 * 
 */
package com.mathtabolism.bo.food;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mathtabolism.eao.food.BrandEAO;
import com.mathtabolism.entity.food.BrandEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class BrandBO {
  
  @Inject
  private BrandEAO brandEAO;
  
  /**
	 * 
	 */
  public BrandBO() {
  }
  
  /**
   * 
   * @return
   */
  public List<BrandEntity> findAllBrands() {
    return brandEAO.findAllBrands();
  }
  
  /**
   * 
   * @param brandEntity
   * @return
   */
  public BrandEntity create(BrandEntity brandEntity) {
    brandEAO.create(brandEntity);
    return brandEntity;
  }
}
