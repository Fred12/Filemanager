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
public class Hotkeys extends HashMap<String, Hotkey> implements Serializable {

	private static final long serialVersionUID = 4430773610590354542L;
	
	private static final String HOTKEY_LIST_FILE = "shortcuts.dat";
	
	private static Hotkeys instance;
	
	private Hotkeys() {
	}
	
	public static Hotkeys getInstance(){
		
		if(instance == null){
			try {
				instance = Hotkeys.readListFromFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Konnte Shortcuts-Datei nicht lesen!");
				instance = new Hotkeys();
				e.printStackTrace();
			}
		}
		return instance;
	}

	public Hotkey getHotkeyByKey(String key){
		Hotkey toReturn = get(key);
		if(toReturn!=null){
			return toReturn;
		} else {
			throw new RuntimeException("Key nicht gefunden!");
		}
	}

	public String[] getKeys(){
		Set<String> keySet = keySet();
		return keySet.toArray(new String[keySet.size()]);
	}
	
	/**
	 * Lädt alle Favorites aus einer vorhandenen Datei und initialisiert diese im Favorites-Singleton.
	 * @param ois
	 * @throws IOException
	 */
	private static Hotkeys readListFromFile() throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HOTKEY_LIST_FILE));
			Hotkeys shortcuts = (Hotkeys) ois.readObject();
			ois.close();
			return shortcuts;
		} catch (ClassNotFoundException e) {
			throw new IOException("No class found. HELP!!");
		} catch (FileNotFoundException e) {
			// Das ist ok, wenn die Datei nicht existiert, wurde sie noch nicht angelegt oder gelöscht
			// Dann ist die Liste eben an dieser Stelle leer
			Hotkeys ret = new Hotkeys();
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
				oos = new ObjectOutputStream(new FileOutputStream(HOTKEY_LIST_FILE));
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
		this.put("temp1", new Hotkey("temp1","Temporärer Shortcut1", "STRG-C"));
	}
	
}
