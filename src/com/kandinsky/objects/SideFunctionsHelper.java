package com.kandinsky.objects;

import java.io.File;
import java.io.IOException;

import org.pmw.tinylog.Logger;

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

	public SideFunctionsHelper(SidePanel sidePanel){
		this.sidePanel = sidePanel;
	}
	
	public void switchFolder(String folderName){
		try {
			sidePanel.getTableAndFavoritesSplitPane().getTable().changeFolder(folderName);
			sidePanel.getFolderNamePanel().setFolderText(folderName);
			sidePanel.getTableAndFavoritesSplitPane().repaint();
			FunctionsHelper.clearMessage();
		} catch (Exception e) {
			Logger.warn(e, "Der angegebene Ordner konnte nicht gefunden werden: "+folderName);
			FunctionsHelper.setMessage(Message.FOLDER_NOT_FOUND);
		}
	}
	
	public void refresh(){
		try {
			sidePanel.getTableAndFavoritesSplitPane().getTable().refresh();
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
	
	public void createNewFile(){
		// TODO: Popup fuer Dateinamen
		File file = new File(sidePanel.getCurrentFolderName()+"NewFile");
		try {
			file.createNewFile();
			refresh();
		} catch (IOException e) {
			Logger.error(e, "Anlegen einer neuen Datei war leider nicht moeglich");
			FunctionsHelper.setMessage(Message.CREATE_FILE_FAILED);
		}
	}
	
	public void createNewFolder() {
		// TODO: Popup fuer Dateinamen
		File file = new File(sidePanel.getCurrentFolderName()+"NewFolder");
		boolean created = file.mkdir();
		if (created) {
			refresh();
		} else {
			Logger.error("Anlegen eines neuen Ordners war leider nicht moeglich");
			FunctionsHelper.setMessage(Message.CREATE_FILE_FAILED);
		}
	}
}
