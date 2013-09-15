package com.kandinsky.objects.fileOperation;

import java.io.IOException;

import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.FileEntry;

/**
 * Gibt verschiedene Datei-Operationen vor.
 * @author Benne
 */
public abstract class OperationHandler {

	protected final SidePanel sidePanel;

	public OperationHandler(SidePanel sidePanel) {
		this.sidePanel = sidePanel;
	}

	/**
	 * Aendert einen Ordner und gibt den neuen aktuellen Pfad zurueck
	 * @param folderName
	 * @param addFolder
	 * @return neuer aktueller Pfad
	 * @throws FolderNotFoundException, falls der Ordner nicht gefunden wurde
	 */
	public abstract String switchFolder(String folderName, boolean addFolder) throws FolderNotFoundException;

	/**
	 * erstellt eine neue Datei.
	 */
	public abstract void createNewFile();

	/**
	 * Erstellt einen neuen Ordner
	 */
	public abstract void createNewFolder();

	/**
	 * Kopiert selektierte Datei-Einträge.
	 */
	public abstract void copySelectedFileEntries();

	/**
	 * Verschiebt selektierte Datei-Einträge.
	 */
	public abstract void moveSelectedFileEntries();

	/**
	 * Löscht selektierte Datei-Einträge.
	 */
	public abstract void deleteSelectedFileEntries();

	/**
	 * Versucht einen gegebenen Datei/Ordner-Eintrag umzubenennen
	 * @param fileEntry
	 * @param newFileName
	 * @throws IOException
	 */
	public abstract void rename(FileEntry fileEntry, String newFileName) throws IOException;

}
