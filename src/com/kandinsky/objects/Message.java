package com.kandinsky.objects;

import java.awt.Color;

/**
 * Nachrichten, die ueber das System ausgegeben werden koennen
 * @author Benne
 */
public enum Message {
	
	FAVORITE_ADDED("Favorit wurde erfolgreich hinzugefuegt.", Color.GREEN),
	FAVORITE_REMOVED("Favorit wurde erfolgreich entfernt.", Color.GREEN),
	DELETE_COMPLETE("Datei(en) wurde(n) erfolgreich geloescht.", Color.GREEN),
	COPY_COMPLETE("Datei(en) wurde(n) erfolgreich kopiert.", Color.GREEN),
	MOVE_COMPLETE("Datei(en) wurde(n) erfolgreich verschoben.", Color.GREEN),
	FAVORITE_NOT_ADDED("Favorit konnte nicht hinzugefuegt werden!", Color.RED),
	FAVORITES_NOT_LOADED("Favoriten konnten nicht aktualisiert werden!", Color.RED),
	FAVORITES_NOT_SAVED("Favoriten konnten nicht gespeichert werden!", Color.RED),
	FOLDER_NOT_FOUND("Der angegebene Ordner wurde nicht gefunden!", Color.RED),
	COPY_FAILED("Die Datei(en) konnten leider nicht vollst�ndig kopiert werden!", Color.RED),
	MOVE_FAILED("Die Datei(en) konnten leider nicht vollst�ndig verschoben werden!", Color.RED),
	DELETE_FAILED("Die Datei(en) konnten leider nicht vollst�ndig geloescht werden!", Color.RED),
	CREATE_FILE_FAILED("Es konnte leider keine neue Datei angelegt werden!", Color.RED),
	CREATE_FOLDER_FAILED("Es konnte leider kein neuer Ordner angelegt werden!", Color.RED),
	NO_FAVORITES_FOUND("Keine Favoriten gefunden.", Color.LIGHT_GRAY),
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
