package com.kandinsky.objects;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class AllActions extends AbstractAction {

	private static final long serialVersionUID = 7862912883759702399L;

	public AllActions() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO weise e.getActionCommand entsprechend zu

		Shortcuts sc = Shortcuts.getInstance();
		Shortcut selectedShortCut = sc.getShortcutByKey(e.getActionCommand());

		if (selectedShortCut == null) {
		} else {
			switch (selectedShortCut.getName()) {
			case "REFRESH": {
				FunctionsHelper.refreshFavorites();
			}
			}
		}
	}

}
