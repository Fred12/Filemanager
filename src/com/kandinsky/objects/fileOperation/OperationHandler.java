package com.kandinsky.objects.fileOperation;

import com.kandinsky.gui.splitPane.SidePanel;

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
	
	public abstract void createNewFile();
	
	public abstract void createNewFolder();
	
	public abstract void copySelectedFileEntries();

	public abstract void moveSelectedFileEntries();

	public abstract void deleteSelectedFileEntries();
	
}
