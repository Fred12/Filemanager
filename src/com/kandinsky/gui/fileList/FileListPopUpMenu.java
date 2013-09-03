package com.kandinsky.gui.fileList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.kandinsky.objects.FunctionsHelper;

public class FileListPopUpMenu extends JPopupMenu {

	private static final String ADD_TO_FAVORITES = "Add to Favorites";
	private FileListPopUpMouseListener mouseListener;
	private FileListTable table;
	private PopUpActionListener actionListener;

	public FileListPopUpMenu(FileListTable table) {
		this.table = table;
		mouseListener = new FileListPopUpMouseListener();
		actionListener = new PopUpActionListener();
		createAndAddMenuItem(ADD_TO_FAVORITES);
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
				}
			}
			
		}
		
	}
}
