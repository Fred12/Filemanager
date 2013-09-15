package com.kandinsky.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.kandinsky.gui.splitPane.MainSplitPane;
import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.FunctionsHelper;
import com.kandinsky.objects.SideFunctionsHelper;

/**
 * PUNKT 3 - ButtonBar. Zeigt eine Liste mit Buttons. Hier ist zu ueberlegen, ob
 * die Buttons fuer beide Seiten angezeigt werden sollen, oder nur fuer eine
 * Seite.
 * 
 * @author Marc L., Mamoudou B.
 */

public class ButtonBar extends JPanel implements ActionListener {

	private static final long serialVersionUID = -7230543120102084314L;
	private static final Insets margins = new Insets(0, 0, 0, 0);
	
	private Stack<String> stack1;
	private Stack<String> stack2;
	private JToolBar buttonBar;
	private JButton neuesFenster;
	private JToggleButton zurueck;
	private JToggleButton weiter;
	private JButton hoch;
	private JButton home;
	private JButton aktualisieren;
	private JButton vertauschen;
	private JButton favoritenHinzufuegen;
	private JButton favoritenAnzeigen;
	private JButton benutzer;
	private JButton ftpVerwalten;
	private JButton ftpAnzeigen;
	private JButton shellOeffnen;
	private JButton hilfe;
	private JButton einstellungen;

	/**
	 * der Funktionshelfer, ueber den verschiedene Funktionen aufgerufen werden
	 * koennen
	 */
	private SideFunctionsHelper sideFunctionsHelper;
	// private FunctionsHelper functionsHelper; Das ding hat nur statische Funktionen!

