/**
 * 
 */
package com.mathtabolism.test.util.dto.account;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathtabolism.util.dto.account.AccountUtils;

/**
 *
 * @author mlaursen
 */
public class AccountUtilsEqualsStrictlyUTest extends BaseAccountUtilsUTest {

  @Test
  public void testAccountsNull() {
    assertTrue(AccountUtils.equalsStrictly(NULL_ACCOUNT, NULL_ACCOUNT));
  }
  
  @Test
  public void testAccountsFirstNull() {
    assertFalse(AccountUtils.equalsStrictly(NULL_ACCOUNT, EMPTY_ACCOUNT));
  }
  
  @Test
  public void testAccountsSecondNull() {
    assertFalse(AccountUtils.equalsStrictly(EMPTY_ACCOUNT, NULL_ACCOUNT));
  }
  
  @Test
  public void testAccountsEmpty() {
    assertTrue(AccountUtils.equalsStrictly(EMPTY_ACCOUNT, EMPTY_ACCOUNT));
  }
  
  @Test
  public void testAccountsFirstEmptySecondFull() {
    assertFalse(AccountUtils.equalsStrictly(EMPTY_ACCOUNT, fullAccountWithBirthday));
  }
  
  @Test
  public void testAccountsFullBirthday() {
    assertTrue(AccountUtils.equalsStrictly(fullAccountWithBirthday, fullAccountWithBirthday));
  }
  
  @Test
  public void testAccountsFullNoBirthday() {
    assertTrue(AccountUtils.equalsStrictly(fullAccountWithNoBirthday, fullAccountWithNoBirthday));
  }
  
  @Test
  public void testAccountsFullFirstBirthdaySecondNoBirthday() {
    assertFalse(AccountUtils.equalsStrictly(fullAccountWithBirthday, fullAccountWithNoBirthday));
  }
  
  @Test
  public void testAccountsFullFirstNoBirthdaySecondBirthday() {
    assertFalse(AccountUtils.equalsStrictly(fullAccountWithNoBirthday, fullAccountWithBirthday));
  }
  
  @Test
  public void testAccountsFirstFullSecondMissingData() {
    assertFalse(AccountUtils.equalsStrictly(fullAccountWithBirthday, accountMissingData));
  }

  @Test
  public void testAccountModelsNull() {
    assertTrue(AccountUtils.equalsStrictly(NULL_ACCOUNT_MODEL, NULL_ACCOUNT_MODEL));
  }
  
  @Test
  public void testAccountModelsFirstNull() {
    assertFalse(AccountUtils.equalsStrictly(NULL_ACCOUNT_MODEL, EMPTY_ACCOUNT_MODEL));
  }
  
  @Test
  public void testAccountModelsSecondNull() {
    assertFalse(AccountUtils.equalsStrictly(EMPTY_ACCOUNT_MODEL, NULL_ACCOUNT_MODEL));
  }
  
  @Test
  public void testAccountModelsEmpty() {
    assertTrue(AccountUtils.equalsStrictly(EMPTY_ACCOUNT_MODEL, EMPTY_ACCOUNT_MODEL));
  }
  
  @Test
  public void testAccountModelsFirstEmptySecondFull() {
    assertFalse(AccountUtils.equalsStrictly(EMPTY_ACCOUNT_MODEL, fullAccountModelWithBirthday));
  }
  
  @Test
  public void testAccountModelsFullBirthday() {
    assertTrue(AccountUtils.equalsStrictly(fullAccountModelWithBirthday, fullAccountModelWithBirthday));
  }
  
  @Test
  public void testAccountModelsFullNoBirthday() {
    assertTrue(AccountUtils.equalsStrictly(fullAccountModelWithNoBirthday, fullAccountModelWithNoBirthday));
  }
  
  @Test
  public void testAccountModelsFullFirstBirthdaySecondNoBirthday() {
    assertFalse(AccountUtils.equalsStrictly(fullAccountModelWithBirthday, fullAccountModelWithNoBirthday));
  }
  
  @Test
  public void testAccountModelsFullFirstNoBirthdaySecondBirthday() {
    assertFalse(AccountUtils.equalsStrictly(fullAccountModelWithNoBirthday, fullAccountModelWithBirthday));
  }
  
  @Test
  public void testAccountModelsFirstFullSecondMissingData() {
    assertFalse(AccountUtils.equalsStrictly(fullAccountModelWithBirthday, accountModelMissingData));
  }

}
