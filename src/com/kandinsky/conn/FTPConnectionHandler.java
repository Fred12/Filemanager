package com.kandinsky.conn;

import java.io.IOException;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.pmw.tinylog.Logger;

import com.kandinsky.objects.FTPEntry;
import com.kandinsky.objects.FTPFileEntry;
import com.kandinsky.objects.FileEntry;

public class FTPConnectionHandler {

	private static FTPConnectionHandler instance;
	private FTPClient ftpConnection;
	private FTPEntry currentConfig;

	private FTPConnectionHandler() {
		ftpConnection = new FTPClient();
		ftpConnection.setConnectTimeout(15000);
	}

	public static FTPConnectionHandler getInstance() {
		if (instance == null)
			instance = new FTPConnectionHandler();

		return instance;
	}
	
	public boolean isConnected(){
		return ftpConnection.isConnected();
	}

	/**
	 * Versucht eine Verbindung mit angegebener Config herzustellen
	 * @param ftpConfig
	 * @return Verbindung hergestellt oder nicht
	 * @throws Exception
	 */
	public void connect(FTPEntry ftpConfig) throws Exception {
		if (ftpConnection.isConnected()) {
			throw new FTPAlreadyConnectedException();
		} else {
			Logger.info("FTP-Verbindung fuer {0} wird hergestellt!", ftpConfig.getName());
			currentConfig = ftpConfig;
			try {
				ftpConnection.connect(ftpConfig.getServer(), ftpConfig.getPort());
				if (!ftpConnection.isConnected())
					throw new FTPConnectionFailedException(ftpConfig);

				boolean login = ftpConnection.login(ftpConfig.getUsername(), ftpConfig.getPassword());
				if (!login) {
					Logger.error("Benutzername und Passwort stimmen nicht!");
					ftpConnection.disconnect();
					throw new FTPLoginFailedException();
				}
			} catch (SocketException e) {
				Logger.error(e, "Irgendwas hat da nicht geklappt! {0}", e.getMessage());
				throw new FTPConnectionFailedException(ftpConfig, e);
			} catch (IOException e) {
				Logger.error(e, "Irgendwas hat da nicht geklappt! {0}", e.getMessage());
				throw new FTPConnectionFailedException(ftpConfig, e);
			}
		}
		Logger.info("FTP-Verbindung hergestellt!");
	}

	/**
	 * Versucht eine bestehende Verbindung zu schliessen
	 */
	public void disconnect() {
		Logger.info("FTP-Verbindung fuer {0} wird geschlossen!", currentConfig.getName());
		try {
			ftpConnection.disconnect();
		} catch (IOException e) {
			Logger.warn("Schliessen der FTP-Verbindung war leider nicht mehr moeglich! {0}", e.getMessage());
		}
	}

	/**
	 * Versucht Dateien eines angegebenen Pfades zu laden
	 * @param pathName
	 * @return Liste mit FileEntries
	 */
	public List<FileEntry> getFilesInFolder(String pathName) {
		try {
			FTPFile[] files = ftpConnection.listFiles(pathName);
			List<FileEntry> fileEntries = new LinkedList<>();
			for (FTPFile nextFile : files) {
				fileEntries.add(new FTPFileEntry(nextFile));
			}
			return fileEntries;
		} catch (IOException e) {
			Logger.error(e, "Laden der Dateien leider nicht moeglich! {0}", e.getMessage());
			throw new RuntimeException("Laden der Dateien leider nicht moeglich! " + e.getMessage(), e);
		}
	}

}
