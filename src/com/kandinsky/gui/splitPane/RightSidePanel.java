package com.kandinsky.gui.splitPane;

import com.kandinsky.gui.ButtonBar;
import com.kandinsky.gui.FolderAnalyser;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.gui.favorites.FavoritesPanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * Rechte Panel-Seite.
 * @author schmidtb
 */
public class RightSidePanel extends SidePanel {

	private static final long serialVersionUID = 744794461134800726L;
	
	private FolderNamePanel folderNamePanel;
	private TableAndFavoritesSplitPane splitPane;
	private FolderAnalyser folderAnalyserPanel;
	private ButtonBar buttonBar;

	public RightSidePanel() throws Exception {
		super();
	}

	@Override
	public FolderNamePanel getFolderNamePanel() {
		if (folderNamePanel == null)
			folderNamePanel = FolderNamePanel.onRightSide(sideFunctionsHelper);
		return folderNamePanel;
	}

	@Override
	public TableAndFavoritesSplitPane getTableAndFavoritesSplitPane() {
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
	public ButtonBar getButtonBar() {
		if(buttonBar == null)
			buttonBar = new ButtonBar(sideFunctionsHelper);
		return buttonBar;
	}
	
	@Override
	public Side getSide() {
		return Side.RIGHT;
	}
}
