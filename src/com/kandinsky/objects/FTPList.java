package com.kandinsky.objects;

import java.util.ArrayList;

/**
 * Hilfsklasse zur Verwaltung der FTP Server Einträge und zur Verfügungstellung
 * über das gesamte Programm hinweg. Da die Arrayfunktionen nicht erneut implementiert
 * werden sollen, wird auf die gegebene ArrayList zurückgegriffen. Diese ist 
 * nicht statisch implementiert, deshalb wird hier ein Singleton darübergebaut,
 * damit die Informationen konsistent bleiben.
 * 
 * @author Stefan
 *
 */
public class FTPList extends ArrayList<FTPEntry> {

	private static FTPList self = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5565796337543758338L;

	private FTPList() {
		
	}
	
	public static FTPList getInstance(){
		
		if(FTPList.self == null){
			FTPList.self = new FTPList();
		}
		return FTPList.self;
		
	}
	
	public static String[] getNames(){
		
		String[] names = new String[FTPList.self.size()];
		
		int counter = 0;
		for (FTPEntry currentEntry: FTPList.self){
			names[counter] = currentEntry.getName();
			++counter;
		}
		
		return names;
		
	}
	
	public static void swapEntries(int first, int second) {
		
		if (first < 0 || first > FTPList.self.size() - 1 || second < 1 || second > FTPList.self.size()) {
			throw new IllegalArgumentException("List Swap Indexes out of Range (Second also cannont be 0 and first not last!");
		}
		FTPEntry firstEntry = FTPList.self.get(first);
		FTPEntry secondEntry = FTPList.self.get(second);
		FTPList.self.set(first, secondEntry);
		FTPList.self.set(second, firstEntry);
	}
	
}
