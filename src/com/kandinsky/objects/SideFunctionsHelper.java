package com.kandinsky.objects;

import java.io.File;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.favorites.FavoriteListener;
import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.fileOperation.CopyOperator;
import com.kandinsky.objects.fileOperation.DeleteOperator;
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
	public void remove(FileEntry fileEntry) {
		try {
			Favorites favorites = Favorites.getInstance();
			favorites.removeFavorite(fileEntry);
			sidePanel.getTableAndFavoritesSplitPane().getFavoritesPanel().refresh();
		} catch (Exception e) {
			// TODO: ordentliches Fehlerhandling, zB Fehlermeldung in der Info setzen
			e.printStackTrace();
		}
	}

	@Override
	public void execute(FileEntry fileEntry) {
		this.switchFolder(fileEntry.getAbsoluteFileName());
	}
	
	public void copyFilesToOtherSide(File[] fileEntries){
		CopyOperator operator = new CopyOperator(fileEntries, sidePanel);
		operator.execute();
//		Logger.info("Kopiere nun Dateien, Anzahl {0}", fileEntries.length);
//		SidePanel otherSide = FunctionsHelper.getOtherSidePanel(sidePanel);
//		try {
//			for(File nextEntry : fileEntries){
//				Logger.info("Kopiere von {0} nach {1}!", nextEntry.getAbsolutePath(), otherSide.getCurrentFolderName()+nextEntry.getName());
//				Files.copy(Paths.get(nextEntry.getAbsolutePath()), Paths.get(otherSide.getCurrentFolderName()+nextEntry.getName()), REPLACE_EXISTING);	
//			}
//			otherSide.refresh();
//		} catch (Exception e){
//			Logger.error("Copy hat nicht funktioniert!", e);
//			FunctionsHelper.setMessage(Message.COPY_FAILED);
//		}
	}
	
	public void moveFilesToOtherSide(File[] fileEntries){
		MoveOperator operator = new MoveOperator(fileEntries, sidePanel);
		operator.execute();
	}
	public void deleteFiles(File[] fileEntries){
		DeleteOperator operator = new DeleteOperator(fileEntries, sidePanel);
		operator.execute();
	}
}
