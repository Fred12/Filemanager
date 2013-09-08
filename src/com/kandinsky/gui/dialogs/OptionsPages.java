package com.kandinsky.gui.dialogs;

public class OptionsPages extends PageCategory {

	@Override
	public void changePage(int _topicIndex) {

		page.removeAll();
		page.setLayout(null);
				
		switch(_topicIndex) {
		
			case 0:
				OptionsShortcuts optionsShortcuts = new OptionsShortcuts();
				optionsShortcuts.buildPage(this.page);
				break;
			case 1:
				
				
				OptionsFTP optionsFTP = new OptionsFTP();
				optionsFTP.buildPage(this.page);
				break;
				
			default:
				this.changePage(0);
				break;
		
		}
		
		
		//page.setLayout(null);
		//page.setBounds(148, 11, 500, 400);
	}

	@Override
	public String[] getTopics() {

		String[] ret = {"Shortcuts","FTP"};
		
		return ret;
	}

}
