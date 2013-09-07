package com.kandinsky.gui.splitPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JPanel;

import com.kandinsky.gui.ButtonBar;
import com.kandinsky.gui.FolderAnalyser;
import com.kandinsky.gui.FolderNamePanel;
import com.kandinsky.objects.SideFunctionsHelper;

/**
 * Handelt den Panel-Aufbau innerhalb der Splitpane. Sollte so dynamisch sein, dass von den abgeleiteten Klassen nur noch versch. Funktionen aufgerufen werden müssen,
 * der Rest passiert von alleine.
 * @author Benne
 */
public abstract class SidePanel extends JPanel implements Comparable<SidePanel> {

	private static final long serialVersionUID = -7091223933460827761L;
	

	public enum Side {
		LEFT, RIGHT
	}
	
	/** jede Seite erhaelt ihren eigenen Seitenfunktionshelper */
	protected SideFunctionsHelper sideFunctionsHelper;

	protected SidePanel() throws Exception {
		this.sideFunctionsHelper = new SideFunctionsHelper(this);
		this.setLayout(new GridBagLayout());
		this.add(getTableAndFavoritesSplitPane(), getSplitPaneConstraints());
		this.add(getFolderNamePanel(), getFolderNameConstraints());
		this.add(getFolderAnalyserPanel(), getFolderAnalyserPanelConstraints());
		this.add(getButtonBar(), getButtonBarConstraints());
		sideFunctionsHelper.switchFolder(new File("").getAbsolutePath());
	}
	
	public void refresh(){
		sideFunctionsHelper.refresh();
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
	
	private Object getButtonBarConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		return gbc;
	}

	private GridBagConstraints getFolderNameConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		return gbc;
	}

	private GridBagConstraints getSplitPaneConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.7;
		return gbc;
	}
	
	private GridBagConstraints getFolderAnalyserPanelConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		return gbc;
	}
	
	/**
	 * @return SplitPane, die die Tabelle und die Favoritenleiste beinhaltet
	 * @throws Exception
	 */
	public abstract TableAndFavoritesSplitPane getTableAndFavoritesSplitPane();

	/**
	 * @return FolderNamePanel, welches Oberhalb der Tabelle hin soll
	 */
	public abstract FolderNamePanel getFolderNamePanel();
	
	/**
	 * @return das FolderAnalyser-Panel
	 */
	protected abstract FolderAnalyser getFolderAnalyserPanel();
	
	/**
	 * @return die ButtonBar
	 */
	protected abstract ButtonBar getButtonBar();

	/**
	 * Setzt selektierte Dateien auf einer Seite
	 * @param files
	 */
	public void setSelectedFiles(File[] files) {
		getFolderAnalyserPanel().setSelectedFiles(files);
	}

	/**
	 * Setzt die Gesamtanzahl vorhandener Dateien in der Liste auf einer Seite
	 * @param fileCount
	 */
	public void setFileCountInFolder(int fileCount) {
		getFolderAnalyserPanel().setAllListedFilesCount(fileCount);
	}
	
	public String getCurrentFolderName() {
		return getTableAndFavoritesSplitPane().getCurrentFolderName();
	}
	
	/**
	 * @return die Seitenbezeichnung
	 */
	public abstract Side getSide();
	
	@Override
	public int compareTo(SidePanel o) {
		return getSide().compareTo(o.getSide());
	}
}
