package com.kandinsky.gui.fileList;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.pmw.tinylog.Logger;

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
	private final SideFunctionsHelper sideFunctionsHelper;
	private FileListPopUpMenu popup;
	private FileEntry entryOfCurrentPopup;

	public FileListTable(final SideFunctionsHelper sideFunctionsHelper) {
		model = new FileListTableModel();
		popup = new FileListPopUpMenu(this, sideFunctionsHelper);
		this.sideFunctionsHelper = sideFunctionsHelper;
		this.setAutoCreateRowSorter(true);
		this.setModel(model);
		this.setFillsViewportHeight(true);
		this.setColumnWidths();
		this.setOpaque(true);
		this.addMouseListener(new ClickListener());
		this.addMouseListener(popup.getMouseListener());
		this.addKeyListener(new TableKeyListener());
		this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		this.getActionMap().put("Enter", new EnterAction());
	}

	private void setColumnWidths() {
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

	/**
	 * Setzt neue FileEntries in die Maske
	 * @param newEntries
	 */
	public void setFileEntries(List<FileEntry> newEntries) {
		model.setValues(newEntries);
		getSelectionModel().clearSelection();
		sideFunctionsHelper.setSelectedFiles(getSelectedFiles());
		repaint();
	}

	/**
	 * @return die selektierten Dateien
	 */
	public File[] getSelectedFiles() {
		File[] files = new File[getSelectedRowCount()];
		int i = 0;
		for (int selectedRow : getSelectedRows()) {
			// ummappen, falls sortiert
			selectedRow = convertRowIndexToModel(selectedRow);
			FileEntry selectedEntry = model.getValueAtRow(selectedRow);
			files[i] = selectedEntry.getFile();
			i++;
		}
		return files;
	}

	/**
	 * Zeigt ein Popup-Menu mit Kontext-Inhalten
	 * @param point der Punkt an dem das Menue angezeigt werden soll
	 */
	public void showPopup(Point point) {
		popup = new FileListPopUpMenu(this, sideFunctionsHelper);
		popup.show(this, (int) point.getX(), (int) point.getY());
		try {
			entryOfCurrentPopup = model.getValueAtRow(rowAtPoint(point));
		} catch (Exception e) {
			entryOfCurrentPopup = null;
		}
	}

	public FileEntry getEntryOfCurrentPopup() {
		return entryOfCurrentPopup;
	}

	/**
	 * Faengt Auswahl und DoubleClicks in der Tabelle ab
	 * @author Benne
	 */
	private class ClickListener extends MouseAdapter {

		private static final int DOUBLE_CLICK = 2;
		private static final int NOTHING_SELECTED = -1;

		@Override
		public void mouseReleased(MouseEvent event) {
			if (getSelectedRow() != NOTHING_SELECTED) {
				if (event.getClickCount() == DOUBLE_CLICK) {
					jumpIntoSelectedFolder();
				} else {
					File[] files = getSelectedFiles();
					sideFunctionsHelper.setSelectedFiles(files);
				}
			}
		}
	}

	private void jumpIntoSelectedFolder() {
		int row = getSelectedRow();
		// ummappen, falls sortiert
		row = convertRowIndexToModel(row);
		FileEntry valueAtRow = model.getValueAtRow(row);
		if (valueAtRow.getType() == FileType.DIRECTORY) {
			sideFunctionsHelper.switchFolder(valueAtRow.getAbsoluteFileName(), true);
			repaint();
		}
	}

	/**
	 * Hoert auf Tastatureingabe auf der Tabelle
	 * @author Benne
	 */
	private class TableKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent event) {
			Logger.info("Key pressed: " + (int) event.getKeyChar());
			switch (event.getKeyChar()) {
				case KeyEvent.VK_DELETE: {
					sideFunctionsHelper.deleteSelectedFiles();
					break;
				}
				//				case "F2": {
				//					if (getSelectedRowCount() == 1) {
				//						int row = getSelectedRow();
				//						// ummappen, falls sortiert
				//						row = convertRowIndexToModel(row);
				//						FileEntry valueAtRow = model.getValueAtRow(row);
				//						sideFunctionsHelper.rename(valueAtRow);
				//					}
				//					break;
				//				}
			}
		}
	}

	public void setFtpConnected(boolean connected) {
		Color color;
		if (connected) {
			color = new Color(0xCEF6EC);
		} else {
			color = Color.white;
		}
		this.setBackground(color);
		this.revalidate();
	}
	
	/**
	 * Bringt eine Action fuer den Enter-Button mit sich, um die JTable-Enter-Aktion zu ueberschreiben
	 * @author Benne
	 */
	private class EnterAction extends AbstractAction {

		private static final long serialVersionUID = 7447863551768598886L;

		@Override
		public void actionPerformed(ActionEvent ae) {
			jumpIntoSelectedFolder();
		}
	}
}
