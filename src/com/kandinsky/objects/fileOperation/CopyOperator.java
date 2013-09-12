package com.kandinsky.objects.fileOperation;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.pmw.tinylog.Logger;

import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.Message;

/**
 * Zum kopieren von Dateien von einer Seite auf die andere
 * @author Benne
 */
public class CopyOperator extends FileOperator {

	public CopyOperator(File[] files, SidePanel sidePanel) {
		super(files, sidePanel);
	}

	@Override
	protected void executeOperation(File nextEntry) throws Exception {
		String currentOtherSideFolder =otherSidePanel.getCurrentFolderName();
		currentOtherSideFolder+=currentOtherSideFolder.endsWith("/")?"":"/";
		Logger.info("Kopiere von {0} nach {1}!", nextEntry.getAbsolutePath(), currentOtherSideFolder + nextEntry.getName());
		if(nextEntry.isDirectory())
			FileUtils.copyDirectoryToDirectory(nextEntry.getAbsoluteFile(), new File(currentOtherSideFolder));
		else
			FileUtils.copyFileToDirectory(nextEntry.getAbsoluteFile(), new File(currentOtherSideFolder));
	}

	@Override
	protected String getFunctionCall() {
		return "COPY";
	}

	@Override
	protected Message getMessage() {
		return Message.COPY_COMPLETE;
	}

	@Override
	protected Message getErrorMessage() {
		return Message.COPY_FAILED;
	}

}
