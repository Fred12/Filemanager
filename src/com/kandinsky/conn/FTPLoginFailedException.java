package com.kandinsky.conn;

public class FTPLoginFailedException extends Exception {

	private static final long serialVersionUID = -4623665762687567617L;

	public FTPLoginFailedException(){
		super("Benutzername und Passwort sind falsch!");
	}
}
