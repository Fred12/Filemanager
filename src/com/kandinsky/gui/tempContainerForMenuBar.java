package com.kandinsky.gui;

import java.awt.FlowLayout;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class tempContainerForMenuBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5289730046619423646L;

	/**
	 * Create the panel.
	 */
	public tempContainerForMenuBar() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		KandinskyMenuBar menuBar = new KandinskyMenuBar();
		add(menuBar);
		
	}

}
