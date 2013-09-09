package com.kandinsky.gui.fileList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.pmw.tinylog.Logger;

import com.kandinsky.conn.FTPConnectionHandler;
import com.kandinsky.objects.FTPEntry;
import com.kandinsky.objects.FTPList;
import com.kandinsky.objects.FileEntry;
import com.kandinsky.objects.FunctionsHelper;
import com.kandinsky.objects.SideFunctionsHelper;

public class FileListPopUpMenu extends JPopupMenu {

	private static final String CLOSE_FTP_CONNECTION = "FTP-Verbindung schliessen";

	private static final long serialVersionUID = 3674741050693322059L;

	private static final String ADD_TO_FAVORITES = "Zu Favoriten hinzufuegen";
	private static final String ADD_CURRENT_FOLDER_TO_FAVORITES = "Aktuellen Ordner zu Favoriten hinzufuegen";
	private static final String COPY_SELECTED_FILES = "Selektierte Datein kopieren";
	private static final String MOVE_SELECTED_FILES = "Selektierte Dateien verschieben";
	private static final String REMOVE_SELECTED_FILES = "Selektierte Dateien entfernen";
	private static final String NEW_FILE = "Neue Datei anlegen";
	private static final String NEW_FOLDER = "Neuen Ordner anlegen";
	private static final String RENAME = "Umbenennen";
	private static final String CONNECT_TO_FTP = "Mit FTP connecten";
	private FileListPopUpMouseListener mouseListener;
	private FileListTable table;
	private PopUpActionListener actionListener;
	private SideFunctionsHelper sideFunctionsHelper;

	public FileListPopUpMenu(FileListTable table, SideFunctionsHelper sideFunctionsHelper) {
		this.table = table;
		this.sideFunctionsHelper = sideFunctionsHelper;
		mouseListener = new FileListPopUpMouseListener();
		actionListener = new PopUpActionListener();
		createAndAddSubMenuItem(this, ADD_TO_FAVORITES);
		createAndAddSubMenuItem(this, COPY_SELECTED_FILES);
		createAndAddSubMenuItem(this, MOVE_SELECTED_FILES);
		createAndAddSubMenuItem(this, REMOVE_SELECTED_FILES);
		createAndAddSubMenuItem(this, NEW_FILE);
		createAndAddSubMenuItem(this, NEW_FOLDER);
		createAndAddSubMenuItem(this, RENAME);
		createAndAddSubMenuItem(this, ADD_CURRENT_FOLDER_TO_FAVORITES);
		createAndAddFTPMenu();
	}

	private void createAndAddSubMenuItem(JComponent menu, String titel){
		JMenuItem newMenuItem = new JMenuItem(titel);
		newMenuItem.setActionCommand(titel);
		menu.add(newMenuItem);
		newMenuItem.addActionListener(actionListener);
	}
	
	private void createAndAddSubMenuItems(JComponent menu, String[] items){
		for(String entry : items){
			createAndAddSubMenuItem(menu, entry);
		}
	}
	
	private void createAndAddFTPMenu(){
		String[] ftpNameList = FTPList.getInstance().getNames();
		if(ftpNameList.length!=0){
			if(FTPConnectionHandler.getInstance().isConnected()){
				createAndAddSubMenuItem(this, CLOSE_FTP_CONNECTION);
			} else {
				JMenu ftpMenu = new JMenu(CONNECT_TO_FTP);
				this.add(ftpMenu);
				createAndAddSubMenuItems(ftpMenu, ftpNameList);		
			}
		}
	}

	public MouseListener getMouseListener() {
		return mouseListener;
	}

	private class FileListPopUpMouseListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent event) {
			contextMenuAction(event);
		}

		@Override
		public void mousePressed(MouseEvent event) {
			contextMenuAction(event);
		}

		private void contextMenuAction(MouseEvent event) {
			if (event.isPopupTrigger()) {
				table.showPopup(event.getPoint());
			}
		}
	}

	private class PopUpActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			switch (event.getActionCommand()) {
				case ADD_TO_FAVORITES: {
					FunctionsHelper.addFavorite(table.getEntryOfCurrentPopup());
					break;
				}
				case COPY_SELECTED_FILES: {
					sideFunctionsHelper.copySelectedFilesToOtherSide();
					break;
				}
				case MOVE_SELECTED_FILES: {
					sideFunctionsHelper.moveSelectedFilesToOtherSide();
					break;
				}
				case REMOVE_SELECTED_FILES: {
					sideFunctionsHelper.deleteSelectedFiles();
					break;
				}
				case NEW_FILE: {
					sideFunctionsHelper.createNewFile();
					break;
				}
				case NEW_FOLDER: {
					sideFunctionsHelper.createNewFolder();
					break;
				}
				case RENAME: {
					sideFunctionsHelper.rename(table.getEntryOfCurrentPopup());
					break;
				}
				case ADD_CURRENT_FOLDER_TO_FAVORITES: {
					FileEntry folderFileEntry = new FileEntry(table.getCurrentFolderName());
					FunctionsHelper.addFavorite(folderFileEntry);
					break;
				}
				case CLOSE_FTP_CONNECTION: {
					FTPConnectionHandler.getInstance().disconnect();
					break;
				}
				default: {
					// Hier muessen die FTPs ueberprueft werden
					// geht wahrscheinlich auch anders, aber wir sind ja Fummler
					FTPList list = FTPList.getInstance();
					for(FTPEntry nextEntry :list){
						if(event.getActionCommand().equals(nextEntry.getName())){
							try {
								FTPConnectionHandler.getInstance().connect(nextEntry);
							} catch (Exception e) {
								Logger.info("Connection versuch mislungen!", e);
							}
							break;
						}
					}
				}
			}
		}
	}
}