	public ButtonBar(SideFunctionsHelper sideFunctionsHelper) {
		this.sideFunctionsHelper = sideFunctionsHelper;
		
		
		// buttonBar = new JToolBar("Button Bar",0);
		buttonBar = new JToolBar();
		this.add(buttonBar, BorderLayout.NORTH);
		// buttonBar.setPreferredSize(new Dimension(200,100));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		buttonBar.setBackground(Color.WHITE);
		buttonBar.setOrientation(SwingConstants.HORIZONTAL);
		buttonBar.setFloatable(false);
		buttonBar.setBorderPainted(false);
		buttonBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonBar.setBorderPainted(true);
		buttonBar.setRollover(true);
		// buttonBar.addSeparator(new Dimension(100,100));
		//buttonBar.setMargin(margins);
		//buttonBar.setAlignmentX(0);

		// Icon Quellen fuer die Buttons, am besten png

		Icon a = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/folder-open.png"));
		Icon b = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/zurueck-icon.png"));
		Icon c = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/weiter-icon.png"));
		Icon d = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/hoch-icon.png"));
		Icon e = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/home-6-icon.png"));
		Icon f = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/refresh-icon.png"));
		Icon g = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/shuffle-icon.png"));
		Icon h = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/favorite-anlegen-icon.png"));
		Icon i = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/favorite-icon.png"));
		Icon j = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/user-icon.png"));
		Icon k = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/network-icon.png"));
		Icon l = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/ftp-connection-icon.png"));
		Icon m = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/shell-icon.png"));
		Icon n = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/help-icon.png"));
		Icon o = new ImageIcon(getClass().getResource(
				"/com/kandinsky/resources/options-icon.png"));

		// Buttons erstellen mit Icons
		neuesFenster = new JButton(a);
		zurueck = new JToggleButton(b);
		weiter = new JToggleButton(c);
		hoch = new JButton(d);
		home = new JButton(e);
		aktualisieren = new JButton(f);
		vertauschen = new JButton(g);
		favoritenHinzufuegen = new JButton(h);
		favoritenAnzeigen = new JButton(i);
		benutzer = new JButton(j);
		ftpVerwalten = new JButton(k);
		ftpAnzeigen = new JButton(l);
		shellOeffnen = new JButton(m);
		hilfe = new JButton(n);
		einstellungen = new JButton(o);

		// Abstaende / Raender der Buttons anpassen
		neuesFenster.setMargin(margins);
		zurueck.setMargin(margins);
		weiter.setMargin(margins);
		hoch.setMargin(margins);
		home.setMargin(margins);
		aktualisieren.setMargin(margins);
		vertauschen.setMargin(margins);
		favoritenHinzufuegen.setMargin(margins);
		favoritenAnzeigen.setMargin(margins);
		benutzer.setMargin(margins);
		ftpVerwalten.setMargin(margins);
		ftpAnzeigen.setMargin(margins);
		shellOeffnen.setMargin(margins);
		hilfe.setMargin(margins);
		einstellungen.setMargin(margins);

		// ToolTips fuer die Buttons
		neuesFenster.setBackground(Color.WHITE);
		// neuesFenster.setFont(new Font("Arial", Font.PLAIN, 12));
		neuesFenster.setToolTipText("Ordner oeffnen");
		zurueck.setBackground(Color.WHITE);
		zurueck.setToolTipText("Zum Ordner zurueck navigieren");
		//zurueck.setEnabled(false);
		weiter.setBackground(Color.WHITE);
		weiter.setToolTipText("Zum Ordner vorwaerts navigieren");
		//weiter.setEnabled(false);
		hoch.setBackground(Color.WHITE);
		hoch.setToolTipText("Zum uebergeordneten Ordner wechseln");

		home.setBackground(Color.WHITE);
		// home.setFont(new Font("Arial", Font.PLAIN, 12));
		home.setToolTipText("Zum Home Ordner wechseln");
		aktualisieren.setBackground(Color.WHITE);
		aktualisieren.setToolTipText("Fenster aktualisieren");
		vertauschen.setBackground(Color.WHITE);
		vertauschen.setToolTipText("Split-Fenster vertauschen");
		favoritenHinzufuegen.setBackground(Color.WHITE);
		favoritenHinzufuegen.setToolTipText("aktuellen Ordner zu Favoriten hinzufuegen");
		favoritenAnzeigen.setBackground(Color.WHITE);
		favoritenAnzeigen.setToolTipText("Favoriten anzeigen");
		benutzer.setBackground(Color.WHITE);
		benutzer.setToolTipText("Benutzer");
		ftpVerwalten.setBackground(Color.WHITE);
		ftpVerwalten.setToolTipText("Neuen FTP Server anlegen");
		ftpAnzeigen.setBackground(Color.WHITE);
		ftpAnzeigen.setToolTipText("FTP Verbindungen anzeigen");
		shellOeffnen.setBackground(Color.WHITE);
		shellOeffnen.setToolTipText("Shell im aktuellen Ordner oeffnen");
		hilfe.setBackground(Color.WHITE);
		hilfe.setToolTipText("Hilfe und Infos");
		einstellungen.setBackground(Color.WHITE);
		einstellungen.setToolTipText("Einstellungen oeffnen");

		// Buttons der buttonBar Anlegen
		buttonBar.add(neuesFenster);
		buttonBar.add(zurueck);
		buttonBar.add(weiter);
		buttonBar.add(hoch);
		// add(btnRename, "3, 6, fill, center");
		buttonBar.add(home);
		buttonBar.add(aktualisieren);
		buttonBar.add(vertauschen);
		buttonBar.add(favoritenHinzufuegen);
		//buttonBar.add(favoritenAnzeigen);
		// buttonBar.add(benutzer);
		buttonBar.add(ftpVerwalten);
		// buttonBar.add(ftpAnzeigen);
		buttonBar.add(shellOeffnen);
		buttonBar.add(hilfe);
		buttonBar.add(einstellungen);

		// ActionListener fuer die einzelnen Buttons
		neuesFenster.addActionListener(this);
		zurueck.addActionListener(this);
		weiter.addActionListener(this);		
		hoch.addActionListener(this);
		home.addActionListener(this);
		aktualisieren.addActionListener(this);
		vertauschen.addActionListener(this);
		favoritenHinzufuegen.addActionListener(this);
		favoritenAnzeigen.addActionListener(this);
		benutzer.addActionListener(this);
		ftpVerwalten.addActionListener(this);
		ftpAnzeigen.addActionListener(this);
		shellOeffnen.addActionListener(this);
		hilfe.addActionListener(this);
		einstellungen.addActionListener(this);
		
		
		zurueck.setEnabled(false);
		weiter.setEnabled(false);
		
		
		
		
		//************************************************************************************************	
		//Stacks anlegen für Pfadlisten der Buttons Zurueck/Weiter
		
		stack1 = new Stack<String>();   //Stack für das Adden von Foldern
		stack2 = new Stack<String>();	//Stack für den Weiter - Button 
		

	}   //************************************************************************************************ Ende der Klasse ButtonBar()

	

