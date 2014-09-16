/**
 * 
 */
package com.github.mlaursen.mathtabolism.test.string.stringutils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.util.string.StringUtils;

/**
 * 
 * @author laursenm
 */
public class ToDatabaseFormat {
	
	@Test
	public void testCamelCase() {
		assertEquals("account_setting", StringUtils.toDatabaseFormat("accountSetting"));
	}
	
	@Test
	public void testCamelCaseCapitalFirstOneWord() {
		assertEquals("account", StringUtils.toDatabaseFormat("Account"));
	}
	
	@Test
	public void testCamelCaseCapitalFirst() {
		assertEquals("account_setting", StringUtils.toDatabaseFormat("AccountSetting"));
	}
	
	@Test
	public void testAllLower() {
		assertEquals("account", StringUtils.toDatabaseFormat("account"));
	}
	
	@Test
	public void testCapitalsBeside() {
		assertEquals("this_is_a_test", StringUtils.toDatabaseFormat("thisIsATest"));
	}
}
