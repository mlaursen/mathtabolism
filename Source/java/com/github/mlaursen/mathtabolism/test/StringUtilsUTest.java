/**
 * 
 */
package com.github.mlaursen.mathtabolism.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.mlaursen.mathtabolism.util.string.StringUtils;

/**
 * 
 * @author laursenm
 */
public class StringUtilsUTest {
	
	@Test
	public void testToDatabaseFormat() {
		String test1 = "AccountModal";
		String test2 = "accountModal";
		String test3 = "account_modal";
		String test4 = "modal";
		String expected = "account_modal";
		String expected2 = "modal";
		assertEquals(expected, StringUtils.toDatabaseFormat(test1));
		assertEquals(expected, StringUtils.toDatabaseFormat(test2));
		assertEquals(expected, StringUtils.toDatabaseFormat(test3));
		assertEquals(expected2, StringUtils.toDatabaseFormat(test4));
	}
	
}
