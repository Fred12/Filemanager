package com.kandinsky.objects.fileOperation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.pmw.tinylog.Logger;

import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.Message;

/**
 * Zum Loeschen von Dateien und Ordnern (rekursiv)
 * @author Benne
 */
public class DeleteOperator extends FileOperator {

	public DeleteOperator(File[] files, SidePanel sidePanel) {
		super(files, sidePanel);
	}

	@Override
	protected void executeOperation(File nextEntry) throws Exception {
		if (nextEntry.isDirectory()) {
			Logger.info("Loesche Ordner rekursiv {0}!", nextEntry.getAbsolutePath());
			FileUtils.deleteDirectory(nextEntry);
		} else {
			Logger.info("Loesche Datei {0}!", nextEntry.getAbsolutePath());
			Files.delete(Paths.get(nextEntry.getAbsolutePath()));
		}
	}

	@Override
	protected Message getMessage() {
		return Message.DELETE_COMPLETE;
	}

	@Override
	protected String getFunctionCall() {
		return "DELETE";
	}

	@Override
	protected Message getErrorMessage() {
		return Message.DELETE_FAILED;
	}

}
