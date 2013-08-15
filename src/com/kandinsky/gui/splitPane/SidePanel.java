package com.kandinsky.gui.splitPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.kandinsky.gui.fileList.FileListTable;

/**
 * Handelt den Panel-Aufbau innerhalb der Splitpane. Sollte so dynamisch sein, dass von den abgeleiteten Klassen nur noch versch. Funktionen aufgerufen werden müssen,
 * der Rest passiert von alleine.
 * @author Benne
 *
 */
public abstract class SidePanel extends JPanel{

	protected SidePanel() throws Exception {
		this.setLayout(new GridBagLayout());
		FileListTable table = getTable();
		GridBagConstraints gbc = getTableConstraint();
		this.add(table.surroundedWithPane(), gbc);
		table.changeFolder("C:\\Windows");
	}

	private GridBagConstraints getTableConstraint() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
		return gbc;
	}
	
	/**
	 * @return Tabelle, die angezeigt werden soll.
	 * @throws Exception 
	 */
	protected abstract FileListTable getTable() throws Exception;
}
