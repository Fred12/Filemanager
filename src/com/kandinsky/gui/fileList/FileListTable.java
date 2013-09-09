package com.kandinsky.gui.fileList;

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
	private String currentFolderName = "";
	private FileListPopUpMenu popup;
	private FileEntry entryOfCurrentPopup;

	public FileListTable(final SideFunctionsHelper sideFunctionsHelper) {
		model = new FileListTableModel();
		popup = new FileListPopUpMenu(this, sideFunctionsHelper);
		this.sideFunctionsHelper = sideFunctionsHelper;
		this.setAutoCreateRowSorter(true);
		this.setModel(model);
		this.setFillsViewportHeight(true);
		setColumnWidths();
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

	public void changeFolder(String folderName) throws Exception {
		File folder = new File(folderName);
		if (!folder.isDirectory())
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

	public void refresh() throws Exception {
		if (currentFolderName == null)
			currentFolderName = "";
		changeFolder(currentFolderName);
		repaint();
	}

	public String getCurrentFolderName() {
		if (currentFolderName.endsWith("/") || currentFolderName.endsWith("\\"))
			return currentFolderName;
		else
			return currentFolderName + "/";
	}

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
	 * F�ngt Auswahl und DoubleClicks in der Tabelle ab.
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
			sideFunctionsHelper.switchFolder(valueAtRow.getAbsoluteFileName());
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
			}
		}
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
