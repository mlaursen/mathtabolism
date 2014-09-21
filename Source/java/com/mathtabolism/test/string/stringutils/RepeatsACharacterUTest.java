/**
 * 
 */
package com.mathtabolism.test.string.stringutils;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.mathtabolism.util.string.StringUtils.repeatsACharacter;

/**
 * 
 * @author mlaursen
 */
public class RepeatsACharacterUTest {
	
	@Test
	public void testNull() {
		assertFalse(repeatsACharacter(null));
	}
	
	@Test
	public void testEmpty() {
		assertFalse(repeatsACharacter(""));
	}
	
	@Test
	public void testOneChar() {
		assertFalse(repeatsACharacter("a"));
	}
	
	@Test
	public void test2SpacesNotAllowed() {
		assertFalse(repeatsACharacter("  "));
	}
	
	@Test
	public void test2SpacesAllowed() {
		assertTrue(repeatsACharacter("  ", true));
	}
	
	@Test
	public void test2As() {
		assertTrue(repeatsACharacter("aa"));
	}
	
	@Test
	public void test2AsSpreadOut() {
		assertTrue(repeatsACharacter("a guacamole"));
	}
	
	@Test
	public void testNoSpacesIncluded() {
		assertTrue(repeatsACharacter("a  ba"));
	}
	
	@Test
	public void testRepeats5Times() {
		assertTrue(repeatsACharacter("A guacamole exists for a better experience.", 5));
	}
	
	@Test
	public void testRepeats5TimesWithSpace() {
		assertTrue(repeatsACharacter("A guacamole exists for a better experience.", 5, true));
	}
}
