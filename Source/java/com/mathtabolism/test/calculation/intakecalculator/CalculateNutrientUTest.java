package com.mathtabolism.test.calculation.intakecalculator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.entity.food.BrandEntity;
import com.mathtabolism.entity.food.DailyIntakeMealEntity;
import com.mathtabolism.entity.food.IngredientEntity;
import com.mathtabolism.entity.food.MealEntity;
import com.mathtabolism.entity.food.MealPartEntity;

import static com.mathtabolism.util.calculation.IntakeCalculator.calculateNutrient;

import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Carbohydrate;
import com.mathtabolism.util.nutrition.Fat;
import com.mathtabolism.util.nutrition.Protein;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitMeasurement;

public class CalculateNutrientUTest {
  
  private static IngredientEntity chickenBreast;
  private static Measurement chickenOzServing;
  private static Measurement chickenGServing;
  private static DailyIntakeMealEntity meal300gChicken;
  private static DailyIntakeMealEntity meal4ozChicken;
  private static MealEntity chicken300g;
  private static MealEntity chicken4oz;
  
  @BeforeClass
  public static void initConstants() {
    chickenOzServing = new Measurement(UnitMeasurement.OUNCE, 4);
    chickenGServing = new Measurement(UnitMeasurement.GRAM, 112);
    chickenBreast = new IngredientEntity();
    chickenBreast.setName("Chicken Breast");
    chickenBreast.setBrand(new BrandEntity("Test"));
    chickenBreast.setCategory(IngredientCategory.MEAT);
    chickenBreast.setCalories(140);
    chickenBreast.setFat(2.5);
    chickenBreast.setCarbohydrates(0);
    chickenBreast.setProtein(24);
    chickenBreast.setServing(chickenOzServing);
    chickenBreast.setAlternateServing(chickenGServing);
    
    chicken300g = new MealEntity();
    chicken300g.setName("300g Chicken Breast");
    
    MealPartEntity mpChicken300g = new MealPartEntity();
    mpChicken300g.setIngredient(chickenBreast);
    mpChicken300g.setMeal(chicken300g);
    mpChicken300g.setServing(new Measurement(UnitMeasurement.GRAM, 300));
    chicken300g.setMealParts(Arrays.asList(mpChicken300g));
    
    chicken4oz = new MealEntity();
    chicken4oz.setName("4 oz Chicken Breast");
    MealPartEntity mpChicken4oz = new MealPartEntity();
    mpChicken4oz.setIngredient(chickenBreast);
    mpChicken4oz.setMeal(chicken4oz);
    mpChicken4oz.setServing(new Measurement(UnitMeasurement.OUNCE, 4));
    chicken4oz.setMealParts(Arrays.asList(mpChicken4oz));
    
    meal300gChicken = new DailyIntakeMealEntity();
    meal300gChicken.setMeal(chicken300g);
    meal4ozChicken = new DailyIntakeMealEntity();
    meal4ozChicken.setMeal(chicken4oz);
  }
  
  @Test
  public void testCalculateCalories300gChicken() {
    BaseNutrient bn = new Calorie(375);
    assertThat(calculateNutrient(meal300gChicken, NutrientType.CALORIE), is(bn));
  }
  
  @Test
  public void testCalculateFat300gChicken() {
    BaseNutrient bn = new Fat(6.696);
    assertThat(calculateNutrient(meal300gChicken, NutrientType.FAT), is(bn));
  }
  
  @Test
  public void testCalculateCarbohydrates300gChicken() {
    BaseNutrient bn = new Carbohydrate(0);
    assertThat(calculateNutrient(meal300gChicken, NutrientType.CARBOHYDRATE), is(bn));
  }
  
  @Test
  public void testCalculateProtein300gChicken() {
    BaseNutrient bn = new Protein(64.285);
    assertThat(calculateNutrient(meal300gChicken, NutrientType.PROTEIN), is(bn));
  }
  
  @Test
  public void testCalculateCalories4ozChicken() {
    BaseNutrient bn = chickenBreast.getCalories();
    assertThat(calculateNutrient(meal4ozChicken, NutrientType.CALORIE), is(bn));
  }
  
  @Test
  public void testCalculateFat4ozChicken() {
    BaseNutrient bn = chickenBreast.getFat();
    assertThat(calculateNutrient(meal4ozChicken, NutrientType.FAT), is(bn));
  }
  
  @Test
  public void testCalculateCarbohydrates4ozChicken() {
    BaseNutrient bn = chickenBreast.getCarbohydrates();
    assertThat(calculateNutrient(meal4ozChicken, NutrientType.CARBOHYDRATE), is(bn));
  }
  
  @Test
  public void testCalculateProtein4ozChicken() {
    BaseNutrient bn = chickenBreast.getProtein();
    assertThat(calculateNutrient(meal4ozChicken, NutrientType.PROTEIN), is(bn));
  }
  
}
