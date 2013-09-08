package com.kandinsky.main;

import java.io.IOException;
import java.net.SocketException;

import com.kandinsky.conn.FTPConnectionHandler;
import com.kandinsky.objects.FTPEntry;
import com.kandinsky.objects.FileEntry;

/**
 * Zum Testen der FTP-Verbindung
 * @author Benne
 *
 */
public class FTPTest {

	public static void main(String[] args) throws SocketException, IOException {
		FTPConnectionHandler handler = FTPConnectionHandler.getInstance();
		
		FTPEntry ftpConfig = new FTPEntry("Testentry", "ftp.halifax.rwth-aachen.de", "anonymous", "mail@mail.de");
		ftpConfig.setPort(21);
		try {
			handler.connect(ftpConfig);
			handler.connect(ftpConfig);
			for(FileEntry fileEntry : handler.getFilesInFolder("apache")){
				System.out.println(fileEntry);
			}
			handler.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
