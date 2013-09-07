package com.kandinsky.objects;

import java.awt.Color;

public enum Message {
	ADDED_FAVORITE("Favorit wurde erfolgreich hinzugefuegt.", Color.GREEN),
	FAVORITE_NOT_ADDED("Favorit konnte nicht hinzugefuegt werden!", Color.RED),
	FAVORITES_NOT_LOADED("Favoriten konnten nicht aktualisiert werden!", Color.RED),
	FOLDER_NOT_FOUND("Der angegebene Ordner wurde nicht gefunden!", Color.RED),
	COPY_FAILED("Die Datei(en) konnten leider nicht vollständig kopiert werden!", Color.RED),
	MOVE_FAILED("Die Datei(en) konnten leider nicht vollständig verschoben werden!", Color.RED),
	DELETE_FAILED("Die Datei(en) konnten leider nicht vollständig geloescht werden!", Color.RED),
	EMPTY("-", Color.LIGHT_GRAY);
	
	private Message(String message, Color color) {
		this.message = message;
		this.color = color;
	}

	private final String message;
	private final Color color;

	public String getMessage() {
		return message;
	}

	public Color getColor() {
		return color;
	}
}
