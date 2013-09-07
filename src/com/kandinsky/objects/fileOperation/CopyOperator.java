package com.kandinsky.objects.fileOperation;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		Logger.info("Kopiere von {0} nach {1}!", nextEntry.getAbsolutePath(), otherSidePanel.getCurrentFolderName() + nextEntry.getName());
		Files.copy(Paths.get(nextEntry.getAbsolutePath()), Paths.get(otherSidePanel.getCurrentFolderName() + nextEntry.getName()), REPLACE_EXISTING);
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
