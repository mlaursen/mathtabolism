/**
 * 
 */
package com.mathtabolism.test.util.dto.account;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.util.unit.UnitSystem;
import com.mathtabolism.view.model.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public abstract class BaseAccountUtilsUTest {

  protected static final Account NULL_ACCOUNT = null;
  protected static final AccountModel NULL_ACCOUNT_MODEL = null;
  protected static final Account EMPTY_ACCOUNT = new Account();
  protected static final AccountModel EMPTY_ACCOUNT_MODEL = new AccountModel();
  protected static final String DEFAULT_ACCOUNT_ID = "MATH000000";
  protected static final String DEFAULT_ALTERNATE_ID = "MATH003A5F";
  protected static final String DEFAULT_USERNAME = "glutes_for_slutes";
  protected static final String DEFAULT_EMAIL = "bob@bobby.flay";
  protected static final Gender DEFAULT_GENDER = Gender.MALE;
  protected static final String DEFAULT_PASSWORD = "neverGonnaGiveYouUp, neverGonnaLetYouDown";
  protected static final ActivityMultiplier DEFAULT_ACTIVITY_MULTIPLIER = ActivityMultiplier.LIGHTLY_ACTIVE;
  protected static final Integer DEFAULT_HEIGHT = 71;
  protected static final Date DEFAULT_ACTIVE_SINCE = new GregorianCalendar(1, 1, 2014).getTime();
  protected static final Date DEFAULT_LAST_LOGIN = new GregorianCalendar(12, 30, 2014).getTime();
  protected static final Date DEFAULT_BIRTHDAY   = new GregorianCalendar(1, 1, 1950).getTime();
  protected static final Integer DEFAULT_AGE = 23;
  protected static final Weekday DEFAULT_RECALCULATION_DAY = Weekday.SUNDAY;
  protected static final UnitSystem DEFAULT_UNIT_SYSTEM = UnitSystem.IMPERIAL;
  protected static final TDEEFormula DEFAULT_TDEE_FORMULA = TDEEFormula.HARRIS_BENEDICT;
  protected static final AccountRole DEFAULT_ROLE = AccountRole.USER;
  protected static final Indicator DEFAULT_USE_AGE = Indicator.FALSE;
  protected static final Double DEFAULT_CURRENT_WEIGHT = 185.3;
  
  protected static Account idUsernameAccount = new Account();
  protected static AccountModel idUsernameAccountModel = new AccountModel();
  protected static Account fullAccountWithBirthday = new Account();
  protected static AccountModel fullAccountModelWithBirthday = new AccountModel();
  protected static Account fullAccountWithNoBirthday = new Account();
  protected static AccountModel fullAccountModelWithNoBirthday = new AccountModel();
  protected static Account accountMissingData = new Account();
  protected static AccountModel accountModelMissingData = new AccountModel();
  
  @BeforeClass
  public static void init() {
    idUsernameAccount.setId(DEFAULT_ACCOUNT_ID);
    idUsernameAccount.setUsername(DEFAULT_USERNAME);
    
    idUsernameAccountModel.setAccountId(DEFAULT_ACCOUNT_ID);
    idUsernameAccountModel.setUsername(DEFAULT_USERNAME);
    
    accountMissingData.setId(DEFAULT_ACCOUNT_ID);
    accountMissingData.setUsername(DEFAULT_USERNAME);
    accountMissingData.setActiveSince(DEFAULT_ACTIVE_SINCE);
    accountMissingData.setBirthday(DEFAULT_BIRTHDAY);
    
    accountModelMissingData.setAccountId(DEFAULT_ACCOUNT_ID);
    accountModelMissingData.setUsername(DEFAULT_USERNAME);
    accountModelMissingData.setActiveSince(DEFAULT_ACTIVE_SINCE);
    accountModelMissingData.setBirthday(DEFAULT_BIRTHDAY);
    
    fullAccountWithBirthday.setId(DEFAULT_ACCOUNT_ID);
    fullAccountWithBirthday.setUsername(DEFAULT_USERNAME);
    fullAccountWithBirthday.setActiveSince(DEFAULT_ACTIVE_SINCE);
    fullAccountWithBirthday.setBirthday(DEFAULT_BIRTHDAY);
    fullAccountWithBirthday.setEmail(DEFAULT_EMAIL);
    fullAccountWithBirthday.setGender(DEFAULT_GENDER);
    fullAccountWithBirthday.setLastLogin(DEFAULT_LAST_LOGIN);
    fullAccountWithBirthday.setPassword(DEFAULT_PASSWORD);
    fullAccountWithBirthday.setRole(DEFAULT_ROLE);
    
    fullAccountModelWithBirthday.setAccountId(DEFAULT_ACCOUNT_ID);
    fullAccountModelWithBirthday.setAccountSettingId(DEFAULT_ALTERNATE_ID);
    fullAccountModelWithBirthday.setUsername(DEFAULT_USERNAME);
    fullAccountModelWithBirthday.setActiveSince(DEFAULT_ACTIVE_SINCE);
    fullAccountModelWithBirthday.setBirthday(DEFAULT_BIRTHDAY);
    fullAccountModelWithBirthday.setEmail(DEFAULT_EMAIL);
    fullAccountModelWithBirthday.setGender(DEFAULT_GENDER);
    fullAccountModelWithBirthday.setLastLogin(DEFAULT_LAST_LOGIN);
    fullAccountModelWithBirthday.setPassword(DEFAULT_PASSWORD);
    fullAccountModelWithBirthday.setRole(DEFAULT_ROLE);
    fullAccountModelWithBirthday.setActivityMultiplier(DEFAULT_ACTIVITY_MULTIPLIER);
    fullAccountModelWithBirthday.setCurrentWeight(DEFAULT_CURRENT_WEIGHT);
    fullAccountModelWithBirthday.setHeight(DEFAULT_HEIGHT);
    fullAccountModelWithBirthday.setRecalculationDay(DEFAULT_RECALCULATION_DAY);
    fullAccountModelWithBirthday.setTdeeFormula(DEFAULT_TDEE_FORMULA);
    fullAccountModelWithBirthday.setUnitSystem(DEFAULT_UNIT_SYSTEM);
    fullAccountModelWithBirthday.setUseAge(DEFAULT_USE_AGE);
    

    fullAccountWithNoBirthday.setId(DEFAULT_ACCOUNT_ID);
    fullAccountWithNoBirthday.setUsername(DEFAULT_USERNAME);
    fullAccountWithNoBirthday.setActiveSince(DEFAULT_ACTIVE_SINCE);
    fullAccountWithNoBirthday.setEmail(DEFAULT_EMAIL);
    fullAccountWithNoBirthday.setGender(DEFAULT_GENDER);
    fullAccountWithNoBirthday.setLastLogin(DEFAULT_LAST_LOGIN);
    fullAccountWithNoBirthday.setPassword(DEFAULT_PASSWORD);
    fullAccountWithNoBirthday.setRole(DEFAULT_ROLE);
    
    fullAccountModelWithNoBirthday.setAccountId(DEFAULT_ACCOUNT_ID);
    fullAccountModelWithNoBirthday.setAccountSettingId(DEFAULT_ALTERNATE_ID);
    fullAccountModelWithNoBirthday.setUsername(DEFAULT_USERNAME);
    fullAccountModelWithNoBirthday.setActiveSince(DEFAULT_ACTIVE_SINCE);
    fullAccountModelWithNoBirthday.setAge(DEFAULT_AGE);
    fullAccountModelWithNoBirthday.setEmail(DEFAULT_EMAIL);
    fullAccountModelWithNoBirthday.setGender(DEFAULT_GENDER);
    fullAccountModelWithNoBirthday.setLastLogin(DEFAULT_LAST_LOGIN);
    fullAccountModelWithNoBirthday.setPassword(DEFAULT_PASSWORD);
    fullAccountModelWithNoBirthday.setRole(DEFAULT_ROLE);
    fullAccountModelWithNoBirthday.setActivityMultiplier(DEFAULT_ACTIVITY_MULTIPLIER);
    fullAccountModelWithNoBirthday.setCurrentWeight(DEFAULT_CURRENT_WEIGHT);
    fullAccountModelWithNoBirthday.setHeight(DEFAULT_HEIGHT);
    fullAccountModelWithNoBirthday.setRecalculationDay(DEFAULT_RECALCULATION_DAY);
    fullAccountModelWithNoBirthday.setTdeeFormula(DEFAULT_TDEE_FORMULA);
    fullAccountModelWithNoBirthday.setUnitSystem(DEFAULT_UNIT_SYSTEM);
    fullAccountModelWithNoBirthday.setUseAge(DEFAULT_USE_AGE);
  }
}
