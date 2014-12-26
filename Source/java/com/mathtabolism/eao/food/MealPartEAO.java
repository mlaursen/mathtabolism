/**
 * 
 */
package com.mathtabolism.eao.food;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.MealPartEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class MealPartEAO extends BaseEAO<MealPartEntity> {
  public MealPartEAO() {
    super(MealPartEntity.class);
  }
}
