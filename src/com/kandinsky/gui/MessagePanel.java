package com.kandinsky.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.kandinsky.objects.Message;

/**
 * PUNKT 8 - Ordnerdetails.
 * Kleine Detailbar zum Anzeigen verschiedener Ordnerdetails.
 * ACHTUNG: Muss auf beiden Seiten der SplitPane angezeigt werden.
 * @author schmidtb
 */
public class MessagePanel extends JPanel {

	private static final long serialVersionUID = -7815299418185514905L;
	
	private final JLabel infoLabel;
	
	public MessagePanel(){
		// TESTFARBE, kann auch wieder raus, wenn nicht benötigt
		infoLabel = new JLabel("-");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setOpaque(true);
		
		// LAYOUT
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx=1.0;
		gbc.weighty=1.0;
		gbc.fill=GridBagConstraints.BOTH;
		this.add(infoLabel, gbc);
	}
	
	public void setMessage(Message infoText){
		infoLabel.setText(infoText.getMessage());
		infoLabel.setBackground(infoText.getColor());
	}
}
