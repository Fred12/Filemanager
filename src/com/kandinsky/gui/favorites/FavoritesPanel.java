package com.kandinsky.gui.favorites;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import com.kandinsky.objects.FileEntry;

/**
 * PUNKT 7 - Favoriten
 * Favoritenliste zum Anzeigen der Favoriten.
 * ACHTUNG: Die Favoriten müssen auf beiden Seiten der SplitPane angezeigt werden! Daher müssen später zwei Instanzen davon
 * exisitieren!
 * @author schmidtb
 */
public class FavoritesPanel extends JPanel {

	private static final String LABEL_FAVORITEN = "Favoriten";

	private static final long serialVersionUID = -5827192149623528389L;

	private JLabel headerLabel;
	private JToolBar favoritesList;

	public FavoritesPanel() {
		
		favoritesList = new JToolBar(JToolBar.VERTICAL);
		JScrollPane listScroller = new JScrollPane(favoritesList);
		createHeaderLabel();

		/*
		 * Layout und Sachen adden
		 */
		this.setLayout(new BorderLayout());
		this.add(headerLabel, BorderLayout.NORTH);
		this.add(listScroller, BorderLayout.CENTER);

		// TODO: nur ein Testeintrag
		addFavoriteForFileEntry(new FileEntry(new File("")));
		addFavorite(new FavoriteElement(new FileEntry(new File(""))));
	}

	/**
	 * Erstellt vollstaendig das Headerlabel und weisst Farbe und Schriftart zu.
	 */
	private void createHeaderLabel() {
		headerLabel = new JLabel(LABEL_FAVORITEN);
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
		headerLabel.setBackground(Color.DARK_GRAY);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setOpaque(true);
	}

	public FavoritesPanel addFavorite(FavoriteElement favorite) {
		favoritesList.add(favorite);
		return this;
	}

	public FavoritesPanel addFavoriteForFileEntry(FileEntry fileEntry) {
		addFavorite(new FavoriteElement(fileEntry));
		return this;
	}

}
