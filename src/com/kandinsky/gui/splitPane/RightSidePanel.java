package com.kandinsky.gui.splitPane;

import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * Rechte Panel-Seite. Bisher noch nicht gefüllt.
 * @author schmidtb
 */
public class RightSidePanel extends SidePanel {

	public RightSidePanel() throws Exception {
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
