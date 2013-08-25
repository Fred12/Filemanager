package com.kandinsky.gui.dialogs;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Functionality defining class for the options dialog system. Helps in defining a structure
 * of just one dialog with a list of topics and a panel to display contents to each topic.
 * 
 * As help and option both apply here, we define this master class to define a function to
 * give the list of topics and a function to be called from the dialog when selecting topics.
 * 
 * Subclasses of this then must also implement this changePage function next to the topic list thing
 * and supply the contents according to the list index selected by the user. This is done as the
 * JPanel in the dialog is generated and referenced here. This is then filled and changed in "changePage"
 * 
 * This is the basic thing with big defference: Help just puts a label with the text from the topic,
 * but for options we supply a whole set of checkbox and other elements and functionality to store them.
 * For this, the JPanel will be set to instances of JPanel subclasses (allows for listeners and easily 
 * bulding the elements).
 * 
 * @author Stefan
 *
 */
public abstract class PageCategory {
	
	protected JPanel page = null;
	
	public PageCategory(){
		
		
		
	}
	
	/**
	 * Gibt ein JPanel mit dem Inhalt der Seite zurück
	 * 
	 * @param _topic
	 * @return
	 */
	public JPanel getPage(){
		
		page = new JPanel();
		page.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		page.setBounds(148, 11, 500, 400);
		return page;
	}
	
	public abstract void changePage(int _topicIndex);
	
	
	/**
	 * Liefert eine Liste der vorhandenen Seiten  zurück
	 * 
	 * @return
	 */
	public abstract String[] getTopics();

}
