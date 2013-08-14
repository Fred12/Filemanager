package com.kandinsky.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.kandinsky.gui.fileList.FileListTable;

/**
 * Testklasse für die FileListe
 * @author schmidtb
 */
public class FileListFrame extends JFrame {
	
	public FileListFrame(){

		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(400,400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FileListTable table = new FileListTable();
		this.add(table.surroundedWithPane(), BorderLayout.NORTH);
		try {
			table.changeFolder("C:\\Windows");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FileListFrame frame = new FileListFrame();
		frame.setVisible(true);
	}

}
