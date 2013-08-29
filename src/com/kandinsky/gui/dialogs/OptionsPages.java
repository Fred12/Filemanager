package com.kandinsky.gui.dialogs;

import javax.swing.JLabel;


public class OptionsPages extends PageCategory {

	@Override
	public void changePage(int _topicIndex) {

		page.removeAll();
		page.add(new JLabel(Integer.toString(_topicIndex)));
		
	}

	@Override
	public String[] getTopics() {

		String[] ret = {"Allgemein","FTP"};
		
		return ret;
	}

}
