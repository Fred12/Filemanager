package com.kandinsky.gui.favorites;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import org.pmw.tinylog.Logger;

import com.kandinsky.objects.Favorites;
import com.kandinsky.objects.FileEntry;
import com.kandinsky.objects.SideFunctionsHelper;

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
	private SideFunctionsHelper sideFunctionsHelper;

	public FavoritesPanel(SideFunctionsHelper sideFunctionsHelper) {
		this.sideFunctionsHelper = sideFunctionsHelper;

		favoritesList = new JToolBar(JToolBar.VERTICAL);
		favoritesList.setFloatable(false);
		JScrollPane listScroller = new JScrollPane(favoritesList);
		createHeaderLabel();

		/*
		 * Layout und Sachen adden
		 */
		this.setLayout(new BorderLayout());
		this.add(headerLabel, BorderLayout.NORTH);
		this.add(listScroller, BorderLayout.CENTER);
		
		loadFavoritesIntoList();
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

	/**
	 * Fuegt einen Favoriten hinzu
	 * @param favorite
	 * @return eigenes Objekt (fluent interface)
	 */
	public FavoritesPanel addFavorite(FavoriteElement favorite) {
		favoritesList.add(favorite);
		revalidate();
		return this;
	}

	/**
	 * Fuegt einen Favoriten hinzu
	 * @param fileEntry
	 * @return eigenes Objekt (fluent interface)
	 */
	public FavoritesPanel addFavoriteForFileEntry(FileEntry fileEntry) {
		addFavorite(new FavoriteElement(fileEntry, sideFunctionsHelper));
		return this;
	}
	
	/**
	 * Aktualisierung der Favorites und der dazugehoerigen Liste
	 */
	public void refresh(){
		favoritesList.removeAll();
		loadFavoritesIntoList();
		favoritesList.repaint();
		this.repaint();
	}
	
	/**
	 * Laedt die aktuellen Favorites in die Liste
	 */
	private void loadFavoritesIntoList() {
		List<FileEntry> favorites = Favorites.getInstance().getFavoritesAsFileEntries();
		Logger.debug("Gefundene Favoriten: "+favorites.size());
		for (FileEntry entry : favorites) {
			addFavoriteForFileEntry(entry);
		}
	}
}
