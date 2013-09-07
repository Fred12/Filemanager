package com.kandinsky.objects.fileOperation;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.Message;

/**
 * Zum Verschieben von Dateien von einer auf die andere Seite
 * @author Benne
 */
public class MoveOperator extends FileOperator {

	public MoveOperator(File[] files, SidePanel sidePanel) {
		super(files, sidePanel);
	}

	@Override
	protected void executeOperation(File nextEntry) throws Exception {
		Logger.info("Verschiebe von {0} nach {1}!", nextEntry.getAbsolutePath(), otherSidePanel.getCurrentFolderName() + nextEntry.getName());
		Files.move(Paths.get(nextEntry.getAbsolutePath()), Paths.get(otherSidePanel.getCurrentFolderName() + nextEntry.getName()), REPLACE_EXISTING);
	}

	@Override
	protected Message getMessage() {
		return Message.MOVE_COMPLETE;
	}

	@Override
	protected String getFunctionCall() {
		return "MOVE";
	}

	@Override
	protected Message getErrorMessage() {
		return Message.MOVE_FAILED;
	}

}
