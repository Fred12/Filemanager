package com.kandinsky.conn;

public class FTPAlreadyConnectedException extends Exception{

	private static final long serialVersionUID = -520262650488066670L;

	public FTPAlreadyConnectedException(){
		super("Es besteht bereits eine FTP-Verbindung!");
	}
}
