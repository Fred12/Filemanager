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
 * ACHTUNG: muss f�r beide Seiten der SplitPane funktionieren!
 * @author schmidtb
 */
public class FileListTable extends JTable {

	private static final long serialVersionUID = -8348644017646168541L;
	
	private FileListTableModel model;

	private SideFunctionsHelper sideFunctionsHelper;

	public FileListTable(SideFunctionsHelper sideFunctionsHelper) throws Exception {
		model = new FileListTableModel();
		this.sideFunctionsHelper = sideFunctionsHelper;
		this.setAutoCreateRowSorter(true);
		this.setModel(model);
		setColumnWidths();
		this.addMouseListener(new DoubleClickListener());
		 
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
	 * @return umschlie�ende ScrollPane
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
	
	/**
	 * F�ngt DoubleClicks in der Tabelle ab.
	 * @author Benne
	 */
	private class DoubleClickListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				FileListTable target = (FileListTable) e.getSource();
				FileEntry valueAtRow = model.getValueAtRow(target.getSelectedRow());
				if (valueAtRow.getType() == FileType.DIRECTORY) {
					sideFunctionsHelper.switchFolder(valueAtRow.getAbsoluteFileName());
					repaint();
				}
			}
		}
	}
}
