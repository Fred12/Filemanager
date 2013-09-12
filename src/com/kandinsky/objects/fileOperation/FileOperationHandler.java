package com.kandinsky.objects.fileOperation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.FileEntry;
import com.kandinsky.objects.FunctionsHelper;
import com.kandinsky.objects.Message;

/**
 * Kuemmert sich um Funktionalitaet auf lokaler Dateiebene.
 * @author Benne
 */
public class FileOperationHandler extends OperationHandler {

	public FileOperationHandler(SidePanel sidePanel) {
		super(sidePanel);
	}

	@Override
	public String switchFolder(String folderName, boolean addFolder) throws FolderNotFoundException {
		File folder = new File(folderName);
		if (!folder.isDirectory()) {
			throw new FolderNotFoundException(folderName);
		} else {
			try {
				List<FileEntry> newEntries = FileEntry.getFileEntryList(folder);
				sidePanel.getTableAndFavoritesSplitPane().getTable().setFileEntries(newEntries);
				sidePanel.setFileCountInFolder(newEntries.size());
				sidePanel.getFolderNamePanel().setFolderText(folderName);

				if (addFolder) {
					sidePanel.getButtonBar().addFolder(folderName);
				}
				FunctionsHelper.clearMessage();
				return folderName;
			} catch (Exception e) {
				throw new FolderNotFoundException(folderName);
			}
		}
	}

	@Override
	public void createNewFile() {
		String name = JOptionPane.showInputDialog(sidePanel, "Dateiname", null);
		if (name != null) {
			String currentFolderName = sidePanel.getCurrentFolderName();
			currentFolderName+=currentFolderName.endsWith("/")?"":"/";
			File file = new File(currentFolderName + name);
			try {
				file.createNewFile();
				Logger.info("Neue Datei {0} wurde erstellt.", currentFolderName + name);
			} catch (IOException e) {
				Logger.error(e, "Anlegen einer neuen Datei war leider nicht moeglich");
				FunctionsHelper.setMessage(Message.CREATE_FILE_FAILED);
			}
		}
	}

	@Override
	public void createNewFolder() {
		String name = JOptionPane.showInputDialog(sidePanel, "Ordnername", null);
		if (name != null) {
			String currentFolderName = sidePanel.getCurrentFolderName();
			currentFolderName+=currentFolderName.endsWith("/")?"":"/";
			File file = new File(currentFolderName + name);
			boolean created = file.mkdir();
			if (created) {
				Logger.info("Neuer Ordner {0} wurde erstellt.", currentFolderName + name);
			} else {
				Logger.error("Anlegen eines neuen Ordners war leider nicht moeglich");
				FunctionsHelper.setMessage(Message.CREATE_FILE_FAILED);
			}
		}
	}

	@Override
	public void copySelectedFileEntries() {
		File[] files = sidePanel.getTableAndFavoritesSplitPane().getTable().getSelectedFiles();
		FileOperator operator = new CopyOperator(files, sidePanel);
		operator.execute();
	}

	@Override
	public void moveSelectedFileEntries() {
		File[] files = sidePanel.getTableAndFavoritesSplitPane().getTable().getSelectedFiles();
		FileOperator operator = new MoveOperator(files, sidePanel);
		operator.execute();
	}

	@Override
	public void deleteSelectedFileEntries() {
		File[] files = sidePanel.getTableAndFavoritesSplitPane().getTable().getSelectedFiles();
		FileOperator operator = new DeleteOperator(files, sidePanel);
		operator.execute();
	}

}
