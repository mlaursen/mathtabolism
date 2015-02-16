/**
 * 
 */
package com.mathtabolism.manager.food;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mathtabolism.eao.food.BrandEAO;
import com.mathtabolism.entity.food.Brand;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class BrandManager {
  
  @Inject
  private BrandEAO brandEAO;
  
  /**
	 * 
	 */
  public BrandManager() {
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
   * @param brand the brand to create or update
   * @return the brand
   */
  public Brand createOrUpdateBrand(Brand brand) {
    if(brandEAO.findById(brand) == null) {
      brand.setId(null);
      brandEAO.create(brand);
    } else {
      brandEAO.update(brand);
    }
    return brand;
  }
}
