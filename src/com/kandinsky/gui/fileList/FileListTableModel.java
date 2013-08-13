package com.kandinsky.gui.fileList;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.kandinsky.objects.FileEntry;

public class FileListTableModel extends AbstractTableModel {

	/** Ueberschriften */
	private String[] columnNames = {"Name", "Größe", "Datum", "Typ", "Endung", "Rechte"};
	
	/** Daten */
	private List<FileEntry> data;
	
	public FileListTableModel(){
		this.data = new LinkedList<>();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		FileEntry s = data.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return s.getName();
		case 1:
			return s.getSize();
		case 2:
			return s.getDate();
		case 3:
			return s.getType();
		case 4:
			return s.getEnding();
		case 5:
			return s.getRights();
		default:
			throw new RuntimeException("Spaltenindex existiert nicht!");
		}
	}

	/**
	 * Setzt die Werte.
	 * @param entries
	 */
	public void setValues(List<FileEntry> entries) {
		this.data = entries;
	}

}
