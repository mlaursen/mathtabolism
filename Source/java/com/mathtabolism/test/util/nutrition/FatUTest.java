package com.mathtabolism.test.util.nutrition;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Fat;

public class FatUTest {

  @Test
  public void test900CaloriesToFat() {
    Calorie c = new Calorie(900);
    Fat f = new Fat();
    f.setFromCalories(c);
    Fat expected = new Fat(100);
    
    assertThat(f, is(expected));
  }
  
  @Test
  public void test900CaloriesToFatWith20Multiplier() {
    Calorie c = new Calorie(900);
    Fat f = new Fat();
    f.setFromCalories(c, 0.2);
    Fat expected = new Fat(20);
    assertThat(f, is(expected));
  }

}
