package com.kandinsky.objects;

public class FTPConfigNotExistingException extends Exception{

	private static final long serialVersionUID = 4623059580322867415L;
	
	public FTPConfigNotExistingException(String name){
		super("Die Konfiguration mit folgendem Namen existiert nicht: "+name);
	}
}
