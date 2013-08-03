package com.kandinsky.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import com.kandinsky.gui.KandinskyMenuBar;

public class Main {

	private JFrame frame;
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public Main() {
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setMinimumSize(new Dimension(400,400));
		
		KandinskyMenuBar menuBar = new KandinskyMenuBar();
		frame.add(menuBar, BorderLayout.NORTH);
		
	}

}
