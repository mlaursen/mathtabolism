/**
 * 
 */
package com.mathtabolism.bo.food;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mathtabolism.eao.food.BrandEAO;
import com.mathtabolism.model.entity.food.Brand;

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
  public List<Brand> findAllBrands() {
    return brandEAO.findAllBrands();
  }
  
  /**
   * 
   * @param brand
   * @return
   */
  public Brand create(Brand brand) {
    brandEAO.create(brand);
    return brand;
  }
}
