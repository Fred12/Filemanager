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
	public void testName() {
		hotkey.setName(TEST_NAME);
		assertEquals(TEST_NAME, hotkey.getName());
	}

	@Test
	public void testKey() {
		hotkey.setInternalKey(STRG_C);
		assertEquals(STRG_C, hotkey.getInternalKey());
	}

	@Test
	public void testHotkeyCombi() {
		hotkey.setShortcutCombi(STRG_C);
		assertEquals(STRG_C, hotkey.getShortcutCombi());
	}
	
	@Test
	public void testConstructor() {
		hotkey = new Hotkey(KEY, NAME, COMBI);
		assertEquals(KEY, hotkey.getInternalKey());
		assertEquals(NAME, hotkey.getName());
		assertEquals(COMBI, hotkey.getShortcutCombi());
	}

}
