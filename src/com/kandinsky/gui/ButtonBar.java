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
 * oder nur fuer eine Seite.
 * @author Marc L.
 */

public class ButtonBar extends JToolBar implements ActionListener {		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7230543120102084314L;
	
	JPanel buttonBar;
	
	JButton neuesFenster;	
	JButton zurueck;
	JButton weiter;
	JButton hoch;
	JButton home;
	JButton aktualisieren;
	JButton vertauschen;
	JButton favoritenAnlegen;
	JButton favoritenAnzeigen;
	JButton benutzer;
	JButton ftpAnlegen;
	JButton ftpAnzeigen;
	JButton shellOeffnen;
	JButton hilfe;
	JButton einstellungen;		
	
	
	
	public ButtonBar() {
		
		//buttonBar = new JToolBar("Werkzeugleiste",0);
		buttonBar = new JPanel();		
		this.add(buttonBar);	
		//buttonBar.setPreferredSize(new Dimension(400,300)); 
		
		
		//Icon Quellen fuer die Buttons, am besten png
		
		Icon a = new ImageIcon(getClass().getResource("/com/kandinsky/resources/nf.png"));		 
		Icon b = new ImageIcon(getClass().getResource("/com/kandinsky/resources/zurueck-icon.png"));
		Icon c = new ImageIcon(getClass().getResource("/com/kandinsky/resources/weiter-icon.png"));
		Icon d = new ImageIcon(getClass().getResource("/com/kandinsky/resources/hoch-icon.png"));
		Icon e = new ImageIcon(getClass().getResource("/com/kandinsky/resources/home-6-icon.png"));
		Icon f = new ImageIcon(getClass().getResource("/com/kandinsky/resources/refresh-icon.png"));
		Icon g = new ImageIcon(getClass().getResource("/com/kandinsky/resources/shuffle-icon.png"));
		Icon h = new ImageIcon(getClass().getResource("/com/kandinsky/resources/favorite-anlegen-icon.png"));
		Icon i = new ImageIcon(getClass().getResource("/com/kandinsky/resources/favorite-icon.png"));
		Icon j = new ImageIcon(getClass().getResource("/com/kandinsky/resources/user-icon.png"));
		Icon k = new ImageIcon(getClass().getResource("/com/kandinsky/resources/network-icon.png"));
		Icon l = new ImageIcon(getClass().getResource(""));
		Icon m = new ImageIcon(getClass().getResource(""));
		Icon n = new ImageIcon(getClass().getResource("/com/kandinsky/resources/help-icon.png"));
		Icon o = new ImageIcon(getClass().getResource("/com/kandinsky/resources/options-icon.png"));	
		
		//Buttons erstellen mit Icons
		neuesFenster =			new JButton(a);
		zurueck = 				new JButton(b);
		weiter = 				new JButton(c);
		hoch =					new JButton(d);
		home =					new JButton(e);
		aktualisieren = 		new JButton(f);
		vertauschen = 			new JButton(g);
		favoritenAnlegen = 		new JButton(h);
		favoritenAnzeigen = 	new JButton(i);
		benutzer = 			    new JButton(j);
		ftpAnlegen = 			new JButton(k);
		ftpAnzeigen =			new JButton("FTP anzeigen",l);
		shellOeffnen = 			new JButton("Shell Öffnen",m);
		hilfe =  				new JButton(n);
		einstellungen = 		new JButton(o);
		
		
		//ToolTips fuer die Buttons
		neuesFenster.setToolTipText("Öffnet eine neues Hauptfenster");
		zurueck.setToolTipText("Zum Ordner zurück navigieren");
		weiter.setToolTipText("Zum Ordner vorwärts navigieren");
		hoch.setToolTipText("Zum Übergeordneten Ordner wechseln");
		home.setToolTipText("Zum Home Ordner wechseln");
		aktualisieren.setToolTipText("Fenster aktualisieren");
		vertauschen.setToolTipText("Split-Fenster vertauschen");
		favoritenAnlegen.setToolTipText("Neue Favoriten anlegen");
		favoritenAnzeigen.setToolTipText("Favoriten anzeigen");
		benutzer.setToolTipText("Benutzer");
		ftpAnlegen.setToolTipText("Neuen FTP Server anlegen");
		ftpAnzeigen.setToolTipText("FTP Verbindungen anzeigen");
		shellOeffnen.setToolTipText("Shell im aktuellen Ordner öffnen");
		hilfe.setToolTipText("Hilfe und Infos");
		einstellungen.setToolTipText("Einstellungen öffnen");
		
		
		//Buttons der buttonBar Anlegen	
		buttonBar.add(neuesFenster);
		buttonBar.add(zurueck);
		buttonBar.add(weiter);
		buttonBar.add(hoch);		
		buttonBar.add(home);
		buttonBar.add(aktualisieren);
		buttonBar.add(vertauschen);
		buttonBar.add(favoritenAnlegen);
		buttonBar.add(favoritenAnzeigen);
		buttonBar.add(benutzer);
		buttonBar.add(ftpAnlegen);
		buttonBar.add(ftpAnzeigen);
		buttonBar.add(shellOeffnen);
		buttonBar.add(hilfe);
		buttonBar.add(einstellungen);
		
		
		//ActionListener fuer die einzelnen Buttons		
		neuesFenster.addActionListener(this);
		zurueck.addActionListener(this);
		weiter.addActionListener(this);
		hoch.addActionListener(this);
		home.addActionListener(this);
		aktualisieren.addActionListener(this);
		vertauschen.addActionListener(this);
		favoritenAnlegen.addActionListener(this);
		favoritenAnzeigen.addActionListener(this);
		benutzer.addActionListener(this);
		ftpAnlegen.addActionListener(this);
		ftpAnzeigen.addActionListener(this);
		shellOeffnen.addActionListener(this);
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
			
			if (quelle == zurueck) {
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
			
			if (quelle == benutzer) {
			}
			
			if (quelle == ftpAnlegen) {
			}
			
			if (quelle == ftpAnzeigen) {
			}
			
			if (quelle == shellOeffnen) {
			}
			
			if (quelle == hilfe) {
			}
			
			if (quelle == einstellungen) {
			}
			
			
			
		}
}
	
	


