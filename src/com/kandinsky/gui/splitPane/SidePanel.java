package com.kandinsky.gui.splitPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.kandinsky.gui.FolderDetailPanel;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.objects.SideFunctionsHelper;

/**
 * Handelt den Panel-Aufbau innerhalb der Splitpane. Sollte so dynamisch sein, dass von den abgeleiteten Klassen nur noch versch. Funktionen aufgerufen werden müssen,
 * der Rest passiert von alleine.
 * @author Benne
 */
public abstract class SidePanel extends JPanel {

	private static final long serialVersionUID = -7091223933460827761L;
	
	/** jede Seite erhaelt ihren eigenen Seitenfunktionshelper */
	protected SideFunctionsHelper sideFunctionsHelper;

	protected SidePanel() throws Exception {
		this.sideFunctionsHelper = new SideFunctionsHelper(this);
		this.setLayout(new GridBagLayout());
		this.add(getTableAndFavoritesSplitPane(), getSplitPaneConstraints());
		this.add(getFolderNamePanel(), getFolderNameConstraints());
		this.add(getFolderDetailsPanel(), getFolderDetailsConstraints());
	}
	
	/**
	 * Versucht einen neuen Ordner zu setzen
	 * @param folderName
	 */
	public void setSelectedFolder(String folderName){
		try {
			getTableAndFavoritesSplitPane().getTable().changeFolder(folderName);
		} catch (Exception e) {
			// TODO: Fehlerhandling
			e.printStackTrace();
		}
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
	
	public abstract TableAndFavoritesSplitPane getTableAndFavoritesSplitPane() throws Exception;

	/**
	 * @return FolderNamePanel, welches Oberhalb der Tabelle hin soll
	 */
	protected abstract FolderNamePanel getFolderNamePanel();
	

	/**
	 * @return das Ordner-Detail-Panel
	 */
	protected abstract FolderDetailPanel getFolderDetailsPanel();

}
