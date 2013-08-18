package com.kandinsky.gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import com.kandinsky.gui.dialogs.AboutDialog;
import com.kandinsky.main.Main;

public class KandinskyMenuBarFunctions implements ActionListener {
	
	// Menu "File" Items
	public static final String RELOAD 					= "reload";
	public static final String SEARCH 					= "search";
	public static final String SWAP_FOLDERS_LEFT_RIGHT	= "swap_folders_left_right";
	public static final String IMPORT_FAVORITES			= "import_favorites";
	public static final String EXPORT_FAVORITES			= "export_favorites";
	public static final String CLOSE_PROGRAM			= "close_program";
	
	// Menu "Edit" Items
	public static final String SELECT_ALL				= "select_all_objects";
	public static final String SELECT_NOTHING			= "select_no_objects";
	public static final String INVERT_SELECTION			= "invert_selection";
	public static final String COPY_OBJECTS				= "copy_objects";
	public static final String PASTE_OBJECTS			= "paste_objects";
	public static final String CUT_OBJECTS				= "cut_objects";
	public static final String DELETE_OBJECTS			= "delete_objects";
	public static final String CREATE_FOLDER			= "create_folder";
	public static final String CREATE_SHORTCUT			= "create_shortcut";
	public static final String CREATE_EMPTY_FILE		= "create_empty_file";
	
	// Menu "FTP" Items
	public static final String FTP_SHOW_EDIT_SERVERS	= "ftp_show_edit_servers";
	public static final String FTP_CONNECT_MOST_RECENT	= "ftp_connect_most_recent";
	public static final String FTP_DISCONNECT			= "ftp_disconnect";
	
	
	// Menu "Settings" Items
	public static final String OPTIONS					= "options_common";
	public static final String OPTIONS_FAVORITES		= "options_favorites";
	public static final String OPTIONS_FTP				= "options_ftp";
	public static final String TOGGLE_HIDDEN_FOLDERS	= "toggle_show_hidden_files";
	public static final String TOGGLE_SHOW_FAVORITES	= "toggle_show_favorites";
		
	// Menu "Help" Items
	public static final String HELP						= "show_help";
	public static final String ABOUT					= "show_about";
	
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
			// Fire Window Close Event for Main Frame . See Main class
			WindowEvent closingEvent = new WindowEvent(Main.getJFrame(), WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
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
			AboutDialog about = new AboutDialog();
			about.setVisible(true);
			break;
		}
		
		
		
	
	}

}
