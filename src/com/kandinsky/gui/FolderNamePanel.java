package com.kandinsky.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.pmw.tinylog.Logger;

import com.kandinsky.objects.SideFunctionsHelper;

/**
 * PUNKT 5 - Verzeichnis Name anzeigen.
 * Dient zum Anzeigen des aktuellen Verzeichnisses.
 * ACHTUNG: Das Panel muss für beide Seiten der SplitPane angezeigt werden. Daher wird es später zwei Instanzen davon geben.
 * 
 * @author schmidtb
 */
public class FolderNamePanel extends JPanel {

	private static final long serialVersionUID = -3069656768393726552L;
	/** ActionCommand fuer den Go!-Button */
	private static final String GO = "go";

	private JComboBox<String> driveSelector;
	private JTextField currentFolderTextField;
	private JButton executeButton;
	private SideFunctionsHelper sideFunctionsHelper;

	/**
	 * @param sideFunctionsHelper
	 * @param buttonGridx X-Stelle an der der Button sein soll
	 * @param textFieldGridx X-Stelle an der das Textfeld sein soll
	 */
	private FolderNamePanel(SideFunctionsHelper sideFunctionsHelper, int driveSelectorGridx, int buttonGridx, int textFieldGridx) {
		this.sideFunctionsHelper = sideFunctionsHelper;
		
		File[] roots = File.listRoots();
		String[] rootPaths = new String[roots.length];
		
		for(int i = 0; i < roots.length; ++i) {
			rootPaths[i] = roots[i].toString();
		}
		
		driveSelector = new JComboBox<String>(rootPaths);
		driveSelector.addItemListener(new DriveSelectorListener());
		currentFolderTextField = new JTextField();
		currentFolderTextField.addKeyListener(new TextFieldListener());
		executeButton = new JButton("go!");
		executeButton.addActionListener(new ButtonActionListener());
		executeButton.setActionCommand(GO);
		
		// Layout
		setLayout(new GridBagLayout());

		// Sachen hinzufuegen
		this.add(driveSelector, getDriveConstraints(driveSelectorGridx));
		this.add(currentFolderTextField, getTextFieldConstraints(textFieldGridx));
		this.add(executeButton, getButtonConstraints(buttonGridx));

	}

	/**
	 * @param sideFunctionsHelper
	 * @return FolderNamePanel fuer die linke Seite, d. h. links das Textfeld und rechts der Button
	 */
	public static FolderNamePanel onLeftSide(SideFunctionsHelper sideFunctionsHelper) {
		return new FolderNamePanel(sideFunctionsHelper, 0, 2, 1);
	}

	/**
	 * 
	 * @param sideFunctionsHelper
	 * @return FolderNamePanel fuer die rechte Seite, d. h. rechts das Textfeld und links der Button
	 */
	public static FolderNamePanel onRightSide(SideFunctionsHelper sideFunctionsHelper) {
		return new FolderNamePanel(sideFunctionsHelper, 1, 0, 2);
	}

	private GridBagConstraints getTextFieldConstraints(int gridx) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.8;
		gbc.weighty = 0.5;
		return gbc;
	}

	private GridBagConstraints getButtonConstraints(int gridx) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.weighty = 0.5;
		return gbc;
	}
	
	private GridBagConstraints getDriveConstraints(int gridx) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		gbc.weighty = 0.5;
		return gbc;
	}

	/**
	 * Setzt den Text von ausserhalb und wechsle
	 * @param text
	 */
	public void setFolderText(String text) {
		this.currentFolderTextField.setText(text);
		
	}
	
	/**
	 * Get the folder path content
	 * 
	 * @return
	 */
	public String getFolderText() {
		return this.currentFolderTextField.getText();
	}

	private void changeFolder() {
		Logger.info("Ordner wird gewechselt: "+currentFolderTextField.getText());
		sideFunctionsHelper.switchFolder(currentFolderTextField.getText());
	}
	
	public void changeFolder(String newPath) {
		Logger.info("Ordner wird gewechselt: "+ newPath);
		sideFunctionsHelper.switchFolder(newPath);
	}
	
	/**
	 * Fängt Auswahl des Laufwerks in der Laufwerksauswahl ab 
	 * und wechselt den Ordner
	 * 
	 * @author Stefan
	 *
	 */
	private class DriveSelectorListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent ie) {
			if(ie.getStateChange() == 1)
			{
				// Wechsel des Items auf ausgewählt führt zu Ordnerwechsel
				FolderNamePanel.this.changeFolder(ie.getItem().toString());
				
			}
		}
	
	}
	
	/**
	 * Faengt die Actions der Buttons innerhalb des Panels ab
	 * @author Benne
	 */
	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			switch(event.getActionCommand()){
				case GO:{
					changeFolder();
					break;
				}
			}
		}
	}
	
	private class TextFieldListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				changeFolder();
			}
		}
	}

}
