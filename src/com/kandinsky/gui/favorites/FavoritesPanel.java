package com.kandinsky.gui.favorites;

import java.awt.BorderLayout;
import java.io.File;

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

	private static final long serialVersionUID = -5827192149623528389L;
	
	private JToolBar favoritesList;
	
	public FavoritesPanel(){
		favoritesList = new JToolBar(JToolBar.VERTICAL);
		JScrollPane listScroller = new JScrollPane(favoritesList);
		this.setLayout(new BorderLayout());
		this.add(listScroller, BorderLayout.NORTH);
		
		// TODO: nur ein Testeintrag
		addFavoriteForFileEntry(new FileEntry(new File("")));
		addFavorite(new Favorite(new FileEntry(new File(""))));
	}
	
	public FavoritesPanel addFavorite(Favorite favorite){
		favoritesList.add(favorite);
		return this;
	}
	
	public FavoritesPanel addFavoriteForFileEntry(FileEntry fileEntry){
		addFavorite(new Favorite(fileEntry));
		return this;
	}

}
