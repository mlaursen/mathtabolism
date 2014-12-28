/**
 * 
 */
package com.mathtabolism.eao.food;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.DailyIntakeMeal;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class DailyIntakeMealEAO extends BaseEAO<DailyIntakeMeal> {
  public DailyIntakeMealEAO() {
    super(DailyIntakeMeal.class);
  }
}
