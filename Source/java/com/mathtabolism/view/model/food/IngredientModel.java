/**
 * 
 */
package com.mathtabolism.view.model.food;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.dto.IngredientDto;
import com.mathtabolism.entity.food.Ingredient;
import com.mathtabolism.util.emconverter.EMConverter;
import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Carbohydrate;
import com.mathtabolism.util.nutrition.Fat;
import com.mathtabolism.util.nutrition.Protein;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.view.model.BaseModel;

/**
 * @author mlaursen
 *
 */
@EMConverter(converter = IngredientDto.class, convertTo = Ingredient.class)
public class IngredientModel extends BaseModel implements IngredientDto {
  
  private String name;
  private String brand;
  private IngredientCategory category;
  private Measurement serving;
  private Measurement alternateServing;
  private Calorie calories;
  private Fat fat;
  private Carbohydrate carbohydrates;
  private Protein protein;
  
  /**
   * Sets the brand name
   * @param brand the brand name
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }
  
  /**
   * Gets the brand name
   * @return the brand name
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Sets the calorie amount from a double. If the calories were initially null,
   * a new Calorie is created.
   * @param amount the amount
   */
  public void setCalories(double amount) {
    if(calories == null) {
      calories = new Calorie();
    }
    calories.setAmount(amount);
  }
  
  /**
   * Sets the fat amount from a double. If the fat was initially null,
   * a new Fat is created.
   * @param amount the amount
   */
  public void setFat(double amount) {
    if(fat == null) {
      fat = new Fat();
    }
    fat.setAmount(amount);
  }
  
  /**
   * Sets the carbohydrate amount from a double. If the carbohydrates were initially null,
   * a new Carbohydrate is created.
   * @param amount the amount
   */
  public void setCarbohydrates(double amount) {
    if(carbohydrates == null) {
      carbohydrates = new Carbohydrate();
    }
    carbohydrates.setAmount(amount);
  }
  
  /**
   * Sets the protein amount from a double. If the protein was initially null,
   * a new Protein is created.
   * @param amount the amount
   */
  public void setProtein(double amount) {
    if(protein == null) {
      protein = new Protein();
    }
    protein.setAmount(amount);
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setCategory(IngredientCategory category) {
    this.category = category;
  }

  @Override
  public IngredientCategory getCategory() {
    return category;
  }

  @Override
  public void setServing(Measurement serving) {
    this.serving = serving;
  }

  @Override
  public Measurement getServing() {
    return serving;
  }

  @Override
  public void setAlternateServing(Measurement alternateServing) {
    this.alternateServing = alternateServing;
  }

  @Override
  public Measurement getAlternateServing() {
    return alternateServing;
  }

  @Override
  public void setCalories(Calorie calories) {
    this.calories = calories;
  }

  @Override
  public Calorie getCalories() {
    return calories;
  }

  @Override
  public void setFat(Fat fat) {
    this.fat = fat;
  }

  @Override
  public Fat getFat() {
    return fat;
  }

  @Override
  public void setCarbohydrates(Carbohydrate carbohydrates) {
    this.carbohydrates = carbohydrates;
  }

  @Override
  public Carbohydrate getCarbohydrates() {
    return carbohydrates;
  }

  @Override
  public void setProtein(Protein protein) {
    this.protein = protein;
  }

  @Override
  public Protein getProtein() {
    return protein;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("name", name).append("serving", serving)
        .append("alternateServing", alternateServing).append("calories", calories).append("fat", fat)
        .append("carbohydrates", carbohydrates).append("protein", protein).toString();
  }
}
