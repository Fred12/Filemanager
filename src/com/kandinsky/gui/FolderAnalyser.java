package com.kandinsky.gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Klasse die anzeigt wie viele Dateien selektiert sind und den freien Speicher
 * errechnet und in einer Progressbar anzeigt.
 * 
 * @author Talha Kalabalik
 * @version 24.08.2013
 */

public class FolderAnalyser extends JPanel {

	private static final long serialVersionUID = 3207380278696253272L;
	
	private static final File fileSystem = new File("C:\\");
	File file;
	File[] selectedFiles=new File[0];
	private int allListetFilesCount;
	float FreeSpace;
	float TotalSpace;
	private JProgressBar hardDiskSpace;
	private JLabel filesCount;
	
	public static FolderAnalyser onLeftSide(){
		return new FolderAnalyser(0, 1);
	}
	
	public static FolderAnalyser onRightSide(){
		return new FolderAnalyser(1, 0);
	}

	/**
	 * Create the panel.
	 */
	private FolderAnalyser(int filesCountGridX, int hardDiskSpaceGridX) {
		
		filesCount = new JLabel("");
		filesCount.setHorizontalAlignment(JLabel.CENTER);
		hardDiskSpace = new JProgressBar();
		
		setLayout(new GridBagLayout());
		GridBagConstraints filesCountConstraints = filesCountConstraints(filesCountGridX);
		add(filesCount, filesCountConstraints);
		GridBagConstraints hardDiskSpaceConstraints = hardDiskSpaceConstraints(hardDiskSpaceGridX);
		add(hardDiskSpace, hardDiskSpaceConstraints);
	}

	private GridBagConstraints filesCountConstraints(int gridx) {
		GridBagConstraints filesCountConstraints = new GridBagConstraints();
		filesCountConstraints.gridx=gridx;
		filesCountConstraints.weighty=1.0;
		filesCountConstraints.weightx=0.5;
		filesCountConstraints.fill= GridBagConstraints.BOTH;
		return filesCountConstraints;
	}

	private GridBagConstraints hardDiskSpaceConstraints(int gridx) {
		GridBagConstraints hardDiskSpaceConstraints = new GridBagConstraints();
		hardDiskSpaceConstraints.gridx=gridx;
		hardDiskSpaceConstraints.weighty=1.0;
		hardDiskSpaceConstraints.weightx=0.5;
		hardDiskSpaceConstraints.fill= GridBagConstraints.BOTH;
		return hardDiskSpaceConstraints;
	}

	/**
	 * Methode die die Anzeige Progressbar und Textfield updatet
	 */
	public void refreshAll() {
		filesCount.setText(refreshText());
		hardDiskSpace.setString(refreshProgressbar());
		hardDiskSpace.setValue(getProgressbarProcent());
	}

	/**
	 * gibt einen String der Form "x von y Dateien makiert" und eine Liste der
	 * makierten Dateien
	 * 
	 * @return Text welcher in filesCount angezeigt wird
	 */
	public String refreshText() {
		return (selectedFiles.length + " von " + allListetFilesCount	+ " Dateien makiert.");
	}

	/**
	 * berechnet den freien- und gesamt Speicher und gibt ein Text zurück
	 * welcher in der Progressbar angezeigt wird "x GB / y GB"
	 * 
	 * @return Text der in hardDiskSpace angezeigt wird
	 */
	public String refreshProgressbar() {
		this.FreeSpace = Math.round(fileSystem.getFreeSpace() / 1024 / 1024 / 1024);
		this.TotalSpace = Math.round(fileSystem.getTotalSpace() / 1024 / 1024 / 1024);
		hardDiskSpace.setStringPainted(true);
		return "Frei: " + FreeSpace + " GB / "+ TotalSpace + " GB";
	}

	/**
	 * Berechnet die Prozentzahl vom freien Speicher zuvor muss zwingend
	 * refreshProgressbar() aufgerufen werden damit FreeSpace / TotalSpace
	 * gesetzt wird.
	 * 
	 * @return gibt die Prozentzahl vom freien Speicher zurück
	 */
	public int getProgressbarProcent() {
		return Math.round(100 - ((FreeSpace / TotalSpace) * 100));
	}

	public float getFreeSpace() {
		return this.FreeSpace;
	}

	public float getTotalSpace() {
		return this.TotalSpace;
	}
	
	public void setAllListedFilesCount(int allListedFilesCount){
		this.allListetFilesCount = allListedFilesCount;
		refreshAll();
	}

	public File[] getSelectedFiles() {
		return selectedFiles;
	}
	
	/**
	 * bei jedem klick auf eine oder mehrere Datei (Dateien auswählen) muss
	 * diese Methode ausgeführt werden
	 * 
	 * @param selectedFiles
	 */

	public void setSelectedFiles(File[] selectedFiles) {
		this.selectedFiles = selectedFiles;
		refreshAll();
	}
}