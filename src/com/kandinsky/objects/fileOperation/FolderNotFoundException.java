package com.kandinsky.objects.fileOperation;

public class FolderNotFoundException extends Exception {

	private static final long serialVersionUID = -7535972608298326132L;
	
	public FolderNotFoundException(String folderName){
		super(folderName);
	}

}
