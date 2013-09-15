package com.kandinsky.objects;

import java.io.Serializable;

/**
 * Shortcut-Klasse, repräsentiert einen einzigen Shortcut.
 * @author schmidtb
 */
public class Hotkey implements Serializable, Comparable<Hotkey>{

	private static final long serialVersionUID = 8510478971200260430L;

	/** Interner Key zur Eindeutigen Referenzierung der Funktion */
	private String internalKey;
	/** Funktionsname der zugeordneten Funktion */
	private String functionName;
	/** Hotkey-Kombination als String */
	private String hotkeyCombination;

	/**
	 * @param internalKey
	 * @param name
	 * @param hotkeyCombination
	 */
	public Hotkey(String internalKey, String name, String hotkeyCombination) {
		this.internalKey = internalKey;
		this.functionName = name;
		setHotkeyCombination(hotkeyCombination);
	}

	public String getInternalKey() {
		return internalKey;
	}

	public void setInternalKey(String key) {
		this.internalKey = key;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getHotkeyCombination() {
		return hotkeyCombination;
	}

	/**
	 * Falls null uebergeben wurde, wir ein Leerstring gesetzt
	 * @param hotkeyCombination
	 */
	public void setHotkeyCombination(String hotkeyCombination) {
		if (hotkeyCombination == null)
			this.hotkeyCombination = "";
		else
			this.hotkeyCombination = hotkeyCombination;
	}

	@Override
	public String toString() {
		return functionName;
	}

	/**
	 * natural ordering nach Funktionsname
	 */
	@Override
	public int compareTo(Hotkey o) {
		return functionName.compareTo(o.getFunctionName());
	}
}
