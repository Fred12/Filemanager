package com.kandinsky.objects;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FTPEntryTest {
	
	private static final int NEGATIVE_PORT = -12;
	private static final int PORT_TOO_BIG = 100000;
	private static final String NAME = "NAME";
	private static final String SERVER = "ftp.halifax.rwth-aachen.de";
	private static final int PORT = 21;
	private static final String USER_NAME = "anonymous";
	private static final String PASSWORD = "mail@mail.de";
	private FTPEntry testEntry;
	
	@Before
	public void setUp(){
		testEntry = new FTPEntry(NAME, SERVER, PORT, USER_NAME, PASSWORD);
	}

	@Test
	public void testName() {
		assertEquals(NAME, testEntry.getName());
	}
	
	@Test
	public void testGetServer() {
		assertEquals(SERVER, testEntry.getServer());
	}
	
	@Test
	public void testGetPort() {
		assertEquals(PORT, testEntry.getPort());
	}
	
	@Test
	public void testGetUserName() {
		assertEquals(USER_NAME, testEntry.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(PASSWORD, testEntry.getPassword());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testNegativePortNumber() {
		testEntry.setPort(NEGATIVE_PORT);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testPositivePortNumberTooBig() {
		testEntry.setPort(PORT_TOO_BIG);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorWithPositivePortNumberTooBig() {
		new FTPEntry(NAME, SERVER, PORT_TOO_BIG, USER_NAME, PASSWORD);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorWithNegativePortNumber() {
		new FTPEntry(NAME, SERVER, NEGATIVE_PORT, USER_NAME, PASSWORD);
	}

}
