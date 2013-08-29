package com.kandinsky.gui.fileList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.kandinsky.objects.FileEntry;
import com.kandinsky.objects.FileType;
import com.kandinsky.objects.SideFunctionsHelper;

/**
 * PUNKT 6 - Fileliste
 * Zeigt die Dateien des aktuellen Verzeichnisses als Tabelle an.
 * ACHTUNG: muss für beide Seiten der SplitPane funktionieren!
 * @author schmidtb
 */
public class FileListTable extends JTable {

	private static final long serialVersionUID = -8348644017646168541L;
	private FileListTableModel model;
	private SideFunctionsHelper sideFunctionsHelper;
	private String currentFolderName="";

	public FileListTable(SideFunctionsHelper sideFunctionsHelper) throws Exception {
		model = new FileListTableModel();
		this.sideFunctionsHelper = sideFunctionsHelper;
		this.setAutoCreateRowSorter(true);
		this.setModel(model);
		setColumnWidths();
		this.addMouseListener(new ClickListener());
		 
	}
	
	private void setColumnWidths(){
		final int factor = 10000;
		TableColumnModel columnModel = this.getColumnModel();
	    for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
	        TableColumn column = columnModel.getColumn(columnIndex);
	        column.setPreferredWidth((int) (model.getColumnWidthAt(columnIndex) * factor));
	    }
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
			currentFolderName = folderName;
			List<FileEntry> newEntries = FileEntry.getFileEntryList(folder);
			model.setValues(newEntries);
			getSelectionModel().clearSelection();
			sideFunctionsHelper.setFileCountInFolder(newEntries.size());
			sideFunctionsHelper.setSelectedFiles(getSelectedFiles());
			repaint();
		}
	}
	
	public void refresh() throws Exception{
		changeFolder(currentFolderName);
		repaint();
	}
	
	/**
	 * Fängt Auswahl und DoubleClicks in der Tabelle ab.
	 * @author Benne
	 */
	private class ClickListener extends MouseAdapter {
		
		private static final int DOUBLE_CLICK = 2;
		private static final int NOTHING_SELECTED = -1;

		public void mouseClicked(MouseEvent event) {
			FileListTable target = (FileListTable) event.getSource();
			if (getSelectedRow() != NOTHING_SELECTED) {
				if (event.getClickCount() == DOUBLE_CLICK) {
					int row = target.getSelectedRow();
					// ummappen, falls sortiert
					row = convertRowIndexToModel(row);
					FileEntry valueAtRow = model.getValueAtRow(row);
					if (valueAtRow.getType() == FileType.DIRECTORY) {
						sideFunctionsHelper.switchFolder(valueAtRow.getAbsoluteFileName());
						repaint();
					}
				} else {
					File[] files = target.getSelectedFiles();
					sideFunctionsHelper.setSelectedFiles(files);
				}
			}
		}
	}
	
	private File[] getSelectedFiles() {
		File[] files = new File[getSelectedRowCount()];
		int i = 0;
		for(int selectedRow : getSelectedRows()){
			// ummappen, falls sortiert
			selectedRow = convertRowIndexToModel(selectedRow);
			FileEntry selectedEntry = model.getValueAtRow(selectedRow);
			files[i]=selectedEntry.getFile();
			i++;
		}
		return files;
	}
	
}
