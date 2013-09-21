package com.kandinsky.conn;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class FTPConnectionHandlerTest {

	@Test
	public void testConstructor() {
		FTPConnectionHandler handler = new FTPConnectionHandler();
		assertFalse(handler.isConnected());
	}
	
	@Test (expected=RuntimeException.class)
	public void testGetFilesInFolderWhileNotConnected() {
		FTPConnectionHandler handler = new FTPConnectionHandler();
		handler.getFilesInFolder("/");
	}

}
