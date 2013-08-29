package com.kandinsky.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class FileEntryTest {
	
	private final static Long SIZE_TEST_SIZE = 28L;
	private FileEntry directoryEntry, fileEntry, sizeTestEntry;
	
	@Before
	public void setUp(){
		fileEntry = new FileEntry("testfiles/test.txt");
		directoryEntry = new FileEntry("testfiles");
		sizeTestEntry = new FileEntry("testfiles/sizeTest");
	}

	@Test
	public void testFileName() {
		assertEquals("test.txt", fileEntry.getName());
	}
	
	@Test
	public void testFileExistsFalse() {
		FileEntry fileEntry = new FileEntry("");
		assertFalse(fileEntry.exists());
	}
	
	@Test
	public void testFileExistsTrue() {
		FileEntry fileEntry = new FileEntry(new File("testfiles"));
		assertTrue(fileEntry.exists());
	}
	
	@Test
	public void testFileTypeDirectory() {
		assertEquals(FileType.DIRECTORY, directoryEntry.getType());
	}
	
	@Test
	public void testFileTypeFile() {
		assertEquals(FileType.FILE, fileEntry.getType());
	}
	
	@Test
	public void testGetEndingEmpty() {
		assertEquals("", directoryEntry.getEnding());
	}
	
	@Test
	public void testGetEndingNotEmpty() {
		assertEquals("txt", fileEntry.getEnding());
	}
	
	@Test
	public void testGetDateNumberOfChars() {
		assertEquals(10, fileEntry.getDate().length());
	}

	@Test
	public void testGetSize() {
		assertEquals(ByteToStringHelper.convertToString(SIZE_TEST_SIZE), sizeTestEntry.getSize());
	}
	
	@Test
	public void testGetFullSizeAsLong() {
		assertEquals(SIZE_TEST_SIZE, sizeTestEntry.getFullSizeAsLong());
	}
	
	@Test
	public void testGetFullDateAsLong() {
		long currentMillies = System.currentTimeMillis();
		assertTrue(fileEntry.getFullDateAsLong()< currentMillies);
	}
	
	@Test
	public void testGetRightsAllAvailable() {
		assertEquals("rwx", fileEntry.getRights());
	}
	
	@Test
	public void testGetRightsNotAllAvailable() {
		assertEquals("r-x", sizeTestEntry.getRights());
	}
	
	@Test
	public void testCompareTo() {
		assertTrue(fileEntry.compareTo(sizeTestEntry)>0);
		assertTrue(sizeTestEntry.compareTo(fileEntry)<0);
		assertTrue(sizeTestEntry.compareTo(sizeTestEntry)==0);
	}
}
