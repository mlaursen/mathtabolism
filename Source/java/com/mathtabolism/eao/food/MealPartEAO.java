/**
 * 
 */
package com.mathtabolism.eao.food;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.MealPart;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class MealPartEAO extends BaseEAO<MealPart> {
  public MealPartEAO() {
    super(MealPart.class);
  }
}
