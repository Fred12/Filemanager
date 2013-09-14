package com.kandinsky.objects;

import java.util.NoSuchElementException;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.splitPane.SidePanel.Side;

public class AllActionsListener {

	public void performHotKeyAction(String hotkey) {
		Logger.debug("Hotkey wurde erkannt: "+hotkey);

		try {
			Hotkeys hotkeys = Hotkeys.getInstance();
			Hotkey selectedShortCut = hotkeys.getHotkeyByKey(hotkey);

			switch (selectedShortCut.getInternalKey()) {
				case Hotkeys.NEW_FILE_LEFT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).createNewFile();
					break;
				}
				case Hotkeys.NEW_FILE_RIGHT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).createNewFile();
					break;
				}
				case Hotkeys.NEW_FOLDER_LEFT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).createNewFolder();
					break;
				}
				case Hotkeys.NEW_FOLDER_RIGHT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).createNewFolder();
					break;
				}
				case Hotkeys.COPY_LEFT_TO_RIGHT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).copySelectedFilesToOtherSide();
					break;
				}
				case Hotkeys.COPY_RIGHT_TO_LEFT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).copySelectedFilesToOtherSide();
					break;
				}
				case Hotkeys.RIGHT_BACK: {
//					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).
					// TODO: fehlt noch die Button-Funktionalitaet im SideFunctionsHelper
				}
				case Hotkeys.LEFT_BACK: {
//					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).
					// TODO: fehlt noch die Button-Funktionalitaet im SideFunctionsHelper
				}
				case Hotkeys.LEFT_PARENT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).getRootFolder();
					// TODO: fehlt noch die Button-Funktionalitaet im SideFunctionsHelper
				}
				case Hotkeys.RIGHT_PARENT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).getRootFolder();
					// TODO: fehlt noch die Button-Funktionalitaet im SideFunctionsHelper
				}
				case "REFRESH": {
					FunctionsHelper.refresh();
					break;
				}
				default: {
					Logger.error(hotkey+" hat keine Funktion!");
				}
			}
		} catch (NoSuchElementException e) {
			Logger.debug(hotkey+" wurde bisher nicht zugewiesen");
		}
	}

}
