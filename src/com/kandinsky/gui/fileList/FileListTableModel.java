package com.kandinsky.gui.fileList;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import com.kandinsky.objects.FileEntry;

public class FileListTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3612169454389635317L;

	/** Ueberschriften */
	private static final String[] columnNames = {"", "Name", "Größe", "Datum", "Typ", "Endung", "Rechte" };
	/** die Spaltenbreiten in Prozent */
	private static final double[] columnWidths = {0.05, 0.45, 0.15, 0.15, 0.1, 0.1, 0.1 };

	/** Daten */
	private List<FileEntry> data;

	public FileListTableModel() {
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
	
	public double getColumnWidthAt(int i){
		return columnWidths[i];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		FileEntry entry = data.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return entry.getIcon();
			case 1:
				return entry.getName();
			case 2:
				return entry.getSize();
			case 3:
				return entry.getDate();
			case 4:
				return entry.getType();
			case 5:
				return entry.getEnding();
			case 6:
				return entry.getRights();
			default:
				throw new RuntimeException("Spaltenindex existiert nicht!");
		}
	}
	
	public FileEntry getValueAtRow(int rowIndex){
		return data.get(rowIndex);
	}

	/**
	 * Setzt die Werte.
	 * @param entries
	 */
	public void setValues(List<FileEntry> entries) {
		this.data = entries;
	}
	
	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
			case 0:
				return ImageIcon.class;
			default:
				return String.class;
		}
	}

}
