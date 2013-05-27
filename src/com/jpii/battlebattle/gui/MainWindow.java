package com.jpii.battlebattle.gui;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import com.jpii.battlebattle.io.ClientUpdateService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -3989467303025155942L;
	private ClientUpdateService clientUpdateService;
	
	public MainWindow(final ClientUpdateService clientUpdateService) {
		this.clientUpdateService = clientUpdateService;
		
		setTitle("BattleBattle");
		getContentPane().setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setDividerLocation(150);
		splitPane.setBounds(0, 0, 444, 272);
		getContentPane().add(splitPane);
		
		JPanel detailsPanel = new JPanel();
		splitPane.setRightComponent(detailsPanel);
		detailsPanel.setLayout(new MigLayout("", "[46px,grow][189px][89px]", "[14px][14px][72.00px,fill][grow][fill][fill][53.00,grow][22.00px,grow]"));
		
		JLabel lblGameTitle = new JLabel("Game Title");
		lblGameTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		detailsPanel.add(lblGameTitle, "cell 0 0 3 1,growx,aligny top");
		
		JLabel lblVersion = new JLabel("version");
		detailsPanel.add(lblVersion, "cell 0 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		detailsPanel.add(scrollPane, "cell 0 2 3 5,grow");
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
		JButton btnPlay = new JButton("Play");
		detailsPanel.add(btnPlay, "cell 0 7 3 1,growx,aligny top");
		
		JPanel gamesPanel = new JPanel();
		splitPane.setLeftComponent(gamesPanel);
		gamesPanel.setLayout(new MigLayout("", "[121px,grow]", "[14px][grow]"));
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"NavalBattle"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		gamesPanel.add(list, "cell 0 0 1 2,grow");
		
		JLabel alertTitle = new JLabel("Alert");
		alertTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		alertTitle.setBounds(10, 289, 325, 14);
		getContentPane().add(alertTitle);
		
		JButton btnMoreInfo = new JButton("More info");
		btnMoreInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientUpdateService.hasAnnouncement() && clientUpdateService.needsUpdate()) {
					String[] options = new String[] {"Announcement", "Update"};
				    int choice = JOptionPane.showOptionDialog(null, "Which alert do you wish to view?", "BattleBattle", 
				        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				        null, options, options[0]);
				    
				    if(choice == 0)
				    	new BroadcastWindow(clientUpdateService.getAnnouncementTitle(), clientUpdateService.getAnnouncementText(), clientUpdateService.getAnnouncementUrl());
				    if(choice == 1)
				    	new UpdateWindow(clientUpdateService.getVersionReadable(), clientUpdateService.getUpdateText(), clientUpdateService.getUpdateUrl());
				} else if(clientUpdateService.hasAnnouncement()) {
					new BroadcastWindow(clientUpdateService.getAnnouncementTitle(), clientUpdateService.getAnnouncementText(), clientUpdateService.getAnnouncementUrl());
				} else if(clientUpdateService.needsUpdate()) {
					new UpdateWindow(clientUpdateService.getVersionReadable(), clientUpdateService.getUpdateText(), clientUpdateService.getUpdateUrl());
				}
			}
		});
		btnMoreInfo.setBounds(345, 283, 89, 27);
		getContentPane().add(btnMoreInfo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 270, 444, 2);
		getContentPane().add(separator);

		if(clientUpdateService.hasAnnouncement() || clientUpdateService.needsUpdate()) {
			setSize(450, 350);
			if(clientUpdateService.hasAnnouncement() && clientUpdateService.needsUpdate()) {
				alertTitle.setText("Update and Announcement");
			} else if(clientUpdateService.hasAnnouncement()) {
				alertTitle.setText("Announcement: " + clientUpdateService.getAnnouncementTitle());
			} else if(clientUpdateService.needsUpdate()) {
				alertTitle.setText("Update: BattleBattle " + clientUpdateService.getVersionReadable());
			}
		} else {
			setSize(450, 300);
		}
		
		setResizable(false);
		setVisible(true);	
	}
	
	public ClientUpdateService getClientUpdateService() {
		return clientUpdateService;
	}
}