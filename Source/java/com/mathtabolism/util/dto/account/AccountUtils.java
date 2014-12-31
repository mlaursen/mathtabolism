/**
 * 
 */
package com.mathtabolism.util.dto.account;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.mathtabolism.dto.AccountDto;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.view.model.account.AccountModel;

/**
 *
 * @author mlaursen
 */
public class AccountUtils {
  private AccountUtils() {
  }
  
  public static <A1 extends AccountDto, A2 extends AccountDto> boolean equals(A1 account1, A2 account2) {
    return equals(account1, account2, false);
  }
  
  public static <A1 extends AccountDto, A2 extends AccountDto> boolean equalsStrictly(A1 account1, A2 account2) {
    return equals(account1, account2, true);
  }
  
  private static <A1 extends AccountDto, A2 extends AccountDto> boolean equals(A1 account1, A2 account2, boolean isStrictlyEqual) {
    if((account1 == null && account2 == null)) {
      return true;
    } else if((account1 == null || account2 == null) || (isStrictlyEqual && !isSameInstance(account1, account2))) {
      return false;
    }
    
    return isAccountEqual(account1, account2, isStrictlyEqual) && isAccountModelEqual(account1, account2, isStrictlyEqual);
  }
  
  private static <A1 extends AccountDto, A2 extends AccountDto> boolean isAccountEqual(A1 account1, A2 account2, boolean isStrictlyEqual) {
    boolean isEqual = StringUtils.equals(account1.getUsername(), account2.getUsername());
    isEqual &= StringUtils.equals(account1.getEmail(), account2.getEmail());
    isEqual &= StringUtils.equals(account1.getPassword(), account2.getPassword());
    
    isEqual &= isEnumsEqual(account1.getGender(), account2.getGender());
    isEqual &= isEnumsEqual(account1.getRole(), account2.getRole());
    
    isEqual &= isDateEqual(account1.getBirthday(), account2.getBirthday());
    isEqual &= isDateEqual(account1.getLastLogin(), account2.getLastLogin());
    isEqual &= isDateEqual(account1.getActiveSince(), account2.getActiveSince());
    
    if(isStrictlyEqual) { 
      if(isSameInstance(account1, account2)) {
        if(isAccount(account1)) {
          isEqual &= StringUtils.equals(((Account) account1).getId(), ((Account) account2).getId());
        } else {
          isEqual &= StringUtils.equals(((AccountModel) account1).getAccountId(), ((AccountModel) account2).getAccountId());
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
  
  private static <E extends Enum<E>> boolean isEnumsEqual(Enum<E> e1, Enum<E> e2) {
    return (e1 == null && e2 == null) || (e1 != null && e2 != null && e1.equals(e2));
  }
  
  private static <A1 extends AccountDto, A2 extends AccountDto> boolean isAccountModelEqual(A1 account1, A2 account2, boolean isStrictlyEqual) {
    if(isStrictlyEqual && !isSameInstance(account1, account2)) {
      return false;
    }
    
    if(isAccountModel(account1) && isAccountModel(account2)) {
      AccountModel a1 = (AccountModel) account1;
      AccountModel a2 = (AccountModel) account2;
      boolean isEqual = StringUtils.equals(a1.getAccountId(), a2.getAccountId());
      isEqual &= StringUtils.equals(a1.getAccountSettingId(), a2.getAccountSettingId());
      
      isEqual &= isEnumsEqual(a1.getActivityMultiplier(), a2.getActivityMultiplier());
      isEqual &= isEnumsEqual(a1.getRecalculationDay(), a2.getRecalculationDay());
      isEqual &= isEnumsEqual(a1.getTdeeFormula(), a2.getTdeeFormula());
      isEqual &= isEnumsEqual(a1.getUnitSystem(), a2.getUnitSystem());
      isEqual &= isEnumsEqual(a1.getUseAge(), a2.getUseAge());
      
      isEqual &= (a1.getAge() == null && a2.getAge() == null) 
          || (a1.getAge() != null && a2.getAge() != null && a1.getAge().equals(a2.getAge()));
      isEqual &= (a1.getCurrentWeight() == null && a2.getCurrentWeight() == null)
          || (a1.getCurrentWeight() != null && a2.getCurrentWeight() != null && a1.getCurrentWeight().equals(a2.getCurrentWeight()));
      isEqual &= (a1.getPreviousWeight() == null && a2.getPreviousWeight() == null)
          || (a1.getPreviousWeight() != null && a2.getPreviousWeight() != null && a1.getPreviousWeight().equals(a2.getPreviousWeight()));
      
      return isEqual;
    } else {
      return true;
    }
  }
  
  private static <A1 extends AccountDto, A2 extends AccountDto> boolean isSameInstance(A1 account1, A2 account2) {
    return (isAccount(account1) && isAccount(account2)) || (isAccountModel(account1) && isAccountModel(account2));
  }
  
  private static <A extends AccountDto> boolean isAccount(A account) {
    return account instanceof Account;
  }
  
  private static <A extends AccountDto> boolean isAccountModel(A account) {
    return account instanceof AccountModel;
  }
}
