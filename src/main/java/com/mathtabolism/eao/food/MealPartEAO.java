/**
 * 
 */
package com.mathtabolism.eao.food;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseGeneratedEntityEAO;
import com.mathtabolism.entity.food.MealPart;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class MealPartEAO extends BaseGeneratedEntityEAO<MealPart> {
  public MealPartEAO() {
    super(MealPart.class);
  }
}
