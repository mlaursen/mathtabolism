/**
 * 
 */
package com.mathtabolism.util.navigation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathtabolism.util.string.StringUtils;
import com.mathtabolism.view.navigation.AccountNav;
import com.mathtabolism.view.navigation.Navigatable;

/**
 * 
 * @author mlaursen
 */
public class ConvertEnum {
  
  @Test
  public void test() {
    String r = redirect(AccountNav.ACCOUNT_INITIALIZATION);
    System.out.println(r);
  }
  
  private <T extends Enum<T> & Navigatable> String redirect(T page) {
    
    return "/pages/" + page.getFolder() + "/" + StringUtils.toCamelCase(page.name()) + "?faces-redirect=true";
  }
  
}
