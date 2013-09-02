package com.kandinsky.objects;

import java.awt.Color;

public enum Message{
	ADDED_FAVORITE("Favorit wurde erfolgreich hinzugefuegt", Color.GREEN);
	
	private Message(String message, Color color){
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
