/**
 * 
 */
package com.mathtabolism.eao.food;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.Meal;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class MealEAO extends BaseEAO<Meal> {
  
  public MealEAO() {
    super(Meal.class);
  }
}
