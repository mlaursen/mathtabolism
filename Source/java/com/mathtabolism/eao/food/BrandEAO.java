/**
 * 
 */
package com.mathtabolism.eao.food;

import java.util.List;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.Brand;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class BrandEAO extends BaseEAO<Brand> {
  public BrandEAO() {
    super(Brand.class);
  }
  
  public List<Brand> findAllBrands() {
    return findResultList(Brand.Q_findAllBrands);
  }
}
