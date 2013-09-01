package com.kandinsky.gui.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import com.kandinsky.objects.FTPEntry;
import com.kandinsky.objects.FTPList;

public class OptionsFTP implements ActionListener {
	
	private JList<String> serverList;
	private JTextField textFieldName;
	private JTextField textFieldServer;
	private JTextField textFieldPort;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void buildPage(JPanel page) {
						
		serverList = new JList<String>();
		serverList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		serverList.setBounds(12, 13, 192, 194);
		page.add(serverList);
	
		JButton buttonNew = new JButton("Neu");
		buttonNew.setBounds(216, 182, 97, 25);
		buttonNew.setActionCommand("new");
		buttonNew.addActionListener(this);
		page.add(buttonNew);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(88, 220, 225, 22);
		textFieldName.setColumns(10);
		page.add(textFieldName);
		
		textFieldServer = new JTextField();
		textFieldServer.setBounds(88, 255, 225, 22);
		textFieldServer.setColumns(10);
		page.add(textFieldServer);
		
		JLabel labelName = new JLabel("Name:");
		labelName.setBounds(12, 220, 56, 16);
		page.add(labelName);
		
		JLabel labelServer = new JLabel("Server:");
		labelServer.setBounds(12, 255, 56, 16);
		page.add(labelServer);
		
		JButton buttonUp = new JButton("Nach oben");
		buttonUp.setBounds(216, 12, 97, 25);
		buttonUp.setActionCommand("Up");
		page.add(buttonUp);
		
		JButton buttonDown = new JButton("Nach unten");
		buttonDown.setBounds(216, 50, 97, 25);
		buttonDown.setActionCommand("down");
		page.add(buttonDown);
		
		JButton buttonDelete = new JButton("Löschen");
		buttonDelete.setBounds(216, 88, 97, 25);
		buttonDelete.setActionCommand("delete");
		page.add(buttonDelete);
		
		JLabel labelUser = new JLabel("Benutzer:");
		labelUser.setBounds(12, 290, 56, 16);
		page.add(labelUser);
		
		JLabel labelPort = new JLabel("Port:");
		labelPort.setBounds(197, 290, 36, 16);
		page.add(labelPort);
				
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(88, 290, 97, 22);
		page.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPort = new JTextField();
		textFieldPort.setBounds(245, 290, 68, 22);
		page.add(textFieldPort);
		textFieldPort.setColumns(10);
		
		JLabel labelPassword = new JLabel("Passwort:");
		labelPassword.setBounds(12, 325, 56, 16);
		page.add(labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(88, 325, 225, 22);
		page.add(passwordField);
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand()){
		
			case "new":
				
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
				
				// Resulting configuration object
				FTPEntry entry = new FTPEntry();
				entry.setName(name);
				entry.setServer(server);
				entry.setUsername(username);
				entry.setPort(portNum);
				entry.setPassword(password);
				FTPList.getInstance().add(entry);
				
				serverList.setListData(FTPList.getNames());
				serverList.validate();
			
			case "up":
			default:
				break;
		}
		
	}
}
