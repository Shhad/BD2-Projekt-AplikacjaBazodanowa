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

import GUI.SeasonsListener.ExampleListCellRenderer;
import GUI.SeasonsListener.PlayerListListener;
import Model.Player;
import Model.Season;

public class SeasonsListener implements ActionListener {
	
	public void actionPerformed(ActionEvent zdarzenie) {

		MainFrame.dataP.removeAll();
		MainFrame.dataP.revalidate();
		MainFrame.dataP.repaint();
		MainFrame.dataP.setLayout(new BorderLayout());
		MainFrame.list = new JList(MainFrame.data.getSeasons().toArray(new Season[MainFrame.data.getSeasons().size()]));
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
		Season option;
		public void valueChanged(ListSelectionEvent event) {
			if(!event.getValueIsAdjusting()) {
				option =  (Season) MainFrame.list.getSelectedValue();
				System.out.println("Wybrano sezon: "+option.getName());
				MainFrame.area.setText("Nazwa: "+option.getName()+"\n"+"Data rozpoczecia: "+option.getOpenDate()+"\n"+"Data zakonczenia: "+option.getCloseDate()+"\nI miejsce: "+option.getWinner());

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

	       Season help = (Season) value;
	       label.setText(help.getName());

	       return label;

	    }
	}
}