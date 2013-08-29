package com.kandinsky.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.kandinsky.gui.splitPane.MainSplitPane;

/**
 * Haupt-Panel der Anwendung, welches alle benoetigten Element der Oberflaeche besitzt (ausgenommen der MenuBar).
 * @author Benne
 */
public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3435757831771873393L;
	
	/** enthaelt die benoetigten Buttons */
	private ButtonBar buttonBar;
	/** Grosse Split-Pane die die Vergleichs-Seiten enthaelt */
	MainSplitPane splitPane;

	/**
	 * Erstellt ein neues Panel, das ALLE benoetigten Elemente (bis auf die MenuBar) enthaelt.
	 * @throws Exception
	 */
	public MainPanel() throws Exception {
		this.setLayout(new GridBagLayout());
		
		buttonBar = new ButtonBar();
		splitPane = new MainSplitPane();
		this.add(buttonBar, getButtonBarConstraints());
		this.add(splitPane, getSplitPaneConstraints());
	}

	private GridBagConstraints getButtonBarConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		return gbc;
	}

	private GridBagConstraints getSplitPaneConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.9;
		return gbc;
	}

}
