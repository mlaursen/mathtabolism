/**
 * 
 */
package com.mathtabolism.model.converter.account;

import com.mathtabolism.model.converter.BaseEntityModelConverter;
import com.mathtabolism.model.entity.account.DailyIntake;
import com.mathtabolism.model.view.food.DailyIntakeModel;

/**
 * @author mlaursen
 *
 */
public class DailyIntakeConverter extends BaseEntityModelConverter<com.mathtabolism.model.DailyIntake, DailyIntake, DailyIntakeModel> {
  
  public DailyIntakeConverter() {
    super(com.mathtabolism.model.DailyIntake.class, DailyIntake.class, DailyIntakeModel.class);
  }
}
