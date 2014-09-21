/**
 * 
 */
package com.mathtabolism.nutrition;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author mlaursen
 */
public abstract class BaseNutrient {
	
	protected double amount;
	
	public BaseNutrient() {}
	public BaseNutrient(double amount) {
		this.amount = amount;
	}
	
	/**
	 * 
	 * @param nutrient
	 */
	public void add(BaseNutrient nutrient) {
		if(this.getClass().equals(nutrient.getClass())) {
			this.amount += ((BaseNutrient) nutrient).amount;
		}
	}
	
	/**
	 * 
	 * @param nutrient
	 */
	public void subtract(BaseNutrient nutrient) {
		if(this.getClass().equals(nutrient.getClass())) {
			this.amount -= ((BaseNutrient) nutrient).amount;
		}
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getAmount() {
		return amount;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object.getClass().equals(getClass())) {
			return amount == ((BaseNutrient) object).amount;
		}
		return false;
	}
	
	/**
	 * @return
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("amount", amount).toString();
	}
	
}
