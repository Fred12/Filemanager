package com.kandinsky.gui;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.KeyStroke;

import com.kandinsky.objects.AllActionsListener;

public class GlobalHotkeyManager extends EventQueue {
	private final List<KeyStroke> keyStrokes;
	private AllActionsListener allActionsListener;

	private static GlobalHotkeyManager instance;

	public static GlobalHotkeyManager getInstance() {
		if (instance == null)
			instance = new GlobalHotkeyManager();

		return instance;
	}

	private GlobalHotkeyManager() {
		allActionsListener = new AllActionsListener();
		keyStrokes = new LinkedList<>();
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(this);

		// F-Tasten
		for(int i = KeyEvent.VK_F1; i <= KeyEvent.VK_F12; i++){
			keyStrokes.add(KeyStroke.getKeyStroke((char) i, 0));
		}
		
		// CTRL+F-Tasten
		for(int i = KeyEvent.VK_F1; i <= KeyEvent.VK_F12; i++){
			keyStrokes.add(KeyStroke.getKeyStroke(i, KeyEvent.CTRL_MASK));
		}

		// CTRL+ALT+F-Tasten
		for(int i = KeyEvent.VK_F1; i <= KeyEvent.VK_F12; i++){
			keyStrokes.add(KeyStroke.getKeyStroke(i, KeyEvent.CTRL_MASK+KeyEvent.ALT_MASK));
		}
	}

	public List<KeyStroke> getKeyStrokes() {
		return keyStrokes;
	}
	
	public Vector<String> getKeyStrokesAsString() {
		Vector<String> stringKeyStrokes = new Vector<>();
		stringKeyStrokes.add("");
		for(KeyStroke nextStroke : getKeyStrokes()){
			stringKeyStrokes.add(nextStroke.toString());
		}
		return stringKeyStrokes;
	}

	protected void dispatchEvent(AWTEvent event) {
		if (event instanceof KeyEvent) {
			KeyStroke ks = KeyStroke.getKeyStrokeForEvent((KeyEvent) event);
			if (keyStrokes.contains(ks)) {
				allActionsListener.performHotKeyAction(ks.toString());
				return;
			}
		}
		super.dispatchEvent(event);
	}
}
