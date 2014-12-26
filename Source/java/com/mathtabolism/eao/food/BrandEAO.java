/**
 * 
 */
package com.mathtabolism.eao.food;

import java.util.List;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.BrandEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class BrandEAO extends BaseEAO<BrandEntity> {
  public BrandEAO() {
    super(BrandEntity.class);
  }
  
  public List<BrandEntity> findAllBrands() {
    return findResultList(BrandEntity.Q_findAllBrands);
  }
}
