package com.kandinsky.objects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HotkeyTest {

	private static final String KEY = "KEY";
	private static final String NAME = "NAME";
	private static final String COMBI = "COMBI";
	private static final String STRG_C = "STRG+C";
	private static final String TEST_NAME = "TEST_NAME";
	private Hotkey hotkey;

	@Before
	public void setUp() {
		hotkey = new Hotkey(null, null, null);
	}

	@Test
	public void testFunctionName() {
		hotkey.setFunctionName(TEST_NAME);
		assertEquals(TEST_NAME, hotkey.getFunctionName());
	}

	@Test
	public void testInternalKey() {
		hotkey.setInternalKey(STRG_C);
		assertEquals(STRG_C, hotkey.getInternalKey());
	}

	@Test
	public void testHotkeyCombination() {
		hotkey.setHotkeyCombination(STRG_C);
		assertEquals(STRG_C, hotkey.getHotkeyCombination());
	}
	
	@Test
	public void testConstructor() {
		hotkey = new Hotkey(KEY, NAME, COMBI);
		assertEquals(KEY, hotkey.getInternalKey());
		assertEquals(NAME, hotkey.getFunctionName());
		assertEquals(COMBI, hotkey.getHotkeyCombination());
	}
	
	@Test
	public void testNaturalOrderWithFirstLowerThanSecond() {
		Hotkey hotkeyA = new Hotkey(null, "ABC", null);
		Hotkey hotkeyB = new Hotkey(null, "DEF", null);
		
		assertTrue(hotkeyA.compareTo(hotkeyB) < 0);
	}
	
	@Test
	public void testNaturalOrderWithSecondLowerThanFirst() {
		Hotkey hotkeyA = new Hotkey(null, "DEF", null);
		Hotkey hotkeyB = new Hotkey(null, "ABC", null);
		
		assertTrue(hotkeyA.compareTo(hotkeyB) > 0);
	}
	
	@Test
	public void testNaturalOrderWithEqualHotkeyFunctionNames() {
		Hotkey hotkeyA = new Hotkey(null, "ABC", null);
		Hotkey hotkeySameAsA = new Hotkey(null, "ABC", null);
		
		assertTrue(hotkeyA.compareTo(hotkeySameAsA) == 0);
	}

}
