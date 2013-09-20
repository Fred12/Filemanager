package com.kandinsky.objects;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import javax.swing.JOptionPane;

import org.pmw.tinylog.Logger;

import com.kandinsky.conn.FTPConnectionHandler;
import com.kandinsky.gui.ButtonBar;
import com.kandinsky.gui.favorites.FavoriteListener;
import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.fileOperation.FTPOperationHandler;
import com.kandinsky.objects.fileOperation.FileOperationHandler;
import com.kandinsky.objects.fileOperation.FolderNotFoundException;
import com.kandinsky.objects.fileOperation.OperationHandler;

/**
 * Hier werden die einzelnen Funktionen aufgeschluesselt, die nicht statisch aufrufbar sind, da sie einer Seite zugeordnet sind, es also immer zwei
 * Aufrufarten gibt. Es wird ein SidePanel mitgegeben, auf welches dann in den einzelnen Funktionen zugegriffen werden kann, z. B. um die Tabelle
 * zu erneurn, einen Seitentext neu zu setzen, neue Favoriten hinzuzufuegen etc.
 * @author Benne
 */
public class SideFunctionsHelper implements FavoriteListener{

	/** Uebergebenes SidePanel, auf welches sich die Funktionen bezieht */
	private SidePanel sidePanel;
	/** aktueller Ordnername */
	private String currentFolderName;
	/** speichert den letzten Ordner ab, in dem man sich vor einem FTPConnect befunden hat */
	private String lastFolderBeforeFTPConnection;
	/** stellt FTP-Funktionalitaet bereit */
	private FTPConnectionHandler ftpConnectionHandler;
	
	private FTPOperationHandler ftpOperationHandler;
	private FileOperationHandler fileOperationHandler;
	
	public SideFunctionsHelper(SidePanel sidePanel){
		this.sidePanel = sidePanel;
		this.ftpConnectionHandler=new FTPConnectionHandler();
		ftpOperationHandler = new FTPOperationHandler(sidePanel, ftpConnectionHandler);
		fileOperationHandler = new FileOperationHandler(sidePanel);
	}
	
	/**
	 * Aendert einen Ordner-Pfad. Zentrale Anlaufstelle zum Aendern des Pfades, sonst sollte nirgendwo ein switchFolder aufgerufen werden.
	 * @param folderName
	 */
	public void switchFolder(String folderName, boolean addFolder){
		try {
			currentFolderName = getOperationHandler().switchFolder(folderName, addFolder);
		} catch(FolderNotFoundException e){
			Logger.warn("Konnte den Ordner {0} nicht finden!", folderName);
			FunctionsHelper.setMessage(Message.FOLDER_NOT_FOUND);
		}
	}
	
	public ButtonBar getButtonBar() {
		ButtonBar buttonBar = sidePanel.getButtonBar();
		return buttonBar;
	}
	
	public void exChangePanelsFolder() {
		//Vertausche die Ordnerpfade
		String thisFolder = getCurrentFolderName();
		SidePanel otherSidePanel = FunctionsHelper.getOtherSidePanel(this.sidePanel);		
		String otherFolder = otherSidePanel.getCurrentFolderName();	
		otherSidePanel.getSideFunctionsHelper().switchFolder(thisFolder, false);
		switchFolder(otherFolder,false);
		//Vertausche die 2 Stacks der jeweiligen ButtonBar
		ButtonBar otherButtonBar = otherSidePanel.getButtonBar();
		Stack<String> otherStack1 = otherButtonBar.getStack1();
		Stack<String> otherStack2 = otherButtonBar.getStack2();
		ButtonBar thisButtonBar = getButtonBar();
		Stack<String> thisStack1 = thisButtonBar.getStack1();
		Stack<String> thisStack2 = thisButtonBar.getStack2();
		otherButtonBar.setStack1(thisStack1);
		otherButtonBar.setStack2(thisStack2);
		thisButtonBar.setStack1(otherStack1);
		thisButtonBar.setStack2(otherStack2);
		thisButtonBar.checkStacks();
		otherButtonBar.checkStacks();
	}
	
