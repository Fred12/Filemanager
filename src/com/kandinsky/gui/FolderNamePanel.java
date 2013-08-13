package com.kandinsky.gui;

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
	}
	
	public void setFolderText(String text){
		this.currentFolderTextField.setText(text);
	}

}
