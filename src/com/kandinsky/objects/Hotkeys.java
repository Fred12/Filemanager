package com.kandinsky.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.pmw.tinylog.Logger;


/**
 * Liste alle im System existierenden Shortcuts.
 * @author schmidtb
 */
public class Hotkeys implements Serializable {


	private static final long serialVersionUID = 4430773610590354542L;

	public static final String REFRESH = "REFRESH";
	public static final String LEFT_PARENT = "LEFT_PARENT";
	public static final String RIGHT_PARENT = "RIGHT_PARENT";
	public static final String LEFT_BACK = "LEFT_BACK";
	public static final String RIGHT_BACK = "RIGHT_BACK";
	public static final String COPY_LEFT_TO_RIGHT = "COPY_LEFT_TO_RIGHT";
	public static final String COPY_RIGHT_TO_LEFT = "COPY_RIGHT_TO_LEFT";
	public static final String NEW_FOLDER_LEFT = "NEW_FOLDER_LEFT";
	public static final String NEW_FOLDER_RIGHT = "NEW_FOLDER_RIGHT";
	public static final String NEW_FILE_LEFT = "NEW_FILE_LEFT";
	public static final String NEW_FILE_RIGHT = "NEW_FILE_RIGHT";
	
	private static final String HOTKEY_LIST_FILE = "shortcuts.dat";
	
	private static Hotkeys instance;
	private SortedSet<Hotkey> hotkeys;
	
	private Hotkeys() {
		hotkeys = new TreeSet<>();
		initAllFunctions();
	}

	private void initAllFunctions() {
		add(REFRESH, "Aktualisieren");
		add(LEFT_PARENT, "Ordner hoch - links");
		add(RIGHT_PARENT, "Ordner hoch - rechts");
		add(LEFT_BACK, "Ordner zurueck - links");
		add(RIGHT_BACK, "Ordner zurueck - rechts");
		add(COPY_LEFT_TO_RIGHT, "Von links nach rechts kopieren");
		add(COPY_RIGHT_TO_LEFT, "Von rechts nach links kopieren");
		add(NEW_FOLDER_LEFT, "Neuer Ordner - links");
		add(NEW_FOLDER_RIGHT, "Neuer Ordner - rechts");
		add(NEW_FILE_LEFT, "Neue Datei links");
		add(NEW_FILE_RIGHT, "Neue Datei rechts");
	}
	
	private void add(String key, String name) {
		Hotkey e = new Hotkey(key, name, "");
		hotkeys.add(e);
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
		for(Hotkey hotkey : hotkeys){
			if(hotkey.getHotkeyCombination().equals(key))
				return hotkey;
		}
		throw new NoSuchElementException();
	}

	public Vector<String> getFunctions(){
		Vector<String>functionNames = new Vector<>();
		for(Hotkey hotkey : hotkeys){
			functionNames.add(hotkey.getFunctionName());
		}
		
		return functionNames;
	}
	
	public Vector<Hotkey> getHotkeys() {
		Vector<Hotkey> hotkeyVec = new Vector<>();
		for(Hotkey hotkey : hotkeys){
			hotkeyVec.add(hotkey);
		}
		
		return hotkeyVec;
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
}
