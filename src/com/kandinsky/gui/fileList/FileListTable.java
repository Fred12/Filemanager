package com.kandinsky.gui.fileList;

import java.io.File;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.kandinsky.objects.FileEntry;

/**
 * PUNKT 6 - Fileliste
 * Zeigt die Dateien des aktuellen Verzeichnisses als Tabelle an.
 * ACHTUNG: muss für beide Seiten der SplitPane funktionieren!
 * @author schmidtb
 */
public class FileListTable extends JTable {

	private FileListTableModel model;

	public FileListTable() {
		model = new FileListTableModel();
		this.setModel(model);
	}

	/**
	 * Umgibt die Tabelle mit einer ScrollPane. Das ist wichtig, damit die Tabelle eine Scrollbar bekommt UND auch die Kopfdaten anzeigt.
	 * @return umschließende ScrollPane
	 */
	public JScrollPane surroundedWithPane() {
		return new JScrollPane(this);
	}
	
	public void changeFolder(String folderName) throws Exception{
		File folder = new File(folderName);
		if(!folder.isDirectory())
			throw new Exception("Kein Verzeichnis angegeben");
		else {
			List<FileEntry> newEntries = FileEntry.getFileEntryList(folder);
			model.setValues(newEntries);
		}
			
	}
}
