/**
 * 
 */
package com.mathtabolism.test.util.emconverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.util.date.DateUtils;
import com.mathtabolism.util.emconverter2.EntityModelConverter;
import com.mathtabolism.util.unit.UnitSystem;
import com.mathtabolism.view.model2.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public abstract class BaseEMConverterUTest {
  protected static EntityModelConverter converter;
  protected static final AccountModel EMPTY_MODEL = new AccountModel();
  protected static final Account NULL_ACCOUNT = null;
  protected static final Account EMPTY_ACCOUNT = new Account();
  protected static final AccountSetting NULL_ACCOUNT_SETTING = null;
  protected static final AccountSetting EMPTY_ACCOUNT_SETTING = new AccountSetting();
  
  protected static AccountModel ID_EMAIL_MODEL;
  protected static AccountModel DEFAULT_FULL_MODEL;
  protected static AccountModel DEFAULT_FULL_AGE_MODEL;
  protected static Account ID_USERNAME_ACCOUNT;
  protected static Account DEFAULT_FULL_ACCOUNT;
  protected static AccountSetting NO_AGE_ACCOUNT_SETTING;
  protected static AccountSetting AGE_ACCOUNT_SETTING;
  
  
  protected static final String DEFAULT_ACCOUNT_ID = "MATH000000";
  protected static final String DEFAULT_ALTERNATE_ID = "MATH003A5F";
  protected static final String DEFAULT_USERNAME = "glutes_for_slutes";
  protected static final String DEFAULT_EMAIL = "bob@bobby.flay";
  protected static final Gender DEFAULT_GENDER = Gender.MALE;
  protected static final String DEFAULT_PASSWORD = "neverGonnaGiveYouUp, neverGonnaLetYouDown";
  protected static final ActivityMultiplier DEFAULT_ACTIVITY_MULTIPLIER = ActivityMultiplier.LIGHTLY_ACTIVE;
  protected static final Double DEFAULT_HEIGHT = new Double(71);
  protected static final Date DEFAULT_ACTIVE_SINCE = DateUtils.createDate(10, 1, 2014);
  protected static final Date DEFAULT_LAST_LOGIN = DateUtils.createDate(12, 30, 2014);
  protected static final Date DEFAULT_BIRTHDAY   = DateUtils.createDate(1, 1, 1950);
  protected static final Integer DEFAULT_AGE = 23;
  protected static final Weekday DEFAULT_RECALCULATION_DAY = Weekday.SUNDAY;
  protected static final UnitSystem DEFAULT_UNIT_SYSTEM = UnitSystem.IMPERIAL;
  protected static final TDEEFormula DEFAULT_TDEE_FORMULA = TDEEFormula.HARRIS_BENEDICT;
  protected static final AccountRole DEFAULT_ROLE = AccountRole.USER;
  protected static final Indicator DEFAULT_USE_AGE = Indicator.FALSE;
  @BeforeClass
  public static void init() {
    converter = new EntityModelConverter();
    ID_EMAIL_MODEL = new AccountModel();
    ID_EMAIL_MODEL.setAccountId(DEFAULT_ACCOUNT_ID);
    ID_EMAIL_MODEL.setEmail(DEFAULT_EMAIL);
    
    DEFAULT_FULL_MODEL = new AccountModel();
    DEFAULT_FULL_MODEL.setAccountId(DEFAULT_ACCOUNT_ID);
    DEFAULT_FULL_MODEL.setAccountSettingId(DEFAULT_ALTERNATE_ID);
    DEFAULT_FULL_MODEL.setActiveSince(DEFAULT_ACTIVE_SINCE);
    DEFAULT_FULL_MODEL.setActivityMultiplier(DEFAULT_ACTIVITY_MULTIPLIER);
    DEFAULT_FULL_MODEL.setBirthday(DEFAULT_BIRTHDAY);
    DEFAULT_FULL_MODEL.setEmail(DEFAULT_EMAIL);
    DEFAULT_FULL_MODEL.setGender(DEFAULT_GENDER);
    DEFAULT_FULL_MODEL.setHeight(DEFAULT_HEIGHT);
    DEFAULT_FULL_MODEL.setLastLogin(DEFAULT_LAST_LOGIN);
    DEFAULT_FULL_MODEL.setRecalculationDay(DEFAULT_RECALCULATION_DAY);
    DEFAULT_FULL_MODEL.setRole(DEFAULT_ROLE);
    DEFAULT_FULL_MODEL.setTdeeFormula(DEFAULT_TDEE_FORMULA);
    DEFAULT_FULL_MODEL.setUnitSystem(DEFAULT_UNIT_SYSTEM);
    DEFAULT_FULL_MODEL.setUseAge(DEFAULT_USE_AGE);
    DEFAULT_FULL_MODEL.setUsername(DEFAULT_USERNAME);

    DEFAULT_FULL_AGE_MODEL = new AccountModel();
    DEFAULT_FULL_AGE_MODEL.setAccountId(DEFAULT_ACCOUNT_ID);
    DEFAULT_FULL_AGE_MODEL.setAccountSettingId(DEFAULT_ALTERNATE_ID);
    DEFAULT_FULL_AGE_MODEL.setActiveSince(DEFAULT_ACTIVE_SINCE);
    DEFAULT_FULL_AGE_MODEL.setActivityMultiplier(DEFAULT_ACTIVITY_MULTIPLIER);
    DEFAULT_FULL_AGE_MODEL.setAge(DEFAULT_AGE);
    DEFAULT_FULL_AGE_MODEL.setEmail(DEFAULT_EMAIL);
    DEFAULT_FULL_AGE_MODEL.setGender(DEFAULT_GENDER);
    DEFAULT_FULL_AGE_MODEL.setHeight(DEFAULT_HEIGHT);
    DEFAULT_FULL_AGE_MODEL.setLastLogin(DEFAULT_LAST_LOGIN);
    DEFAULT_FULL_AGE_MODEL.setRecalculationDay(DEFAULT_RECALCULATION_DAY);
    DEFAULT_FULL_AGE_MODEL.setRole(DEFAULT_ROLE);
    DEFAULT_FULL_AGE_MODEL.setTdeeFormula(DEFAULT_TDEE_FORMULA);
    DEFAULT_FULL_AGE_MODEL.setUnitSystem(DEFAULT_UNIT_SYSTEM);
    DEFAULT_FULL_AGE_MODEL.setUseAge(Indicator.TRUE);
    DEFAULT_FULL_AGE_MODEL.setUsername(DEFAULT_USERNAME);
    
    ID_USERNAME_ACCOUNT = new Account();
    ID_USERNAME_ACCOUNT.setId(DEFAULT_ACCOUNT_ID);
    ID_USERNAME_ACCOUNT.setUsername(DEFAULT_USERNAME);
    
    DEFAULT_FULL_ACCOUNT = new Account();
    DEFAULT_FULL_ACCOUNT.setActiveSince(DEFAULT_ACTIVE_SINCE);
    DEFAULT_FULL_ACCOUNT.setBirthday(DEFAULT_BIRTHDAY);
    DEFAULT_FULL_ACCOUNT.setEmail(DEFAULT_EMAIL);
    DEFAULT_FULL_ACCOUNT.setGender(DEFAULT_GENDER);
    DEFAULT_FULL_ACCOUNT.setId(DEFAULT_ACCOUNT_ID);
    DEFAULT_FULL_ACCOUNT.setLastLogin(DEFAULT_LAST_LOGIN);
    DEFAULT_FULL_ACCOUNT.setPassword(DEFAULT_PASSWORD);
    DEFAULT_FULL_ACCOUNT.setRole(DEFAULT_ROLE);
    DEFAULT_FULL_ACCOUNT.setUsername(DEFAULT_USERNAME);
    
    NO_AGE_ACCOUNT_SETTING = new AccountSetting();
    NO_AGE_ACCOUNT_SETTING.setActivityMultiplier(DEFAULT_ACTIVITY_MULTIPLIER);
    NO_AGE_ACCOUNT_SETTING.setHeight(DEFAULT_HEIGHT);
    NO_AGE_ACCOUNT_SETTING.setId(DEFAULT_ALTERNATE_ID);
    NO_AGE_ACCOUNT_SETTING.setRecalculationDay(DEFAULT_RECALCULATION_DAY);
    NO_AGE_ACCOUNT_SETTING.setTdeeFormula(DEFAULT_TDEE_FORMULA);
    NO_AGE_ACCOUNT_SETTING.setUnitSystem(DEFAULT_UNIT_SYSTEM);
    NO_AGE_ACCOUNT_SETTING.setUseAge(DEFAULT_USE_AGE);

    
    AGE_ACCOUNT_SETTING = new AccountSetting();
    AGE_ACCOUNT_SETTING.setActivityMultiplier(DEFAULT_ACTIVITY_MULTIPLIER);
    AGE_ACCOUNT_SETTING.setHeight(DEFAULT_HEIGHT);
    AGE_ACCOUNT_SETTING.setId(DEFAULT_ALTERNATE_ID);
    AGE_ACCOUNT_SETTING.setRecalculationDay(DEFAULT_RECALCULATION_DAY);
    AGE_ACCOUNT_SETTING.setTdeeFormula(DEFAULT_TDEE_FORMULA);
    AGE_ACCOUNT_SETTING.setUnitSystem(DEFAULT_UNIT_SYSTEM);
    AGE_ACCOUNT_SETTING.setUseAge(DEFAULT_USE_AGE);
  }
  
  protected Account extractAccountManually(AccountModel accountModel) {
    Account account = new Account();
    account.setId(accountModel.getAccountId());
    account.setActiveSince(accountModel.getActiveSince());
    account.setBirthday(accountModel.getBirthday());
    account.setEmail(accountModel.getEmail());
    account.setGender(accountModel.getGender());
    account.setLastLogin(accountModel.getLastLogin());
    account.setPassword(accountModel.getPassword());
    account.setRole(accountModel.getRole());
    account.setUsername(accountModel.getUsername());
    return account;
  }
  
  protected void assertThatAccountIsExpected(Account account, Account expected) {
    assertThat(account.getActiveSince(), is(expected.getActiveSince()));
    assertTrue(DateUtils.isSameDate(account.getBirthday(), expected.getBirthday()));
    assertThat(account.getEmail(), is(expected.getEmail()));
    assertThat(account.getGender(), is(expected.getGender()));
    assertThat(account.getId(), is(expected.getId()));
    assertTrue(DateUtils.isSameDate(account.getLastLogin(), expected.getLastLogin()));
    assertThat(account.getPassword(), is(expected.getPassword()));
    assertThat(account.getRole(), is(expected.getRole()));
    assertThat(account.getUsername(), is(expected.getUsername()));
  }
  
  protected AccountSetting extractAccountSettingManually(AccountModel accountModel) {
    AccountSetting accountSettings = new AccountSetting();
    accountSettings.setId(accountModel.getAccountSettingId());
    accountSettings.setActivityMultiplier(accountModel.getActivityMultiplier());
    accountSettings.setAge(accountModel.getAge());
    accountSettings.setHeight(accountModel.getHeight());
    accountSettings.setRecalculationDay(accountModel.getRecalculationDay());
    accountSettings.setTdeeFormula(accountModel.getTdeeFormula());
    accountSettings.setUnitSystem(accountModel.getUnitSystem());
    accountSettings.setUseAge(accountModel.getUseAge());
    return accountSettings;
  }
  
  protected void assertThatAccountSettingIsExpected(AccountSetting accountSettings, AccountSetting expected) {
    assertThat(accountSettings.getActivityMultiplier(), is(expected.getActivityMultiplier()));
    assertThat(accountSettings.getAge(), is(expected.getAge()));
    assertTrue(DateUtils.isSameDate(accountSettings.getDateChanged(), expected.getDateChanged()));
    assertThat(accountSettings.getHeight(), is(expected.getHeight()));
    assertThat(accountSettings.getId(), is(expected.getId()));
    assertThat(accountSettings.getRecalculationDay(), is(expected.getRecalculationDay()));
    assertThat(accountSettings.getTdeeFormula(), is(expected.getTdeeFormula()));
    assertThat(accountSettings.getUnitSystem(), is(expected.getUnitSystem()));
    assertThat(accountSettings.getUseAge(), is(expected.getUseAge()));
  }
  
  protected AccountModel extractAccountModelManually(Account account) {
    AccountModel accountModel = new AccountModel();
    accountModel.setAccountId(account.getId());
    accountModel.setActiveSince(account.getActiveSince());
    accountModel.setBirthday(account.getBirthday());
    accountModel.setEmail(account.getEmail());
    accountModel.setGender(account.getGender());
    accountModel.setLastLogin(account.getLastLogin());
    accountModel.setPassword(account.getPassword());
    accountModel.setRole(account.getRole());
    accountModel.setUsername(account.getUsername());
    return accountModel;
  }
  
  protected AccountModel extractAccountModelManually(AccountSetting accountSetting) {
    AccountModel accountModel = new AccountModel();
    accountModel.setAccountSettingId(accountSetting.getId());
    accountModel.setActivityMultiplier(accountSetting.getActivityMultiplier());
    accountModel.setAge(accountSetting.getAge());
    accountModel.setHeight(accountSetting.getHeight());
    accountModel.setRecalculationDay(accountSetting.getRecalculationDay());
    accountModel.setTdeeFormula(accountSetting.getTdeeFormula());
    accountModel.setUnitSystem(accountSetting.getUnitSystem());
    accountModel.setUseAge(accountSetting.getUseAge());
    return accountModel;
  }
  
  protected AccountModel extractAccountModelManually(Account account, AccountSetting accountSetting) {
    AccountModel accountModel = new AccountModel();
    accountModel.setAccountId(account.getId());
    accountModel.setActiveSince(account.getActiveSince());
    accountModel.setBirthday(account.getBirthday());
    accountModel.setEmail(account.getEmail());
    accountModel.setGender(account.getGender());
    accountModel.setLastLogin(account.getLastLogin());
    accountModel.setPassword(account.getPassword());
    accountModel.setRole(account.getRole());
    accountModel.setUsername(account.getUsername());
    accountModel.setAccountSettingId(accountSetting.getId());
    accountModel.setActivityMultiplier(accountSetting.getActivityMultiplier());
    accountModel.setAge(accountSetting.getAge());
    accountModel.setHeight(accountSetting.getHeight());
    accountModel.setRecalculationDay(accountSetting.getRecalculationDay());
    accountModel.setTdeeFormula(accountSetting.getTdeeFormula());
    accountModel.setUnitSystem(accountSetting.getUnitSystem());
    accountModel.setUseAge(accountSetting.getUseAge());
    return accountModel;
  }
  
  protected void assertThatAccountModelIsExpected(AccountModel accountModel, AccountModel expected) {
    assertThat(accountModel.getActiveSince(), is(expected.getActiveSince()));
    assertTrue(DateUtils.isSameDate(accountModel.getBirthday(), expected.getBirthday()));
    assertThat(accountModel.getEmail(), is(expected.getEmail()));
    assertThat(accountModel.getGender(), is(expected.getGender()));
    assertThat(accountModel.getAccountId(), is(expected.getAccountId()));
    assertTrue(DateUtils.isSameDate(accountModel.getLastLogin(), expected.getLastLogin()));
    assertThat(accountModel.getPassword(), is(expected.getPassword()));
    assertThat(accountModel.getRole(), is(expected.getRole()));
    assertThat(accountModel.getUsername(), is(expected.getUsername()));
    
    assertThat(accountModel.getActivityMultiplier(), is(expected.getActivityMultiplier()));
    assertThat(accountModel.getAge(), is(expected.getAge()));
    assertThat(accountModel.getHeight(), is(expected.getHeight()));
    assertThat(accountModel.getAccountSettingId(), is(expected.getAccountSettingId()));
    assertThat(accountModel.getRecalculationDay(), is(expected.getRecalculationDay()));
    assertThat(accountModel.getTdeeFormula(), is(expected.getTdeeFormula()));
    assertThat(accountModel.getUnitSystem(), is(expected.getUnitSystem()));
    assertThat(accountModel.getUseAge(), is(expected.getUseAge()));
  }
}
