/**
 * 
 */
package com.mathtabolism.eao.food;

import javax.ejb.Stateless;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.food.DailyIntakeMealEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class DailyIntakeMealEAO extends BaseEAO<DailyIntakeMealEntity> {
  public DailyIntakeMealEAO() {
    super(DailyIntakeMealEntity.class);
  }
}
