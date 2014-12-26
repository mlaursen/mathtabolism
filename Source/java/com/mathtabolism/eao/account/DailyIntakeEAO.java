/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.joda.time.DateTime;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.account.AccountEntity;
import com.mathtabolism.entity.account.DailyIntakeEntity;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class DailyIntakeEAO extends BaseEAO<DailyIntakeEntity> {
  protected DailyIntakeEAO() {
    super(DailyIntakeEntity.class);
  }
  
  /**
   * Finds the current week for an account
   * 
   * @param accountEntity
   *          the {@link AccountEntity} to find a DailyIntake week for
   * @param startDate
   *          the startDate as a Calendar to find
   * @return a List of {@link DailyIntakeEntity}
   */
  public List<DailyIntakeEntity> findCurrentWeek(AccountEntity accountEntity, DateTime startDate) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", accountEntity.getId());
    parameters.put("start_date", startDate.toDate());
    parameters.put("end_date", startDate.plusDays(7).toDate());
    
    return findResultList(DailyIntakeEntity.Q_findCurrentWeek, parameters);
  }
  
}
