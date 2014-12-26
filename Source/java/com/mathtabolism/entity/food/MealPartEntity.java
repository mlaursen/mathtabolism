/**
 * 
 */
package com.mathtabolism.entity.food;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.entity.BaseGeneratedEntity;
import com.mathtabolism.util.unit.Measurement;

/**
 * 
 * @author mlaursen
 */
@Entity
public class MealPartEntity extends BaseGeneratedEntity {
  
  @ManyToOne
  @JoinColumn(name = "meal_id")
  private MealEntity mealEntity;
  
  @OneToOne
  @JoinColumn(name = "ingredient_id")
  private IngredientEntity ingredientEntity;
  
  @AttributeOverrides({
      @AttributeOverride(name = "unitMeasurement", column = @Column(name = "ingredient_serving")),
      @AttributeOverride(name = "value", column = @Column(name = "ingredient_size"))
  })
  private Measurement serving;
  
  public MealPartEntity() {
  }
  
  /**
   * 
   * @return
   */
  public MealEntity getMeal() {
    return mealEntity;
  }
  
  /**
   * 
   * @param mealEntity
   */
  public void setMeal(MealEntity mealEntity) {
    this.mealEntity = mealEntity;
  }
  
  /**
   * 
   * @return
   */
  public IngredientEntity getIngredient() {
    return ingredientEntity;
  }
  
  /**
   * 
   * @param ingredientEntity
   */
  public void setIngredient(IngredientEntity ingredientEntity) {
    this.ingredientEntity = ingredientEntity;
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
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("ingredient", ingredientEntity)
        .append("meal", mealEntity).append("serving", serving).toString();
  }
}
