package com.kandinsky.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kandinsky.gui.splitPane.MainSplitPane;

public class SplitPaneFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5155741862662507039L;

	public SplitPaneFrame(){
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(400,400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Links"));

		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("Rechts"));
		
		this.add(new MainSplitPane(panel1, panel2));
		this.pack();
	}
	
	public static void main(String[] args) {
		SplitPaneFrame frame = new SplitPaneFrame();
		frame.setVisible(true);
	}

}
