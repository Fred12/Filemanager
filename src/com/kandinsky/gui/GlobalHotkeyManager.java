package com.kandinsky.gui;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

import com.kandinsky.objects.AllActionsListener;

public class GlobalHotkeyManager extends EventQueue {
	private static final boolean DEBUG = false;
	private final InputMap keyStrokes = new InputMap();
	private final ActionMap actions = new ActionMap();


	public GlobalHotkeyManager() {
		AllActionsListener allActions = new AllActionsListener();
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(this);
		
		// TODO: hotkeys belegen
		KeyStroke refresh = KeyStroke.getKeyStroke((char)KeyEvent.VK_F5,0);
		System.out.println("Event hinzugefuegt: "+refresh.toString());
		keyStrokes.put(refresh, "REFRESH");
		actions.put("REFRESH", allActions);
	} // One is enough - singleton

	public InputMap getInputMap() {
		return keyStrokes;
	}

	public ActionMap getActionMap() {
		return actions;
	}

	protected void dispatchEvent(AWTEvent event) {
		if (event instanceof KeyEvent) {
			// KeyStroke.getKeyStrokeForEvent converts an ordinary KeyEvent
			// to a keystroke, as stored in the InputMap. Keep in mind that
			// Numpad keystrokes are different to ordinary keys, i.e. if you
			// are listening to
			KeyStroke ks = KeyStroke.getKeyStrokeForEvent((KeyEvent) event);
			if (DEBUG)
				System.out.println("KeyStroke=" + ks);
			String actionKey = (String) keyStrokes.get(ks);
			if (actionKey != null) {
				if (DEBUG)
					System.out.println("ActionKey=" + actionKey);
				Action action = actions.get(actionKey);
				if (action != null && action.isEnabled()) {
					//Im not sure about the parameters
					action.actionPerformed(new ActionEvent(event.getSource(),
							event.getID(), ks.toString(), ((KeyEvent) event)
									.getModifiers()));

					return; //consume event
				}
			}
		}
		super.dispatchEvent(event);
	}
}

