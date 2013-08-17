package com.kandinsky.gui.splitPane;

import com.kandinsky.gui.FavoritesPanel;
import com.kandinsky.gui.FolderDetailPanel;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * Rechte Panel-Seite. Bisher noch nicht gefüllt.
 * @author schmidtb
 */
public class RightSidePanel extends SidePanel {
	
	private FileListTable fileListTable;
	private FolderNamePanel folderNamePanel;
	private FolderDetailPanel folderDetailPanel;
	private FavoritesPanel favoritesPanel;

	public RightSidePanel() throws Exception {
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
		return 0;
	}

	@Override
	protected int getFavoritesXPositon() {
		return 1;
	}
}
