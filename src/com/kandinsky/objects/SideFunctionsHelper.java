package com.kandinsky.objects;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.pmw.tinylog.Logger;

import com.kandinsky.conn.FTPConnectionHandler;
import com.kandinsky.gui.ButtonBar;
import com.kandinsky.gui.favorites.FavoriteListener;
import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.fileOperation.CopyOperator;
import com.kandinsky.objects.fileOperation.DeleteOperator;
import com.kandinsky.objects.fileOperation.FileOperator;
import com.kandinsky.objects.fileOperation.MoveOperator;

/**
 * Hier werden die einzelnen Funktionen aufgeschluesselt, die nicht statisch aufrufbar sind, da sie einer Seite zugeordnet sind, es also immer zwei
 * Aufrufarten gibt. Es wird ein SidePanel mitgegeben, auf welches dann in den einzelnen Funktionen zugegriffen werden kann, z. B. um die Tabelle
 * zu erneurn, einen Seitentext neu zu setzen, neue Favoriten hinzuzufuegen etc.
 * @author Benne
 */
public class SideFunctionsHelper implements FavoriteListener{

	/** Uebergebenes SidePanel, auf welches sich die Funktionen bezieht */
	private SidePanel sidePanel;
	private String currentFolderName;
	
	public SideFunctionsHelper(SidePanel sidePanel){
		this.sidePanel = sidePanel;
	}
	
	/**
	 * Aendert einen Ordner-Pfad. Zentrale Anlaufstelle zum Aendern des Pfades, sonst sollte nirgendwo ein switchFolder aufgerufen werden.
	 * @param folderName
	 */
	public void switchFolder(String folderName){
		File folder = new File(folderName);
		if (!folder.isDirectory()){
			Logger.warn("Konnte den Ordner {0} nicht finden!", folderName);
			FunctionsHelper.setMessage(Message.FOLDER_NOT_FOUND);
		} else {
			currentFolderName=folderName;
			List<FileEntry> newEntries = FileEntry.getFileEntryList(folder);
			sidePanel.getTableAndFavoritesSplitPane().getTable().setFileEntries(newEntries);
			this.setFileCountInFolder(newEntries.size());
			sidePanel.getFolderNamePanel().setFolderText(folderName);
			ButtonBar.addFolder(folderName);
			FunctionsHelper.clearMessage();
		}
	}
	
	public String getFolder()  {
		return sidePanel.getFolderNamePanel().getFolderText();
	}
	
	public void refresh(){
		try {
			sidePanel.getTableAndFavoritesSplitPane().getTable().repaint();
			sidePanel.getTableAndFavoritesSplitPane().repaint();
		} catch (Exception e) {
			// TODO: ordentliches Fehlerhandling, zB Fehlermeldung in der Info setzen
			e.printStackTrace();
		}
	}
	
	public void setSelectedFiles(File[] files){
		sidePanel.setSelectedFiles(files);
	}

	public void setFileCountInFolder(int size) {
		sidePanel.setFileCountInFolder(size);
	}

	@Override
	public void removeFavorite(FileEntry fileEntry) {
		try {
			Favorites favorites = Favorites.getInstance();
			favorites.removeFavorite(fileEntry);
			sidePanel.getTableAndFavoritesSplitPane().getFavoritesPanel().refresh();
			FunctionsHelper.setMessage(Message.FAVORITE_REMOVED);
		} catch (Exception e) {
			Logger.error(e, "Konnte Favorit nicht entfernen fuer fileEntry {0}!", fileEntry);
		}
	}

	@Override
	public void execute(FileEntry fileEntry) {
		if(fileEntry.getType()==FileType.DIRECTORY)
			this.switchFolder(fileEntry.getAbsoluteFileName());
	}
	
	public void copySelectedFilesToOtherSide(){
		File[] files = sidePanel.getTableAndFavoritesSplitPane().getTable().getSelectedFiles();
		FileOperator operator = new CopyOperator(files, sidePanel);
		operator.execute();
	}
	
