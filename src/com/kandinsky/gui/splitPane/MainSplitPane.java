package com.kandinsky.gui.splitPane;

import javax.swing.JSplitPane;

import com.kandinsky.gui.fileList.FileListTable;

/**
 * PUNKT 4 - SplitPane.
 * Gro�e Hauptsplitpane, in dem die Hautpdetails liegen. Dabei werden der Splitpane ein linkes und
 * ein rechtes Panel mit�bergeben, welche dann 50:50 geteilt angezeigt werden.
 * @author schmidtb
 */
public class MainSplitPane extends JSplitPane {

	private static final long serialVersionUID = -2515375754619078110L;
	
	private SidePanel leftPanel, rightPanel;
	
	/** Unterteilung der beiden Panels. Aktuell 50:50 */
	private static final double DIVIDER_RATIO = 0.5;

	/**
	 * Die Splitpane wird in einen linken und rechten Teil unterteil. Hierf�r wird ein linkes und ein rechtes Panel �bergeben.
	 * @param leftPanel
	 * @param rightPanel
	 */
	public MainSplitPane(SidePanel leftPanel, SidePanel rightPanel){
		super(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		this.setOneTouchExpandable(true);
		this.setResizeWeight(DIVIDER_RATIO); 
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
	}
	
	/**
	 * Die Splitpane wird in einen linken und rechten Teil unterteil. Hierf�r wird ein linkes und ein rechtes Panel komplett neu angelegt.
	 * @param leftPanel
	 * @param rightPanel
	 * @throws Exception 
	 */
	public MainSplitPane() throws Exception{
		this(new LeftSidePanel(), new RightSidePanel());
	}
	
	public FileListTable getLeftTable() throws Exception{
		return leftPanel.getTableAndFavoritesSplitPane().getTable();
	}
	
	public FileListTable getRightTable() throws Exception{
		return rightPanel.getTableAndFavoritesSplitPane().getTable();
	}


}
