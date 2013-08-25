package com.kandinsky.gui.favorites;

import javax.swing.JButton;

import com.kandinsky.objects.FileEntry;

/**
 * Repräsentiert genau einen Favorite-Eintrag und stellt ihn als einen Button mit passendem Icon dar.
 * @author Benne
 */
public class Favorite extends JButton {

	/** der FileEntry auf den der Button referenziert */
	private FileEntry fileEntry;
	
	/**
	 * Erstellt den Button und setzt sowohl Text als auch Item dafür
	 * @param fileEntry
	 */
	public Favorite(FileEntry fileEntry){
		super(fileEntry.getName());
		this.fileEntry = fileEntry;
		this.setIcon(fileEntry.getIcon());
	}
	
	/**
	 * @return den passenden FileEntry
	 */
	public FileEntry getFileEntry(){
		return fileEntry;
	}
	
	@Override
	public String toString() {
		return fileEntry.getName();
	}
}
