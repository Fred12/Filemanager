package com.kandinsky.gui.splitPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.kandinsky.gui.FolderDetailPanel;
import com.kandinsky.gui.FolderNamePanel;

/**
 * Handelt den Panel-Aufbau innerhalb der Splitpane. Sollte so dynamisch sein, dass von den abgeleiteten Klassen nur noch versch. Funktionen aufgerufen werden müssen,
 * der Rest passiert von alleine.
 * @author Benne
 */
public abstract class SidePanel extends JPanel {


	protected SidePanel() throws Exception {
		this.setLayout(new GridBagLayout());
		this.add(getTableAndFavoritesSplitPane(), getSplitPaneConstraints());
		this.add(getFolderNamePanel(), getFolderNameConstraints());
		this.add(getFolderDetailsPanel(), getFolderDetailsConstraints());
	}

	private GridBagConstraints getFolderNameConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		return gbc;
	}

	private GridBagConstraints getSplitPaneConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.8;
		return gbc;
	}

	private GridBagConstraints getFolderDetailsConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		return gbc;
	}
	
	protected abstract TableAndFavoritesSplitPane getTableAndFavoritesSplitPane() throws Exception;

	/**
	 * @return FolderNamePanel, welches Oberhalb der Tabelle hin soll
	 */
	protected abstract FolderNamePanel getFolderNamePanel();
	

	/**
	 * @return das Ordner-Detail-Panel
	 */
	protected abstract FolderDetailPanel getFolderDetailsPanel();

}
