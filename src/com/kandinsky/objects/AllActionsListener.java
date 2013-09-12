package com.kandinsky.objects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class AllActionsListener extends AbstractAction {

	private static final long serialVersionUID = 7862912883759702399L;

	public AllActionsListener() {
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Action wurde erkannt: " + event.getActionCommand());
		// TODO weise e.getActionCommand entsprechend zu

		try {
			Hotkeys sc = Hotkeys.getInstance();
			Hotkey selectedShortCut = sc.getHotkeyByKey(event
					.getActionCommand());

			switch (selectedShortCut.getName()) {
			case "REFRESH": {
				FunctionsHelper.refreshFavorites();
			}
			}
		} catch (Exception e) {

		}
	}

}
