package com.kandinsky.objects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ByteToStringHelperTest {

	@Test
	public void testByteToStringForBytes() {
		long bytes = 512L;
		assertEquals("512,000 B", ByteToStringHelper.convertToString(bytes));
	}
	
	@Test
	public void testByteToStringForKiloBytes() {
		long bytes = 2048+512;
		assertEquals("2,500 KB", ByteToStringHelper.convertToString(bytes));
	}
	
	@Test
	public void testByteToStringForMegaBytes() {
		long bytes = 1024*(2048+512);
		assertEquals("2,500 MB", ByteToStringHelper.convertToString(bytes));
	}
}
