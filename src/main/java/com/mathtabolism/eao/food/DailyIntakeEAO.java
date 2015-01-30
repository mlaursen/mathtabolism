/**
 * 
 */
package com.mathtabolism.eao.food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.joda.time.DateTime;

import com.mathtabolism.eao.BaseGeneratedEntityEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.DailyIntake;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class DailyIntakeEAO extends BaseGeneratedEntityEAO<DailyIntake> {
  protected DailyIntakeEAO() {
    super(DailyIntake.class);
  }
  
  /**
   * Finds the current week for an account
   * 
   * @param account the {@link Account} to find a DailyIntake week for
   * @param startDate the startDate as a Calendar to find
   * @return a List of {@link DailyIntake}
   */
  public List<DailyIntake> findCurrentWeek(Account account, DateTime startDate) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("account_id", account.getId());
    parameters.put("start_date", startDate.toDate());
    parameters.put("end_date", startDate.plusDays(7).toDate());
    
    return findResultList(DailyIntake.Q_findCurrentWeek, parameters);
  }
  
}
