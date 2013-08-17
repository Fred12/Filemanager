package com.kandinsky.gui.splitPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JPanel;

import com.kandinsky.gui.FavoritesPanel;
import com.kandinsky.gui.FolderDetailPanel;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.gui.fileList.FileListTable;

/**
 * Handelt den Panel-Aufbau innerhalb der Splitpane. Sollte so dynamisch sein, dass von den abgeleiteten Klassen nur noch versch. Funktionen aufgerufen werden müssen,
 * der Rest passiert von alleine.
 * @author Benne
 */
public abstract class SidePanel extends JPanel {

	private static final int HORIZONTAL_ELEMENTS = 2;

	protected SidePanel() throws Exception {
		this.setLayout(new GridBagLayout());
		FileListTable table = getTable();
		this.add(table.surroundedWithPane(), getTableConstraints());
		this.add(getFolderNamePanel(), getFolderNameConstraints());
		this.add(getFolderDetailsPanel(), getFolderDetailsConstraints());
		this.add(getFavoritesPanel(), getFavoritesConstraints());
		table.changeFolder(new File("").getAbsolutePath());
	}

	private GridBagConstraints getFolderNameConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = HORIZONTAL_ELEMENTS;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		return gbc;
	}

	private GridBagConstraints getTableConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = getTableXPositon();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.8;
		gbc.weighty = 0.8;
		return gbc;
	}

	private GridBagConstraints getFavoritesConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = getFavoritesXPositon();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.2;
		gbc.weighty = 0.8;
		return gbc;
	}

	private GridBagConstraints getFolderDetailsConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = HORIZONTAL_ELEMENTS;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		return gbc;
	}

	/**
	 * @return Tabelle, die angezeigt werden soll.
	 * @throws Exception 
	 */
	protected abstract FileListTable getTable() throws Exception;

	/**
	 * @return FolderNamePanel, welches Oberhalb der Tabelle hin soll
	 */
	protected abstract FolderNamePanel getFolderNamePanel();

	/**
	 * @return das Ordner-Detail-Panel
	 */
	protected abstract FolderDetailPanel getFolderDetailsPanel();

	/**
	 * @return Favoritenleiste
	 */
	protected abstract FavoritesPanel getFavoritesPanel();

	/**
	 * @return Tabellenposition im Grid
	 */
	protected abstract int getTableXPositon();

	/**
	 * @return Favoritenposition im Grid
	 */
	protected abstract int getFavoritesXPositon();

}
