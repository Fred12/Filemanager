package com.kandinsky.objects.fileOperation;

import java.io.File;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.FunctionsHelper;
import com.kandinsky.objects.Message;

public abstract class FileOperator {

	protected File[] files;
	protected SidePanel sidePanel;
	protected SidePanel otherSidePanel;
	
	public FileOperator(File[] files, SidePanel sidePanel){
		this.files = files;
		this.sidePanel = sidePanel;
		otherSidePanel = FunctionsHelper.getOtherSidePanel(sidePanel);
	}
	
	protected abstract void executeOperation(File nextEntry) throws Exception;
	
	public void execute(){
		try {
			for(File nextEntry : files){
				executeOperation(nextEntry);
			}
			otherSidePanel.refresh();
			sidePanel.refresh();
		} catch (Exception e){
			Logger.error(e, "{0} hat nicht funktioniert!", getFunctionCall());
			FunctionsHelper.setMessage(getMessage());
		}
	}
	
	protected abstract Message getMessage();
	
	protected abstract String getFunctionCall();
}
