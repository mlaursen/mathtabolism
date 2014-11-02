/**
 * 
 */
package com.mathtabolism.eao.account;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.DailyIntake;

/**
 * 
 * @author mlaursen
 */
@Stateless
public class DailyIntakeEAO extends BaseEAO<DailyIntake> {
	protected DailyIntakeEAO() {
		super(DailyIntake.class);
	}
	
	/**
	 * Finds the current week for an account
	 * @param account the {@link Account} to find a DailyIntake week for
	 * @param startDate the startDate as a Calendar to find
	 * @return a List of {@link DailyIntake}
	 */
	public List<DailyIntake> findCurrentWeek(Account account, Calendar startDate) {
		TypedQuery<DailyIntake> q = em.createNamedQuery(DailyIntake.Q_findCurrentWeek, DailyIntake.class);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("account_id", account.getId());
		parameters.put("start_date", startDate.getTime());
		
		startDate.add(Calendar.DATE, 7);
		parameters.put("end_date", startDate.getTime());
		bindParameters(q, parameters);
		return q.getResultList();
	}
	
}
