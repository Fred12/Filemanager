package com.kandinsky.gui.fileList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.kandinsky.objects.FunctionsHelper;
import com.kandinsky.objects.SideFunctionsHelper;

public class FileListPopUpMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3674741050693322059L;
	
	private static final String ADD_TO_FAVORITES = "Add to Favorites";
	private static final String COPY_SELECTED_FILES = "Copy selected files";
	private static final String MOVE_SELECTED_FILES = "Move selected files";
	private static final String REMOVE_SELECTED_FILES = "Remove selected files";
	private static final String NEW_FILE = "Neue Datei anlegen";
	private static final String NEW_FOLDER = "Neuen Ordner anlegen";
	private FileListPopUpMouseListener mouseListener;
	private FileListTable table;
	private PopUpActionListener actionListener;
	private SideFunctionsHelper sideFunctionsHelper;

	public FileListPopUpMenu(FileListTable table, SideFunctionsHelper sideFunctionsHelper) {
		this.table = table;
		this.sideFunctionsHelper = sideFunctionsHelper;
		mouseListener = new FileListPopUpMouseListener();
		actionListener = new PopUpActionListener();
		createAndAddMenuItem(ADD_TO_FAVORITES);
		createAndAddMenuItem(COPY_SELECTED_FILES);
		createAndAddMenuItem(MOVE_SELECTED_FILES);
		createAndAddMenuItem(REMOVE_SELECTED_FILES);
		createAndAddMenuItem(NEW_FILE);
		createAndAddMenuItem(NEW_FOLDER);
	}

	private void createAndAddMenuItem(String titel) {
		JMenuItem newMenuItem = new JMenuItem(titel);
		newMenuItem.setActionCommand(titel);
		this.add(newMenuItem);
		newMenuItem.addActionListener(actionListener);
	}

	public MouseListener getMouseListener(){
		return mouseListener;
	}

	private class FileListPopUpMouseListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent event){
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
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
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
			}
			
		}
		
	}
}
