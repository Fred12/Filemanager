package com.kandinsky.gui.favorites;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import com.kandinsky.objects.FileEntry;
import com.kandinsky.objects.FunctionsHelper;

/**
 * Repräsentiert genau einen Favorite-Eintrag und stellt ihn als einen Button mit passendem Icon dar.
 * @author Benne
 */
public class FavoriteElement extends JToolBar {

	private static final long serialVersionUID = 7975201576957189105L;

	private static final Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 8);

	/** der FileEntry auf den der Button referenziert */
	private FileEntry fileEntry;

	/** Callback */
	private FavoriteListener listener;

	/** remove-Button */
	private JButton removeButton;
	/** Selection-Button */
	private JButton executeButton;

	/**
	 * Erstellt den Button und setzt sowohl Text als auch Item dafür
	 * @param fileEntry
	 */
	public FavoriteElement(FileEntry fileEntry, FavoriteListener listener) {
		this.setFloatable(false);

		this.fileEntry = fileEntry;
		this.listener = listener;
		FavoritesActionListener actionListener =new FavoritesActionListener();
		executeButton = new JButton(fileEntry.getName());
		executeButton.setIcon(fileEntry.getIcon());
		executeButton.setFont(BUTTON_FONT);
		executeButton.setActionCommand("execute");
		executeButton.addActionListener(actionListener);
		removeButton = new JButton("x");
		removeButton.setFont(BUTTON_FONT);
		removeButton.setActionCommand("remove");
		removeButton.addActionListener(actionListener);

		add(executeButton);
		add(removeButton);
	}

	/**
	 * @return den passenden FileEntry
	 */
	public FileEntry getFileEntry() {
		return fileEntry;
	}

	@Override
	public String toString() {
		return fileEntry.getName();
	}

	/**
	 * Ueberwacht die Buttons der Maske
	 * @author Benne
	 */
	public class FavoritesActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			switch (event.getActionCommand()) {
				case "remove": {
					listener.remove(fileEntry);
					FunctionsHelper.refreshFavorites();
					break;
				}
				case "execute": {
					listener.execute(fileEntry);
					break;
				}
			}

		}

	}
}
