package com.kandinsky.gui;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

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

		// TODO: hotkeys belegen
		KeyStroke f5 = KeyStroke.getKeyStroke((char) KeyEvent.VK_F5, 0);
		System.out.println("Event hinzugefuegt: " + f5.toString());
		keyStrokes.add(f5);
	}

	public List<KeyStroke> getInputMap() {
		return keyStrokes;
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
