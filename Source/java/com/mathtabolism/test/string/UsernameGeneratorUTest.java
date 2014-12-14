package com.mathtabolism.test.string;

import org.junit.Test;

import com.mathtabolism.util.string.UsernameGenerator;

public class UsernameGeneratorUTest {

  @Test
  public void testGetRandomUsername() {
    for(int i = 0; i < 30; i++) {
      System.out.println(UsernameGenerator.getRandomUsername());
    }
  }

}
