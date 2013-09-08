package com.kandinsky.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.kandinsky.gui.KandinskyMenuBar;
import com.kandinsky.gui.MainPanel;

/**
 * Testklasse für die komplette Ansicht
 * @author schmidtb
 */
public class MainFrameTest extends JFrame {
	
	private static final long serialVersionUID = 7344100431867938808L;

	public MainFrameTest() throws Exception{
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1600,1024));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(MainPanel.createInstance());
		this.setJMenuBar(KandinskyMenuBar.createInstance());
	}
	
	public static void main(String[] args) throws Exception {
		MainFrameTest frame = new MainFrameTest();
		frame.setVisible(true);
	}

}
