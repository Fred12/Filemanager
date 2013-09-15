package com.kandinsky.objects;

import java.util.NoSuchElementException;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.splitPane.SidePanel.Side;

public class AllActionsListener {

	public void performHotKeyAction(String hotkey) {
		Logger.debug("Hotkey wurde erkannt: " + hotkey);

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
				case Hotkeys.LEFT_BACK: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).back();
					break;
				}
				case Hotkeys.RIGHT_BACK: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).back();
					break;
				}
				case Hotkeys.LEFT_FORWARD: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).forward();
					break;
				}
				case Hotkeys.RIGHT_FORWARD: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).forward();
					break;
				}
				case Hotkeys.LEFT_PARENT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).getRootFolder();
					break;
				}
				case Hotkeys.RIGHT_PARENT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).getRootFolder();
					break;
				}
				case Hotkeys.OPEN_FOLDER_LEFT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).openFolderInWindows();
					break;
				}
				case Hotkeys.OPEN_FOLDER_RIGHT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).openFolderInWindows();
					break;
				}
				case Hotkeys.SHELL_LEFT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).openCMDShell();
					break;
				}
				case Hotkeys.SHELL_RIGHT: {
					FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).openCMDShell();
					break;
				}
				case Hotkeys.REFRESH: {
					FunctionsHelper.refresh();
					break;
				}
				case Hotkeys.ADD_LEFT_FOLDER_TO_FAVORITES: {
					String folderName = FunctionsHelper.getSideFunctionsHelperOnSide(Side.LEFT).getCurrentFolderName();
					FileEntry selectedFolder = new FileEntry(folderName);
					FunctionsHelper.addFavorite(selectedFolder);
					break;
				}
				case Hotkeys.ADD_RIGHT_FOLDER_TO_FAVORITES: {
					String folderName = FunctionsHelper.getSideFunctionsHelperOnSide(Side.RIGHT).getCurrentFolderName();
					FileEntry selectedFolder = new FileEntry(folderName);
					FunctionsHelper.addFavorite(selectedFolder);
					break;
				}
				default: {
					Logger.error(hotkey + " hat keine Funktion!");
					break;
				}
			}
		} catch (NoSuchElementException e) {
			Logger.debug(hotkey + " wurde bisher nicht zugewiesen");
		}
	}

}
