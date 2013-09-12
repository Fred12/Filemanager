package com.kandinsky.objects;

public class AllActionsListener {

	public AllActionsListener() {
	}

	public void performHotKeyAction(String hotkey) {
		System.out.println("Action wurde erkannt: " + hotkey);

		try {
			Hotkeys sc = Hotkeys.getInstance();
			Hotkey selectedShortCut = sc.getHotkeyByKey(hotkey);

			switch (selectedShortCut.getName()) {
				case "REFRESH": {
					FunctionsHelper.refreshFavorites();
				}
			}
		} catch (Exception e) {

		}
	}

}
