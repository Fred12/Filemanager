package com.kandinsky.objects.fileOperation;

import java.io.File;

import org.pmw.tinylog.Logger;

import com.kandinsky.gui.splitPane.SidePanel;
import com.kandinsky.objects.FunctionsHelper;
import com.kandinsky.objects.Message;

public abstract class FileOperator {

	protected File[] files;
	/** ausfuehrende Seite */
	protected SidePanel sidePanel;
	/** andere Seite */
	protected SidePanel otherSidePanel;

	/**
	 * @param files Dateien, auf die die Funktion ausgefuehrt werden soll
	 * @param sidePanel Seite
	 */
	public FileOperator(File[] files, SidePanel sidePanel) {
		this.files = files;
		this.sidePanel = sidePanel;
		otherSidePanel = FunctionsHelper.getOtherSidePanel(sidePanel);
	}

	/**
	 * Datei-Operation
	 * @param file
	 * @throws Exception
	 */
	protected abstract void executeOperation(File file) throws Exception;

	/**
	 * Durchlaeuft alle angegebenen Dateien und fuehrt die passende Operation durch
	 */
	public void execute() {
		try {
			for (File nextEntry : files) {
				executeOperation(nextEntry);
			}
			otherSidePanel.refresh();
			sidePanel.refresh();
			Logger.info("{0} wurde fuer die gewaehlten Elemente durchgefuehrt!", getFunctionCall());
			FunctionsHelper.setMessage(getMessage());
		} catch (Exception e) {
			Logger.error(e, "{0} hat nicht funktioniert!", getFunctionCall());
			FunctionsHelper.setMessage(getErrorMessage());
		}
	}

	/**
	 * @return Fehlernachricht
	 */
	protected abstract Message getErrorMessage();

	/**
	 * @return erfolgreiche Nachricht
	 */
	protected abstract Message getMessage();

	/**
	 * @return Funktionsname, der ausgefuehrt wird
	 */
	protected abstract String getFunctionCall();
}
