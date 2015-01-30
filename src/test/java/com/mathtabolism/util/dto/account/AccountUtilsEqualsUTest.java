/**
 * 
 */
package com.mathtabolism.util.dto.account;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathtabolism.util.dto.account.AccountUtils;

/**
 *
 * @author mlaursen
 */
public class AccountUtilsEqualsUTest extends BaseAccountUtilsUTest {

  @Test
  public void testAccountsNull() {
    assertTrue(AccountUtils.equals(NULL_ACCOUNT, NULL_ACCOUNT));
  }
  
  @Test
  public void testAccountsFirstNull() {
    assertFalse(AccountUtils.equals(NULL_ACCOUNT, EMPTY_ACCOUNT));
  }
  
  @Test
  public void testAccountsSecondNull() {
    assertFalse(AccountUtils.equals(EMPTY_ACCOUNT, NULL_ACCOUNT));
  }
  
  @Test
  public void testAccountsEmpty() {
    assertTrue(AccountUtils.equals(EMPTY_ACCOUNT, EMPTY_ACCOUNT));
  }
  
  @Test
  public void testAccountsFirstEmptySecondFull() {
    assertFalse(AccountUtils.equals(EMPTY_ACCOUNT, fullAccountWithBirthday));
  }
  
  @Test
  public void testAccountsFullBirthday() {
    assertTrue(AccountUtils.equals(fullAccountWithBirthday, fullAccountWithBirthday));
  }
  
  @Test
  public void testAccountsFullNoBirthday() {
    assertTrue(AccountUtils.equals(fullAccountWithNoBirthday, fullAccountWithNoBirthday));
  }
  
  @Test
  public void testAccountsFullFirstBirthdaySecondNoBirthday() {
    assertFalse(AccountUtils.equals(fullAccountWithBirthday, fullAccountWithNoBirthday));
  }
  
  @Test
  public void testAccountsFullFirstNoBirthdaySecondBirthday() {
    assertFalse(AccountUtils.equals(fullAccountWithNoBirthday, fullAccountWithBirthday));
  }
  
  @Test
  public void testAccountsFirstFullSecondMissingData() {
    assertFalse(AccountUtils.equals(fullAccountWithBirthday, accountMissingData));
  }

  @Test
  public void testAccountModelsNull() {
    assertTrue(AccountUtils.equals(NULL_ACCOUNT_MODEL, NULL_ACCOUNT_MODEL));
  }
  
  @Test
  public void testAccountModelsFirstNull() {
    assertFalse(AccountUtils.equals(NULL_ACCOUNT_MODEL, EMPTY_ACCOUNT_MODEL));
  }
  
  @Test
  public void testAccountModelsSecondNull() {
    assertFalse(AccountUtils.equals(EMPTY_ACCOUNT_MODEL, NULL_ACCOUNT_MODEL));
  }
  
  @Test
  public void testAccountModelsEmpty() {
    assertTrue(AccountUtils.equals(EMPTY_ACCOUNT_MODEL, EMPTY_ACCOUNT_MODEL));
  }
  
  @Test
  public void testAccountModelsFirstEmptySecondFull() {
    assertFalse(AccountUtils.equals(EMPTY_ACCOUNT_MODEL, fullAccountModelWithBirthday));
  }
  
  @Test
  public void testAccountModelsFullBirthday() {
    assertTrue(AccountUtils.equals(fullAccountModelWithBirthday, fullAccountModelWithBirthday));
  }
  
  @Test
  public void testAccountModelsFullNoBirthday() {
    assertTrue(AccountUtils.equals(fullAccountModelWithNoBirthday, fullAccountModelWithNoBirthday));
  }
  
  @Test
  public void testAccountModelsFullFirstBirthdaySecondNoBirthday() {
    assertFalse(AccountUtils.equals(fullAccountModelWithBirthday, fullAccountModelWithNoBirthday));
  }
  
  @Test
  public void testAccountModelsFullFirstNoBirthdaySecondBirthday() {
    assertFalse(AccountUtils.equals(fullAccountModelWithNoBirthday, fullAccountModelWithBirthday));
  }
  
  @Test
  public void testAccountModelsFirstFullSecondMissingData() {
    assertFalse(AccountUtils.equals(fullAccountModelWithBirthday, accountModelMissingData));
  }

}
