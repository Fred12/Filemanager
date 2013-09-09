package com.kandinsky.conn;

import com.kandinsky.objects.FTPEntry;

public class FTPConnectionFailedException extends Exception{

	private static final long serialVersionUID = -8264509377633581509L;

	public FTPConnectionFailedException(FTPEntry config, Exception e){
		super("Konnte keine Verbindung herstellen. Konfiguration: "+config.toString(), e);
	}
	
	public FTPConnectionFailedException(FTPEntry config){
		super("Konnte keine Verbindung herstellen. Konfiguration: "+config.toString());
	}
}
