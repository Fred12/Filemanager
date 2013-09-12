package com.kandinsky.objects;

import java.io.Serializable;

/**
 * Shortcut-Klasse, repräsentiert einen einzigen Shortcut.
 * @author schmidtb
 */
public class Hotkey implements Serializable {

	private static final long serialVersionUID = 8510478971200260430L;

	private String internalKey;
	private String name;
	private String shortcutCombi;

	public Hotkey() {
		super();
	}
	
	public Hotkey(String internalKey, String name, String shortcutCombi) {
		this.internalKey = internalKey;
		this.name = name;
		this.shortcutCombi = shortcutCombi;
	}

	public String getInternalKey() {
		return internalKey;
	}

	public void setInternalKey(String key) {
		this.internalKey = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortcutCombi() {
		return shortcutCombi;
	}

	public void setShortcutCombi(String shortcutCombi) {
		this.shortcutCombi = shortcutCombi;
	}

	@Override
	public String toString() {
		return name;
	}

}
