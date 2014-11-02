/**
 * 
 */
package com.mathtabolism.eao.account;

import javax.persistence.Embeddable;

import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Carbohydrate;
import com.mathtabolism.util.nutrition.Fat;
import com.mathtabolism.util.nutrition.Protein;

/**
 * 
 * @author mlaursen
 */
@Embeddable
public class DailyIntakeTotal {
	
	private Calorie calories;
	private Fat fat;
	private Carbohydrate carbohydrates;
	private Protein protein;

	public DailyIntakeTotal() {
	}

	/**
	 * 
	 * @return 
	 */
	public Calorie getCalories() {
		return calories;
	}

	/**
	 * 
	 * @param calories 
	 */
	public void setCalories(Calorie calories) {
		this.calories = calories;
	}

	/**
	 * 
	 * @return 
	 */
	public Fat getFat() {
		return fat;
	}

	/**
	 * 
	 * @param fat 
	 */
	public void setFat(Fat fat) {
		this.fat = fat;
	}

	/**
	 * 
	 * @return 
	 */
	public Carbohydrate getCarbohydrates() {
		return carbohydrates;
	}

	/**
	 * 
	 * @param carbohydrates 
	 */
	public void setCarbohydrates(Carbohydrate carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	/**
	 * 
	 * @return 
	 */
	public Protein getProtein() {
		return protein;
	}

	/**
	 * 
	 * @param protein 
	 */
	public void setProtein(Protein protein) {
		this.protein = protein;
	}
	
	
}