	// public class ButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ae) {

		Object quelle = ae.getSource();	
		
		if (quelle == neuesFenster) {
			sideFunctionsHelper.openFolder();
		}

		if (quelle == zurueck) {			
			if (stack1.size() == 1) {				
			}
			else if (!stack1.isEmpty()) {
				String now = stack1.pop();
				stack2.push(now);
				String before = stack1.peek();
				System.out.println(before);
				sideFunctionsHelper.switchFolder(before, false);	
				weiter.setEnabled(true);
				if (stack1.size() == 1) {
					zurueck.setEnabled(false);
				}
			}
		}		

		if (quelle == weiter) {		
			if (!stack2.isEmpty()) {
				String now = stack2.pop();
				stack1.push(now);
				System.out.println(now);				
				sideFunctionsHelper.switchFolder(now, false);
				zurueck.setEnabled(true);
				if (stack2.isEmpty()) {
					weiter.setEnabled(false);
				}
			}			
		}		

		if (quelle == hoch) {		
			sideFunctionsHelper.getRootFolder();
		}		

		if (quelle == home) {
			sideFunctionsHelper.switchFolder(System.getProperty("user.home"), true);			
		}

		if (quelle == aktualisieren) {
			sideFunctionsHelper.refresh();
		}
		
		if (quelle == vertauschen)  {
			sideFunctionsHelper.exChangePanelsFolder();
		}

		if (quelle == favoritenHinzufuegen) {
			
		}		

		if (quelle == ftpVerwalten) {
			FunctionsHelper.showOptions(1);
		}
		
		if (quelle == shellOeffnen) {
			sideFunctionsHelper.openCMDShell();
		}		

		if (quelle == hilfe) {
			FunctionsHelper.showAbout();
		}

		if (quelle == einstellungen) {
			FunctionsHelper.showOptions(0);
		}						
	}
	
	
	//*****************************************************************************************************************
		//Funktionen für die ButtonNavigation Vor/Zurueck - Ordner in die Liste einfügen	

		public void addFolder(String folderName) {	
		stack1.push(folderName);
		if (stack1.size() > 1) {
			zurueck.setEnabled(true);
		}		
		stack2.clear();	
		weiter.setEnabled(false);		
		}	
		
		public Stack<String> getStack1() {
			return stack1;
		}
		
		public Stack<String>getStack2() {
			return stack2;
		}
		
		public void setStack1(Stack<String> otherStack) {
			this.stack1 = otherStack;
		}
		
		public void setStack2(Stack<String> otherStack) {
			this.stack2 = otherStack;
		}
		
		public void checkStacks() {
			if (stack1.size() > 1) {
				zurueck.setEnabled(true);				
			}
			else {
				zurueck.setEnabled(false);
			}
			
			if (stack2.size() >= 1) {
				weiter.setEnabled(true);
			}
			else {
				weiter.setEnabled(false);
			}
		}
		

	}

