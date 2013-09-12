package com.kandinsky.objects.fileOperation;

import java.util.List;

import org.pmw.tinylog.Logger;

import com.kandinsky.conn.FTPConnectionHandler;
import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.FileEntry;
import com.kandinsky.objects.FunctionsHelper;

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
		// TODO: gibts noch nicht, muss noch rein
		throw new UnsupportedOperationException();
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

}
