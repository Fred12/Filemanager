package com.kandinsky.gui.dialogs;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Sub class for the Options Dialog system to provide a list of help topics and
 * supply the text contents for each topic to the content panel.
 * 
 * Help Pages are currently stored as constants. Maybe load them from files.
 * 
 * @see PageCategory
 * 
 * @author Stefan
 *
 */
public class HelpPages extends PageCategory {

	
	
	public static final String HELP_MAIN_KEY = "Allgemein";
	public static final String HELP_MAIN = "<html><h1> Help Content Topic 1 </h1><br><br>" +
										  
											"Content of Help Topic 1 </html>";
	
	public static final String HELP_FAV_KEY = "Kopieren";
	public static final String HELP_FAV = "Help Content Topic 2 \n\n" +
			  
											"Content of Help Topic 2";
	
	public static final String[] 	TOPICS			= {HELP_MAIN_KEY, HELP_FAV_KEY};
	public static final String[] 	TOPICS_ASSOC 	= {HELP_MAIN, HELP_FAV};
	
	
	@Override
	public void changePage(int _topicIndex) {
		
		// Seite leeren
		page.removeAll();
		page.setLayout(null);
				
		String text = TOPICS_ASSOC[_topicIndex];
		
		JLabel content = new JLabel(text);
		content.setVerticalAlignment(SwingConstants.TOP);
		

		content.setBounds(10,0, 490, 400);
		
		
		page.add(content);
				
	}
	
	public String[] getTopics(){
		
		return TOPICS;
		
	}

}
