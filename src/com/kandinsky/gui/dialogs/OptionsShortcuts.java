package com.kandinsky.gui.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.kandinsky.gui.GlobalHotkeyManager;
import com.kandinsky.objects.Hotkey;
import com.kandinsky.objects.Hotkeys;

public class OptionsShortcuts implements ActionListener, ListSelectionListener {

	/** Hotkey-Liste */
	private Hotkeys hotkeys;
	/** Liste der Hotkeys */
	private JList<Hotkey> hotkeyDisplayList;
	/** Name des selektierten Hotkeys */
	private JTextField textFieldName;
	/** interner Key des selektierten Hotkeys */
	private JTextField textFieldInternalKey;
	/** List-Box mit den Keystrokes */
	private JComboBox<String> keyStrokes;

	/**
	 * Constructor for the Object instance behind the Options FTP page.
	 * Only retrieves the FTPList instance for access to the entries on disk.
	 */
	public OptionsShortcuts() {
		hotkeys = Hotkeys.getInstance();
	}

	/**
	 * Add the shortcut list controls to the given page.
	 * The page is expected to be emptied before passing it.
	 * wbp.parser.entryPoint
	 * @param page
	 */
	public void buildPage(JPanel page) {
		hotkeyDisplayList = new JList<Hotkey>(hotkeys.getHotkeys());
		JScrollPane scrollPane = new JScrollPane(hotkeyDisplayList);
		hotkeyDisplayList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		hotkeyDisplayList.addListSelectionListener(OptionsShortcuts.this);
		scrollPane.setBounds(12, 13, 316, 200);
		page.add(scrollPane);

		JLabel labelName = new JLabel("Name:");
		labelName.setBounds(12, 227, 80, 16);
		page.add(labelName);

		JLabel labelKey = new JLabel("Identifikation:");
		labelKey.setBounds(12, 262, 80, 16);
		page.add(labelKey);

		JLabel labelUser = new JLabel("Kombination:");
		labelUser.setBounds(12, 297, 80, 16);
		page.add(labelUser);

		textFieldName = new JTextField();
		textFieldName.setBounds(110, 227, 218, 22);
		textFieldName.setColumns(10);
		page.add(textFieldName);

		textFieldInternalKey = new JTextField();
		textFieldInternalKey.setBounds(110, 262, 218, 22);
		textFieldInternalKey.setColumns(10);
		textFieldInternalKey.setEnabled(false);
		page.add(textFieldInternalKey);

		keyStrokes = new JComboBox<>(GlobalHotkeyManager.getInstance().getKeyStrokesAsString());
		keyStrokes.setBounds(110, 297, 218, 22);
		page.add(keyStrokes);
		
		JButton buttonStore = new JButton("Speichern");
		buttonStore.setBounds(12, 330, 316, 25);
		buttonStore.setActionCommand("store");
		buttonStore.addActionListener(OptionsShortcuts.this);
		page.add(buttonStore);
	}

	/**
	 * Listener for buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		int index = hotkeyDisplayList.getSelectedIndex();

		// Bedeutungen der Aktionen selbsterklärend aus Action-Name
		switch (e.getActionCommand()) {

			case "store":

				// Here, use false to trigger it to get the entry and then modify it
				// instead of creating a new one
				this.storeEntry();
				index = -1;
				break;

			default:
				break;
		}

		// Was auch immer getan wurde, aktualisiere den inhalt und speichere!
		hotkeys.saveListToFile();
		hotkeyDisplayList.setListData(hotkeys.getHotkeys());

		// Elementfokus wiederherstellen und Darstellung erneuern
		if (index >= 0) {
			hotkeyDisplayList.setSelectedIndex(index);
		}
		hotkeyDisplayList.validate();
	}

	/**
	 * Listener to display selected entry
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {

		int index = OptionsShortcuts.this.hotkeyDisplayList.getSelectedIndex();

		// Catch nothing selected
		if (index < 0) {
			return;
		}

		Hotkey entry = OptionsShortcuts.this.hotkeyDisplayList.getModel().getElementAt(index);

		this.textFieldName.setText(entry.getFunctionName());
		this.textFieldInternalKey.setText(entry.getInternalKey());
		this.keyStrokes.setSelectedItem(entry.getHotkeyCombination());
	}

	/**
	 * Store or modify entry and check all fields are filled before storing it
	 * Note: Does not check server-side existance of server, port, user and password!
	 */
	private void storeEntry() {

		// Anzeigename
		String name = textFieldName.getText().trim();
		if (name.length() < 1) {
			JOptionPane.showMessageDialog(null, "Name muss vorhanden sein");
			return;
		}

		// Interner Schlüssel
		String key = textFieldInternalKey.getText().trim();
		if (key.length() < 1) {
			JOptionPane.showMessageDialog(null, "Identifikation muss vorhanden sein");
		}

		// Combination
		String combination = (String) keyStrokes.getSelectedItem();

		int index = hotkeyDisplayList.getSelectedIndex();
		Hotkey storeEntry = hotkeyDisplayList.getModel().getElementAt(index);
		storeEntry.setFunctionName(name);
		storeEntry.setInternalKey(key);
		storeEntry.setHotkeyCombination(combination);

		// No backstore to arraylist as this is no "copy", we got the reference which is still in the list
		// So the list points to the object which is modified.

	}
}
