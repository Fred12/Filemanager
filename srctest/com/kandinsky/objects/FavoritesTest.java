package com.kandinsky.objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class FavoritesTest {

	@Test
	public void testSingleton() {
		Favorites first = Favorites.getInstance();
		Favorites second = Favorites.getInstance();
		assertTrue(first==second);
	}

}
