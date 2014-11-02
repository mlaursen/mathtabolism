/**
 * 
 */
package com.mathtabolism.entity.food;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mathtabolism.entity.BaseGeneratedEntity;
import com.mathtabolism.entity.account.DailyIntake;

/**
 * 
 * @author mlaursen
 */
@Entity
public class DailyIntakeMeal extends BaseGeneratedEntity {
	
	@OneToMany
	@JoinColumn(name="meal_id")
	private List<Meal> meal;
	
	@ManyToOne
	@JoinColumn(name="daily_intake_id")
	private DailyIntake dailyIntake;
	public DailyIntakeMeal() {
	}
	/**
	 * 
	 * @return 
	 */
	public List<Meal> getMeal() {
		return meal;
	}
	/**
	 * 
	 * @param meal 
	 */
	public void setMeal(List<Meal> meal) {
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
