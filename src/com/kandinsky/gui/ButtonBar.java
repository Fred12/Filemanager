package com.kandinsky.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * PUNKT 3 - ButtonBar.
 * Zeigt eine Liste mit Buttons. Hier ist zu überlegen, ob die Buttons für beide Seiten angezeigt werden sollen,
 * oder nur für eine Seite.
 * @author Marc L.
 */

public class ButtonBar extends JToolBar implements ActionListener {		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7230543120102084314L;
	
	JPanel buttonBar;
	
	JButton neuesFenster;	
	JButton zurück;
	JButton weiter;
	JButton hoch;
	JButton home;
	JButton aktualisieren;
	JButton vertauschen;
	JButton favoritenAnlegen;
	JButton favoritenAnzeigen;
	JButton gruppieren;
	JButton ftpAnlegen;
	JButton ftpAnzeigen;
	JButton shellÖffnen;
	JButton hilfe;
	JButton einstellungen;		
	
	
	
	public ButtonBar() {
		
		//buttonBar = new JToolBar("Werkzeugleiste",0);
		buttonBar = new JPanel();		
		this.add(buttonBar);	
		//buttonBar.setPreferredSize(new Dimension(400,300)); 
		
		
		//Icon Quellen für die Buttons, am besten png
		
		Icon a = new ImageIcon(getClass().getResource("nf.png"));		 
		Icon b = new ImageIcon(getClass().getResource("zurück-icon.png"));
		Icon c = new ImageIcon(getClass().getResource("weiter-icon.png"));
		Icon d = new ImageIcon(getClass().getResource("hoch-icon.png"));
		Icon e = new ImageIcon(getClass().getResource("home-6-icon.png"));
		Icon f = new ImageIcon(getClass().getResource("refresh-icon.png"));
		Icon g = new ImageIcon(getClass().getResource(""));
		Icon h = new ImageIcon(getClass().getResource(""));
		Icon i = new ImageIcon(getClass().getResource(""));
		Icon j = new ImageIcon(getClass().getResource(""));
		Icon k = new ImageIcon(getClass().getResource(""));
		Icon l = new ImageIcon(getClass().getResource(""));
		Icon m = new ImageIcon(getClass().getResource(""));
		Icon n = new ImageIcon(getClass().getResource(""));
		Icon o = new ImageIcon(getClass().getResource(""));	
		
		//Buttons erstellen mit Icons
		neuesFenster =			new JButton(a);
		zurück = 				new JButton(b);
		weiter = 				new JButton(c);
		hoch =					new JButton(d);
		home =					new JButton(e);
		aktualisieren = 		new JButton(f);
		vertauschen = 			new JButton("Vertauschen",g);
		favoritenAnlegen = 		new JButton("Favoriten Anlegen",h);
		favoritenAnzeigen = 	new JButton("Favoriten anzeigen",i);
		gruppieren = 			new JButton("Gruppieren",j);
		ftpAnlegen = 			new JButton("FTP anlegen",k);
		ftpAnzeigen =			new JButton("FTP anzeigen",l);
		shellÖffnen = 			new JButton("Shell öffnen",m);
		hilfe =  				new JButton("Hilfe",n);
		einstellungen = 		new JButton("Einstellungen",o);
		
		
		//ToolTips für die Buttons
		neuesFenster.setToolTipText("Öffnet eine neues Hauptfenster");
		zurück.setToolTipText("Zum Ordner zurück navigieren");
		weiter.setToolTipText("Zum Ordner vorwärts navigieren");
		hoch.setToolTipText("Zum übergeordneten Ordner wechseln");
		home.setToolTipText("Zum Home Ordner wechseln");
		aktualisieren.setToolTipText("Fenster aktualisieren");
		vertauschen.setToolTipText("Split-Fenster vertauschen");
		favoritenAnlegen.setToolTipText("Neue Favoriten anlegen");
		favoritenAnzeigen.setToolTipText("Favoriten anzeigen");
		gruppieren.setToolTipText("Gruppieren");
		ftpAnlegen.setToolTipText("Neuen FTP Server anlegen");
		ftpAnzeigen.setToolTipText("FTP Verbindungen anzeigen");
		shellÖffnen.setToolTipText("Shell im aktuellen Ordner öffnen");
		hilfe.setToolTipText("Hilfe und Infos");
		einstellungen.setToolTipText("Einstellungen öffnen");
		
		
		//Buttons der buttonBar Anlegen	
		buttonBar.add(neuesFenster);
		buttonBar.add(zurück);
		buttonBar.add(weiter);
		buttonBar.add(hoch);		
		buttonBar.add(home);
		buttonBar.add(aktualisieren);
		buttonBar.add(vertauschen);
		buttonBar.add(favoritenAnlegen);
		buttonBar.add(favoritenAnzeigen);
		buttonBar.add(gruppieren);
		buttonBar.add(ftpAnlegen);
		buttonBar.add(ftpAnzeigen);
		buttonBar.add(shellÖffnen);
		buttonBar.add(hilfe);
		buttonBar.add(einstellungen);
		
		
		//ActionListener für die einzelnen Buttons		
		neuesFenster.addActionListener(this);
		zurück.addActionListener(this);
		weiter.addActionListener(this);
		hoch.addActionListener(this);
		home.addActionListener(this);
		aktualisieren.addActionListener(this);
		vertauschen.addActionListener(this);
		favoritenAnlegen.addActionListener(this);
		favoritenAnzeigen.addActionListener(this);
		gruppieren.addActionListener(this);
		ftpAnlegen.addActionListener(this);
		ftpAnzeigen.addActionListener(this);
		shellÖffnen.addActionListener(this);
		hilfe.addActionListener(this);
		einstellungen.addActionListener(this);
		
		
	}

	//public class ButtonListener implements ActionListener {		
	
		@Override		
		public void actionPerformed(ActionEvent ae) {
			
			Object quelle = ae.getSource();			
		
			if (quelle == neuesFenster) {
				buttonBar.add(new JButton("GEKLICKT!"));
				
			}
		
			if (quelle == neuesFenster) {
			}
			
			if (quelle == zurück) {
			}
			
			if (quelle == weiter) {
			}
			
			if (quelle == hoch) {
			}
			
			if (quelle == home) {
			}
			
			if (quelle == aktualisieren) {
			}
			
			if (quelle == favoritenAnlegen) {
			}
			
			if (quelle == favoritenAnzeigen) {
			}
			
			if (quelle == gruppieren) {
			}
			
			if (quelle == ftpAnlegen) {
			}
			
			if (quelle == ftpAnzeigen) {
			}
			
			if (quelle == shellÖffnen) {
			}
			
			if (quelle == hilfe) {
			}
			
			if (quelle == einstellungen) {
			}
			
			
			
		}
}
	
	


