package com.kandinsky.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * PUNKT 5 - Verzeichnis Name anzeigen.
 * Dient zum Anzeigen des aktuellen Verzeichnisses.
 * ACHTUNG: Das Panel muss für beide Seiten der SplitPane angezeigt werden. Daher wird es später zwei Instantzen davon geben.
 * 
 * @author schmidtb
 */
public class FolderNamePanel extends JPanel {
	
	private JTextField currentFolderTextField;
	
	public FolderNamePanel(){
		this.currentFolderTextField = new JTextField();
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.weightx=1.0;
		gbc.weighty=0.5;
		this.add(currentFolderTextField, gbc);
	}
	
	public void setFolderText(String text){
		this.currentFolderTextField.setText(text);
	}

}
