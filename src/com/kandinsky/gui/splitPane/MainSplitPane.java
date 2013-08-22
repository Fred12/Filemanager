package com.kandinsky.gui.splitPane;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * PUNKT 4 - SplitPane.
 * Gro�e Hauptsplitpane, in dem die Hautpdetails liegen. Dabei werden der Splitpane ein linkes und
 * ein rechtes Panel mit�bergeben, welche dann 50:50 geteilt angezeigt werden.
 * @author schmidtb
 */
public class MainSplitPane extends JSplitPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2515375754619078110L;
	
	/** Unterteilung der beiden Panels. Aktuell 50:50 */
	private static final double DIVIDER_RATIO = 0.5;

	/**
	 * Die Splitpane wird in einen linken und rechten Teil unterteil. Hierf�r wird ein linkes und ein rechtes Panel �bergeben.
	 * @param leftPanel
	 * @param rightPanel
	 */
	public MainSplitPane(JPanel leftPanel, JPanel rightPanel){
		super(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		this.setOneTouchExpandable(true);
		this.setResizeWeight(DIVIDER_RATIO); 
	}
	
	/**
	 * Die Splitpane wird in einen linken und rechten Teil unterteil. Hierf�r wird ein linkes und ein rechtes Panel komplett neu angelegt.
	 * @param leftPanel
	 * @param rightPanel
	 * @throws Exception 
	 */
	public MainSplitPane() throws Exception{
		super(JSplitPane.HORIZONTAL_SPLIT, new LeftSidePanel(), new RightSidePanel());
		this.setOneTouchExpandable(true);
		this.setResizeWeight(DIVIDER_RATIO); 
	}

}
