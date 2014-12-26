/**
 * 
 */
package com.mathtabolism.converter.account;

import com.mathtabolism.converter.BaseEntityModelConverter;
import com.mathtabolism.entity.account.DailyIntakeEntity;
import com.mathtabolism.emcontract.DailyIntake;
import com.mathtabolism.model.food.DailyIntakeModel;

/**
 * @author mlaursen
 *
 */
public class DailyIntakeConverter extends BaseEntityModelConverter<DailyIntake, DailyIntakeEntity, DailyIntakeModel> {
  
  public DailyIntakeConverter() {
    super(DailyIntake.class, DailyIntakeEntity.class, DailyIntakeModel.class);
  }
}
