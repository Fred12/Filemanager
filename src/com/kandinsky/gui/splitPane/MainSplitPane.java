package com.kandinsky.gui.splitPane;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JSplitPane;

import com.kandinsky.gui.fileList.FileListTable;
import com.kandinsky.gui.splitPane.SidePanel.Side;
import com.kandinsky.objects.SideFunctionsHelper;

/**
 * PUNKT 4 - SplitPane.
 * Groöe Hauptsplitpane, in dem die Hautpdetails liegen. Dabei werden der Splitpane ein linkes und
 * ein rechtes Panel mitübergeben, welche dann 50:50 geteilt angezeigt werden.
 * @author schmidtb
 */
public class MainSplitPane extends JSplitPane {

	private static final long serialVersionUID = -2515375754619078110L;

	private SidePanel leftPanel, rightPanel;

	/** Unterteilung der beiden Panels. Aktuell 50:50 */
	private static final double DIVIDER_RATIO = 0.5;

	/**
	 * Die Splitpane wird in einen linken und rechten Teil unterteil. Hierfür wird ein linkes und ein rechtes Panel übergeben.
	 * @param leftPanel
	 * @param rightPanel
	 */
	public MainSplitPane(SidePanel leftPanel, SidePanel rightPanel) {
		super(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		this.setOneTouchExpandable(true);
		this.setResizeWeight(DIVIDER_RATIO);
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
	}

	/**
	 * Die Splitpane wird in einen linken und rechten Teil unterteil. Hierfür wird ein linkes und ein rechtes Panel komplett neu angelegt.
	 * @param leftPanel
	 * @param rightPanel
	 * @throws Exception 
	 */
	public MainSplitPane() throws Exception {
		this(new LeftSidePanel(), new RightSidePanel());
	}

	public FileListTable getLeftTable() throws Exception {
		return leftPanel.getTableAndFavoritesSplitPane().getTable();
	}

	public FileListTable getRightTable() throws Exception {
		return rightPanel.getTableAndFavoritesSplitPane().getTable();
	}

	public List<SidePanel> getSidePanels() {
		List<SidePanel> sidePanels = new LinkedList<>();
		sidePanels.add(leftPanel);
		sidePanels.add(rightPanel);
		return sidePanels;
	}
	
	public SideFunctionsHelper getSideFunctionsHelperForSide(Side side){
		if(leftPanel.getSide()==side)
			return leftPanel.getSideFunctionsHelper();
		else
			return rightPanel.getSideFunctionsHelper();
	}

}
