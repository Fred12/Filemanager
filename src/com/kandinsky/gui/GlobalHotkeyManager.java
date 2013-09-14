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

		addFromKeyToKey(KeyEvent.VK_F1, KeyEvent.VK_F12);
		addFromKeyToKey(KeyEvent.VK_A, KeyEvent.VK_Z);
	}

	private void addFromKeyToKey(int start, int end) {
		// normal
		for (int i = start; i <= end; i++) {
			keyStrokes.add(KeyStroke.getKeyStroke((char) i, 0));
		}
		
		// Ctrl
		for (int i = start; i <= end; i++) {
			keyStrokes.add(KeyStroke.getKeyStroke(i, KeyEvent.CTRL_MASK));
		}
		
		// Alt
		for (int i = start; i <= end; i++) {
			keyStrokes.add(KeyStroke.getKeyStroke(i, KeyEvent.ALT_MASK));
		}
		
		// Ctrl+Alt
		for (int i = start; i <= end; i++) {
			keyStrokes.add(KeyStroke.getKeyStroke(i, KeyEvent.CTRL_MASK+KeyEvent.ALT_MASK));
		}
	}

	public Vector<String> getKeyStrokesAsString() {
		Vector<String> stringKeyStrokes = new Vector<>();
		stringKeyStrokes.add("");
		for(KeyStroke nextStroke : keyStrokes){
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
