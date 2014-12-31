/**
 * 
 */
package com.mathtabolism.test.util.emconverter;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.mathtabolism.view.model2.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public class ConvertEntitiesToModelUTest extends BaseEMConverterUTest {

  @Test
  public void testNullAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(NULL_ACCOUNT, NULL_ACCOUNT_SETTING);
    AccountModel nullModel    = null;
    assertThat(accountModel, is(nullModel));
  }
  
  @Test
  public void testEmptyAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(EMPTY_ACCOUNT, NULL_ACCOUNT_SETTING);
    AccountModel emptyModel   = new AccountModel();
    assertThat(accountModel, is(emptyModel));
  }
  
  @Test
  public void testNullAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(NULL_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    AccountModel emptyModel   = new AccountModel();
    assertThat(accountModel, is(emptyModel));
  }
  
  @Test
  public void testEmptyAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(EMPTY_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    AccountModel emptyModel   = new AccountModel();
    assertThat(accountModel, is(emptyModel));
  }
  
  @Test
  public void testIdUsernameAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(ID_USERNAME_ACCOUNT, NULL_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(ID_USERNAME_ACCOUNT, NULL_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testFullAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(DEFAULT_FULL_ACCOUNT, NULL_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, NULL_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testNoBirthdayAccountNullAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(NO_BIRTHDAY_ACCOUNT, NULL_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, NULL_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testIdUsernameAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(ID_USERNAME_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(ID_USERNAME_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testFullAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(DEFAULT_FULL_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testNoBirthdayAccountEmptyAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(NO_BIRTHDAY_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, EMPTY_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }

  
  @Test
  public void testIdUsernameAccountNoAgeAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(ID_USERNAME_ACCOUNT, NO_AGE_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(ID_USERNAME_ACCOUNT, NO_AGE_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testFullAccountNoAgeAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(DEFAULT_FULL_ACCOUNT, NO_AGE_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, NO_AGE_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
  
  @Test
  public void testNoBirthdayAccountAgeAccountSettingToModel() {
    AccountModel accountModel = converter.convertEntitiesToModel(NO_BIRTHDAY_ACCOUNT, AGE_ACCOUNT_SETTING);
    AccountModel expected     = extractAccountModelManually(DEFAULT_FULL_ACCOUNT, AGE_ACCOUNT_SETTING);
    assertThatAccountModelIsExpected(accountModel, expected);
  }
}
