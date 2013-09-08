package com.kandinsky.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;


/**
 * Liste alle im System existierenden Shortcuts.
 * @author schmidtb
 */
public class Shortcuts extends HashMap<String, Shortcut> implements Serializable {

	private static final long serialVersionUID = 4430773610590354542L;
	
	private static final String SHORTCUT_LIST_FILE = "shortcuts.dat";
	
	private static Shortcuts self;
	
	private Shortcuts() {
		
	}
	
	public static Shortcuts getInstance(){
		
		if(Shortcuts.self == null){
			
			try {
				Shortcuts.self = Shortcuts.readListFromFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Konnte Shortcuts-Datei nicht lesen!");
				Shortcuts.self = new Shortcuts();
				e.printStackTrace();
			}
		}
		return Shortcuts.self;
		
	}


	
	public Shortcut getShortcutByKey(String key){
		
		Shortcut toReturn = Shortcuts.self.get(key);
		if(toReturn!=null){
			return toReturn;
		} else {
			throw new RuntimeException("Key nicht gefunden!");
		}
	}

	
	public String[] getKeys(){
		
		Set<String> keySet = Shortcuts.self.keySet();
		return keySet.toArray(new String[keySet.size()]);
		
	}
	
	/**
	 * Lädt alle Favorites aus einer vorhandenen Datei und initialisiert diese im Favorites-Singleton.
	 * @param ois
	 * @throws IOException
	 */
	private static Shortcuts readListFromFile() throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SHORTCUT_LIST_FILE));
			Shortcuts shortcuts = (Shortcuts) ois.readObject();
			ois.close();
			return shortcuts;
		} catch (ClassNotFoundException e) {
			throw new IOException("No class found. HELP!!");
		} catch (FileNotFoundException e) {
			// Das ist ok, wenn die Datei nicht existiert, wurde sie noch nicht angelegt oder gelöscht
			// Dann ist die Liste eben an dieser Stelle leer
			Shortcuts ret = new Shortcuts();
			ret.createTemplate();
			return ret;
			
			
		}
	}
	
	/**
	 * Speichert die Favorites weg. Kann nur von intern aufgerufen werden.
	 * 
	 * Temporär unwirksam, solange die Shortcuts Liste nicht feststeht
	 */
	public void saveListToFile() {
		try {
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(SHORTCUT_LIST_FILE));
				//oos.writeObject(Shortcuts.self);
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
	
	/**
	 * 
	 */
	private void createTemplate(){
		
		this.put("temp1", new Shortcut("temp1","Temporärer Shortcut1", "STRG-C"));
	}
	
}
