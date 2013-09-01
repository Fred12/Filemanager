package com.kandinsky.gui.splitPane;

import com.kandinsky.gui.ButtonBar;
import com.kandinsky.gui.FolderAnalyser;
import com.kandinsky.gui.FolderDetailPanel;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.gui.favorites.FavoritesPanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * Rechte Panel-Seite. Bisher noch nicht gefüllt.
 * @author schmidtb
 */
public class RightSidePanel extends SidePanel {

	private static final long serialVersionUID = 744794461134800726L;
	
	private FolderNamePanel folderNamePanel;
	private FolderDetailPanel folderDetailPanel;
	private TableAndFavoritesSplitPane splitPane;
	private FolderAnalyser folderAnalyserPanel;
	private ButtonBar buttonBar;

	public RightSidePanel() throws Exception {
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
	public TableAndFavoritesSplitPane getTableAndFavoritesSplitPane() throws Exception {
		if (splitPane == null)
			splitPane = TableAndFavoritesSplitPane.onRightSide(new FavoritesPanel(sideFunctionsHelper), new FileListTable(sideFunctionsHelper));
		return splitPane;
	}

	@Override
	protected FolderAnalyser getFolderAnalyserPanel() {
		if(folderAnalyserPanel == null)
			folderAnalyserPanel = FolderAnalyser.onRightSide();
		return folderAnalyserPanel;
	}

	@Override
	protected ButtonBar getButtonBar() {
		if(buttonBar == null)
			buttonBar = new ButtonBar(sideFunctionsHelper);
		return buttonBar;
	}
}
