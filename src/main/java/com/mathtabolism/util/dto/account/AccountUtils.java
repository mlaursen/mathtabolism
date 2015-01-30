/**
 * 
 */
package com.mathtabolism.util.dto.account;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.mathtabolism.dto.AccountDto;
import com.mathtabolism.dto.AccountSettingDto;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.entity.account.AccountSetting;
import com.mathtabolism.view.model.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public class AccountUtils {
  private AccountUtils() {
  }
  
  /**
   * Checks if two Objects that extend AccountDto are considered equal. They are considered equal if
   * all the common fields in the AccountDto are equal AND the id fields are equal.
   * 
   * <code><pre>
   * account1 = null, account2 = null -> true
   * account1 = null, account2 != null -> false
   * account1 != null, account2 = null -> false
   * account1 is AccountModel, account2 is Account, all fields equal -> true
   * account1 is AccountModel, account2 is Account, id fields different -> false
   * </pre></code>
   * 
   * @param account1 the first account, allows null
   * @param account2 the second account, allows null
   * @return true if the accounts are considered equal
   */
  public static <A1 extends AccountDto, A2 extends AccountDto> boolean equals(A1 account1, A2 account2) {
    return equals(account1, account2, false);
  }
  
  /**
   * Checks if two Objects that extend AccountDto are considered equal. They are considered equal if
   * all the common fields in the AccountDto are equal, the id fields are equal, AND they are both the same 
   * subclass instance.
   * 
   * <code><pre>
   * account1 = null, account2 = null -> true
   * account1 = null, account2 != null -> false
   * account1 != null, account2 = null -> false
   * account1 is AccountModel, account2 is Account -> false
   * account1 is Account, account2 is AccountModel -> false
   * account1 is AccountModel, account2 is AccountModel, all fields equal -> true
   * </pre></code>
   * @param account1 the first account, allows null
   * @param account2 the second account, allows null
   * @return true if the accounts are considered equal
   */
  public static <A1 extends AccountDto, A2 extends AccountDto> boolean equalsStrictly(A1 account1, A2 account2) {
    return equals(account1, account2, true);
  }
  
  /**
   * Checks if two objects that extends AccountSettingDto are considered equal. They are considered equal if
   * all the common fields in the AccountSettingDto are equal AND the id fields are equal.
   * 
   * <code><pre>
   * setting1 = null, setting2 = null -> true
   * setting1 = null, setting2 != null -> false
   * setting1 != null, setting2 = null -> false
   * setting1 is AccountModel, setting2 is Account -> false
   * setting1 is AccountSetting, setting2 is AccountSettingModel, all fields equal -> true
   * setting1 is AccountSettingModel, setting2 is AccountSettingModel, all fields not equal -> false
   * </pre></code>
   * @param setting1 the first account settings, allows null
   * @param setting2 the second account settings, allows null
   * @return true if the account settings are equal
   */
  public static <AS1 extends AccountSettingDto, AS2 extends AccountSettingDto> boolean equalsSettings(AS1 setting1, AS2 setting2) {
    return isAccountSettingEqual(setting1, setting2, false);
  }
  
  /**
   * Checks if two objects that extends AccountSettingDto are considered equal. They are considered equal if
   * all the common fields in the AccountSettingDto are equal, the id fields are equal, AND they are both the same 
   * subclass instance.
   * 
   * <code><pre>
   * setting1 = null, setting2 = null -> true
   * setting1 = null, setting2 != null -> false
   * setting1 != null, setting2 = null -> false
   * setting1 is AccountModel, setting2 is Account -> false
   * setting1 is AccountSetting, setting2 is AccountSettingModel -> false
   * setting1 is AccountSettingModel, setting2 is AccountSettingModel, all fields equal -> true
   * </pre></code>
   * 
   * @param setting1 the first account settings, allows null
   * @param setting2 the second account settings, allows null
   * @return true if the account settings are equal
   */
  public static <AS1 extends AccountSettingDto, AS2 extends AccountSettingDto> boolean equalsSettingsStrictly(AS1 setting1, AS2 setting2) {
    return isAccountSettingEqual(setting1, setting2, true);
  }
  
  private static <A1 extends AccountDto, A2 extends AccountDto> boolean equals(A1 account1, A2 account2, boolean isStrictlyEqual) {
    if((account1 == null && account2 == null)) {
      return true;
    } else if((account1 == null || account2 == null) || (isStrictlyEqual && !isSameInstance(account1, account2))) {
      return false;
    }
    
    return isAccountEqual(account1, account2, isStrictlyEqual) && isAccountModelEqual(account1, account2, isStrictlyEqual);
  }
  
  private static <AS1 extends AccountSettingDto, AS2 extends AccountSettingDto> boolean isAccountSettingEqual(AS1 setting1, AS2 setting2, boolean isStrictlyEqual) {
    if(isBothNull(setting1, setting2)) {
      return true;
    } else if((setting1 == null || setting2 == null) || isStrictlyEqual && !isSameInstance(setting1, setting1)) {
      return false;
    }
    
    boolean isEqual = isObjectsEqual(setting1.getActivityMultiplier(), setting2.getActivityMultiplier());
    isEqual &= isObjectsEqual(setting1.getRecalculationDay(), setting2.getRecalculationDay());
    isEqual &= isObjectsEqual(setting1.getTdeeFormula(), setting2.getTdeeFormula());
    isEqual &= isObjectsEqual(setting1.getUnitSystem(), setting2.getUnitSystem());
    isEqual &= isObjectsEqual(setting1.getUseAge(), setting2.getUseAge());
    isEqual &= isObjectsEqual(setting1.getAge(), setting2.getAge());
    
    Long id1 = isAccountSetting(setting1) ? ((AccountSetting) setting1).getId() : ((AccountModel) setting1).getAccountSettingId();
    Long id2 = isAccountSetting(setting2) ? ((AccountSetting) setting2).getId() : ((AccountModel) setting2).getAccountSettingId();
    isEqual &= id1 == id2;
    
    return isEqual;
  }
  
  private static <A1 extends AccountDto, A2 extends AccountDto> boolean isAccountEqual(A1 account1, A2 account2, boolean isStrictlyEqual) {
    boolean isEqual = StringUtils.equals(account1.getUsername(), account2.getUsername());
    isEqual &= StringUtils.equals(account1.getEmail(), account2.getEmail());
    isEqual &= StringUtils.equals(account1.getPassword(), account2.getPassword());
    
    isEqual &= isObjectsEqual(account1.getGender(), account2.getGender());
    isEqual &= isObjectsEqual(account1.getRole(), account2.getRole());
    
    isEqual &= isDateEqual(account1.getBirthday(), account2.getBirthday());
    isEqual &= isDateEqual(account1.getLastLogin(), account2.getLastLogin());
    isEqual &= isDateEqual(account1.getActiveSince(), account2.getActiveSince());
    
    if(isStrictlyEqual) { 
      if(isSameInstance(account1, account2)) {
        if(isAccount(account1)) {
          isEqual &= ((Account) account1).getId() == ((Account) account2).getId();
        } else {
          isEqual &= ((AccountModel) account1).getAccountId() == ((AccountModel) account2).getAccountId();
        }
      } else {
        isEqual = false;
      }
    }
    return isEqual;
  }
  
  private static boolean isDateEqual(Date d1, Date d2) {
    return (d1 == null && d2 == null) || (d1 != null && d2 != null && DateUtils.isSameDay(d1, d2));
  }
  
  private static <A1 extends AccountDto, A2 extends AccountDto> boolean isAccountModelEqual(A1 account1, A2 account2, boolean isStrictlyEqual) {
    if(isStrictlyEqual && !isSameInstance(account1, account2)) {
      return false;
    }
    
    if(isAccountModel(account1) && isAccountModel(account2)) {
      AccountModel a1 = (AccountModel) account1;
      AccountModel a2 = (AccountModel) account2;
      boolean isEqual = a1.getAccountId() == a2.getAccountId();
      isEqual &= isAccountSettingEqual(a1, a2, isStrictlyEqual);
      
      isEqual &= isObjectsEqual(a1.getUseAge(), a2.getUseAge());
      isEqual &= isObjectsEqual(a1.getCurrentWeight(), a2.getCurrentWeight());
      isEqual &= isObjectsEqual(a1.getPreviousWeight(), a2.getPreviousWeight());
      
      return isEqual;
    } else {
      return true;
    }
  }
  
  private static boolean isSameInstance(Object object1, Object object2) {
    return (isAccount(object1) && isAccount(object2))
        || (isAccountSetting(object1) && isAccountSetting(object2))
        || (isAccountModel(object1) && isAccountModel(object2));
  }
  
  private static boolean isObjectsEqual(Object object1, Object object2) {
    return isBothNull(object1, object2) || isBothNotNullEquals(object1, object2);
  }
  
  private static boolean isBothNull(Object object1, Object object2) {
    return object1 == null && object2 == null;
  }
  
  private static boolean isBothNotNullEquals(Object object1, Object object2) {
    return object1 != null && object2 != null && object1.equals(object2);
  }
  
  private static boolean isAccount(Object object) {
    return object instanceof Account;
  }
  
  private static boolean isAccountModel(Object object) {
    return object instanceof AccountModel;
  }
  
  private static boolean isAccountSetting(Object object) {
    return object instanceof AccountSetting;
  }
}
