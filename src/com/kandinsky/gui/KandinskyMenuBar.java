package com.kandinsky.gui;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class KandinskyMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4388114126509784209L;

	public KandinskyMenuBar() {
		
		KandinskyMenuBarFunctions functions = new KandinskyMenuBarFunctions(); 
		
		JMenu menuFile = new JMenu("Datei");
		this.add(menuFile);
				
		JMenuItem menuItemReload = new JMenuItem("Aktualisieren");
		menuFile.add(menuItemReload);
		menuItemReload.setActionCommand(KandinskyMenuBarFunctions.RELOAD);
		menuItemReload.addActionListener(functions);
		
		JMenuItem menuItemSearch = new JMenuItem("Suchen");
		menuFile.add(menuItemSearch);
		menuItemSearch.setActionCommand(KandinskyMenuBarFunctions.SEARCH);
		menuItemSearch.addActionListener(functions);
		
		JMenuItem menuItemSwapFolderViewLeftRight = new JMenuItem("Ordner rechts/links tauschen");
		menuFile.add(menuItemSwapFolderViewLeftRight);
		menuItemSwapFolderViewLeftRight.setActionCommand(KandinskyMenuBarFunctions.SWAP_FOLDERS_LEFT_RIGHT);
		menuItemSwapFolderViewLeftRight.addActionListener(functions);		
		
		JSeparator separator_5 = new JSeparator();
		menuFile.add(separator_5);
		
		JMenuItem menuItemImportFavorites = new JMenuItem("Favoriten importieren");
		menuFile.add(menuItemImportFavorites);
		menuItemImportFavorites.setActionCommand(KandinskyMenuBarFunctions.IMPORT_FAVORITES);
		menuItemImportFavorites.addActionListener(functions);
		
		JMenuItem menuItemExportFavorites = new JMenuItem("Favoriten exportieren");
		menuFile.add(menuItemExportFavorites);
		menuItemExportFavorites.setActionCommand(KandinskyMenuBarFunctions.EXPORT_FAVORITES);
		menuItemExportFavorites.addActionListener(functions);
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		JMenuItem menuItemCloseProgram = new JMenuItem("Schliessen");
		menuFile.add(menuItemCloseProgram);
		menuItemCloseProgram.setActionCommand(KandinskyMenuBarFunctions.CLOSE_PROGRAM);
		menuItemCloseProgram.addActionListener(functions);
		
		// Edit menu section
		
		JMenu menuEdit = new JMenu("Bearbeiten");
		this.add(menuEdit);
		
		JMenuItem menuItemSelectAll = new JMenuItem("Alles ausw\u00E4hlen");
		menuEdit.add(menuItemSelectAll);
		menuItemSelectAll.setActionCommand(KandinskyMenuBarFunctions.SELECT_ALL);
		menuItemSelectAll.addActionListener(functions);
		
		JMenuItem menuItemSelectNothing = new JMenuItem("Nichts ausw\u00E4hlen");
		menuEdit.add(menuItemSelectNothing);
		menuItemSelectNothing.setActionCommand(KandinskyMenuBarFunctions.SELECT_NOTHING);
		menuItemSelectNothing.addActionListener(functions);
		
		JMenuItem menuItemInvertSelection = new JMenuItem("Auswahl umkehren");
		menuEdit.add(menuItemInvertSelection);
		menuItemInvertSelection.setActionCommand(KandinskyMenuBarFunctions.INVERT_SELECTION);
		menuItemInvertSelection.addActionListener(functions);
		
		JSeparator separator_6 = new JSeparator();
		menuEdit.add(separator_6);
		
		JMenuItem menuItemCopy = new JMenuItem("Kopieren");
		menuEdit.add(menuItemCopy);
		menuItemCopy.setActionCommand(KandinskyMenuBarFunctions.COPY_OBJECTS);
		menuItemCopy.addActionListener(functions);
		
		JMenuItem menuItemPaste = new JMenuItem("Einf\u00FCgen");
		menuEdit.add(menuItemPaste);
		menuItemPaste.setActionCommand(KandinskyMenuBarFunctions.PASTE_OBJECTS);
		menuItemPaste.addActionListener(functions);
		
		JMenuItem menuItemCut = new JMenuItem("Ausschneiden");
		menuEdit.add(menuItemCut);
		menuItemCut.setActionCommand(KandinskyMenuBarFunctions.CUT_OBJECTS);
		menuItemCut.addActionListener(functions);
		
		JMenuItem menuItemDelete = new JMenuItem("L\u00F6schen");
		menuEdit.add(menuItemDelete);
		menuItemDelete.setActionCommand(KandinskyMenuBarFunctions.DELETE_OBJECTS);
		menuItemDelete.addActionListener(functions);
		
		JSeparator separator_1 = new JSeparator();
		menuEdit.add(separator_1);
		
		JMenuItem menuItemCreateFolder = new JMenuItem("Ordner anlegen");
		menuEdit.add(menuItemCreateFolder);
		menuItemCreateFolder.setActionCommand(KandinskyMenuBarFunctions.CREATE_FOLDER);
		menuItemCreateFolder.addActionListener(functions);
		
		JMenuItem menuItemCreateShortcut = new JMenuItem("Verkn\u00FCpfung anlegen");
		menuEdit.add(menuItemCreateShortcut);
		menuItemCreateShortcut.setActionCommand(KandinskyMenuBarFunctions.CREATE_SHORTCUT);
		menuItemCreateShortcut.addActionListener(functions);
		
		JMenuItem menuItemCreateEmptyFile = new JMenuItem("Leere Datei anlegen");
		menuEdit.add(menuItemCreateEmptyFile);
		menuItemCreateEmptyFile.setActionCommand(KandinskyMenuBarFunctions.CREATE_EMPTY_FILE);
		menuItemCreateEmptyFile.addActionListener(functions);
		
		// FTP Menu Section
		
		JMenu menuFTP = new JMenu("FTP");
		this.add(menuFTP);
		
		JMenuItem menuItemEditServerList = new JMenuItem("Server verwalten");
		menuFTP.add(menuItemEditServerList);
		menuItemEditServerList.setActionCommand(KandinskyMenuBarFunctions.FTP_SHOW_EDIT_SERVERS);
		menuItemEditServerList.addActionListener(functions);
		
		JSeparator separator_2 = new JSeparator();
		menuFTP.add(separator_2);
		
		JMenu menuFTPConnect = new JMenu("Verbinden");
		menuFTP.add(menuFTPConnect);
		menuFTPConnect.addActionListener(functions);
		
		JMenuItem menuItemFTPServerEntryDummy = new JMenuItem("Serverliste hier erzeugen");
		menuFTPConnect.add(menuItemFTPServerEntryDummy);
				
		JMenuItem menuItemFTPCloseConnection = new JMenuItem("Verbindung trennen");
		menuFTP.add(menuItemFTPCloseConnection);
		menuItemFTPCloseConnection.setActionCommand(KandinskyMenuBarFunctions.FTP_DISCONNECT);
		menuItemFTPCloseConnection.addActionListener(functions);
		
		// Settings menu section
		
		JMenu menuSettings = new JMenu("Einstellungen");
		this.add(menuSettings);
		
		JMenuItem menuItemGeneralOptions = new JMenuItem("Allgemeine Optionen");
		menuSettings.add(menuItemGeneralOptions);
		menuItemGeneralOptions.setActionCommand(KandinskyMenuBarFunctions.OPTIONS);
		menuItemGeneralOptions.addActionListener(functions);
		
		JMenuItem menuItemEditFavorites = new JMenuItem("Favoriten verwalten");
		menuSettings.add(menuItemEditFavorites);
		menuItemEditFavorites.setActionCommand(KandinskyMenuBarFunctions.OPTIONS_FAVORITES);
		menuItemEditFavorites.addActionListener(functions);
		
		JMenuItem menuItemFTPOptions = new JMenuItem("FTP Optionen");
		menuSettings.add(menuItemFTPOptions);
		menuItemFTPOptions.setActionCommand(KandinskyMenuBarFunctions.OPTIONS_FTP);
		menuItemFTPOptions.addActionListener(functions);
		
		JSeparator separator_4 = new JSeparator();
		menuSettings.add(separator_4);
		
		JCheckBoxMenuItem menuCheckboxToggleShowHiddenFiles = new JCheckBoxMenuItem("Versteckte Dateien anzeigen");
		menuSettings.add(menuCheckboxToggleShowHiddenFiles);
		menuCheckboxToggleShowHiddenFiles.setActionCommand(KandinskyMenuBarFunctions.TOGGLE_HIDDEN_FOLDERS);
		menuCheckboxToggleShowHiddenFiles.addActionListener(functions);
		
		JCheckBoxMenuItem menuCheckboxToggleShowFavorites = new JCheckBoxMenuItem("Favoritenliste anzeigen");
		menuSettings.add(menuCheckboxToggleShowFavorites);
		menuCheckboxToggleShowFavorites.setActionCommand(KandinskyMenuBarFunctions.TOGGLE_SHOW_FAVORITES);
		menuCheckboxToggleShowFavorites.addActionListener(functions);
		
		// Help Menu section
		
		JMenu menuHelp = new JMenu("Hilfe");
		this.add(menuHelp);
		
		JMenuItem menuItemHelp = new JMenuItem("Hilfe");
		menuHelp.add(menuItemHelp);
		menuItemHelp.setActionCommand(KandinskyMenuBarFunctions.HELP);
		menuItemHelp.addActionListener(functions);
		
		JMenuItem menuItemAbout = new JMenuItem("\u00DCber Kandinsky File Manager");
		menuHelp.add(menuItemAbout);
		menuItemAbout.setActionCommand(KandinskyMenuBarFunctions.ABOUT);
		menuItemAbout.addActionListener(functions);
		
	}
	
}
