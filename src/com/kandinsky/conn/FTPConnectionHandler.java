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
	private FTPClient f;
	private FTPEntry currentConfig;

	private FTPConnectionHandler() {
		f = new FTPClient();
		f.setConnectTimeout(15000);
	}

	public static FTPConnectionHandler getInstance() {
		if (instance == null)
			instance = new FTPConnectionHandler();

		return instance;
	}

	/**
	 * Versucht eine Verbindung mit angegebener Config herzustellen
	 * @param ftpConfig
	 * @return Verbindung hergestellt oder nicht
	 * @throws Exception
	 */
	public boolean connect(FTPEntry ftpConfig) throws Exception {
		if (f.isConnected()) {
			throw new FTPAlreadyConnectedException();
		} else {
			Logger.info("FTP-Verbindung fuer {0} wird hergestellt!", ftpConfig.getName());
			currentConfig = ftpConfig;
			try {
				f.connect(ftpConfig.getServer(), ftpConfig.getPort());
				if (!f.isConnected())
					return false;

				boolean login = f.login(ftpConfig.getUsername(), ftpConfig.getPassword());
				if (!login) {
					Logger.error("Benutzername und Passwort stimmen nicht!");
					f.disconnect();
					throw new FTPLoginFailedException();
				} else {
					return true;
				}
			} catch (SocketException e) {
				Logger.error(e, "Irgendwas hat da nicht geklappt! {0}", e.getMessage());
			} catch (IOException e) {
				Logger.error(e, "Irgendwas hat da nicht geklappt! {0}", e.getMessage());
			}
			return false;
		}
	}

	/**
	 * Versucht eine bestehende Verbindung zu schliessen
	 */
	public void disconnect() {
		Logger.info("FTP-Verbindung fuer {0} wird geschlossen!", currentConfig.getName());
		try {
			f.disconnect();
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
			FTPFile[] files = f.listFiles(pathName);
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
