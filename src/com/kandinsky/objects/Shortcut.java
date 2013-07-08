package com.kandinsky.objects;

import java.io.Serializable;

public class Shortcut implements Serializable {

	private static final long serialVersionUID = 8510478971200260430L;

	private String key;
	private String name;
	private String shortcutCombi;

	public Shortcut(String key, String name, String shortcutCombi) {
		super();
		this.key = key;
		this.name = name;
		this.shortcutCombi = shortcutCombi;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
		return "Shortcut [key=" + key + ", name=" + name + ", shortcutCombi="
				+ shortcutCombi + "]";
	}

}
