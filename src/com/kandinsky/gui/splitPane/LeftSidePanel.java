package com.kandinsky.gui.splitPane;

import com.kandinsky.gui.FolderDetailPanel;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.gui.favorites.FavoritesPanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * Linke Panel-Seite. Bisher noch nicht gefüllt. Unter Umständen könnte man hier noch ein Interface drüber setzen,
 * welches vorgibt, welche Elemente vorhanden sein müssten:
 * - Favoriten
 * - FileListe
 * - FolderDetail
 * - FolderName
 * @author schmidtb
 */
public class LeftSidePanel extends SidePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7730356958423920630L;
	
	private FolderNamePanel folderNamePanel;
	private FolderDetailPanel folderDetailPanel;

	private TableAndFavoritesSplitPane splitPane;

	public LeftSidePanel() throws Exception {
		super();
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
	protected TableAndFavoritesSplitPane getTableAndFavoritesSplitPane() throws Exception {
		if (splitPane == null)
			splitPane = TableAndFavoritesSplitPane.onLeftSide(new FavoritesPanel(), new FileListTable());
		return splitPane;
	}

}
