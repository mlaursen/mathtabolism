/**
 * 
 */
package com.mathtabolism.test.util.emconverter;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.mathtabolism.view.model.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public class ConvertEntitiesToModelUTest extends BaseEMConverterUTest {

  @Test
  public void testNullAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(NULL_ACCOUNT);
    accountModel = converter.convertEntityToModel(NULL_ACCOUNT_SETTING, accountModel);
    AccountModel nullModel    = null;
    assertThat(accountModel, is(nullModel));
  }
  
  @Test
  public void testEmptyAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT);
    accountModel = converter.convertEntityToModel(NULL_ACCOUNT_SETTING, accountModel);
    AccountModel emptyModel   = new AccountModel();
    assertThatAccountModelIsExpected(accountModel, emptyModel);
  }
  
  @Test
  public void testNullAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(NULL_ACCOUNT);
    accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT_SETTING, accountModel);
    AccountModel emptyModel   = new AccountModel();
    assertThatAccountModelIsExpected(accountModel, emptyModel);
  }
  
  @Test
  public void testEmptyAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT);
    accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT_SETTING, accountModel);
    AccountModel emptyModel   = new AccountModel();
    assertThatAccountModelIsExpected(accountModel, emptyModel);
  }
  
  @Test
  public void testIdUsernameAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(ID_USERNAME_ACCOUNT);
    accountModel = converter.convertEntityToModel(NULL_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(ID_USERNAME_ACCOUNT, NULL_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testFullAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(DEFAULT_FULL_ACCOUNT);
    accountModel = converter.convertEntityToModel(NULL_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, NULL_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testNoBirthdayAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(NO_BIRTHDAY_ACCOUNT);
    accountModel = converter.convertEntityToModel(NULL_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(NO_BIRTHDAY_ACCOUNT, NULL_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testIdUsernameAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(ID_USERNAME_ACCOUNT);
    accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(ID_USERNAME_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testFullAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(DEFAULT_FULL_ACCOUNT);
    accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testNoBirthdayAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(NO_BIRTHDAY_ACCOUNT);
    accountModel = converter.convertEntityToModel(EMPTY_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(NO_BIRTHDAY_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }

  
  @Test
  public void testIdUsernameAccountNoAgeAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(ID_USERNAME_ACCOUNT);
    accountModel = converter.convertEntityToModel(NO_AGE_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(ID_USERNAME_ACCOUNT, NO_AGE_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testFullAccountNoAgeAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(DEFAULT_FULL_ACCOUNT);
    accountModel = converter.convertEntityToModel(NO_AGE_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, NO_AGE_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testNoBirthdayAccountAgeAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntityToModel(NO_BIRTHDAY_ACCOUNT);
    accountModel = converter.convertEntityToModel(AGE_ACCOUNT_SETTING, accountModel);
    AccountModel expected     = extractAccountModelManually(NO_BIRTHDAY_ACCOUNT, AGE_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
}
