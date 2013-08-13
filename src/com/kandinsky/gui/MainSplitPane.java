package com.kandinsky.gui;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * Gro�e Hauptsplitpane, in dem die Hautpdetails liegen. Dabei werden der Splitpane ein linkes und
 * ein rechtes Panel mit�bergeben, welche dann 50:50 geteilt angezeigt werden.
 * @author schmidtb
 */
public class MainSplitPane extends JSplitPane {

	/** Unterteilung der beiden Panels. Aktuell 50:50 */
	private static final double DIVIDER_RATIO = 0.5;

	/**
	 * Die Splitpane wird in einen linken und rechten Teil unterteil. Hierf�r wird ein linkes und ein rechtes Panel �bergeben.
	 * @param leftPanel
	 * @param rightPanel
	 */
	public MainSplitPane(JPanel leftPanel, JPanel rightPanel){
		super(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		this.setResizeWeight(DIVIDER_RATIO); 
	}

}
