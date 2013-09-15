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

	private Hotkeys shortcutList;
	private JList<Hotkey> shortcutDisplayList;

	private JTextField textFieldName;
	private JTextField textFieldKey;
	private JComboBox<String> keyStrokes;

	/**
	 * Constructor for the Object instance behind the Options FTP page.
	 * Only retrieves the FTPList instance for access to the entries on disk.
	 */
	public OptionsShortcuts() {
		shortcutList = Hotkeys.getInstance();
	}

	/**
	 * Add the shortcut list controls to the given page.
	 * The page is expected to be emptied before passing it.
	 * @wbp.parser.entryPoint
	 */
	public void buildPage(final JPanel page) {
		shortcutDisplayList = new JList<Hotkey>(shortcutList.getHotkeys());
		JScrollPane scrollPane = new JScrollPane(shortcutDisplayList);
		shortcutDisplayList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		shortcutDisplayList.addListSelectionListener(OptionsShortcuts.this);
		scrollPane.setBounds(12, 13, 192, 200);
		page.add(scrollPane);
		JButton buttonStore = new JButton("Speichern");
		buttonStore.setBounds(216, 147, 110, 25);
		buttonStore.setActionCommand("store");
		buttonStore.addActionListener(OptionsShortcuts.this);
		page.add(buttonStore);

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

		textFieldKey = new JTextField();
		textFieldKey.setBounds(110, 262, 218, 22);
		textFieldKey.setColumns(10);
		textFieldKey.setEnabled(false);
		page.add(textFieldKey);

		keyStrokes = new JComboBox<>(GlobalHotkeyManager.getInstance().getKeyStrokesAsString());
		keyStrokes.setBounds(110, 297, 218, 22);
		page.add(keyStrokes);
	}

	/**
	 * Listener for buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		int index = shortcutDisplayList.getSelectedIndex();

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
		shortcutList.saveListToFile();
		shortcutDisplayList.setListData(shortcutList.getHotkeys());

		// Elementfokus wiederherstellen und Darstellung erneuern
		if (index >= 0) {
			shortcutDisplayList.setSelectedIndex(index);
		}
		shortcutDisplayList.validate();
	}

	/**
	 * Listener to display selected entry
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {

		int index = OptionsShortcuts.this.shortcutDisplayList.getSelectedIndex();

		// Catch nothing selected
		if (index < 0) {
			return;
		}

		Hotkey entry = OptionsShortcuts.this.shortcutDisplayList.getModel().getElementAt(index);

		this.textFieldName.setText(entry.getFunctionName());
		this.textFieldKey.setText(entry.getInternalKey());
		this.keyStrokes.setSelectedItem(entry.getHotkeyCombination());

	}

	/**
	 * Store or modify entry and check all fields are filled before storing it
	 * Note: Does not check server-side existance of server, port, user and password!
	 * 
	 * @param _new New entry or modify the currently selected one (directly read from list)
	 */
	private void storeEntry() {

		// Anzeigename
		String name = textFieldName.getText().trim();
		if (name.length() < 1) {
			JOptionPane.showMessageDialog(null, "Name muss vorhanden sein");
			return;
		}

		// Interner Schl�ssel
		String key = textFieldKey.getText().trim();
		if (key.length() < 1) {
			JOptionPane.showMessageDialog(null, "Identifikation muss vorhanden sein");
		}

		// Combination
		String combination = (String) keyStrokes.getSelectedItem();

		int index = shortcutDisplayList.getSelectedIndex();
		Hotkey storeEntry = shortcutDisplayList.getModel().getElementAt(index);
		storeEntry.setFunctionName(name);
		storeEntry.setInternalKey(key);
		storeEntry.setHotkeyCombination(combination);

		// No backstore to arraylist as this is no "copy", we got the reference which is still in the list
		// So the list points to the object which is modified.

	}
}
