package com.mathtabolism.util.string;

import java.util.Random;

public class UsernameGenerator {
  private UsernameGenerator(){}
  
  private static final String[] USERNAMES = { "glutesForTheSlutes", "legday_baby", "nopain_nogain", "ev1lpeng1unofD00M", "f1tness_fella",
    "blob_M4rly", "light_weight", "biceps__only", "2sexy4u", "reps4jesus", "some_clever_Username344", "33repeating_ofcourse",
    "its_clobberin_thyme"};
  
  /**
   * They aren't really good yet.
   * @return a "random" username
   */
  public static String getRandomUsername() {
    return USERNAMES[new Random().nextInt(USERNAMES.length)];
  }
  
}
