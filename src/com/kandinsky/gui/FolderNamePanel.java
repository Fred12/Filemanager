package com.kandinsky.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
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

	private JTextField currentFolderTextField;
	private JButton executeButton;
	private SideFunctionsHelper sideFunctionsHelper;

	/**
	 * @param sideFunctionsHelper
	 * @param buttonGridx X-Stelle an der der Button sein soll
	 * @param textFieldGridx X-Stelle an der das Textfeld sein soll
	 */
	private FolderNamePanel(SideFunctionsHelper sideFunctionsHelper, int buttonGridx, int textFieldGridx) {
		this.sideFunctionsHelper = sideFunctionsHelper;
		currentFolderTextField = new JTextField();
		currentFolderTextField.addKeyListener(new TextFieldListener());
		executeButton = new JButton("go!");
		executeButton.addActionListener(new ButtonActionListener());
		executeButton.setActionCommand(GO);
		
		// Layout
		setLayout(new GridBagLayout());

		// Sachen hinzufuegen
		this.add(currentFolderTextField, getTextFieldConstraints(textFieldGridx));
		this.add(executeButton, getButtonConstraints(buttonGridx));
	}

	/**
	 * @param sideFunctionsHelper
	 * @return FolderNamePanel fuer die linke Seite, d. h. links das Textfeld und rechts der Button
	 */
	public static FolderNamePanel onLeftSide(SideFunctionsHelper sideFunctionsHelper) {
		return new FolderNamePanel(sideFunctionsHelper, 1, 0);
	}

	/**
	 * 
	 * @param sideFunctionsHelper
	 * @return FolderNamePanel fuer die rechte Seite, d. h. rechts das Textfeld und links der Button
	 */
	public static FolderNamePanel onRightSide(SideFunctionsHelper sideFunctionsHelper) {
		return new FolderNamePanel(sideFunctionsHelper, 0, 1);
	}

	private GridBagConstraints getTextFieldConstraints(int gridx) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.9;
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

	/**
	 * Setzt den Text von ausserhalb
	 * @param text
	 */
	public void setFolderText(String text) {
		this.currentFolderTextField.setText(text);
	}

	private void changeFolder() {
		Logger.info("Ordner wird gewechselt: "+currentFolderTextField.getText());
		sideFunctionsHelper.switchFolder(currentFolderTextField.getText());
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				changeFolder();
			}

		}
		
	}

}
