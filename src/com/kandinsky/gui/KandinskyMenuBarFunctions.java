package com.kandinsky.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class KandinskyMenuBarFunctions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String source = e.getActionCommand();
		
		if(source.equals("Aktualisieren")) {
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
		}
		else if(source.equals("Suchen")) {
			JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
		}

	}

}
