package com.kandinsky.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PUNKT 8 - Ordnerdetails.
 * Kleine Detailbar zum Anzeigen verschiedener Ordnerdetails.
 * ACHTUNG: Muss auf beiden Seiten der SplitPane angezeigt werden.
 * @author schmidtb
 */
public class FolderDetailPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7815299418185514905L;
	
	private JLabel infoLabel;
	
	public FolderDetailPanel(){
		// TESTFARBE, kann auch wieder raus, wenn nicht benötigt
		this.setBackground(Color.RED);
		infoLabel = new JLabel("INFO");
		
		// LAYOUT
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.fill=GridBagConstraints.VERTICAL;
		this.add(infoLabel, gbc);
	}
}
