package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GUI.CountriesListener.ExampleListCellRenderer;
import GUI.CountriesListener.PlayerListListener;
import Model.Country;

public class CountriesListener implements ActionListener {
	
	public void actionPerformed(ActionEvent zdarzenie) {
		
		MainFrame.dataP.removeAll();
		MainFrame.dataP.revalidate();
		MainFrame.dataP.repaint();
		MainFrame.dataP.setLayout(new BorderLayout());
		MainFrame.list = new JList(MainFrame.data.getCountries().toArray(new Country[MainFrame.data.getCountries().size()]));
		MainFrame.list.setVisibleRowCount(12);
		MainFrame.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MainFrame.list.addListSelectionListener( new PlayerListListener());
		MainFrame.list.setCellRenderer(new ExampleListCellRenderer());
		MainFrame.area = new JTextArea(5,34);
		MainFrame.area.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.BLACK));
		
		JScrollPane przewi = new JScrollPane(MainFrame.list);
		przewi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		przewi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		przewi.setPreferredSize(new Dimension(200,270));
		
		MainFrame.dataP.add(BorderLayout.WEST, przewi);
		MainFrame.dataP.add(BorderLayout.EAST, MainFrame.area);
		MainFrame.dataP.validate();
		MainFrame.dataP.repaint();
		
	}
	class PlayerListListener implements ListSelectionListener {
		Country option;
		public void valueChanged(ListSelectionEvent event) {
			if(!event.getValueIsAdjusting()) {
				option =  (Country) MainFrame.list.getSelectedValue();
				System.out.println("Wybrano kraj: "+option.getName());
				MainFrame.area.setText("Kraj: "+option.getName()+"\n");

			}
		}
	}
	//Klasa wewnetrzna obslugujaca pokazanie elementow na liscie
	public class ExampleListCellRenderer extends DefaultListCellRenderer
	{
	    public Component getListCellRendererComponent(
	        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	    {
	       JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	       Country name = (Country) value;
	       label.setText(name.getName());

	       return label;

	    }
	}
}
