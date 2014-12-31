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
  protected static AccountModel ID_EMAIL_MODEL;
  protected static AccountModel DEFAULT_FULL_MODEL;
  protected static AccountModel DEFAULT_FULL_AGE_MODEL;
  protected static final String DEFAULT_ACCOUNT_ID = "MATH000000";
  protected static final String DEFAULT_ALTERNATE_ID = "MATH003A5F";
  protected static final String DEFAULT_USERNAME = "glutes_for_slutes";
  protected static final String DEFAULT_EMAIL = "bob@bobby.flay";
  protected static final Gender DEFAULT_GENDER = Gender.MALE;
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
    AccountSetting as = new AccountSetting();
    as.setId(accountModel.getAccountSettingId());
    as.setActivityMultiplier(accountModel.getActivityMultiplier());
    as.setAge(accountModel.getAge());
    as.setHeight(accountModel.getHeight());
    as.setRecalculationDay(accountModel.getRecalculationDay());
    as.setTdeeFormula(accountModel.getTdeeFormula());
    as.setUnitSystem(accountModel.getUnitSystem());
    as.setUseAge(accountModel.getUseAge());
    return as;
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
}
