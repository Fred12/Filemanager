package com.kandinsky.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Singleton, welches ALLE Favoriten als Datei-Namens-Liste beinhaltet. Diese kann weggespeichert und wieder geladen werden. Das Laden geschieht dabei initial,
 * weggespeichert wird bei jeder Änderung.
 * @author Benne
 */
public class Favorites implements Serializable {

	private static final long serialVersionUID = -4667828043063811476L;
	
	private static final String SERIALISING_FILE_NAME = "Favorites.dat";
	private static Favorites instance;
	private List<String> fileNames;

	/** einziges Favorites-Objekt anlegen */
	private Favorites() {
		fileNames = new LinkedList<>();
	}

	/**
	 * Singleton-Zugriffsstelle
	 * @return einziges Favorites-Objekt
	 */
	public static Favorites getInstance() {
		if (instance == null)
			instance = new Favorites();

		return instance;
	}

	/**
	 * @return die vorhandene Favoriten-Liste als FileEntry-Liste
	 */
	public List<FileEntry> getFavoritesAsFileEntries() {
		List<FileEntry> entries = new LinkedList<>();
		for (String fileName : fileNames) {
			entries.add(new FileEntry(fileName));
		}

		return entries;
	}

	/**
	 * Fügt einen Favorite hinzu und speichert ihn direkt weg
	 * @param entry
	 */
	public void addToFavorites(FileEntry entry) {
		fileNames.add(entry.getAbsoluteFileName());
		save();
	}
	
	/**
	 * Loescht einen Favorite aus der Liste und speichert diese wieder weg
	 * @param entry
	 */
	public void removeFavorite(FileEntry entry){
		fileNames.remove(entry.getAbsoluteFileName());
		save();
	}

	/**
	 * Speichert die Favorites weg. Kann nur von intern aufgerufen werden.
	 */
	private void save() {
		try {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(SERIALISING_FILE_NAME));
				oos.writeObject(this);
				oos.flush();
			} finally {
				oos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lädt alle Favorites aus einer vorhandenen Datei und initialisiert diese im Favorites-Singleton.
	 * @param ois
	 * @throws IOException
	 */
	public static void loadAllFavorites() throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIALISING_FILE_NAME));
			instance = (Favorites) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException e) {
			throw new IOException("No class found. HELP!!");
		} catch (FileNotFoundException e) {
			// TODO: mach irgendwas, was nix kaputt macht, immerhin heisst das nur, dass es noch keine Favorites gibt
		}
	}

}
