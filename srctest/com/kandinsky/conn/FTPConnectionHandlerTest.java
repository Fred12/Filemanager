package com.kandinsky.conn;

import static org.junit.Assert.*;

import org.junit.Test;

public class FTPConnectionHandlerTest {

	@Test
	public void testGetInstance() {
		FTPConnectionHandler handler1 = FTPConnectionHandler.getInstance();
		FTPConnectionHandler handler2 = FTPConnectionHandler.getInstance();
		assertTrue(handler1==handler2);
	}

}
