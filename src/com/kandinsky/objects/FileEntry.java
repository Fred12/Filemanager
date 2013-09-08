package com.kandinsky.objects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Repraesentiert einen anzuzeigenden Eintrag und kapselt dabei die Funktionalität die später
 * auf der Oberfläche angezeigt werden soll.
 * @author schmidtb
 */
public class FileEntry implements Comparable<FileEntry> {

	protected static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	/** Wunderhübsch */
	
	private static final ImageIcon FOLDER_ICON = new ImageIcon(new ImageIcon(FileEntry.class.getResource("/com/kandinsky/resources/folder-7-icon.png")).getImage().getScaledInstance(16, 16,
			java.awt.Image.SCALE_SMOOTH));
	private static final ImageIcon TEXT_FILE_ICON = new ImageIcon(new ImageIcon(FileEntry.class.getResource("/com/kandinsky/resources/text-file-4-icon.png")).getImage().getScaledInstance(16, 16,
			java.awt.Image.SCALE_SMOOTH));

	/** gekapselte Datei */
	private File file;
	protected FileType fileType;

	/**
	 * @param files Dateiliste
	 * @return eine passende FileEntry-Liste, wobei dort in jedem Eintrag eine Datei gekapselt ist.
	 */
	public static List<FileEntry> getFileEntryList(File[] files) {
		List<FileEntry> entries = new LinkedList<FileEntry>();
		for (File currentFile : files) {
			entries.add(new FileEntry(currentFile));
		}
		return entries;
	}

	/**
	 * @param files Dateiliste
	 * @return eine passende FileEntry-Liste, wobei dort in jedem Eintrag eine Datei gekapselt ist.
	 */
	public static List<FileEntry> getFileEntryList(File folder) {
		if (folder.isDirectory())
			return getFileEntryList(folder.listFiles());
		else
			throw new RuntimeException("Geht nicht, kein Verzeichnis");
	}

	public FileEntry(File file) {
		this.file = file;
		if(file.isDirectory())
			fileType = FileType.DIRECTORY;
		else if(file.isFile())
			fileType = FileType.FILE;
		else
			fileType = FileType.UNKNOWN;
	}
	
	public FileEntry(String fileName){
		this(new File(fileName));
	}
	
	public FileEntry(){
		
	}
	
	public File getFile(){
		return file;
	}

	/**
	 * @return den Namen, bzw. den Namen der absoluten Datei
	 */
	public String getName() {
		if(file.getName().isEmpty())
			return file.getAbsoluteFile().getName();
		return file.getName();
	}

	public String getEnding() {
		if (getName().contains("."))
			return getName().substring(getName().lastIndexOf(".") + 1, getName().length());
		else
			return "";
	}

	public String getSize() {
		return ByteToStringHelper.convertToString(getFullSizeAsLong());
	}

	public String getDate() {
		return FORMATTER.format(getFullDateAsLong());
	}

	public Long getFullDateAsLong() {
		return file.lastModified();
	}

	public Long getFullSizeAsLong() {
		return file.length();
	}

	public FileType getType(){
		return fileType;
	}

	/**
	 * Rechte in der Formatierung "rwx" - dreistellig<p>
	 * für vorhandene Rechte wird der Buchstabe an diese Stelle geschrieben, ansonsten ein Minus. Folgende Rechte sind möglich:<br>
	 * - r = Lesen (read)<br>
	 * - w = Schreiben (write)<br>
	 * - x = Ausführen (execute)
	 * @return Rechte in "rwx"-Struktur
	 */
	public String getRights() {
		String rwx = "";
		rwx += file.canRead() ? "r" : "-";
		rwx += file.canWrite() ? "w" : "-";
		rwx += file.canExecute() ? "x" : "-";
		return rwx;
	}

	/**
	 * @return ein passendes Icon
	 */
	public ImageIcon getIcon() {
		if (getType()==FileType.DIRECTORY)
			return FOLDER_ICON;
		else
			return TEXT_FILE_ICON;
	}
	
	public String getAbsoluteFileName(){
		return file.getAbsolutePath();
	}
	
	public boolean exists(){
		return file.exists();
	}

	@Override
	public int compareTo(FileEntry o) {
		return getName().compareTo(o.getName());
	}
	
	@Override
	public String toString() {
		return getAbsoluteFileName();
	}
}
