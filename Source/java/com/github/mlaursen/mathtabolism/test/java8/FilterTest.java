/**
 * PROPRIETARY RIGHTS NOTICE:  All rights reserved. No part of the code
 * (source,object,executable), screen-images, report-formats, database
 * schemas, and documentation of this program may be reproduced or transmitted
 * in any manner without the License or expressed written permission of
 * TEDS, LLC  P.O. Box 700, Atkins, VA 24311.
 * 
 * All parts of the code (source,object,executable), screen-images,
 * database schemas, report-formats, and documentation of this program
 * are considered proprietary information and should only be accessible
 * by those identified in the License.
 */
package com.github.mlaursen.mathtabolism.test.java8;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.github.mlaursen.mathtabolism.constants.AccountRole;
import com.github.mlaursen.mathtabolism.constants.ActivityMultiplier;
import com.github.mlaursen.mathtabolism.constants.TDEEFormula;
import com.github.mlaursen.mathtabolism.constants.Weekday;
import com.github.mlaursen.mathtabolism.entity.account.Account;
import com.github.mlaursen.mathtabolism.entity.account.AccountSetting;
import com.github.mlaursen.mathtabolism.util.date.DateUtils;

/**
 * @author laursenm
 *
 * @since 11.7
 * @version TEDS-
 */
public class FilterTest {

  /**
   * @param args
   * @since 11.7
   * @version TEDS-
   */
  public static void main(String[] args) {
    Account account = new Account();
    account.setId((long) 0);
    account.setRole(AccountRole.USER);
    account.setBirthday(new Date());
    account.setUsername("abcd1234");
    account.setActiveSince(new Date());
    AccountSetting as1 = new AccountSetting();
    as1.setAccount(account);
    as1.setActivityMultiplier(ActivityMultiplier.SEDENTARY);
    as1.setRecalculationDay(Weekday.MONDAY);
    as1.setTdeeFormula(TDEEFormula.HARRIS_BENEDICT);
    as1.setDateChanged(DateUtils.createDate(9, 16, 2014));

    AccountSetting as2 = new AccountSetting();
    as1.setAccount(account);
    as1.setActivityMultiplier(ActivityMultiplier.LIGHTLY_ACTIVE);
    as1.setRecalculationDay(Weekday.MONDAY);
    as1.setTdeeFormula(TDEEFormula.HARRIS_BENEDICT);
    as1.setDateChanged(DateUtils.createDate(9, 17, 2014));

    AccountSetting as3 = new AccountSetting();
    as3.setAccount(account);
    as3.setActivityMultiplier(ActivityMultiplier.SEDENTARY);
    as3.setRecalculationDay(Weekday.WEDNESDAY);
    as3.setTdeeFormula(TDEEFormula.HARRIS_BENEDICT);
    as3.setDateChanged(DateUtils.createDate(9, 18, 2014));
    account.setAccountSettings(Arrays.asList(as1, as2, as3));
    List<AccountSetting> settings = account.getAccountSettings();
    AccountSetting current = settings.stream().max((s1, s2) -> s1.getDateChanged().compareTo(s2.getDateChanged())).get();
    System.out.println(current);
    
  }

}
