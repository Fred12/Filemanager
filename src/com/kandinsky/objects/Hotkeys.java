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

import org.pmw.tinylog.Logger;


/**
 * Liste alle im System existierenden Shortcuts.
 * @author schmidtb
 */
public class Hotkeys extends HashMap<String, Hotkey> implements Serializable {

	private static final long serialVersionUID = 4430773610590354542L;

	public static final String REFRESH = "REFRESH";
	public static final String LEFT_PARENT = "LEFT_PARENT";
	public static final String RIGHT_PARENT = "RIGHT_PARENT";
	public static final String LEFT_BACK = "LEFT_BACK";
	public static final String RIGHT_BACK = "RIGHT_BACK";
	public static final String COPY_LEFT_TO_RIGHT = "COPY_LEFT_TO_RIGHT";
	
	private static final String HOTKEY_LIST_FILE = "shortcuts.dat";
	
	private static Hotkeys instance;
	
	private Hotkeys() {
		initAllFunctions();
	}

	private void initAllFunctions() {
		addIfNotExisting(REFRESH, "Aktualisieren");
		addIfNotExisting(LEFT_PARENT, "Ordner hoch - links");
		addIfNotExisting(RIGHT_PARENT, "Ordner hoch - rechts");
		addIfNotExisting(LEFT_BACK, "Ordner zurueck - links");
		addIfNotExisting(RIGHT_BACK, "Ordner zurueck - rechts");
		addIfNotExisting(COPY_LEFT_TO_RIGHT, "Von links nach rechts kopieren");
	}
	
	private void addIfNotExisting(String key, String name) {
		if(!containsKey(key)){
			put(new Hotkey(key, name, ""));
		}
	}

	public static Hotkeys getInstance(){
		if(instance == null){
			try {
				Hotkeys.readListFromFile();
			} catch (IOException e) {
				Logger.error(e);
				JOptionPane.showMessageDialog(null, "Konnte Shortcuts-Datei nicht lesen!");
				instance = new Hotkeys();
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
	private static void readListFromFile() throws IOException {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(HOTKEY_LIST_FILE));
			instance = (Hotkeys) ois.readObject();
			instance.initAllFunctions();
		} catch (ClassNotFoundException e) {
			throw new IOException("No class found. HELP!!");
		} catch (FileNotFoundException e) {
			// Das ist ok, wenn die Datei nicht existiert, wurde sie noch nicht angelegt oder gelöscht
			// Dann ist die Liste eben an dieser Stelle leer
			instance= new Hotkeys();
		} finally {
			if(ois!=null)
				ois.close();
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
				oos.writeObject(this);
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
	
	@Override
	public Hotkey put(String internalKey, Hotkey value) {
		Hotkey hotkey = super.put(internalKey, value);
		saveListToFile();
		return hotkey;
	}
	
	public Hotkey put(Hotkey hotkey){
		return put(hotkey.getInternalKey(), hotkey);
	}
	
}
