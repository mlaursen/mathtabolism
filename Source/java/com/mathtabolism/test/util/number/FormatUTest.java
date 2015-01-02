/**
 * 
 */
package com.mathtabolism.test.util.number;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import static com.mathtabolism.util.number.MathtabolismNumberUtils.format;

/**
 *
 * @author mlaursen
 */
public class FormatUTest {

  @Test
  public void testNullFormat2() {
    Double nullD = null;
    String expected = null;
    assertThat(format(nullD, 2), is(expected));
  }
  
  @Test
  public void testNullFormat0() {
    Double nullD = null;
    String expected = null;
    assertThat(format(nullD, 0), is(expected));
  }
  
  @Test
  public void testNullFormatDefault() {
    Double nullD = null;
    String expected = null;
    assertThat(format(nullD), is(expected));
  }
  
  @Test
  public void test0Format2() {
    String expected = "0.00";
    assertThat(format(0.0, 2), is(expected));
  }
  
  @Test
  public void test0Format0() {
    String expected = "0";
    assertThat(format(0.0, 0), is(expected));
  }
  
  @Test
  public void test1Format0() {
    Double d = new Double(1);
    String expected = "1";
    assertThat(format(d, 0), is(expected));
  }
  
  @Test
  public void test1Format2() {
    Double d = new Double(1);
    String expected = "1.00";
    assertThat(format(d, 2), is(expected));
  }
  
  @Test
  public void test1FormatDefault() {
    Double d = new Double(1);
    String expected = "1.000";
    assertThat(format(d), is(expected));
  }
  
  @Test
  public void testNegative1Format0() {
    Double d = new Double(-1);
    String expected = "-1";
    assertThat(format(d, 0), is(expected));
  }
  
  @Test
  public void testNegative1FormatDefault() {
    Double d = new Double(-1);
    String expected = "-1.000";
    assertThat(format(d), is(expected));
  }
  
  @Test
  public void testNegative1Format2() {
    Double d = new Double(-1);
    String expected = "-1.00";
    assertThat(format(d, 2), is(expected));
  }

  @Test
  public void test100Format0() {
    Double d = new Double(100);
    String expected = "100";
    assertThat(format(d, 0), is(expected));
  }

  @Test
  public void test100Format2() {
    Double d = new Double(100);
    String expected = "100.00";
    assertThat(format(d, 2), is(expected));
  }

  @Test
  public void test100FormatDefault() {
    Double d = new Double(100);
    String expected = "100.000";
    assertThat(format(d), is(expected));
  }

  @Test
  public void testNegative100Format0() {
    Double d = new Double(-100);
    String expected = "-100";
    assertThat(format(d, 0), is(expected));
  }

  @Test
  public void testNegative100Format2() {
    Double d = new Double(-100);
    String expected = "-100.00";
    assertThat(format(d, 2), is(expected));
  }

  @Test
  public void testNegative100FormatDefault() {
    Double d = new Double(-100);
    String expected = "-100.000";
    assertThat(format(d), is(expected));
  }
  
  @Test(expected = NumberFormatException.class)
  public void test100FormatNegative10() {
    format(new Double(100), -10);
  }
  
  @Test(expected = NumberFormatException.class)
  public void testNegative100FormatNegative10() {
    format(new Double(-100), -10);
  }
  
  @Test
  public void testOneMillionPointEightyEightFormat10() {
    Double d = Math.pow(10, 6) + 0.88;
    String expected = "1,000,000.8800000000";
    assertThat(format(d, 10), is(expected));
  }
}
