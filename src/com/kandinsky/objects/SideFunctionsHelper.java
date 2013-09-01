package com.kandinsky.objects;

import java.io.File;

import com.kandinsky.gui.favorites.FavoriteListener;
import com.kandinsky.gui.splitPane.SidePanel;

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
			sidePanel.getTableAndFavoritesSplitPane().repaint();
		} catch (Exception e) {
			// TODO: ordentliches Fehlerhandling, zB Fehlermeldung in der Info setzen
			e.printStackTrace();
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
}
