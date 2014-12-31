/**
 * 
 */
package com.mathtabolism.eao.food;

import java.util.List;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseGeneratedEntityEAO;
import com.mathtabolism.entity.food.Brand;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class BrandEAO extends BaseGeneratedEntityEAO<Brand> {
  public BrandEAO() {
    super(Brand.class);
  }
  
  public List<Brand> findAllBrands() {
    return findResultList(Brand.Q_findAllBrands);
  }
}
