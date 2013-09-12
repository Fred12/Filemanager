package com.kandinsky.objects.fileOperation;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.pmw.tinylog.Logger;

import com.kandinsky.conn.FTPConnectionHandler;
import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.FileEntry;
import com.kandinsky.objects.FunctionsHelper;
import com.kandinsky.objects.Message;

/**
 * Kuemmert sich um Funktionalitaet auf einem FTP-Server
 * @author Benne
 *
 */
public class FTPOperationHandler extends OperationHandler {

	private final FTPConnectionHandler ftpConnectionHandler;
	
	public FTPOperationHandler(SidePanel sidePanel, FTPConnectionHandler ftpConnectionHandler) {
		super(sidePanel);
		this.ftpConnectionHandler = ftpConnectionHandler;
	}

	@Override
	public String switchFolder(String folderName, boolean addFolder) throws FolderNotFoundException {
		try {
			String newFolderName = ftpConnectionHandler.changeWorkingDirectory(folderName);
			Logger.info("Ordner gewechselt: " + newFolderName);
			// Absolut
			List<FileEntry> newEntries = ftpConnectionHandler.getFilesInFolder();
			sidePanel.getTableAndFavoritesSplitPane().getTable().setFileEntries(newEntries);
			sidePanel.setFileCountInFolder(newEntries.size());
			sidePanel.getFolderNamePanel().setFolderText(newFolderName);
			if(addFolder) {
				sidePanel.getButtonBar().addFolder(folderName);
			}
			FunctionsHelper.clearMessage();
			return newFolderName;
		} catch (Exception e) {
			throw new FolderNotFoundException(folderName);
		}
	}

	@Override
	public void createNewFile() {
		// TODO: gibts noch nicht, muss noch rein
		throw new UnsupportedOperationException();
	}

	@Override
	public void createNewFolder() {
		String name = JOptionPane.showInputDialog(sidePanel, "Ordnername", null);
		if (name != null) {
			String currentFolderName = sidePanel.getCurrentFolderName();
			currentFolderName+=currentFolderName.endsWith("/")?"":"/";
			try {
				ftpConnectionHandler.makeDirectory(currentFolderName + name);
				Logger.info("Neuer Ordner {0} wurde erstellt.", currentFolderName + name);
			} catch (IOException e) {
				Logger.error("Anlegen eines neuen Ordners war leider nicht moeglich");
				FunctionsHelper.setMessage(Message.CREATE_FILE_FAILED);
			}
		}
	}

	@Override
	public void copySelectedFileEntries() {
		// TODO: gibts noch nicht, muss noch rein
		throw new UnsupportedOperationException();
	}

	@Override
	public void moveSelectedFileEntries() {
		// TODO: gibts noch nicht, muss noch rein
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteSelectedFileEntries() {
		// TODO: gibts noch nicht, muss noch rein
		throw new UnsupportedOperationException();
	}

	@Override
	public void rename(FileEntry fileEntry, String newFileName) throws IOException {
		// TODO: gibts noch nicht, muss noch rein
		throw new UnsupportedOperationException();
	}
}
