package com.mathtabolism.test.calculation.intakecalculator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.constants.NutrientType;
import com.mathtabolism.entity.food.Brand;
import com.mathtabolism.entity.food.DailyIntakeMeal;
import com.mathtabolism.entity.food.Ingredient;
import com.mathtabolism.entity.food.Meal;
import com.mathtabolism.entity.food.MealPart;

import static com.mathtabolism.util.calculation.IntakeCalculator.calculateNutrient;

import com.mathtabolism.util.nutrition.BaseNutrient;
import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Carbohydrate;
import com.mathtabolism.util.nutrition.Fat;
import com.mathtabolism.util.nutrition.Protein;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitMeasurement;

public class CalculateNutrientUTest {

	private static Ingredient chickenBreast;
	private static Measurement chickenOzServing;
	private static Measurement chickenGServing;
	private static DailyIntakeMeal meal300gChicken;
	private static DailyIntakeMeal meal4ozChicken;
	private static Meal chicken300g;
	private static Meal chicken4oz;
	
	@BeforeClass
	public static void initConstants() {
		chickenOzServing = new Measurement(UnitMeasurement.OUNCE, 4);
		chickenGServing = new Measurement(UnitMeasurement.GRAM, 112);
		chickenBreast = new Ingredient();
		chickenBreast.setName("Chicken Breast");
		chickenBreast.setBrand(new Brand("Test"));
		chickenBreast.setCategory(IngredientCategory.MEAT);
		chickenBreast.setCalories(140);
		chickenBreast.setFat(2.5);
		chickenBreast.setCarbohydrates(0);
		chickenBreast.setProtein(24);
		chickenBreast.setServing(chickenOzServing);
		chickenBreast.setAlternateServing(chickenGServing);
		
		chicken300g = new Meal();
		chicken300g.setName("300g Chicken Breast");
		
		MealPart mpChicken300g = new MealPart();
		mpChicken300g.setIngredient(chickenBreast);
		mpChicken300g.setMeal(chicken300g);
		mpChicken300g.setServing(new Measurement(UnitMeasurement.GRAM, 300));
		chicken300g.setMealParts(Arrays.asList(mpChicken300g));
		

		chicken4oz = new Meal();
		chicken4oz.setName("4 oz Chicken Breast");
		MealPart mpChicken4oz = new MealPart();
		mpChicken4oz.setIngredient(chickenBreast);
		mpChicken4oz.setMeal(chicken4oz);
		mpChicken4oz.setServing(new Measurement(UnitMeasurement.OUNCE, 4));
		chicken4oz.setMealParts(Arrays.asList(mpChicken4oz));
		
		meal300gChicken = new DailyIntakeMeal();
		meal300gChicken.setMeal(chicken300g);
		meal4ozChicken = new DailyIntakeMeal();
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
