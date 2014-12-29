/**
 * 
 */
package com.mathtabolism.eao.food;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseGeneratedEntityEAO;
import com.mathtabolism.entity.food.Meal;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class MealEAO extends BaseGeneratedEntityEAO<Meal> {
  
  public MealEAO() {
    super(Meal.class);
  }
}
