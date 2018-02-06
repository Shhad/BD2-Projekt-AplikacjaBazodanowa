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

import GUI.TeamsListener.ExampleListCellRenderer;
import GUI.TeamsListener.PlayerListListener;
import Model.Player;
import Model.Team;

public class TeamsListener implements ActionListener {
	
	public void actionPerformed(ActionEvent zdarzenie) {

		MainFrame.dataP.removeAll();
		MainFrame.dataP.revalidate();
		MainFrame.dataP.repaint();
		MainFrame.dataP.setLayout(new BorderLayout());
		MainFrame.list = new JList(MainFrame.data.getTeams().toArray(new Team[MainFrame.data.getTeams().size()]));
		MainFrame.list.setVisibleRowCount(12);
		MainFrame.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MainFrame.list.addListSelectionListener( new PlayerListListener());
		MainFrame.list.setCellRenderer(new ExampleListCellRenderer());
		MainFrame.area = new JTextArea(5,34);
		MainFrame.area.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 3, Color.BLACK));

		JScrollPane przewi = new JScrollPane(MainFrame.list);
		przewi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		przewi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		przewi.setPreferredSize(new Dimension(200,300));
		
		MainFrame.dataP.add(BorderLayout.WEST, przewi);
		MainFrame.dataP.add(BorderLayout.EAST, MainFrame.area);
		MainFrame.dataP.validate();
		MainFrame.dataP.repaint();

	}
	class PlayerListListener implements ListSelectionListener {
		Team option;
		public void valueChanged(ListSelectionEvent event) {
			if(!event.getValueIsAdjusting()) {
				option = (Team) MainFrame.list.getSelectedValue();
				System.out.println("Wybrano druzyne: "+option.getName());
				MainFrame.area.setText("Nazwa: "+option.getName()+"\n"+"Kraj: "+option.getCountry()+"\n"+"Liga: "+option.getLeague()+"\n"+"Ranking FIFA: "+option.getRank());

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

	       Team help = (Team) value;
	       label.setText(help.getName());

	       return label;

	    }
	}
}