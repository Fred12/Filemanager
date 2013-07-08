package com.kandinsky.objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Liste alle im System existierenden Shortcuts.
 * @author schmidtb
 */
public class Shortcuts implements Serializable {

	private static final long serialVersionUID = 4430773610590354542L;
	
	private Map<String, Shortcut> shortcutList;
	
	public Shortcuts(){
		shortcutList = new HashMap<String, Shortcut>();
	}
	
	public Shortcuts(Map<String, Shortcut> shortcuts){
		shortcutList = shortcuts;
	}
	
	public Shortcut getShortcutForKey(String key){
		Shortcut toReturn = shortcutList.get(key);
		if(toReturn!=null){
			return toReturn;
		} else {
			throw new RuntimeException("Key nicht gefunden!");
		}
	}

}
