/**
 * 
 */
package com.mathtabolism.util.number;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static com.mathtabolism.util.number.MathtabolismNumberUtils.aboutEqual;

import org.junit.Test;


/**
 *
 * @author mlaursen
 */
public class AboutEqualsUTest {

  @Test
  public void testTwoDecimalPlaces() {
    assertTrue(aboutEqual(8.33, 8.334, 2));
  }
  
  @Test
  public void testDefault() {
    assertFalse(aboutEqual(8.33, 8.334));
    assertTrue(aboutEqual(8.3343, 8.3342));
  }

}
