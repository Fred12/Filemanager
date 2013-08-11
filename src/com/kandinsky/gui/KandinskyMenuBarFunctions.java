package com.kandinsky.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class KandinskyMenuBarFunctions implements ActionListener {
	
	// Menu "File" Items
	public static final String RELOAD 					= "Aktualisieren";
	public static final String SEARCH 					= "Suchen";
	public static final String SWAP_FOLDERS_LEFT_RIGHT	= "Ordner rechts/links tauschen";
	public static final String IMPORT_FAVORITES			= "Favoriten importieren";
	public static final String EXPORT_FAVORITES			= "Favoriten exportieren";
	public static final String CLOSE_PROGRAM			= "Schliessen";
	
	// Menu "Edit" Items
	public static final String SELECT_ALL				= "Alles ausw\u00E4hlen";
	public static final String SELECT_NOTHING			= "Nichts ausw\u00E4hlen";
	public static final String INVERT_SELECTION			= "Auswahl umkehren";
	public static final String COPY						= "Kopieren";
	public static final String PASTE					= "Einf\u00FCgen";
	public static final String CUT						= "Ausschneiden";
	public static final String DELETE					= "L\u00F6schen";
	public static final String CREATE_FOLDER			= "Ordner anlegen";
	public static final String CREATE_SHORTCUT			= "Verkn\u00FCpfung anlegen";
	public static final String CREATE_EMPTY_FILE		= "Leere Datei anlegen";
	
	// Menu "FTP" Items
	public static final String FTP_EDIT_SERVERS			= "Server verwalten";
	public static final String FTP_CONNECT				= "Verbinden";
	public static final String FTP_DISCONNECT			= "Verbindung trennen";
	
	
	// Menu "Settings" Items
	public static final String OPTIONS					= "Allgemeine Optionen";
	public static final String OPTIONS_FAVORITES		= "Favoriten verwalten";
	public static final String OPTIONS_FTP				= "FTP Optionen";
	public static final String TOGGLE_HIDDEN_FOLDERS	= "Versteckte Dateien anzeigen";
	public static final String TOGGLE_SHOW_FAVORITES	= "Favoritenliste anzeigen";
		
	// Menu "Help" Items
	public static final String HELP						= "Hilfe";
	public static final String ABOUT					= "Über Kandinsky File Manager";
	
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
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
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
			
		case COPY:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case PASTE:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case CUT:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case DELETE:
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
		case FTP_EDIT_SERVERS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case FTP_CONNECT:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case FTP_DISCONNECT:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		// Menu "Options" Actions
		case OPTIONS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case OPTIONS_FAVORITES:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case OPTIONS_FTP:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case TOGGLE_SHOW_FAVORITES:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		
		case TOGGLE_HIDDEN_FOLDERS:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		// Menu "Help" Actions
		case HELP:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
			
		case ABOUT:
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
			break;
		}
		
		
		
	
	}

}
