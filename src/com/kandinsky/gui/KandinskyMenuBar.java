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

	// Singleton instance
	private static KandinskyMenuBar instance = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4388114126509784209L;
	
	////////////////////////////////// Menu Item identifier Strings ////////////////////////////
	
	// Menu "File" Items
	public static final String CLOSE_PROGRAM			= "menuItemCloseProgram";
	
	// Menu "FTP" Items
	public static final String FTP_SHOW_EDIT_SERVERS	= "menuItemEditServerList";
	public static final String FTP_CONNECT_MOST_RECENT	= "ftp_connect_most_recent";
	public static final String FTP_DISCONNECT			= "ftp_disconnect";
		
	// Menu "Settings" Items
	public static final String OPTIONS					= "menuItemGeneralOptions";
	public static final String OPTIONS_FTP				= "menuItemFTPOptions";
	public static final String TOGGLE_HIDDEN_FOLDERS	= "toggle_show_hidden_files";
	public static final String TOGGLE_SHOW_FAVORITES	= "toggle_show_favorites";
		
	// Menu "Help" Items
	public static final String HELP						= "menuItemHelp";
	public static final String ABOUT					= "menuItemAbout";
	
	///////////////////////////////////////// Menu item variables (Needed for stuff like graying them out /////////////////
	
	// File Menu
	public JMenu 		menuFile = null;
	public JMenuItem	menuItemCloseProgram = null;
	
	// FTP Menu
	public JMenu		menuFTP = null;
	public JMenuItem	menuItemEditServerList = null;
	
	// Options Menu
	public JMenu		menuOptions = null;
	public JMenuItem	menuItemGeneralOptions = null;
	public JMenuItem	menuItemFTPOptions = null;
	
	// Help Menu
	public JMenu		menuHelp = null;
	public JMenuItem	menuItemHelp = null;
	public JMenuItem	menuItemAbout = null;
	
	/**
	 * Creates the MenuBar with all entries
	 */
	private KandinskyMenuBar() {
		
		JMenu menuFile = new JMenu("Datei");
		this.add(menuFile);
		
		menuItemCloseProgram = new JMenuItem("Schliessen");
		menuFile.add(menuItemCloseProgram);
		menuItemCloseProgram.setActionCommand(KandinskyMenuBar.CLOSE_PROGRAM);
		menuItemCloseProgram.addActionListener(this);
			
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
	 * Create the instance 
	 * @return 
	 */
	public static KandinskyMenuBar createInstance(){
		
		KandinskyMenuBar.instance = new KandinskyMenuBar();
		return KandinskyMenuBar.instance;
	}
	
	/**
	 * Get the instance
	 * @return 
	 */
	public static KandinskyMenuBar getInstance(){
		
		if (KandinskyMenuBar.instance == null) {
			KandinskyMenuBar.createInstance();
		}
		return KandinskyMenuBar.instance;
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

		case CLOSE_PROGRAM:
			FunctionsHelper.closeProgram();
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
		
		case OPTIONS_FTP:
			FunctionsHelper.showOptions(1);
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
