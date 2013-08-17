package com.kandinsky.gui.splitPane;

import com.kandinsky.gui.FolderNamePanel;
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

	public LeftSidePanel() throws Exception {
		super();
	}

	@Override
	protected FileListTable getTable() throws Exception {
		return new FileListTable();
	}

	@Override
	protected FolderNamePanel getFolderNamePanel() {
		return new FolderNamePanel();
	}

}
