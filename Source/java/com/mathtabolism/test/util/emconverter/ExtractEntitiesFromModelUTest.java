/**
 * 
 */
package com.mathtabolism.test.util.emconverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;

/**
 *
 * @author mlaursen
 */
public class ExtractEntitiesFromModelUTest extends BaseEMConverterUTest {
  @Test
  public void testAccountExtractNullModel() {
    Account account = converter.extractEntityFromModel(null);
    Account nullAccount = null;
    assertThat(account, is(nullAccount));
  }
  
  @Test
  public void testAccountExtractEmptyModel() {
    Account account = converter.extractEntityFromModel(EMPTY_MODEL);
    Account expected = new Account();
    assertThatAccountIsExpected(account, expected);
  }
  
  @Test
  public void testAccountExtractPartialModel() {
    Account expected = extractAccountManually(ID_EMAIL_MODEL);
    Account account = converter.extractEntityFromModel(ID_EMAIL_MODEL);
    assertThatAccountIsExpected(account, expected);
  }

  @Test
  public void testAccountExtractFullModel() {
    Account expected = extractAccountManually(DEFAULT_FULL_MODEL);
    
    Account account = converter.extractEntityFromModel(DEFAULT_FULL_MODEL);
    assertThatAccountIsExpected(account, expected);
  }
  
  @Test
  public void testAccountExtractFullAgeModel() {
    Account expected = extractAccountManually(DEFAULT_FULL_AGE_MODEL);
    Account account = converter.extractEntityFromModel(DEFAULT_FULL_AGE_MODEL);
    assertThatAccountIsExpected(account, expected);
  }
  
  @Test
  public void testAccountSettingExtractAccountFromNullModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(null);
    AccountSetting nullSettings = null;
    assertThat(accountSetting, is(nullSettings));
  }
  
  @Test(expected = ClassCastException.class)
  public void testAccountSettingExtractAccountFromEmptyModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(EMPTY_MODEL);
    AccountSetting nullSettings = null;
    assertThat(accountSetting, is(nullSettings));
  }
  
  @Test(expected = ClassCastException.class)
  public void testAccountSettingExtractAccountFromIdEmailModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(ID_EMAIL_MODEL);
    AccountSetting nullSettings = null;
    assertThat(accountSetting, is(nullSettings));
  }
  
  @Test(expected = ClassCastException.class)
  public void testAccountSettingExtractAccountFromFullModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(DEFAULT_FULL_MODEL);
    AccountSetting nullSettings = null;
    assertThat(accountSetting, is(nullSettings));
  }
  
  @Test(expected = ClassCastException.class)
  public void testAccountSettingExtractAccountFromFullAgeModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(DEFAULT_FULL_AGE_MODEL);
    AccountSetting nullSettings = null;
    assertThat(accountSetting, is(nullSettings));
  }
  
  @Test
  public void testAccountSettingExtractNullModel() {
    AccountSetting accountSettings = converter.extractEntityFromModel(null, AccountSetting.class);
    AccountSetting nullSettings = null;
    assertThat(accountSettings, is(nullSettings));
  }
  
  @Test
  public void testAccountSettingExtractEmptyModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(EMPTY_MODEL, AccountSetting.class);
    AccountSetting expected = new AccountSetting();
    assertThatAccountSettingIsExpected(accountSetting, expected);
  }
  
  @Test
  public void testAccountSettingExtractIdEmailModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(ID_EMAIL_MODEL, AccountSetting.class);
    AccountSetting expected = new AccountSetting();
    assertThatAccountSettingIsExpected(accountSetting, expected);
  }
  
  @Test
  public void testAccountSettingExtractFullModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(DEFAULT_FULL_MODEL, AccountSetting.class);
    AccountSetting expected = extractAccountSettingManually(DEFAULT_FULL_MODEL);
    assertThatAccountSettingIsExpected(accountSetting, expected);
  }

  
  @Test
  public void testAccountSettingExtractFullAgeModel() {
    AccountSetting accountSetting = converter.extractEntityFromModel(DEFAULT_FULL_AGE_MODEL, AccountSetting.class);
    AccountSetting expected = extractAccountSettingManually(DEFAULT_FULL_AGE_MODEL);
    assertThatAccountSettingIsExpected(accountSetting, expected);
  }
}