	/**
	 * Öffnet eine CMD-Shell (unter windows)
	 */
	public void openCMDShell() {
		try {	   
			
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
			String path = getCurrentFolderName().toString();
			ProcessBuilder b = new ProcessBuilder();
			b.directory(new File(path));
			b.command("cmd", "/k", "start"); 								
			b.start();		
			}
			
			else  {
				String path = getCurrentFolderName().toString();
		        String[] shellcom = {"xterm"};
		        Process p ;		             
		        ProcessBuilder builder = new ProcessBuilder(shellcom);                
		        builder.directory(new File(path));          // here.
		        p = builder.start();
		        
		        
				
		          }
		}
		    catch (IOException e) {
		    	System.err.println(e.toString());
		    	e.printStackTrace();
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	

	 
	
	public void openFolder() {
		try {
			
			File path = new File(getCurrentFolderName().toString());
			java.awt.Desktop.getDesktop().browse((path).toURI());			
			} catch (IOException e) {
				System.err.println(e);
				e.printStackTrace();
			}
		}
	
	
	public void getRootFolder() {
		File actualPath = new File(getCurrentFolderName());
		String parentFolder = actualPath.getParent();
		if (parentFolder != null) {			
			switchFolder(parentFolder, false);
		}
	}
	
	
	public String getFolder()  {
		return sidePanel.getFolderNamePanel().getFolderText();		
	}
	
	public void refresh(){
		try {
			switchFolder(currentFolderName, false);
			sidePanel.getTableAndFavoritesSplitPane().getTable().repaint();
			sidePanel.getTableAndFavoritesSplitPane().repaint();
		} catch (Exception e) {
			// TODO: ordentliches Fehlerhandling, zB Fehlermeldung in der Info setzen
			e.printStackTrace();
		}
	}
	
	public void setSelectedFiles(File[] files){
		sidePanel.setSelectedFiles(files);
	}

	public void setFileCountInFolder(int size) {
		sidePanel.setFileCountInFolder(size);
	}

	@Override
	public void removeFavorite(FileEntry fileEntry) {
		try {
			Favorites favorites = Favorites.getInstance();
			favorites.removeFavorite(fileEntry);
			sidePanel.getTableAndFavoritesSplitPane().getFavoritesPanel().refresh();
			FunctionsHelper.setMessage(Message.FAVORITE_REMOVED);
		} catch (Exception e) {
			Logger.error(e, "Konnte Favorit nicht entfernen fuer fileEntry {0}!", fileEntry);
		}
	}

	@Override
	public void execute(FileEntry fileEntry) {
		if(fileEntry.getType()==FileType.DIRECTORY)
			this.switchFolder(fileEntry.getAbsoluteFileName(), true);
	}
	
	public void copySelectedFilesToOtherSide(){
		getOperationHandler().copySelectedFileEntries();
		refresh();
	}
	
	public void moveSelectedFilesToOtherSide(){
		getOperationHandler().moveSelectedFileEntries();
		refresh();
	}
	
	public void deleteSelectedFiles(){
		getOperationHandler().deleteSelectedFileEntries();
		refresh();
	}
	
	/**
	 * Fragt nach einem Datei-Namen und legt diesen dann an.
	 */
	public void createNewFile(){
		getOperationHandler().createNewFile();
		refresh();
	}
	
	/**
	 * Fragt nach einem Ordner-Namen und legt diesen dann an.
	 */
	public void createNewFolder() {
		getOperationHandler().createNewFolder();
		refresh();
	}

	/**
	 * Fragt nach einem neuen Namen fuer eine Datei/Ordner und versucht dann ein Rename durchzufuehren. Falls die Datei/Ordner bereits vorhanden ist, wird 
	 * entsprechend eine Fehlermeldung angezeigt.
	 * @param fileEntry umzubenennende Datei
	 */
	public void rename(FileEntry fileEntry) {
		String name = JOptionPane.showInputDialog(sidePanel, "Neuer Name", fileEntry.getName());
		if (name != null) {
			try {
				String folderName = currentFolderName.endsWith("/")?currentFolderName:currentFolderName+"/";
				getOperationHandler().rename(fileEntry, folderName+name);
				refresh();
			} catch (IOException e) {
				Logger.error(e, "Datei umbenennen nicht moeglich. Neuer Name: {0}", name);
				FunctionsHelper.setMessage(Message.RENAME_FOLDER_FAILED);
			}
		}
	}
	
	/**
	 * Versucht eine Verbindung mithilfe eines FTP-Namens herzustellen
	 * @param ftpName Konfigurationsname
	 */
	public void connectToFtp(String ftpName){
		try {
			FTPEntry entry = FTPList.getInstance().getConfigByName(ftpName);
			ftpConnectionHandler.connect(entry);
			FunctionsHelper.setMessage(Message.FTP_CONNECTED);

			sidePanel.getTableAndFavoritesSplitPane().getTable().setFtpConnected(true);
			lastFolderBeforeFTPConnection = getCurrentFolderName();
			currentFolderName="";

			switchFolder("/", true);

		} catch (Exception e) {
			Logger.error("Connection Versuch misslungen!", e);
			FunctionsHelper.setMessage(Message.FTP_CONNECT_FAILED);
		}
	}
	
	/**
	 * Schliesst eine FTP-Verbindung falls vorhanden und gibt eine Nachricht aus.
	 */
	public void disconnectFromFtp(){
		ftpConnectionHandler.disconnect();
		FunctionsHelper.setMessage(Message.FTP_DISCONNECTED);
		sidePanel.getTableAndFavoritesSplitPane().getTable().setFtpConnected(false);
		switchFolder(lastFolderBeforeFTPConnection, true);
	}

	public String getCurrentFolderName() {
		return currentFolderName;
	}
	
	public String setCurrentFolderName() {
		return currentFolderName;
	}
	
	public boolean isFtpConnected(){
		return ftpConnectionHandler.isConnected();
	}
	
	/**
	 * @return FTP- oder normalen FileOperator, je nachdem, ob FTP-Connection aufgebaut ist.
	 */
	private OperationHandler getOperationHandler(){
		if(isFtpConnected())
			return ftpOperationHandler;
		else
			return fileOperationHandler;
	}
	
	/**
	 * oeffnet (zumindest unter Windows) den aktuell gewaehlten Ordner in einem eigenen Fenster
	 */
	public void openFolderInWindows(){
		switchFolder(System.getProperty("user.home"), true);
	}
	
	/**
	 * Springt im Verlauf eins zurück.
	 */
	public void back(){
		sidePanel.getButtonBar().back();
	}
	
	/**
	 * Springt im Verlauf eins nach vorne.
	 */
	public void forward(){
		sidePanel.getButtonBar().forward();
	}
}
