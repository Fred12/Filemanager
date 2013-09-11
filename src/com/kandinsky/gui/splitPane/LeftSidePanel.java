package com.kandinsky.gui.splitPane;

import com.kandinsky.gui.ButtonBar;
import com.kandinsky.gui.FolderAnalyser;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.gui.favorites.FavoritesPanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * Linke Panel-Seite.
 * @author schmidtb
 */
public class LeftSidePanel extends SidePanel {

	private static final long serialVersionUID = 7730356958423920630L;
	
	private FolderNamePanel folderNamePanel;
	private TableAndFavoritesSplitPane splitPane;
	private FolderAnalyser folderAnalyserPanel;
	private ButtonBar buttonBar;

	public LeftSidePanel() throws Exception {
		super();
	}

	@Override
	public FolderNamePanel getFolderNamePanel() {
		if (folderNamePanel == null)
			folderNamePanel = FolderNamePanel.onLeftSide(sideFunctionsHelper);
		return folderNamePanel;
	}

	@Override
	public TableAndFavoritesSplitPane getTableAndFavoritesSplitPane() {
		if (splitPane == null){
			splitPane = TableAndFavoritesSplitPane.onLeftSide(new FavoritesPanel(sideFunctionsHelper), new FileListTable(sideFunctionsHelper));
		}
		return splitPane;
	}

	@Override
	protected FolderAnalyser getFolderAnalyserPanel() {
		if(folderAnalyserPanel == null)
			folderAnalyserPanel = FolderAnalyser.onLeftSide();
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
		return Side.LEFT;
	}

}
