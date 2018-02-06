package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Player;
import Model.PlayerInSeason;
import Model.Season;

public class SeasonPlayerListener implements ActionListener { 
	static JList<PlayerInSeason> listPlayer;
	static JScrollPane przewiS;
	static JScrollPane przewiP;
	static ArrayList<PlayerInSeason> playersS;
	public void actionPerformed(ActionEvent zdarzenie) {
		MainFrame.dataP.removeAll();
		MainFrame.dataP.revalidate();
		MainFrame.dataP.repaint();
		MainFrame.dataP.setLayout(new FlowLayout());
		((FlowLayout)MainFrame.dataP.getLayout()).setVgap(0);
		
		MainFrame.list = new JList(MainFrame.data.getSeasons().toArray(new Season[MainFrame.data.getSeasons().size()]));
		MainFrame.list.setVisibleRowCount(12);
		
		MainFrame.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		MainFrame.list.addListSelectionListener( new SeasonListListener());
		
		MainFrame.list.setCellRenderer(new SeasonListCellRenderer());
		
		MainFrame.area = new JTextArea();
		MainFrame.area.setBorder(BorderFactory.createMatteBorder(0,3,0,3, Color.black));
		//------------------------
		przewiS = new JScrollPane(MainFrame.list);
		przewiS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		przewiS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		przewiS.setBorder(BorderFactory.createMatteBorder(0,3,0,3, Color.black));
		przewiS.setPreferredSize(new Dimension(280,392));
		
		MainFrame.dataP.add(przewiS);
		MainFrame.dataP.validate();
		MainFrame.dataP.repaint();
	}
	class PlayerListListener implements ListSelectionListener {
		PlayerInSeason option;
		Player player;
		public void valueChanged(ListSelectionEvent event) {
			if(!event.getValueIsAdjusting()) {
				option =  (PlayerInSeason) SeasonPlayerListener.listPlayer.getSelectedValue();
				for(int i = 0;i<MainFrame.data.getPlayers().size();i++) {
					if(MainFrame.data.getPlayers().get(i).getId() == option.getPlayerID()) {
						player = MainFrame.data.getPlayers().get(i);
					}
				}
				System.out.println("Wybrano pilkarza: "+player.getName()+" "+player.getSurname());
				MainFrame.dataP.removeAll();
				MainFrame.dataP.add(BorderLayout.CENTER, MainFrame.area);
				MainFrame.area.setText("Pilkarz: "+player.getName() + " "+player.getSurname()+"\n"+"Kartki: "+option.getCards()+"\n"+"Strzelone bramki: "+option.getGoals()+"\n"+"Asysty: "+option.getAssists());
				MainFrame.area.setPreferredSize(new Dimension(380,392));
				SeasonPlayerListener.przewiS.setPreferredSize(new Dimension(180,392));
				MainFrame.dataP.add(SeasonPlayerListener.przewiS);
				MainFrame.dataP.add(MainFrame.area);
				MainFrame.dataP.revalidate();
				MainFrame.dataP.repaint();
				//SeasonPlayerListener.listPlayer.clearSelection();
				player = null;
				playersS = null;
			}
		}
	}
	class SeasonListListener implements ListSelectionListener {
		Season option;
		public void valueChanged(ListSelectionEvent event) {
			if(!event.getValueIsAdjusting()) {
				option =  (Season) MainFrame.list.getSelectedValue();
				System.out.println("Wybrano sezon: "+option.getName());
				MainFrame.dataP.removeAll();
				SeasonPlayerListener.playersS = new ArrayList<PlayerInSeason>();
				PlayerInSeason player;
				for(int i = 0;i<MainFrame.data.getPlayersInSeason().size();i++) {
					if(MainFrame.data.getPlayersInSeason().get(i).getSeasonID() == option.getId()) {
						playersS.add(MainFrame.data.getPlayersInSeason().get(i));
					}
				}
				listPlayer = new JList(playersS.toArray(new PlayerInSeason[playersS.size()]));
				listPlayer.setVisibleRowCount(12);
				listPlayer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listPlayer.addListSelectionListener( new PlayerListListener());
				listPlayer.setCellRenderer(new PlayerListCellRenderer());
				przewiP = new JScrollPane(listPlayer);
				przewiP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				przewiP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				przewiP.setBorder(BorderFactory.createMatteBorder(0,3,0,3, Color.black));
				przewiP.setPreferredSize(new Dimension(280,392));
				MainFrame.dataP.add(SeasonPlayerListener.przewiS);
				MainFrame.dataP.add(SeasonPlayerListener.przewiP);
				MainFrame.dataP.revalidate();
				MainFrame.dataP.repaint();
				//MainFrame.list.clearSelection();
			}
		}
	}
	//Klasa wewnetrzna obslugujaca pokazanie elementow na liscie
	@SuppressWarnings("serial")
	public class SeasonListCellRenderer extends DefaultListCellRenderer
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
	public class PlayerListCellRenderer extends DefaultListCellRenderer
	{
	    public Component getListCellRendererComponent(
	        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	    {
	       JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	       PlayerInSeason help = (PlayerInSeason) value;
	       Player player = null;
	       for(int i = 0;i<MainFrame.data.getPlayers().size();i++) {
				if(MainFrame.data.getPlayers().get(i).getId() == help.getPlayerID()) {
					player = MainFrame.data.getPlayers().get(i);
				}
			}
	       label.setText(player.getName() + " " + player.getSurname());

	       return label;

	    }
	}
}