package com.kandinsky.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.KandinskyMenuBar;
import com.kandinsky.gui.MainPanel;
import com.kandinsky.objects.Favorites;

public class Main {

	private static JFrame frame;
		
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public Main() throws Exception {
		
		Logger.info("Anwendung wird gestartet!");
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setMinimumSize(new Dimension(400,400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Favorites.loadAllFavorites();
		
		KandinskyMenuBar menuBar = KandinskyMenuBar.getInstance();
		frame.add(menuBar, BorderLayout.NORTH);
		
		MainPanel main = MainPanel.createInstance();
		frame.add(main, BorderLayout.CENTER);
		
		
		WindowAdapter windowAdapter = new WindowAdapter() {
	        // WINDOW_CLOSING event handler
	        @Override
	        public void windowClosing(WindowEvent e) {
	            super.windowClosing(e);
	            // You can still stop closing if you want to
	            int res = JOptionPane.showConfirmDialog(frame, "Sicher? Noch laufende Operationen\nkönnen Datenverlust verursachen!", "Close?", JOptionPane.YES_NO_OPTION);
	            if ( res == 0 ) {
	                // dispose method issues the WINDOW_CLOSED event
	                frame.dispose();
	            }
	        }

	        // WINDOW_CLOSED event handler
	        @Override
	        public void windowClosed(WindowEvent e) {
	            super.windowClosed(e);
	            Logger.info("Anwendung wird beendet...");
	            // Close application if you want to with System.exit(0)
	            // but don't forget to dispose of all resources 
	            // like child frames, threads, ...
	            System.exit(0);
	        }
	    };

	    // when you press "X" the WINDOW_CLOSING event is called but that is it
	    // nothing else happens
	    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    // don't forget this
	    frame.addWindowListener(windowAdapter);
	    
	    frame.setVisible(true);	
	    
	    frame.pack();
	   
	    frame.setLocation(30, 20);
				
		
	}
	
	public static JFrame getJFrame(){
		
		return frame;
	}

}
