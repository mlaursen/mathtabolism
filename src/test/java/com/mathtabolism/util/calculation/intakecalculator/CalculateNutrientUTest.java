package com.mathtabolism.util.calculation.intakecalculator;

import static com.mathtabolism.util.calculation.IntakeCalculator.calculateNutrient;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Carbohydrate;
import com.mathtabolism.util.nutrition.Fat;
import com.mathtabolism.util.nutrition.Protein;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitMeasurement;
import com.mathtabolism.view.model.food.IngredientModel;
import com.mathtabolism.view.model.food.MealModel;
import com.mathtabolism.view.model.food.MealPartModel;

public class CalculateNutrientUTest {
  
  private static IngredientModel chickenBreast;
  private static Measurement chickenOzServing;
  private static Measurement chickenGServing;
  private static MealModel meal300gChicken;
  private static MealModel meal4ozChicken;
  
  @BeforeClass
  public static void initConstants() {
    chickenOzServing = new Measurement(UnitMeasurement.OUNCE, 4);
    chickenGServing = new Measurement(UnitMeasurement.GRAM, 112);
    chickenBreast = new IngredientModel();
    chickenBreast.setName("Chicken Breast");
    chickenBreast.setBrand("Test");
    chickenBreast.setCategory(IngredientCategory.MEAT);
    chickenBreast.setCalories(new Calorie(140));
    chickenBreast.setFat(new Fat(2.5));
    chickenBreast.setCarbohydrates(new Carbohydrate(0));
    chickenBreast.setProtein(new Protein(24));
    chickenBreast.setServing(chickenOzServing);
    chickenBreast.setAlternateServing(chickenGServing);
    
    meal300gChicken = new MealModel();
    meal300gChicken.setName("300g Chicken Breast");
    
    MealPartModel mpChicken300g = new MealPartModel();
    mpChicken300g.setIngredient(chickenBreast);
    mpChicken300g.setServing(new Measurement(UnitMeasurement.GRAM, 300));
    meal300gChicken.setMealParts(Arrays.asList(mpChicken300g));
    
    meal4ozChicken = new MealModel();
    meal4ozChicken.setName("4 oz Chicken Breast");
    MealPartModel mpChicken4oz = new MealPartModel();
    mpChicken4oz.setIngredient(chickenBreast);
    mpChicken4oz.setServing(new Measurement(UnitMeasurement.OUNCE, 4));
    meal4ozChicken.setMealParts(Arrays.asList(mpChicken4oz));
    
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
    BaseNutrient bn = new Protein(64.286);
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