	public void moveSelectedFilesToOtherSide(){
		File[] files = sidePanel.getTableAndFavoritesSplitPane().getTable().getSelectedFiles();
		FileOperator operator = new MoveOperator(files, sidePanel);
		operator.execute();
	}
	
	public void deleteSelectedFiles(){
		File[] files = sidePanel.getTableAndFavoritesSplitPane().getTable().getSelectedFiles();
		FileOperator operator = new DeleteOperator(files, sidePanel);
		operator.execute();
	}
	
	/**
	 * Fragt nach einem Datei-Namen und legt diesen dann an.
	 */
	public void createNewFile(){
		String name = JOptionPane.showInputDialog(sidePanel, "Dateiname", null);
		if (name != null) {
			File file = new File(sidePanel.getCurrentFolderName() + name);
			try {
				file.createNewFile();
				Logger.info("Neue Datei {0} wurde erstellt.", name);
				refresh();
			} catch (IOException e) {
				Logger.error(e, "Anlegen einer neuen Datei war leider nicht moeglich");
				FunctionsHelper.setMessage(Message.CREATE_FILE_FAILED);
			}
		}
	}
	
	/**
	 * Fragt nach einem Ordner-Namen und legt diesen dann an.
	 */
	public void createNewFolder() {
		String name = JOptionPane.showInputDialog(sidePanel, "Ordnername", null);
		if (name != null) {
			File file = new File(sidePanel.getCurrentFolderName() + name);
			boolean created = file.mkdir();
			if (created) {
				Logger.info("Neuer Ordner {0} wurde erstellt.", name);
				refresh();
			} else {
				Logger.error("Anlegen eines neuen Ordners war leider nicht moeglich");
				FunctionsHelper.setMessage(Message.CREATE_FILE_FAILED);
			}
		}
	}

	/**
	 * Fragt nach einem neuen Namen fuer eine Datei/Ordner und versucht dann ein Rename durchzufuehren. Falls die Datei/Ordner bereits vorhanden ist, wird 
	 * entsprechend eine Fehlermeldung angezeigt.
	 * @param fileEntry umzubenennende Datei
	 */
	public void rename(FileEntry fileEntry) {
		String name = JOptionPane.showInputDialog(sidePanel, "Neuer Name", fileEntry.getName());
		if (name != null) {
			try {
				File newFile = new File(currentFolderName + name);
				if (fileEntry.getType() == FileType.DIRECTORY) {
					FileUtils.moveDirectory(fileEntry.getFile(), newFile);
				} else {
					FileUtils.moveFile(fileEntry.getFile(), newFile);
				}
				refresh();
			} catch (IOException e) {
				Logger.error(e, "Datei umbenennen nicht moeglich. Neuer Name: {0}", name);
				FunctionsHelper.setMessage(Message.RENAME_FOLDER_FAILED);
			}
		}
	}
	
	/**
	 * Versucht eine Verbindung mithilfe eines FTP-Namens herzustellen
	 * @param ftpName Konfigurationsname
	 */
	public void connectToFtp(String ftpName){
		try {
			FTPEntry entry = FTPList.getInstance().getConfigByName(ftpName);
			FTPConnectionHandler.getInstance().connect(entry);
			FunctionsHelper.setMessage(Message.FTP_CONNECTED);
		} catch (Exception e) {
			Logger.error("Connection versuch misslungen!", e);
			FunctionsHelper.setMessage(Message.FTP_CONNECT_FAILED);
		}
	}
	
	/**
	 * Schliesst eine FTP-Verbindung falls vorhanden und gibt eine Nachricht aus.
	 */
	public void disconnectFromFtp(){
		FTPConnectionHandler.getInstance().disconnect();
		FunctionsHelper.setMessage(Message.FTP_DISCONNECTED);
	}

	public String getCurrentFolderName() {
		return currentFolderName;
	}
	
	/**
	 * This method will return the users home-path if it's set.
	 * 
	 * @return
	 */
	
}
