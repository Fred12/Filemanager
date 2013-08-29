package com.kandinsky.gui.dialogs;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutDialog extends JDialog implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7128309504850733578L;
	

	
	public AboutDialog() {
		
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addMouseListener(this);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 0, 243, 460);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(AboutDialog.class.getResource("/com/kandinsky/resources/java logo.png")));
		
		JLabel lblNewLabel_1 = new JLabel("Kandinsky File Manager v0.0.0.1");
		lblNewLabel_1.setBounds(398, 187, 220, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Copyright Kandinsky");
		lblNewLabel_2.setBounds(423, 255, 134, 14);
		getContentPane().add(lblNewLabel_2);

		this.setMinimumSize(new Dimension(700,500));
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		dispose();
		
	}
}
