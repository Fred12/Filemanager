package com.kandinsky.objects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Repraesentiert einen anzuzeigenden Eintrag und kapselt dabei die Funktionalität die später
 * auf der Oberfläche angezeigt werden soll.
 * @author schmidtb
 */
public class FileEntry {
	
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	
	/** gekapselte Datei */
	private File file;
	
	/**
	 * @param files Dateiliste
	 * @return eine passende FileEntry-Liste, wobei dort in jedem Eintrag eine Datei gekapselt ist.
	 */
	public static List<FileEntry> getFileEntryList(File[] files){
		List<FileEntry> entries = new LinkedList<FileEntry>();
		for(File currentFile : files){
			entries.add(new FileEntry(currentFile));
		}
		return entries;
	}
	
	/**
	 * @param files Dateiliste
	 * @return eine passende FileEntry-Liste, wobei dort in jedem Eintrag eine Datei gekapselt ist.
	 */
	public static List<FileEntry> getFileEntryList(File folder){
		if(folder.isDirectory())
			return getFileEntryList(folder.listFiles());
		else
			throw new RuntimeException("Geht nicht, kein Verzeichnis");
	}
	
	public FileEntry(File file){
		this.file = file;
	}
	
	public String getName(){
		return file.getName();
	}
	
	public String getEnding(){
		if(getName().contains("."))
			return getName().substring(getName().lastIndexOf(".")+1, getName().length());
		else
			return "";
	}
	
	public String getSize(){
		return ByteToStringHelper.convertToString(file.length());
	}
	
	public String getDate(){
		return FORMATTER.format(file.lastModified());
	}
	
	public String getType(){
		if(file.isDirectory())
			return "Ordner";
		else if(file.isFile())
			return "Datei";
		else
			return "Unbekannt";
	}
	
	public String getRights(){
		String rwx = "";
		rwx+=file.canRead()?"r":"-";
		rwx+=file.canWrite()?"w":"-";
		rwx+=file.canExecute()?"x":"-";
		return rwx;
	}
}
