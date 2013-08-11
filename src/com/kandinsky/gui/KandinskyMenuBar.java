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
		menuItemReload.addActionListener(functions);
		
		JMenuItem menuItemSearch = new JMenuItem("Suchen");
		menuFile.add(menuItemSearch);
		menuItemSearch.addActionListener(functions);
		
		JMenuItem menuItemSwapFolderViewLeftRight = new JMenuItem("Ordner rechts/links tauschen");
		menuFile.add(menuItemSwapFolderViewLeftRight);
		menuItemSwapFolderViewLeftRight.addActionListener(functions);		
		
		JSeparator separator_5 = new JSeparator();
		menuFile.add(separator_5);
		
		JMenuItem menuItemImportFavorites = new JMenuItem("Favoriten importieren");
		menuFile.add(menuItemImportFavorites);
		menuItemImportFavorites.addActionListener(functions);
		
		JMenuItem menuItemExportFavorites = new JMenuItem("Favoriten exportieren");
		menuFile.add(menuItemExportFavorites);
		menuItemExportFavorites.addActionListener(functions);
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		JMenuItem menuItemCloseProgram = new JMenuItem("Schliessen");
		menuFile.add(menuItemCloseProgram);
		menuItemCloseProgram.addActionListener(functions);
		
		// Edit menu section
		
		JMenu menuEdit = new JMenu("Bearbeiten");
		this.add(menuEdit);
		
		JMenuItem menuItemSelectAll = new JMenuItem("Alles ausw\u00E4hlen");
		menuEdit.add(menuItemSelectAll);
		menuItemSelectAll.addActionListener(functions);
		
		JMenuItem menuItemSelectNothing = new JMenuItem("Nichts ausw\u00E4hlen");
		menuEdit.add(menuItemSelectNothing);
		menuItemSelectNothing.addActionListener(functions);
		
		JMenuItem menuItemInvertSelection = new JMenuItem("Auswahl umkehren");
		menuEdit.add(menuItemInvertSelection);
		menuItemInvertSelection.addActionListener(functions);
		
		JSeparator separator_6 = new JSeparator();
		menuEdit.add(separator_6);
		
		JMenuItem menuItemCopy = new JMenuItem("Kopieren");
		menuEdit.add(menuItemCopy);
		menuItemCopy.addActionListener(functions);
		
		JMenuItem menuItemPaste = new JMenuItem("Einf\u00FCgen");
		menuEdit.add(menuItemPaste);
		menuItemPaste.addActionListener(functions);
		
		JMenuItem menuItemCut = new JMenuItem("Ausschneiden");
		menuEdit.add(menuItemCut);
		menuItemCut.addActionListener(functions);
		
		JMenuItem menuItemDelete = new JMenuItem("L\u00F6schen");
		menuEdit.add(menuItemDelete);
		menuItemDelete.addActionListener(functions);
		
		JSeparator separator_1 = new JSeparator();
		menuEdit.add(separator_1);
		
		JMenuItem menuItemCreateFolder = new JMenuItem("Ordner anlegen");
		menuEdit.add(menuItemCreateFolder);
		menuItemCreateFolder.addActionListener(functions);
		
		JMenuItem menuItemCreateShortcut = new JMenuItem("Verkn\u00FCpfung anlegen");
		menuEdit.add(menuItemCreateShortcut);
		menuItemCreateShortcut.addActionListener(functions);
		
		JMenuItem menuItemCreateEmptyFile = new JMenuItem("Leere Datei anlegen");
		menuEdit.add(menuItemCreateEmptyFile);
		menuItemCreateEmptyFile.addActionListener(functions);
		
		// FTP Menu Section
		
		JMenu menuFTP = new JMenu("FTP");
		this.add(menuFTP);
		
		JMenuItem menuItemEditServerList = new JMenuItem("Server verwalten");
		menuFTP.add(menuItemEditServerList);
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
		menuItemFTPCloseConnection.addActionListener(functions);
		
		// Settings menu section
		
		JMenu menuSettings = new JMenu("Einstellungen");
		this.add(menuSettings);
		
		JMenuItem menuItemGeneralOptions = new JMenuItem("Allgemeine Optionen");
		menuSettings.add(menuItemGeneralOptions);
		menuItemGeneralOptions.addActionListener(functions);
		
		JMenuItem menuItemEditFavorites = new JMenuItem("Favoriten verwalten");
		menuSettings.add(menuItemEditFavorites);
		menuItemEditFavorites.addActionListener(functions);
		
		JMenuItem menuItemFTPOptions = new JMenuItem("FTP Optionen");
		menuSettings.add(menuItemFTPOptions);
		menuItemFTPOptions.addActionListener(functions);
		
		JSeparator separator_4 = new JSeparator();
		menuSettings.add(separator_4);
		
		JCheckBoxMenuItem menuCheckboxToggleShowHiddenFiles = new JCheckBoxMenuItem("Versteckte Dateien anzeigen");
		menuSettings.add(menuCheckboxToggleShowHiddenFiles);
		menuCheckboxToggleShowHiddenFiles.addActionListener(functions);
		
		JCheckBoxMenuItem menuCheckboxToggleShowFavorites = new JCheckBoxMenuItem("Favoritenliste anzeigen");
		menuSettings.add(menuCheckboxToggleShowFavorites);
		menuCheckboxToggleShowFavorites.addActionListener(functions);
		
		// Help Menu section
		
		JMenu menuHelp = new JMenu("Hilfe");
		this.add(menuHelp);
		
		JMenuItem menuItemHelp = new JMenuItem("Hilfe");
		menuHelp.add(menuItemHelp);
		menuItemHelp.addActionListener(functions);
		
		JMenuItem menuItemAbout = new JMenuItem("\u00DCber Kandinsky File Manager");
		menuHelp.add(menuItemAbout);
		menuItemAbout.addActionListener(functions);
		
	}
	
}
