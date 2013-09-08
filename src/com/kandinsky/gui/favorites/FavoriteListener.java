package com.kandinsky.gui.favorites;

import com.kandinsky.objects.FileEntry;

/**
 * Interface zur Bereitstellung der Favorites-Funktionen zum loeschen und ausfuehren eines Favoriten
 * @author Benne
 */
public interface FavoriteListener {

	public void removeFavorite(FileEntry fileEntry);
	
	public void execute(FileEntry fileEntry);
}
