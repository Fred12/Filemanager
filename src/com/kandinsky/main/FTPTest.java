package com.kandinsky.main;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Zum Testen der FTP-Verbindung
 * @author Benne
 *
 */
public class FTPTest {

	public static void main(String[] args) throws SocketException, IOException {
		FTPClient f = new FTPClient();
		f.setConnectTimeout(30000);
		f.connect("speedtest.qsc.de", 21);
		
		boolean login = f.login("anonymous", "test@mail.de");
		System.out.println(login);
		FTPFile[] files = f.listFiles("/");
		for(FTPFile file : files){
			System.out.println(file.getType()+", "+file.getName());
		}
	}
}
