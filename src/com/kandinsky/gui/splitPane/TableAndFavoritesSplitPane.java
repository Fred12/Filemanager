package com.kandinsky.gui.splitPane;

import javax.swing.JSplitPane;

import com.kandinsky.gui.favorites.FavoritesPanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * SplitPane zur Unterteilung von Favorites und der Tabelle.
 * @author Benne
 */
public class TableAndFavoritesSplitPane extends JSplitPane {

	private static final long serialVersionUID = -2806563759554923249L;
	
	private FavoritesPanel favoritesPanel;
	private FileListTable table;

	/**
	 * Linke Seite
	 * @param favoritesPanel
	 * @param table
	 * @throws Exception 
	 */
	private TableAndFavoritesSplitPane(FavoritesPanel favoritesPanel, FileListTable table) {
		super(JSplitPane.HORIZONTAL_SPLIT, favoritesPanel, table.surroundedWithPane());
		this.setResizeWeight(0.2); 
		initSplitPane(favoritesPanel, table);
	}

	/**
	 * Rechte Seite
	 * @param favoritesPanel
	 * @param table
	 * @throws Exception 
	 */
	private TableAndFavoritesSplitPane(FileListTable table, FavoritesPanel favoritesPanel) {
		super(JSplitPane.HORIZONTAL_SPLIT, table.surroundedWithPane(), favoritesPanel);
		this.setResizeWeight(0.8); 
		this.setContinuousLayout(true);
		initSplitPane(favoritesPanel, table);
	}

	private void initSplitPane(FavoritesPanel favoritesPanel, FileListTable table) {
		this.setOneTouchExpandable(true);
		this.favoritesPanel = favoritesPanel;
		this.table = table;
	}

	public static TableAndFavoritesSplitPane onLeftSide(FavoritesPanel favoritesPanel, FileListTable table) {
		return new TableAndFavoritesSplitPane(favoritesPanel, table);
	}

	public static TableAndFavoritesSplitPane onRightSide(FavoritesPanel favoritesPanel, FileListTable table) {
		return new TableAndFavoritesSplitPane(table, favoritesPanel);
	}

	public FavoritesPanel getFavoritesPanel() {
		return favoritesPanel;
	}

	public FileListTable getTable() {
		return table;
	}
	
	public String getCurrentFolderName(){
		return table.getCurrentFolderName();
	}
}
