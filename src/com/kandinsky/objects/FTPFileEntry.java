package com.kandinsky.objects;

import org.apache.commons.net.ftp.FTPFile;

/**
 * Ueberschreibt benoetigte Funktionen eines normalen FileEntries, sodass diese auch auf FTP-Dateien funktioniert.
 * @author Benne
 */
public class FTPFileEntry extends FileEntry {

	private FTPFile ftpFile;
	
	public FTPFileEntry(FTPFile ftpFile) {
		this.ftpFile = ftpFile;
		if(ftpFile.isDirectory())
			fileType = FileType.DIRECTORY;
		else if(ftpFile.isFile())
			fileType = FileType.FILE;
		else
			fileType = FileType.UNKNOWN;
	}

	/**
	 * @return den Namen, bzw. den Namen der absoluten Datei
	 */
	@Override
	public String getName() {
		return ftpFile.getName();
	}
	
	@Override
	public Long getFullSizeAsLong() {
		return ftpFile.getSize();
	}
	
	@Override
	public String getRights() {
		String rwx = "";
		rwx += ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION) ? "r" : "-";
		rwx += ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION) ? "w" : "-";
		rwx += ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION) ? "x" : "-";
		return rwx;
	}

	@Override
	public String getAbsoluteFileName(){
		return ftpFile.getName();
	}

	@Override
	public boolean exists(){
		return ftpFile.isDirectory() || ftpFile.isFile() || ftpFile.isSymbolicLink() || ftpFile.isUnknown();
	}

	@Override
	public Long getFullDateAsLong() {
		return ftpFile.getTimestamp().getTimeInMillis();
	}
}
