package com.kandinsky.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.kandinsky.gui.splitPane.MainSplitPane;

/**
 * Haupt-Panel der Anwendung, welches alle benoetigten Element der Oberflaeche besitzt (ausgenommen der MenuBar).#
 * <p>Singleto (halbwegs), ueber zentrale Funktion wird einziges Objekt erzeugt und ueber andere Funktion wird diesses Objekt wieder zurueckgegeben
 * @author Benne
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 3435757831771873393L;

	private static MainPanel instance;

	public static MainPanel createInstance() throws Exception {
		instance = new MainPanel();
		return instance;
	}

	public static MainPanel getInstance() {
		return instance;
	}

	/** Grosse Split-Pane die die Vergleichs-Seiten enthaelt */
	MainSplitPane splitPane;

	/**
	 * Erstellt ein neues Panel, das ALLE benoetigten Elemente (bis auf die MenuBar) enthaelt.
	 * @throws Exception
	 */
	private MainPanel() throws Exception {
		this.setLayout(new GridBagLayout());

		splitPane = new MainSplitPane();
		this.add(splitPane, getSplitPaneConstraints());
	}

	private GridBagConstraints getSplitPaneConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}

	public MainSplitPane getMainSplitPane() {
		return splitPane;
	}

}
