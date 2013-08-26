package com.kandinsky.objects;

public enum FileType {

	FILE("Datei"), DIRECTORY("Ordner"), UNKNOWN("Unbekannt");
	
	private FileType(String representation){
		this.representation = representation;
	}
	
	private String representation;
	
	@Override
	public String toString() {
		return representation;
	}
}
