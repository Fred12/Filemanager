package com.kandinsky.objects;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.List;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.MainPanel;
import com.kandinsky.gui.dialogs.AboutDialog;
import com.kandinsky.gui.dialogs.HelpPages;
import com.kandinsky.gui.dialogs.OptionsDialog;
import com.kandinsky.gui.dialogs.OptionsPages;
import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.main.Main;

/**
 * Helper class to bundle all functionality accessed by menu buttons. This makes
 * the same functionality accessible to MenuBar and ButtonBar by just using single
 * function calls. Evaluating objects to get selections and other stuff or whatever
 * is done here or within further called functions.
 * 
 * @author Stefan
 *
 */
public final class FunctionsHelper {

	/**
	 * Initialisierung der Klasse verhindern, da für statische Hilfsfunktionen
	 */
	private FunctionsHelper(){}
	
	//////////////////////////////////////////////// File Menu / Global Actions /////////////////////////////////
	
	/**
	 * Close the programm by firing closing event. This event is handled by the main class (where the programm starts before
	 * displaying anything). See the handler there in Main.java in "main" pacakage.
	 */
	public static void closeProgram() {
		// Schliessen Event feuern. Achtung: Wird in Main Klasse behandelt, Sicherheitsdialog wird in Main erzeugt.
		WindowEvent closingEvent = new WindowEvent(Main.getJFrame(), WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
	}
	
	
	///////////////////////////////////////////////// Show Dialogs ///////////////////////////////////////////////
	
	/**
	 * Show Options Dialog with given option page number displayed (number is index on the topic list on the left of the dialog)
	 * 
	 * @param _topicIndex
	 */
	public static void showOptions(int _topicIndex) {
		
		OptionsDialog options = new OptionsDialog(new OptionsPages(), _topicIndex);
		options.setVisible(true);
		
	}
	
	/**
	 * Shows help dialog with given topic number on opening
	 * 
	 * @param _topicIndex 
	 */
	public static void showHelp(int _topicIndex){
		
		OptionsDialog help = new OptionsDialog(new HelpPages(), _topicIndex);
		help.setVisible(true);
		
	}
	
	/**
	 * Show about dialog
	 */
	public static void showAbout(){
		
		AboutDialog about = new AboutDialog();
		about.setVisible(true);
	}
	
	/**
	 * Refresht auf beiden Seiten die Favorites und gleicht sie mit dem aktuellen Stand ab, nachdem diese komplett neu geladen wurden
	 */
	public static void refreshFavorites(){
		try {
			Favorites.loadAllFavorites();
			List<SidePanel> sidePanels = MainPanel.getInstance().getMainSplitPane().getSidePanels();
			for (SidePanel sidePanel : sidePanels) {
				sidePanel.getTableAndFavoritesSplitPane().getFavoritesPanel().refresh();
			}
		} catch (Exception e) {
			Logger.error(e, "Favoriten konnten nicht aktualisiert werden!");
			FunctionsHelper.setMessage(Message.FAVORITES_NOT_LOADED);
		}
	}
	
	/**
	 * added einen Favoriten auf beiden Seiten
	 * @param fileEntry
	 */
	public static void addFavorite(FileEntry fileEntry){
		Favorites.getInstance().addToFavorites(fileEntry);
		try {
			List<SidePanel> sidePanels = MainPanel.getInstance().getMainSplitPane().getSidePanels();
			for (SidePanel sidePanel : sidePanels) {
				sidePanel.getTableAndFavoritesSplitPane().getFavoritesPanel().addFavoriteForFileEntry(fileEntry);
			}
			FunctionsHelper.setMessage(Message.FAVORITE_ADDED);
		} catch (Exception e) {
			Logger.error(e, "Favorit {0} konnte nicht hinzugefuegt werden!", fileEntry.getName());
			FunctionsHelper.setMessage(Message.FAVORITE_NOT_ADDED);
		}
	}
	
	/**
	 * @param message zu setzende Nachricht
	 */
	public static void setMessage(Message message){
		try {
			MainPanel.getInstance().setMessage(message);
		} catch(Exception e){
			// gotta catch 'em all? nah!
		}
	}
	
	/**
	 * Leert die Nachricht
	 */
	public static void clearMessage(){
		FunctionsHelper.setMessage(Message.EMPTY);
	}
	
	public static SidePanel getOtherSidePanel(SidePanel sourcePanel){
		List<SidePanel> sidePanels = MainPanel.getInstance().getMainSplitPane().getSidePanels();
		for (SidePanel nextPanel : sidePanels) {
			if(sourcePanel.compareTo(nextPanel)!=0)
				return nextPanel;
		}
		
		throw new RuntimeException("Geht irgendwie doch nicht!");
	}
}
