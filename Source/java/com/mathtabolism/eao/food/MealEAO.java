/**
 * 
 */
package com.mathtabolism.eao.food;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.MealEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class MealEAO extends BaseEAO<MealEntity> {
  
  public MealEAO() {
    super(MealEntity.class);
  }
}
