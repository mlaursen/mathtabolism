/**
 * 
 */
package com.mathtabolism.util.emconverter;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.mathtabolism.view.model.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public class ConvertEntityToModelUTest extends BaseEMConverterUTest {

  @Test
  public void testConvertNullAccountToModel() {
    AccountModel accountModel = converter.convertEntityToModel(NULL_ACCOUNT);
    AccountModel expected = null;
    assertThat(accountModel, is(expected));
  }

  @Test
  public void testConvertEmptyAccountToModel() {
    AccountModel accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT);
    AccountModel expected = new AccountModel();
    assertThatAccountModelIsExpected(accountModel, expected);
  }

  @Test
  public void testConvertIdEmailAccountToModel() {
    AccountModel accountModel = converter.convertEntityToModel(ID_USERNAME_ACCOUNT);
    AccountModel expected = extractAccountModelManually(ID_USERNAME_ACCOUNT);
    assertThatAccountModelIsExpected(accountModel, expected);
  }

  @Test
  public void testConvertFullAccountToModel() {
    AccountModel accountModel = converter.convertEntityToModel(DEFAULT_FULL_ACCOUNT);
    AccountModel expected = extractAccountModelManually(DEFAULT_FULL_ACCOUNT);
    assertThatAccountModelIsExpected(accountModel, expected);
  }

  @Test
  public void testConvertNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(NULL_ACCOUNT_SETTING);
    AccountModel expected = null;
    assertThat(accountModel, is(expected));
  }

  @Test
  public void testConvertEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT_SETTING);
    AccountModel expected = extractAccountModelManually(EMPTY_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }

  @Test
  public void testConvertAgeAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(AGE_ACCOUNT_SETTING);
    AccountModel expected = extractAccountModelManually(AGE_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }

  @Test
  public void testConvertNoAgeAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(NO_AGE_ACCOUNT_SETTING);
    AccountModel expected = extractAccountModelManually(NO_AGE_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
}
