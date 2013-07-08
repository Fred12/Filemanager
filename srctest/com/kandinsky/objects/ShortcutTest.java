package com.kandinsky.objects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShortcutTest {

	private static final String KEY = "KEY";
	private static final String NAME = "NAME";
	private static final String COMBI = "COMBI";
	private static final String STRG_C = "STRG+C";
	private static final String TEST_NAME = "TEST_NAME";
	private Shortcut shortcut;

	@Before
	public void setUp() {
		shortcut = new Shortcut(null, null, null);
	}

	@Test
	public void testName() {
		shortcut.setName(TEST_NAME);
		assertEquals(TEST_NAME, shortcut.getName());
	}

	@Test
	public void testKey() {
		shortcut.setKey(STRG_C);
		assertEquals(STRG_C, shortcut.getKey());
	}

	@Test
	public void testShortcutCombi() {
		shortcut.setShortcutCombi(STRG_C);
		assertEquals(STRG_C, shortcut.getShortcutCombi());
	}
	
	@Test
	public void testConstructor() {
		shortcut = new Shortcut(KEY, NAME, COMBI);
		assertEquals(KEY, shortcut.getKey());
		assertEquals(NAME, shortcut.getName());
		assertEquals(COMBI, shortcut.getShortcutCombi());
	}

}
