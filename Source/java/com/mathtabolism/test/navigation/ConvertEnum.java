/**
 * 
 */
package com.mathtabolism.test.navigation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathtabolism.navigation.AccountNav;
import com.mathtabolism.navigation.Navigatable;
import com.mathtabolism.util.string.StringUtils;

/**
 * 
 * @author mlaursen
 */
public class ConvertEnum {
	
	@Test
	public void test() {
		String r = redirect(AccountNav.ACCOUNT_SETTINGS);
		System.out.println(r);
	}
	
	private <T extends Enum<T> & Navigatable> String redirect(T page) {
		
		return "/pages/" + page.getFolder() + "/" + StringUtils.toCamelCase(page.name()) + "?faces-redirect=true";
	}
	
}
