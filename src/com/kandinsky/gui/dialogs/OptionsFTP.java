package com.kandinsky.gui.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import com.kandinsky.objects.FTPEntry;
import com.kandinsky.objects.FTPList;

public class OptionsFTP implements ActionListener, ListSelectionListener {
	
	private FTPList ftpList;
	private JList<String> serverList;
	
	private JTextField textFieldName;
	private JTextField textFieldServer;
	private JTextField textFieldPort;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	/**
	 * Constructor for the Object instance behind the Options FTP page.
	 * Only retrieves the FTPList instance for access to the entries on disk.
	 */
	public OptionsFTP() {
		
		ftpList = FTPList.getInstance();
	}
	
	/**
	 * Add the ftp list controls to the given page.
	 * The page is expected to be emptied before passing it.
	 * wbp.parser.entryPoint
	 * @param page
	 */
	public void buildPage(JPanel page) {
		
		serverList = new JList<String>(FTPList.getInstance().getNames());
		//System.out.println(FTPList.getInstance().getNames()[0].toString());
		serverList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		serverList.setBounds(12, 13, 192, 200);
		serverList.addListSelectionListener(this);
		page.add(serverList);
			
		JButton buttonUp = new JButton("Nach oben");
		buttonUp.setBounds(216, 12, 110, 25);
		buttonUp.setActionCommand("up");
		buttonUp.addActionListener(this);
		page.add(buttonUp);
		
		JButton buttonDown = new JButton("Nach unten");
		buttonDown.setBounds(216, 57, 110, 25);
		buttonDown.setActionCommand("down");
		buttonDown.addActionListener(this);
		page.add(buttonDown);
		
		JButton buttonNew = new JButton("Neu");
		buttonNew.setBounds(216, 102, 110, 25);
		buttonNew.setActionCommand("new");
		buttonNew.addActionListener(this);
		page.add(buttonNew);
		
		JButton buttonStore = new JButton("Speichern");
		buttonStore.setBounds(216, 147, 110, 25);
		buttonStore.setActionCommand("store");
		buttonStore.addActionListener(this);
		page.add(buttonStore);
		
		JButton buttonDelete = new JButton("Löschen");
		buttonDelete.setBounds(216, 192, 110, 25);
		buttonDelete.setActionCommand("delete");
		buttonDelete.addActionListener(this);
		page.add(buttonDelete);
	
		JLabel labelName = new JLabel("Name:");
		labelName.setBounds(12, 227, 56, 16);
		page.add(labelName);
		
		JLabel labelServer = new JLabel("Server:");
		labelServer.setBounds(12, 262, 56, 16);
		page.add(labelServer);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(88, 227, 238, 22);
		textFieldName.setColumns(10);
		page.add(textFieldName);
		
		textFieldServer = new JTextField();
		textFieldServer.setBounds(88, 262, 238, 22);
		textFieldServer.setColumns(10);
		page.add(textFieldServer);
		
		JLabel labelUser = new JLabel("Benutzer:");
		labelUser.setBounds(12, 297, 56, 16);
		page.add(labelUser);
		
		JLabel labelPort = new JLabel("Port:");
		labelPort.setBounds(197, 297, 36, 16);
		page.add(labelPort);
				
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(88, 297, 97, 22);
		page.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPort = new JTextField();
		textFieldPort.setBounds(245, 297, 81, 22);
		page.add(textFieldPort);
		textFieldPort.setColumns(10);
		
		JLabel labelPassword = new JLabel("Passwort:");
		labelPassword.setBounds(12, 332, 70, 16);
		page.add(labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(88, 332, 238, 22);
		page.add(passwordField);

	}

	/**
	 * Listener for buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		int index = serverList.getSelectedIndex();
		
		// Bedeutungen der Aktionen selbsterkl�rend aus Action-Name
		switch(e.getActionCommand()){
			
			case "up":
				
				// We go up, so second is what we clicked and first of the pair is the one above.
				// Index 0 does not work (or -1 if not selected)
				if(index <= 0) {
					break;
				}
				
				ftpList.swapEntries(index-1, index);
				// To keep selection focus at end of select
				index--;
				break;
				
			case "down":
				
				// We go down, so first is what we clicked and second is the next entry
				// Index last element does not work
				if(index == -1 || index >= serverList.getModel().getSize()-1) {
					break;
				}
				
				ftpList.swapEntries(index, index+1);
				// To keep selection focus at end of select
				index++;
				break;
				
			case "new":
				
				// Use one common function as field checking is the same
				// Difference only in storage, here create a new entry
				this.storeEntry(true);
				this.clearFields();
				index = -1;
				break;
			
			case "store":
				
				// Here, use false to trigger it to get the entry and then modify it
				// instead of creating a new one
				this.storeEntry(false);
				this.clearFields();
				index = -1;
				break;
				
			case "delete":
				
				ftpList.remove(index);
				// No focus after this
				index = -1;
				this.clearFields();
				break;
				
			default:
				break;
		}
		
		// Was auch immer getan wurde, aktualisiere den inhalt und speichere!
		ftpList.saveListToFile();
		serverList.setListData(ftpList.getNames());
		
		// Elementfokus wiederherstellen und Darstellung erneuern
		if (index >= 0){
			serverList.setSelectedIndex(index);
		}
		serverList.validate();
	}

	/**
	 * Listener to display selected entry
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int index = OptionsFTP.this.serverList.getSelectedIndex();
		
		// Catch nothing selected
		if (index < 0){
			return;
		}
		
		FTPList ftpList = FTPList.getInstance();
		FTPEntry entry = ftpList.get(index);
		
		this.textFieldName.setText(entry.getName());
		this.textFieldServer.setText(entry.getServer());
		this.textFieldPort.setText(Integer.toString(entry.getPort()));
		this.textFieldUsername.setText(entry.getUsername());
		this.passwordField.setText(entry.getPassword());
		
		
	}
	
	/**
	 * Just clear all input fields
	 */
	private void clearFields() {
		
		this.textFieldName.setText("");
		this.textFieldServer.setText("");
		this.textFieldPort.setText("");
		this.textFieldUsername.setText("");
		this.passwordField.setText("");
		
	}
	
	/**
	 * Store or modify entry and check all fields are filled before storing it
	 * Note: Does not check server-side existance of server, port, user and password!
	 * 
	 * @param _new New entry or modify the currently selected one (directly read from list)
	 */
	private void storeEntry(boolean _new){
		
		// Name
		String name = textFieldName.getText().trim();
		if (name.length() < 1) {
			JOptionPane.showMessageDialog(null, "Name muss vorhanden sein");
			return;
		}
		
		// Server
		String server = textFieldServer.getText().trim();
		if (server.length() < 1) {
			JOptionPane.showMessageDialog(null, "Server muss vorhanden sein");
		}
		
		// Port
		String port = textFieldPort.getText().trim();
		int portNum = 0;
		try {
			portNum = Integer.parseInt(port);
		}
		catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Port is not a valid Number!");
			return;
		}
		if (portNum < 0 || portNum > 65535) {
			JOptionPane.showMessageDialog(null, "Port number out of Range (0-65535)!");
			return;
		}
		
		// User
		String username = textFieldUsername.getText().trim();
		
		// Password
		String password = new String(passwordField.getPassword());
		
		if(_new) {
			
			// Resulting configuration object
			FTPEntry newEntry = new FTPEntry(name, server, portNum, username, password);
			ftpList.add(newEntry);
			
		}
		else {
			
			int index = serverList.getSelectedIndex();
			FTPEntry storeEntry = ftpList.get(index);
			storeEntry.setName(name);
			storeEntry.setServer(server);
			storeEntry.setUsername(username);
			storeEntry.setPort(portNum);
			storeEntry.setPassword(password);
			
			// No backstore to arraylist as this is no "copy", we got the reference which is still in the list
			// So the list points to the object which is modified.
		}
		
	}
}
