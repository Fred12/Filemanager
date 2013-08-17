package com.kandinsky.gui.splitPane;

import com.kandinsky.gui.FavoritesPanel;
import com.kandinsky.gui.FolderDetailPanel;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * Linke Panel-Seite. Bisher noch nicht gef�llt. Unter Umst�nden k�nnte man hier noch ein Interface dr�ber setzen,
 * welches vorgibt, welche Elemente vorhanden sein m�ssten:
 * - Favoriten
 * - FileListe
 * - FolderDetail
 * - FolderName
 * @author schmidtb
 */
public class LeftSidePanel extends SidePanel {
	private FileListTable fileListTable;
	private FolderNamePanel folderNamePanel;
	private FolderDetailPanel folderDetailPanel;
	private FavoritesPanel favoritesPanel;

	public LeftSidePanel() throws Exception {
		super();
	}

	@Override
	protected FileListTable getTable() throws Exception {
		if (fileListTable == null)
			fileListTable = new FileListTable();
		return fileListTable;
	}

	@Override
	protected FolderNamePanel getFolderNamePanel() {
		if (folderNamePanel == null)
			folderNamePanel = new FolderNamePanel();
		return folderNamePanel;
	}

	@Override
	protected FolderDetailPanel getFolderDetailsPanel() {
		if (folderDetailPanel == null)
			folderDetailPanel = new FolderDetailPanel();
		return folderDetailPanel;
	}
	
	@Override
	protected FavoritesPanel getFavoritesPanel() {
		if (favoritesPanel == null)
			favoritesPanel = new FavoritesPanel();
		return favoritesPanel;
	}

	@Override
	protected int getTableXPositon() {
		return 1;
	}

	@Override
	protected int getFavoritesXPositon() {
		return 0;
	}

}
