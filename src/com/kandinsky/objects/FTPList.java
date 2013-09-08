package com.kandinsky.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Hilfsklasse zur Verwaltung der FTP Server Einträge und zur Verfügungstellung
 * über das gesamte Programm hinweg. Da die Arrayfunktionen nicht erneut implementiert
 * werden sollen, wird auf die gegebene ArrayList zurückgegriffen. Diese ist 
 * nicht statisch implementiert, deshalb wird hier ein Singleton darübergebaut,
 * damit die Informationen konsistent bleiben.
 * 
 * @author Stefan
 *
 */
public class FTPList extends ArrayList<FTPEntry> implements Serializable {

	private static FTPList self = null;
	
	private static String FTP_LIST_FILE = "FTPList.dat";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5565796337543758338L;

	private FTPList() {
		
	}
	
	public static FTPList getInstance(){
		
		if(FTPList.self == null){
			
			try {
				FTPList.self = FTPList.readListFromFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Konnte FTP-Datei nicht lesen!");
				FTPList.self = new FTPList();
				e.printStackTrace();
			}
		}
		return FTPList.self;
		
	}
	
	public String[] getNames(){
		
		String[] names = new String[FTPList.self.size()];
		
		int counter = 0;
		for (FTPEntry currentEntry: FTPList.self){
			names[counter] = currentEntry.getName();
			++counter;
		}
		
		return names;
		
	}
	
	public void swapEntries(int first, int second) {
		
		if (first < 0 || first > FTPList.self.size() - 1 || second < 1 || second > FTPList.self.size()) {
			throw new IllegalArgumentException("List Swap Indexes out of Range (Second also cannont be 0 and first not last!");
		}
		FTPEntry firstEntry = FTPList.self.get(first);
		FTPEntry secondEntry = FTPList.self.get(second);
		FTPList.self.set(first, secondEntry);
		FTPList.self.set(second, firstEntry);
		this.saveListToFile();
	}
	
	/**
	 * Lädt alle Favorites aus einer vorhandenen Datei und initialisiert diese im Favorites-Singleton.
	 * @param ois
	 * @throws IOException
	 */
	private static FTPList readListFromFile() throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FTP_LIST_FILE));
			FTPList ftpList = (FTPList) ois.readObject();
			ois.close();
			return ftpList;
		} catch (ClassNotFoundException e) {
			throw new IOException("No class found. HELP!!");
		} catch (FileNotFoundException e) {
			// Das ist ok, wenn die Datei nicht existiert, wurde sie noch nicht angelegt oder gelöscht
			// Dann ist die Liste eben an dieser Stelle leer
			return new FTPList();
		}
	}
	
	/**
	 * Speichert die Favorites weg. Kann nur von intern aufgerufen werden.
	 */
	public void saveListToFile() {
		try {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(FTP_LIST_FILE));
				oos.writeObject(FTPList.self);
				oos.flush();
			} finally {
				oos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
