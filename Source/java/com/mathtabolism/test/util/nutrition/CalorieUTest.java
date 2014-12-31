/**
 * 
 */
package com.mathtabolism.test.util.nutrition;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Fat;

/**
 * 
 * @author mlaursen
 */
public class CalorieUTest {
  
  @Test
  public void testAddOneToZero() {
    Calorie zeroCalories = new Calorie(0);
    Calorie oneCalorie = new Calorie(1);
    zeroCalories.add(oneCalorie);
    assertThat(zeroCalories, is(new Calorie(1)));
  }
  
  @Test
  public void testAddZeroToZero() {
    Calorie zeroCalories = new Calorie(0);
    zeroCalories.add(new Calorie(0));
    assertThat(zeroCalories, is(new Calorie(0)));
  }
  
  @Test
  public void testAddFatToCalorie() {
    Calorie c = new Calorie(20);
    Fat f = new Fat(20);
    c.add(f);
    assertThat(c, not(new Calorie(40)));
    assertThat(c, is(new Calorie(20)));
  }
  
  @Test
  public void testAddCalories() {
    Calorie twoHundred = new Calorie(200);
    Calorie oneThousand = new Calorie(1000);
    twoHundred.add(oneThousand);
    assertThat(twoHundred, is(new Calorie(1200)));
  }
  
  @Test
  public void testSubtractOneAndZero() {
    Calorie zero = new Calorie(0);
    Calorie one = new Calorie(1);
    one.subtract(zero);
    assertThat(one, is(new Calorie(1)));
  }
  
  @Test
  public void testSubtractZero() {
    Calorie zero1 = new Calorie(0);
    Calorie zero2 = new Calorie(0);
    zero1.subtract(zero2);
    assertThat(zero1, is(new Calorie(0)));
  }
  
  @Test
  public void testSubtractFat() {
    Calorie c = new Calorie(150);
    Fat f = new Fat(20);
    c.subtract(f);
    assertThat(c, is(new Calorie(150)));
  }
  
}
