package com.kandinsky.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.kandinsky.gui.splitPane.LeftSidePanel;
import com.kandinsky.gui.splitPane.MainSplitPane;
import com.kandinsky.gui.splitPane.RightSidePanel;

/**
 * Testklasse für die FileListe
 * @author schmidtb
 */
public class FileListFrame extends JFrame {
	
	public FileListFrame() throws Exception{
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1600,1024));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LeftSidePanel leftPanel = new LeftSidePanel();
		RightSidePanel rightPanel = new RightSidePanel();
		this.add(new MainSplitPane(leftPanel, rightPanel));
	}
	
	public static void main(String[] args) throws Exception {
		FileListFrame frame = new FileListFrame();
		frame.setVisible(true);
	}

}
