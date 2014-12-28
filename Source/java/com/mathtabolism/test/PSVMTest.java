package com.mathtabolism.test;

import java.util.Date;

import com.mathtabolism.constants.AccountRole;
import com.mathtabolism.constants.Gender;
import com.mathtabolism.constants.Indicator;
import com.mathtabolism.entity.account.Account;
import com.mathtabolism.util.emconverter.EntityModelConverter;
import com.mathtabolism.util.unit.Measurement;
import com.mathtabolism.util.unit.UnitConverter;
import com.mathtabolism.util.unit.UnitMeasurement;
import com.mathtabolism.view.model.account.AccountModel;

/**
 * @author mlaursen
 *
 */
public class PSVMTest {
  
  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    EntityModelConverter emConverter = new EntityModelConverter();
    Account account = new Account();
    account.setId("MATH000000");
    account.setActiveSince(new Date());
    account.setBirthday(new Date());
    account.setEmail("some@email.com");
    account.setGender(Gender.MALE);
    account.setPassword("abcd1234");
    account.setRole(AccountRole.USER);
    account.setUseBirthday(Indicator.TRUE);
    account.setUsername("bob");
    
    AccountModel accountModel = emConverter.convertEntityToModel(account);
    
    Account a = emConverter.convertModelToEntity(accountModel);
    
    System.out.println(accountModel);
    System.out.println(a);
  }
  
}
