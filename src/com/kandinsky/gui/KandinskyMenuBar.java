package com.kandinsky.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import com.kandinsky.objects.FunctionsHelper;

public class KandinskyMenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4388114126509784209L;
	
	////////////////////////////////// Menu Item identifier Strings ////////////////////////////
	
	// Menu "File" Items
	public static final String RELOAD 					= "menuItemReload";
	public static final String SEARCH 					= "menuItemSearch";
	public static final String SWAP_FOLDERS_LEFT_RIGHT	= "menuItemSwapFolderViewLeftRight";
	public static final String IMPORT_FAVORITES			= "menuItemImportFavorites";
	public static final String EXPORT_FAVORITES			= "menuItemExportFavorites";
	public static final String CLOSE_PROGRAM			= "menuItemCloseProgram";
	
	// Menu "Edit" Items
	public static final String SELECT_ALL				= "menuItemSelectAll";
	public static final String SELECT_NOTHING			= "menuItemSelectNothing";
	public static final String INVERT_SELECTION			= "menuItemInvertSelection";
	public static final String COPY_OBJECTS				= "menuItemCopy";
	public static final String PASTE_OBJECTS			= "menuItemPaste";
	public static final String CUT_OBJECTS				= "menuItemCut";
	public static final String DELETE_OBJECTS			= "menuItemDelete";
	public static final String CREATE_FOLDER			= "menuItemCreateFolder";
	public static final String CREATE_SHORTCUT			= "menuItemCreateShortcut";
	public static final String CREATE_EMPTY_FILE		= "menuItemCreateEmptyFile";
	
	// Menu "FTP" Items
	public static final String FTP_SHOW_EDIT_SERVERS	= "menuItemEditServerList";
	public static final String FTP_CONNECT_MOST_RECENT	= "ftp_connect_most_recent";
	public static final String FTP_DISCONNECT			= "ftp_disconnect";
	
	
	// Menu "Settings" Items
	public static final String OPTIONS					= "menuItemGeneralOptions";
	public static final String OPTIONS_FAVORITES		= "menuItemEditFavorites";
	public static final String OPTIONS_FTP				= "menuItemFTPOptions";
	public static final String TOGGLE_HIDDEN_FOLDERS	= "toggle_show_hidden_files";
	public static final String TOGGLE_SHOW_FAVORITES	= "toggle_show_favorites";
		
	// Menu "Help" Items
	public static final String HELP						= "menuItemHelp";
	public static final String ABOUT					= "menuItemAbout";
	
	///////////////////////////////////////// Menu item variables (Needed for stuff like graying them out /////////////////
	
	// File Menu
	public JMenu 		menuFile = null;
	public JMenuItem 	menuItemReload = null;
	public JMenuItem	menuItemSearch = null;
	public JMenuItem	menuItemSwapFolderViewLeftRight = null;
	public JMenuItem	menuItemImportFavorites = null;
	public JMenuItem	menuItemExportFavorites = null;
	public JMenuItem	menuItemCloseProgram = null;
	
	// Edit Menu
	public JMenu		menuEdit = null;
	public JMenuItem	menuItemSelectAll = null;
	public JMenuItem	menuItemSelectNothing = null;
	public JMenuItem	menuItemInvertSelection = null;
	public JMenuItem	menuItemCopy = null;
	public JMenuItem	menuItemPaste = null;
	public JMenuItem	menuItemCut = null;
	public JMenuItem	menuItemDelete = null;
	public JMenuItem	menuItemCreateFolder = null;
	public JMenuItem	menuItemCreateShortcut = null;
	public JMenuItem	menuItemCreateEmptyFile = null;
	
	// FTP Menu
	public JMenu		menuFTP = null;
	public JMenuItem	menuItemEditServerList = null;
	
	// Options Menu
	public JMenu		menuOptions = null;
	public JMenuItem	menuItemGeneralOptions = null;
	public JMenuItem	menuItemEditFavorites = null;
	public JMenuItem	menuItemFTPOptions = null;
	
	// Help Menu
	public JMenu		menuHelp = null;
	public JMenuItem	menuItemHelp = null;
	public JMenuItem	menuItemAbout = null;
	
	/**
	 * Creates the MenuBar with all entries
	 */
	public KandinskyMenuBar() {
		
		JMenu menuFile = new JMenu("Datei");
		this.add(menuFile);
				
		menuItemReload = new JMenuItem("Aktualisieren");
		menuFile.add(menuItemReload);
		menuItemReload.setActionCommand(KandinskyMenuBar.RELOAD);
		menuItemReload.addActionListener(this);
		
		menuItemSearch = new JMenuItem("Suchen");
		menuFile.add(menuItemSearch);
		menuItemSearch.setActionCommand(KandinskyMenuBar.SEARCH);
		menuItemSearch.addActionListener(this);
		
		menuItemSwapFolderViewLeftRight = new JMenuItem("Ordner rechts/links tauschen");
		menuFile.add(menuItemSwapFolderViewLeftRight);
		menuItemSwapFolderViewLeftRight.setActionCommand(KandinskyMenuBar.SWAP_FOLDERS_LEFT_RIGHT);
		menuItemSwapFolderViewLeftRight.addActionListener(this);		
		
		JSeparator separator_5 = new JSeparator();
		menuFile.add(separator_5);
		
		menuItemImportFavorites = new JMenuItem("Favoriten importieren");
		menuFile.add(menuItemImportFavorites);
		menuItemImportFavorites.setActionCommand(KandinskyMenuBar.IMPORT_FAVORITES);
		menuItemImportFavorites.addActionListener(this);
		
		menuItemExportFavorites = new JMenuItem("Favoriten exportieren");
		menuFile.add(menuItemExportFavorites);
		menuItemExportFavorites.setActionCommand(KandinskyMenuBar.EXPORT_FAVORITES);
		menuItemExportFavorites.addActionListener(this);
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		menuItemCloseProgram = new JMenuItem("Schliessen");
		menuFile.add(menuItemCloseProgram);
		menuItemCloseProgram.setActionCommand(KandinskyMenuBar.CLOSE_PROGRAM);
		menuItemCloseProgram.addActionListener(this);
		
		// Edit menu section
		
		JMenu menuEdit = new JMenu("Bearbeiten");
		this.add(menuEdit);
		
		menuItemSelectAll = new JMenuItem("Alles ausw\u00E4hlen");
		menuEdit.add(menuItemSelectAll);
		menuItemSelectAll.setActionCommand(KandinskyMenuBar.SELECT_ALL);
		menuItemSelectAll.addActionListener(this);
		
		menuItemSelectNothing = new JMenuItem("Nichts ausw\u00E4hlen");
		menuEdit.add(menuItemSelectNothing);
		menuItemSelectNothing.setActionCommand(KandinskyMenuBar.SELECT_NOTHING);
		menuItemSelectNothing.addActionListener(this);
		
		menuItemInvertSelection = new JMenuItem("Auswahl umkehren");
		menuEdit.add(menuItemInvertSelection);
		menuItemInvertSelection.setActionCommand(KandinskyMenuBar.INVERT_SELECTION);
		menuItemInvertSelection.addActionListener(this);
		
		JSeparator separator_6 = new JSeparator();
		menuEdit.add(separator_6);
		
		menuItemCopy = new JMenuItem("Kopieren");
		menuEdit.add(menuItemCopy);
		menuItemCopy.setActionCommand(KandinskyMenuBar.COPY_OBJECTS);
		menuItemCopy.addActionListener(this);
		
		menuItemPaste = new JMenuItem("Einf\u00FCgen");
		menuEdit.add(menuItemPaste);
		menuItemPaste.setActionCommand(KandinskyMenuBar.PASTE_OBJECTS);
		menuItemPaste.addActionListener(this);
		
		menuItemCut = new JMenuItem("Ausschneiden");
		menuEdit.add(menuItemCut);
		menuItemCut.setActionCommand(KandinskyMenuBar.CUT_OBJECTS);
		menuItemCut.addActionListener(this);
		
		menuItemDelete = new JMenuItem("L\u00F6schen");
		menuEdit.add(menuItemDelete);
		menuItemDelete.setActionCommand(KandinskyMenuBar.DELETE_OBJECTS);
		menuItemDelete.addActionListener(this);
		
		JSeparator separator_1 = new JSeparator();
		menuEdit.add(separator_1);
		
		menuItemCreateFolder = new JMenuItem("Ordner anlegen");
		menuEdit.add(menuItemCreateFolder);
		menuItemCreateFolder.setActionCommand(KandinskyMenuBar.CREATE_FOLDER);
		menuItemCreateFolder.addActionListener(this);
		
		menuItemCreateShortcut = new JMenuItem("Verkn\u00FCpfung anlegen");
		menuEdit.add(menuItemCreateShortcut);
		menuItemCreateShortcut.setActionCommand(KandinskyMenuBar.CREATE_SHORTCUT);
		menuItemCreateShortcut.addActionListener(this);
		
		menuItemCreateEmptyFile = new JMenuItem("Leere Datei anlegen");
		menuEdit.add(menuItemCreateEmptyFile);
		menuItemCreateEmptyFile.setActionCommand(KandinskyMenuBar.CREATE_EMPTY_FILE);
		menuItemCreateEmptyFile.addActionListener(this);
		
		// FTP Menu Section
		
		JMenu menuFTP = new JMenu("FTP");
		this.add(menuFTP);
		
		menuItemEditServerList = new JMenuItem("Server verwalten");
		menuFTP.add(menuItemEditServerList);
		menuItemEditServerList.setActionCommand(KandinskyMenuBar.FTP_SHOW_EDIT_SERVERS);
		menuItemEditServerList.addActionListener(this);
		
		JSeparator separator_2 = new JSeparator();
		menuFTP.add(separator_2);
		
		JMenu menuFTPConnect = new JMenu("Verbinden");
		menuFTP.add(menuFTPConnect);
		menuFTPConnect.addActionListener(this);
		
		JMenuItem menuItemFTPServerEntryDummy = new JMenuItem("Serverliste hier erzeugen");
		menuFTPConnect.add(menuItemFTPServerEntryDummy);
				
		JMenuItem menuItemFTPCloseConnection = new JMenuItem("Verbindung trennen");
		menuFTP.add(menuItemFTPCloseConnection);
		menuItemFTPCloseConnection.setActionCommand(KandinskyMenuBar.FTP_DISCONNECT);
		menuItemFTPCloseConnection.addActionListener(this);
		
		// Settings menu section
		
		JMenu menuSettings = new JMenu("Einstellungen");
		this.add(menuSettings);
		
		menuItemGeneralOptions = new JMenuItem("Allgemeine Optionen");
		menuSettings.add(menuItemGeneralOptions);
		menuItemGeneralOptions.setActionCommand(KandinskyMenuBar.OPTIONS);
		menuItemGeneralOptions.addActionListener(this);
		
		menuItemEditFavorites = new JMenuItem("Favoriten verwalten");
		menuSettings.add(menuItemEditFavorites);
		menuItemEditFavorites.setActionCommand(KandinskyMenuBar.OPTIONS_FAVORITES);
		menuItemEditFavorites.addActionListener(this);
		
		menuItemFTPOptions = new JMenuItem("FTP Optionen");
		menuSettings.add(menuItemFTPOptions);
		menuItemFTPOptions.setActionCommand(KandinskyMenuBar.OPTIONS_FTP);
		menuItemFTPOptions.addActionListener(this);
		
		JSeparator separator_4 = new JSeparator();
		menuSettings.add(separator_4);
		
		JCheckBoxMenuItem menuCheckboxToggleShowHiddenFiles = new JCheckBoxMenuItem("Versteckte Dateien anzeigen");
		menuSettings.add(menuCheckboxToggleShowHiddenFiles);
		menuCheckboxToggleShowHiddenFiles.setActionCommand(KandinskyMenuBar.TOGGLE_HIDDEN_FOLDERS);
		menuCheckboxToggleShowHiddenFiles.addActionListener(this);
		
		JCheckBoxMenuItem menuCheckboxToggleShowFavorites = new JCheckBoxMenuItem("Favoritenliste anzeigen");
		menuSettings.add(menuCheckboxToggleShowFavorites);
		menuCheckboxToggleShowFavorites.setActionCommand(KandinskyMenuBar.TOGGLE_SHOW_FAVORITES);
		menuCheckboxToggleShowFavorites.addActionListener(this);
		
		// Help Menu section
		
		JMenu menuHelp = new JMenu("Hilfe");
		this.add(menuHelp);
		
		menuItemHelp = new JMenuItem("Hilfe");
		menuHelp.add(menuItemHelp);
		menuItemHelp.setActionCommand(KandinskyMenuBar.HELP);
		menuItemHelp.addActionListener(this);
		
		menuItemAbout = new JMenuItem("\u00DCber Kandinsky File Manager");
		menuHelp.add(menuItemAbout);
		menuItemAbout.setActionCommand(KandinskyMenuBar.ABOUT);
		menuItemAbout.addActionListener(this);
		
	
		
	}
	
	/**
	 * Helper function to perform ActionListener actions from Menu Items
	 * All Calls are made to the Functions Helper class which contains the actual logik
	 * It's not here to make the functionality usable by the ButtonBar too.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String source = e.getActionCommand();
		
		switch(source) {
		
		// Menu "File" Actions
		
		case RELOAD:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case SEARCH:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case SWAP_FOLDERS_LEFT_RIGHT:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;

		case IMPORT_FAVORITES:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case EXPORT_FAVORITES:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case CLOSE_PROGRAM:
			FunctionsHelper.closeProgram();
			break;
		
		// Menu "Edit" Actions
		case SELECT_ALL:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case SELECT_NOTHING:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case INVERT_SELECTION:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case COPY_OBJECTS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case PASTE_OBJECTS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case CUT_OBJECTS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case DELETE_OBJECTS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case CREATE_FOLDER:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case CREATE_SHORTCUT:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case CREATE_EMPTY_FILE:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		// Menu "FTP" Actions
		case FTP_SHOW_EDIT_SERVERS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case FTP_CONNECT_MOST_RECENT:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case FTP_DISCONNECT:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		// Menu "Options" Actions
		case OPTIONS:
			FunctionsHelper.showOptions(0);
			break;
			
		case OPTIONS_FAVORITES:
			FunctionsHelper.showOptions(1);
			break;
			
		case OPTIONS_FTP:
			FunctionsHelper.showOptions(3);
			break;
			
		case TOGGLE_SHOW_FAVORITES:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case TOGGLE_HIDDEN_FOLDERS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		// Menu "Help" Actions
		case HELP:
			FunctionsHelper.showHelp(0);
			break;
			
		case ABOUT:
			FunctionsHelper.showAbout();
			break;
		}
		
	}
	
	/**
	 * Switch Activation status of given Menu Item by its identifier
	 * 
	 * Use the Constans in this class as identifiers.
	 * 
	 * @param _identifier
	 * @param _enabled
	 */
	public void setItemEnabled(String _identifier, boolean _enabled) {
		
		try {
			JMenuItem menuItem = (JMenuItem)(this.getClass().getField(_identifier).get(this));
			menuItem.setEnabled(_enabled);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
