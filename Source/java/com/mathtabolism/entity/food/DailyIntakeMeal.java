/**
 * 
 */
package com.mathtabolism.entity.food;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.mathtabolism.entity.BaseGeneratedEntity;
import com.mathtabolism.entity.account.DailyIntake;

/**
 * 
 * @author mlaursen
 */
@Entity
public class DailyIntakeMeal extends BaseGeneratedEntity {
	
	@OneToOne
	@JoinColumn(name="meal_id")
	private Meal meal;
	
	@ManyToOne
	@JoinColumn(name="daily_intake_id")
	private DailyIntake dailyIntake;
	public DailyIntakeMeal() {
	}
	/**
	 * 
	 * @return 
	 */
	public Meal getMeal() {
		return meal;
	}
	/**
	 * 
	 * @param meal 
	 */
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	/**
	 * 
	 * @return 
	 */
	public DailyIntake getDailyIntake() {
		return dailyIntake;
	}
	/**
	 * 
	 * @param dailyIntake 
	 */
	public void setDailyIntake(DailyIntake dailyIntake) {
		this.dailyIntake = dailyIntake;
	}
	
}
