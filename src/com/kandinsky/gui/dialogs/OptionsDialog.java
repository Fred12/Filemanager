package com.kandinsky.gui.dialogs;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Class for the options dialog system to provide different option page depending on 
 * a selection from a list. Functionality in options pages for i.e. favorites editing
 * are provided by JPanel subclasses which contain the page contents and that
 * functionality for buttons, lists, checkboxes, etc.
 * 
 * @see PageCategory
 * 
 * @author Stefan
 *
 */
public class OptionsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8036270720088615486L;

	@SuppressWarnings("rawtypes")
	JList topics = null;
	
	PageCategory pageCategory = null;

	
	public OptionsDialog(PageCategory _pages) {
		
		this(_pages, 0);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OptionsDialog(PageCategory _pages, int _topicIndex) {
		
		setTitle("Optionen");
		
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		if(_pages == null){
			_pages = new OptionsPages();
		}
		pageCategory = _pages;
		
		topics = new JList();
		topics.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		topics.setBounds(10, 11, 128, 400);
		topics.setListData(_pages.getTopics());
		topics.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		createListener();
		getContentPane().add(topics);
			
		JPanel content = pageCategory.getPage();
		pageCategory.changePage(_topicIndex);
		getContentPane().add(content);

		this.setMinimumSize(new Dimension(675, 460));
		
	}
	
	private void createListener(){
		
		topics.addListSelectionListener(new ListSelectionListener() {
			
			
			@Override
			public void valueChanged(ListSelectionEvent e) {

				int index = OptionsDialog.this.topics.getSelectedIndex();
								
				// Erstelle die Inhalte der neuen Seite im Panel
				OptionsDialog.this.pageCategory.changePage(index);
				OptionsDialog.this.validate();
				OptionsDialog.this.repaint();
				
			}
		});
		
	}


}
