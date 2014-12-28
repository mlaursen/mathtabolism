/**
 * 
 */
package com.mathtabolism.model.entity.food;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.model.entity.BaseGeneratedEntity;
import com.mathtabolism.util.unit.Measurement;

/**
 * 
 * @author mlaursen
 */
@Entity
public class MealPart extends BaseGeneratedEntity {
  
  @ManyToOne
  @JoinColumn(name = "meal_id")
  private Meal meal;
  
  @OneToOne
  @JoinColumn(name = "ingredient_id")
  private Ingredient ingredient;
  
  @AttributeOverrides({
      @AttributeOverride(name = "unitMeasurement", column = @Column(name = "ingredient_serving")),
      @AttributeOverride(name = "value", column = @Column(name = "ingredient_size"))
  })
  private Measurement serving;
  
  public MealPart() {
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
  public Ingredient getIngredient() {
    return ingredient;
  }
  
  /**
   * 
   * @param ingredient
   */
  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }
  
  /**
   * 
   * @return
   */
  public Measurement getServing() {
    return serving;
  }
  
  /**
   * 
   * @param serving
   */
  public void setServing(Measurement serving) {
    this.serving = serving;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("ingredient", ingredient)
        .append("meal", meal).append("serving", serving).toString();
  }
}
